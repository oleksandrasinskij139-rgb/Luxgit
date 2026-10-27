#version 330
layout(std140) uniform RevealData {
    vec4 screenOrigin;
    vec4 wave;
    vec4 tint;
};
uniform sampler2D PreviousFrame;
out vec4 fragColor;
void main() {
    float distanceToOrigin = length(gl_FragCoord.xy - screenOrigin.zw);
    float edge = (distanceToOrigin - wave.x) / wave.y;
    float oldMask = smoothstep(-1.0, 1.0, edge);
    oldMask *= 1.0 - smoothstep(0.86, 1.0, wave.z);
    float glow = exp(-edge * edge * 0.65) * sin(wave.z * 3.14159265) * 0.26;
    float alpha = oldMask + glow * (1.0 - oldMask);
    if (alpha < 0.001) discard;
    vec3 previous = texture(PreviousFrame, gl_FragCoord.xy / screenOrigin.xy).rgb;
    vec3 shell = mix(tint.rgb, vec3(1.0), 0.25);
    fragColor = vec4((previous * oldMask + shell * glow * (1.0 - oldMask)) / max(alpha, 0.001), alpha);
}
