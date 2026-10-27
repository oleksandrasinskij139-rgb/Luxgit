#version 150

uniform sampler2D Sampler0;  // beforeHandFbo (фон без рук)
uniform sampler2D Sampler1;  // withHandFbo (фон с руками)
uniform vec4 uScreen;
uniform vec4 uParams;   // x=blur, y=distortion, z=edge, w=radius
uniform vec4 uTint;
uniform vec4 uState;    // x=alpha, y=waveSpeed, z=time, w=waveAmp
uniform float uQuality;

in vec2 TexCoord;
in vec4 FragColor;
out vec4 OutColor;

void main() {
    vec2 screenUv = TexCoord;
    
    // Получаем цвета из обоих фреймбуферов
    vec4 beforeColor = texture(Sampler0, screenUv);
    vec4 withColor = texture(Sampler1, screenUv);
    
    // Вычисляем маску рук (разница между фреймбуферами)
    float handMask = length(withColor.rgb - beforeColor.rgb);
    handMask = smoothstep(0.01, 0.1, handMask);
    
    // Если нет руки, просто выводим оригинальный цвет
    if (handMask < 0.01) {
        OutColor = withColor;
        return;
    }
    
    // Применяем glass эффект только к области рук
    vec2 uv = screenUv;
    vec2 centered = uv - 0.5;
    
    float waveSpeed = uState.y;
    float waveAmp = uState.w;
    float waveA = sin((uv.y * 15.0 + uv.x * 5.0) + uState.z * 0.85 * waveSpeed);
    float waveB = cos((uv.x * 18.0 - uv.y * 4.0) - uState.z * 0.7 * waveSpeed);
    
    float edgeStrength = uParams.z;
    vec2 bend = normalize(centered + vec2(0.0001)) * (0.35 + handMask * 1.65 * edgeStrength);
    bend += vec2(waveA, waveB) * 0.28 * waveAmp;
    
    float distortionPx = uParams.y * (0.6 + handMask * 1.8);
    vec2 distortion = bend * distortionPx / uScreen.xy;
    distortion.y = -distortion.y;
    
    vec2 sampleUv = screenUv + distortion;
    
    float blurPx = max(0.0, uParams.x);
    vec2 ts = vec2(1.0) / uScreen.xy;
    
    vec3 color = texture(Sampler0, sampleUv).rgb;
    int quality = int(uQuality);
    
    if (quality == 2) {
        // High – 9 сэмплов
        color += texture(Sampler0, sampleUv + ts * vec2( blurPx, 0.0)).rgb;
        color += texture(Sampler0, sampleUv + ts * vec2(-blurPx, 0.0)).rgb;
        color += texture(Sampler0, sampleUv + ts * vec2(0.0,  blurPx)).rgb;
        color += texture(Sampler0, sampleUv + ts * vec2(0.0, -blurPx)).rgb;
        color += texture(Sampler0, sampleUv + ts * vec2( blurPx,  blurPx) * 0.72).rgb * 0.7;
        color += texture(Sampler0, sampleUv + ts * vec2(-blurPx,  blurPx) * 0.72).rgb * 0.7;
        color += texture(Sampler0, sampleUv + ts * vec2( blurPx, -blurPx) * 0.72).rgb * 0.7;
        color += texture(Sampler0, sampleUv + ts * vec2(-blurPx, -blurPx) * 0.72).rgb * 0.7;
        color /= 1.0 + 8.0 * 0.7;
    } else if (quality == 1) {
        // Medium – 5 сэмплов
        color += texture(Sampler0, sampleUv + ts * vec2( blurPx, 0.0)).rgb;
        color += texture(Sampler0, sampleUv + ts * vec2(-blurPx, 0.0)).rgb;
        color += texture(Sampler0, sampleUv + ts * vec2(0.0,  blurPx)).rgb;
        color += texture(Sampler0, sampleUv + ts * vec2(0.0, -blurPx)).rgb;
        color /= 5.0;
    }
    
    // Применяем tint
    vec3 tint = uTint.rgb;
    color = mix(color, tint, clamp(uTint.a, 0.0, 1.0));
    
    // Смешиваем glass эффект с оригинальной рукой
    vec3 finalColor = mix(withColor.rgb, color, handMask * uState.x);
    OutColor = vec4(finalColor, 1.0);
}
