#version 150

#moj_import <phantom:common.glsl>

in vec2 FragCoord;
in vec2 TexCoord;
in vec4 FragColor;

uniform sampler2D Sampler0;
uniform vec2 Size;
uniform vec4 Radius;
uniform float Smoothness;
uniform float BlurRadius;
uniform vec4 ColorModulator;

out vec4 OutColor;

float hash12(vec2 p) {
    vec3 p3 = fract(vec3(p.xyx) * 0.1031);
    p3 += dot(p3, p3.yzx + 33.33);
    return fract((p3.x + p3.y) * p3.z);
}

const int TAPS = 64;

vec3 auraDiskBlur(vec2 uv, vec2 texel, float radius) {
    float rotation = hash12(gl_FragCoord.xy) * 6.2831853;
    vec3 sum = vec3(0.0);
    float weightSum = 0.0;

    for (int i = 0; i < TAPS; i++) {
        float t = (float(i) + 0.5) / float(TAPS);
        float r = sqrt(t);
        float phi = float(i) * 2.39996323 + rotation;
        vec2 offset = vec2(cos(phi), sin(phi)) * (r * radius) * texel;
        float weight = exp(-2.2 * r * r);
        sum += texture(Sampler0, clamp(uv + offset, vec2(0.0), vec2(1.0))).rgb * weight;
        weightSum += weight;
    }

    return sum / max(weightSum, 0.0001);
}

void main() {
    vec2 inputResolution = vec2(textureSize(Sampler0, 0));
    vec2 texel = 1.0 / inputResolution;
    vec2 uv = gl_FragCoord.xy / inputResolution;
    vec3 blurred = auraDiskBlur(uv, texel, max(0.75, BlurRadius));

    vec2 center = Size * 0.5;
    float distance = roundedBoxSDF(center - (FragCoord * Size), center - 1.0, Radius);
    float alpha = 1.0 - smoothstep(1.0 - Smoothness, 1.0, distance);

    vec4 finalColor = vec4(blurred, alpha) * FragColor * ColorModulator;
    if (finalColor.a <= 0.001) {
        discard;
    }
    OutColor = finalColor;
}
