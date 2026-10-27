#version 150

in vec4 vertexColor;
in vec2 texCoord0;

uniform sampler2D Sampler0;
uniform vec4 ColorModulator;
uniform float Time;

out vec4 fragColor;

void main() {
    vec4 texColor = texture(Sampler0, texCoord0);
    
    if (texColor.a < 0.01) {
        discard;
    }
    
    // Очень мягкая пульсация для glow (от 0.9 до 1.0)
    float pulse = 0.9 + 0.1 * (0.5 + 0.5 * sin(Time * 1.2));
    
    // Усиливаем яркость для эффекта свечения
    vec3 glowColor = texColor.rgb * 1.3 * pulse;
    
    // Сохраняем альфу иконки
    fragColor = vec4(glowColor, texColor.a) * vertexColor * ColorModulator;
}
