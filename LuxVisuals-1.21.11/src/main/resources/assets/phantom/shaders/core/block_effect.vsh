#version 150

in vec3 Position; // POSITION_COLOR
in vec4 Color;

uniform mat4 ProjMat;
uniform mat4 ModelViewMat;

out vec2 texCoord;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    texCoord = gl_Position.xy * 0.5 + 0.5;
}

