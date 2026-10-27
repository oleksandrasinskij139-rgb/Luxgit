#version 150

#moj_import <luxvisuals:common.glsl>

in vec2 FragCoord;
in vec4 FragColor;

uniform vec2 uSize;
uniform vec4 uRadius;
uniform float uSmoothness;
uniform float uTime;
uniform vec4 uColorModulator;

out vec4 fragColor;

void main() {
    // Базовая альфа с закруглениями
    float alpha = ralpha(uSize, FragCoord, uRadius, uSmoothness);
    
    if (alpha < 0.01) {
        discard;
    }
    
    // Очень мягкая пульсация (от 0.92 до 1.0) - едва заметная
    float pulse = 0.92 + 0.08 * (0.5 + 0.5 * sin(uTime * 0.8));
    
    // Фиолетовый цвет RGB(128, 64, 192) = #8040C0
    vec3 purpleColor = vec3(0.5, 0.25, 0.75);
    
    // СИЛЬНАЯ прозрачность (0.15 = 15% непрозрачности) - почти прозрачная
    float baseAlpha = 0.15;
    
    // Итоговый цвет с пульсацией и прозрачностью
    vec4 color = vec4(purpleColor, baseAlpha * alpha * pulse);

    fragColor = color * uColorModulator;
}
