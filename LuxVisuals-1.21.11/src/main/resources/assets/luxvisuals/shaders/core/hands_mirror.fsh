#version 150

uniform sampler2D Sampler0; // with hand
uniform sampler2D Sampler1; // before hand
uniform float uMixFactor;   // mix between normal and mirrored
uniform float uAlpha;       // overall alpha

in vec2 TexCoord;
in vec4 FragColor;
out vec4 OutColor;

// Определение маски руки
float getHandMask(vec2 uv) {
    vec4 withHand = texture(Sampler0, uv);
    vec4 beforeHand = texture(Sampler1, uv);
    
    float colorDiff = length(withHand.rgb - beforeHand.rgb);
    float alphaDiff = abs(withHand.a - beforeHand.a);
    
    float diff = max(colorDiff, alphaDiff * 0.5);
    return smoothstep(0.005, 0.08, diff);
}

void main() {
    vec2 uv = TexCoord;
    
    // Определяем маску руки
    float handMask = getHandMask(uv);
    
    vec4 handColor = texture(Sampler0, uv);
    
    // Если это не рука, просто выводим оригинал
    if (handMask < 0.001) {
        OutColor = handColor;
        return;
    }
    
    // Зеркальное отражение по горизонтали
    vec2 mirroredUV = vec2(1.0 - uv.x, uv.y);
    vec4 mirroredColor = texture(Sampler0, mirroredUV);
    
    // Смешиваем оригинал и зеркало
    vec3 mixedColor = mix(handColor.rgb, mirroredColor.rgb, uMixFactor);
    
    // Применяем альфу только к зеркальному эффекту
    vec3 finalColor = mix(handColor.rgb, mixedColor, uAlpha * handMask);
    
    OutColor = vec4(finalColor, 1.0);
}
