#version 330
layout(std140) uniform GhostData { vec4 screen; vec4 slots[41]; };
uniform sampler2D Icons;
in vec2 texCoord;
out vec4 fragColor;
void main() {
    vec4 icon = texture(Icons, texCoord);
    fragColor = vec4(icon.rgb, icon.a * screen.z);
    if (fragColor.a < 0.001) discard;
}
