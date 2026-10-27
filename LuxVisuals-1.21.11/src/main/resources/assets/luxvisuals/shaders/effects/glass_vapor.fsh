#version 330
uniform sampler2D uScene;
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
out vec4 fragColor;
void main() {
    vec2 uv = gl_FragCoord.xy / screenRefraction.xy;
    vec2 aspect = vec2(screenRefraction.x / screenRefraction.y, 1.0);
    float field = 0.0;
    vec2 gradient = vec2(0.0);
    float nearestZ = -1.0e6;
    float nearestRadius = 0.0;
    float opacity = 0.0;
    float weightedRadius = 0.0;
    float totalWeight = 0.0;
    // Compact metaball kernels merge droplets without internal circular rims.
    for (int i = 0; i < int(controls.x); i++) {
        vec4 sphere = spheres[i];
        vec4 clip = projection * vec4(sphere.xyz, 1.0);
        vec2 center = clip.xy / clip.w * 0.5 + 0.5;
        float radius = abs(projection[1][1] * sphere.w / clip.w) * 0.5;
        float support = radius * 1.65 * (0.65 + 0.35 * extras[i].x);
        vec2 delta = (uv - center) * aspect;
        delta += support * appearance.w * 0.08 * vec2(sin(appearance.z * 2.0 + extras[i].y),
                                                     cos(appearance.z * 1.7 + extras[i].y));
        float q = dot(delta, delta) / (support * support);
        if (q >= 1.0) continue;
        float k = 1.0 - q;
        float weight = k * k;
        field += weight;
        gradient += -4.0 * k * delta / (support * support);
        totalWeight += weight;
        weightedRadius += radius * weight;
        opacity = max(opacity, extras[i].x);
        if (sphere.z > nearestZ) {
            nearestZ = sphere.z;
            nearestRadius = sphere.w * sqrt(k);
        }
    }
    const float iso = 0.40;
    float aa = max(fwidth(field), 0.002);
    float mask = smoothstep(iso - aa, iso + aa, field);
    if (mask < 0.001 || opacity < 0.001) discard;
    vec4 depthClip = projection * vec4(0.0, 0.0, min(-0.05, nearestZ + nearestRadius), 1.0);
    gl_FragDepth = depthClip.z / depthClip.w * 0.5 + 0.5;
    float radius = weightedRadius / max(totalWeight, 0.0001);
    vec2 normal = -gradient * radius * 0.38;
    normal /= sqrt(1.0 + dot(normal, normal));
    float thickness = clamp((field - iso) * 1.4, 0.0, 1.0);
    // Broad ripples distort scenery instead of painting soap-bubble highlights.
    vec2 ripple = vec2(sin(uv.y * 95.0 + field * 3.8 + appearance.z * 0.65),
                       cos(uv.x * 83.0 - field * 3.1 - appearance.z * 0.55));
    vec2 bend = (normal + ripple * thickness * 0.24) * screenRefraction.z
                * min(radius * 22.0, 1.8) / aspect;
    float chroma = screenRefraction.w * 0.12;
    vec2 minUV = 0.5 / screenRefraction.xy;
    vec2 maxUV = 1.0 - minUV;
    vec3 glass;
    glass.r = texture(uScene, clamp(uv - bend * (1.0 + chroma), minUV, maxUV)).r;
    glass.g = texture(uScene, clamp(uv - bend, minUV, maxUV)).g;
    glass.b = texture(uScene, clamp(uv - bend * (1.0 - chroma), minUV, maxUV)).b;
    vec3 paleTint = mix(tintColor.rgb, vec3(1.0), 0.38);
    glass = mix(glass, paleTint, appearance.x * (0.55 + thickness * 0.20));
    float edgeDistance = (field - iso) / max(length(gradient), 0.001);
    float rim = exp(-pow(edgeDistance / max(radius * 0.055, 1.0 / screenRefraction.y), 2.0));
    glass += rim * appearance.y * mix(vec3(1.0), paleTint, 0.40);
    fragColor = vec4(clamp(glass, 0.0, 1.0), mask * opacity * 0.94);
}
