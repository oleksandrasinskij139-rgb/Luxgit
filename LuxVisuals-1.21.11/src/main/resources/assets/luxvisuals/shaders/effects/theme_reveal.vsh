#version 330
const vec2 corners[6] = vec2[6](vec2(-1,-1), vec2(1,-1), vec2(1,1),
                              vec2(-1,-1), vec2(1,1), vec2(-1,1));
void main() { gl_Position = vec4(corners[gl_VertexID], 0.0, 1.0); }
