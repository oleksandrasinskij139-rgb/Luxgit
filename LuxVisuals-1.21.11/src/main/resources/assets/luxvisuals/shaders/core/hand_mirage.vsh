#version 150

in vec3 Position;
in vec4 Color;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out vec2 TexCoord;
out vec4 FragColor;

void main() {
    gl_Position = vec4(Position, 1.0);
    
    // UV координаты из NDC позиции (-1..1) -> (0..1)
    TexCoord = Position.xy * 0.5 + 0.5;
    FragColor = Color;
}
