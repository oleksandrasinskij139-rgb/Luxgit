#version 150

in vec3 Direction;
in vec4 vColor;

uniform float Time;
uniform vec3 Accent;
uniform vec4 ColorModulator;

out vec4 OutColor;

float bloomHash3(vec3 p) {
    p = fract(p * vec3(443.8975, 397.2973, 491.1871));
    p += dot(p, p.yxz + 19.19);
    return fract((p.x + p.y) * p.z);
}

float bloomNoise3D(vec3 p) {
    vec3 i = floor(p);
    vec3 f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    return mix(
        mix(mix(bloomHash3(i), bloomHash3(i + vec3(1.0, 0.0, 0.0)), f.x),
            mix(bloomHash3(i + vec3(0.0, 1.0, 0.0)), bloomHash3(i + vec3(1.0, 1.0, 0.0)), f.x), f.y),
        mix(mix(bloomHash3(i + vec3(0.0, 0.0, 1.0)), bloomHash3(i + vec3(1.0, 0.0, 1.0)), f.x),
            mix(bloomHash3(i + vec3(0.0, 1.0, 1.0)), bloomHash3(i + vec3(1.0, 1.0, 1.0)), f.x), f.y),
        f.z
    );
}

vec3 cosmosBloom(vec3 rayW, float uTime, vec3 uColor, float uSpeed, float uScale, float uIntensity) {
    vec3 darkBackground = uColor * 0.02;
    float time = uTime * uSpeed * 0.4;
    vec3 p = rayW * (uScale * 0.4 + 0.8);
    vec3 warp = vec3(
        bloomNoise3D(p + vec3(0.0, time * 0.8, 0.0)),
        bloomNoise3D(p + vec3(time * 0.6, 12.4, time * 0.4)),
        bloomNoise3D(p + vec3(27.1, 0.0, time * 0.7))
    ) * 1.2;

    vec3 distortedRay = normalize(rayW + warp * 0.45);

    float lineBloom = 0.0;
    float lineCore = 0.0;
    for (int i = 0; i < 4; i++) {
        float fi = float(i);
        float wave = sin(distortedRay.x * 2.5 + time + fi * 1.8) *
                     cos(distortedRay.z * 2.5 - time * 0.6 + fi * 0.7) * 0.45;
        float organicBend = (bloomNoise3D(distortedRay * 2.0 + vec3(time * 0.3, fi * 4.0, time * 0.2)) - 0.5) * 0.6;
        float targetY = (fi - 1.5) * 0.3 + wave + organicBend;
        float dist = abs(rayW.y - targetY);
        float core = exp(-110.0 * dist);
        float bloom = exp(-11.0 * dist);

        lineCore += core;
        lineBloom += bloom;
    }

    vec3 brightColor = uColor * (2.0 + uIntensity * 20.0);
    vec3 bloomColor = uColor * (0.6 + uIntensity * 10.0);

    vec3 finalColor = darkBackground;
    finalColor += bloomColor * lineBloom;
    finalColor += brightColor * lineCore;

    return mix(vec3(0.002, 0.002, 0.005), finalColor, 1.0);
}

void main() {
    vec3 d = normalize(Direction);
    float altitude = max(d.y, 0.0);
    vec3 accent = max(Accent, vec3(0.005));
    vec3 cb = cosmosBloom(d, Time, accent, 1.0, 5.0, 0.02);
    vec3 horizon = accent * 0.15;
    vec3 finalColor = mix(horizon, cb, smoothstep(0.0, 0.12, altitude));
    finalColor = finalColor / (1.0 + finalColor * 0.35);
    finalColor = pow(finalColor, vec3(0.95));
    OutColor = vec4(clamp(finalColor, 0.0, 1.0), 1.0) * ColorModulator * vColor;
}
