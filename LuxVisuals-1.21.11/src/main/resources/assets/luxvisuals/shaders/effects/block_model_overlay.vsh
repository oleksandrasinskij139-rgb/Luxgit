#version 330
layout(std140) uniform OverlayData {
    mat4 modelView;
    mat4 projection;
    vec4 tint;
    vec4 effect;
    vec4 style;
    vec4 boxMin;
    vec4 boxMax;
};
in vec3 Position;
in vec2 UV0;
in vec4 Color;
out vec2 texCoord;
out vec2 quadUV;
out vec3 localPosition;
void main() {
    texCoord = UV0;
    quadUV = Color.rg;                      // координаты внутри грани модели (0..1)
    localPosition = Position;  // позиция внутри блока — для непрерывной плазмы
    gl_Position = projection * modelView * vec4(Position + boxMin.xyz, 1.0);
}
