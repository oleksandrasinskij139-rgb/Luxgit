#version 150

uniform sampler2D Sampler0; // with hand
uniform sampler2D Sampler1; // before hand
uniform vec4 uParams;    // blur, distortion, edge, radius
uniform vec4 uTint;      // r, g, b, a
uniform vec4 uState;     // alpha, waveSpeed, time, waveAmp
uniform float uQuality;

in vec2 TexCoord;
in vec4 FragColor;
out vec4 OutColor;

// Улучшенное определение маски руки
float getHandMask(vec2 uv) {
    vec4 withHand = texture(Sampler0, uv);
    vec4 beforeHand = texture(Sampler1, uv);
    
    // Проверяем разницу по всем каналам включая альфу
    float colorDiff = length(withHand.rgb - beforeHand.rgb);
    float alphaDiff = abs(withHand.a - beforeHand.a);
    
    // Комбинируем обе проверки для более точной маски
    float diff = max(colorDiff, alphaDiff * 0.5);
    
    // Более агрессивный порог для лучшего покрытия
    return smoothstep(0.005, 0.08, diff);
}

// Волновая дисторсия
vec2 applyDistortion(vec2 uv, float mask) {
    float time = uState.z;
    float waveSpeed = uState.y;
    float waveAmp = uState.w;
    float distortion = uParams.y;
    
    // Более плавные волны
    vec2 wave = vec2(
        sin(uv.y * 12.0 + time * waveSpeed * 0.8) * waveAmp,
        cos(uv.x * 12.0 + time * waveSpeed * 0.8) * waveAmp
    ) * 0.012 * distortion * mask;
    
    return uv + wave;
}

// Улучшенный блюр с учетом маски
vec4 applyBlur(sampler2D tex, vec2 uv, float mask) {
    float blur = uParams.x;
    vec2 texelSize = 1.0 / vec2(textureSize(tex, 0));
    
    vec4 color = vec4(0.0);
    float total = 0.0;
    int quality = int(uQuality);
    int samples = quality == 0 ? 1 : quality == 1 ? 2 : 3;
    
    for (int x = -samples; x <= samples; x++) {
        for (int y = -samples; y <= samples; y++) {
            vec2 offset = vec2(float(x), float(y)) * texelSize * blur * mask;
            color += texture(tex, uv + offset);
            total += 1.0;
        }
    }
    
    return color / total;
}

// Эффект свечения на краях
float getEdgeGlow(vec2 uv, float mask) {
    float edge = uParams.z;
    vec2 texelSize = 1.0 / vec2(textureSize(Sampler0, 0));
    
    float maskCenter = getHandMask(uv);
    float maskUp = getHandMask(uv + vec2(0, texelSize.y * 2.0));
    float maskDown = getHandMask(uv - vec2(0, texelSize.y * 2.0));
    float maskLeft = getHandMask(uv + vec2(texelSize.x * 2.0, 0));
    float maskRight = getHandMask(uv - vec2(texelSize.x * 2.0, 0));
    
    float edgeDiff = abs(maskCenter - maskUp) + abs(maskCenter - maskDown) + 
                     abs(maskCenter - maskLeft) + abs(maskCenter - maskRight);
    
    return edgeDiff * edge * mask * 0.5;
}

void main() {
    vec2 uv = TexCoord;
    
    // Определяем маску руки
    float handMask = getHandMask(uv);
    
    // Если маска слишком слабая, просто выводим оригинал
    if (handMask < 0.001) {
        OutColor = texture(Sampler0, uv);
        return;
    }
    
    // Применяем дисторсию
    vec2 distortedUV = applyDistortion(uv, handMask);
    
    // Получаем цвет фона с блюром
    vec4 bgBlurred = applyBlur(Sampler1, distortedUV, handMask);
    vec4 handColor = texture(Sampler0, uv);
    
    // Применяем тинт к размытому фону
    bgBlurred.rgb = mix(bgBlurred.rgb, bgBlurred.rgb * uTint.rgb, uTint.a);
    
    // Добавляем свечение на краях
    float edgeGlow = getEdgeGlow(uv, handMask);
    bgBlurred.rgb += vec3(edgeGlow) * uTint.rgb * uState.x;
    
    // Смешиваем размытый фон с рукой
    // Используем handMask для плавного перехода
    float mixFactor = handMask * uState.x;
    OutColor = mix(handColor, bgBlurred, mixFactor);
    OutColor.a = 1.0;
}
