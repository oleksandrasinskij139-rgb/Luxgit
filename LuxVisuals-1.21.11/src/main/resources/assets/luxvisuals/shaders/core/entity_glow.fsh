#version 150

uniform vec4 ColorModulator;
uniform float Time; // Для анимации

in vec4 vertexColor;
out vec4 fragColor;

void main() {
    // Оптимизированный glow эффект
    vec4 baseColor = vertexColor * ColorModulator;
    
    // Простая пульсация яркости
    float pulse = 0.85 + 0.15 * sin(Time * 2.0);
    
    fragColor = vec4(baseColor.rgb * pulse, baseColor.a);
}
