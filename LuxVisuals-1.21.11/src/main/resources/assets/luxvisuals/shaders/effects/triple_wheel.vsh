#version 330 core
layout(std140) uniform WheelData {
    vec4 screen; vec4 rect; vec4 radii; vec4 style; vec4 border; vec4 sectors[3];
};
out vec2 vLocalPos;
out vec2 vSize;
const vec2 corners[6]=vec2[6](vec2(0,0),vec2(1,0),vec2(0,1),vec2(0,1),vec2(1,0),vec2(1,1));
void main() {
    vec2 pos=corners[gl_VertexID];
    vLocalPos=pos*rect.xy;vSize=rect.xy;
    vec2 ndc=((screen.zw+vLocalPos)/screen.xy)*2.0-1.0;
    gl_Position=vec4(ndc.x,-ndc.y,0,1);
}
