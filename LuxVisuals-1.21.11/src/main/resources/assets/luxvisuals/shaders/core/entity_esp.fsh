#version 150

uniform vec4 ColorModulator;

in vec4 vertexColor;
out vec4 fragColor;

void main() {
    // Максимально простой и оптимизированный шейдер для ESP
    fragColor = vertexColor * ColorModulator;
}
