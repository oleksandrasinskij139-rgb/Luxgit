#version 150

uniform sampler2D Sampler0; // with hand
uniform sampler2D Sampler1; // before hand
uniform vec4 uTint;         // r, g, b, a (fillAlpha)
uniform float uShading;     // shading strength

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

// Простое затенение на основе градиента
float getShading(vec2 uv) {
    // Градиент от верха к низу
    float vertical = uv.y;
    
    // Радиальное затенение от центра
    vec2 centered = uv - 0.5;
    float radial = length(centered) * 2.0;
    
    return mix(vertical, radial, 0.5);
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
    
    vec4 handColor = texture(Sampler0, uv);
    
    // Применяем затенение
    float shading = getShading(uv);
    vec3 shadedColor = uTint.rgb * (1.0 - shading * uShading * 0.5);
    
    // Смешиваем заливку с оригинальной рукой
    vec3 finalColor = mix(handColor.rgb, shadedColor, handMask * uTint.a);
    
    OutColor = vec4(finalColor, 1.0);
}
