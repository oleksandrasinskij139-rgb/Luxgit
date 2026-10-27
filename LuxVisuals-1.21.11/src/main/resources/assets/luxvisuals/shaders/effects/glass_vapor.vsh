#version 330
layout(std140) uniform VaporData {
    mat4 projection;
    vec4 screenRefraction;
    vec4 appearance;
    vec4 tintColor;
    vec4 bounds;
    vec4 controls;
    vec4 spheres[128];
    vec4 extras[128];
};
const vec2 corners[6] = vec2[6](vec2(0,0), vec2(1,0), vec2(1,1),
                              vec2(0,0), vec2(1,1), vec2(0,1));
void main() {
    gl_Position = vec4(mix(bounds.xy, bounds.zw, corners[gl_VertexID]), 0.0, 1.0);
}
