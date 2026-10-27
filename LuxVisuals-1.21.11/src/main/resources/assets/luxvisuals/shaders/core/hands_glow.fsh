#version 150

uniform sampler2D Sampler0; // with hand
uniform sampler2D Sampler1; // before hand
uniform vec4 uColor1;       // primary glow color
uniform vec4 uColor2;       // secondary glow color
uniform float uExposure;    // glow exposure/brightness

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

// Градиент для смешивания двух цветов
vec3 getGlowGradient(vec2 uv) {
    // Вертикальный градиент
    float t = smoothstep(0.0, 1.0, uv.y);
    return mix(uColor1.rgb, uColor2.rgb, t);
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
    
    // Получаем цвет свечения
    vec3 glowColor = getGlowGradient(uv);
    
    // Применяем экспозицию
    glowColor *= uExposure;
    
    // Смешиваем свечение с оригинальной рукой аддитивно
    vec3 finalColor = handColor.rgb + glowColor * handMask;
    
    OutColor = vec4(finalColor, 1.0);
}
