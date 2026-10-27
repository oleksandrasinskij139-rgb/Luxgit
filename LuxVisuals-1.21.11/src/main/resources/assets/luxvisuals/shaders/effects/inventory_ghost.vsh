#version 330
layout(std140) uniform GhostData { vec4 screen; vec4 slots[41]; };
const vec2 corners[6] = vec2[6](vec2(0,0),vec2(1,0),vec2(1,1),vec2(0,0),vec2(1,1),vec2(0,1));
out vec2 texCoord;
void main() {
    vec4 slot = slots[gl_VertexID / 6];
    vec2 corner = corners[gl_VertexID % 6];
    int index = int(slot.z);
    vec2 source = vec2(index % 8, index / 8) * 18.0 + corner * 16.0;
    texCoord = vec2(source.x / 144.0, 1.0 - source.y / 108.0);
    vec2 point = slot.xy + corner * (slot.w > 0.0 ? slot.w : 16.0);
    gl_Position = vec4(point.x / screen.x * 2.0 - 1.0, 1.0 - point.y / screen.y * 2.0, 0.0, 1.0);
}
