#version 150

in vec2 texCoord;
out vec4 fragColor;

uniform float time;
uniform vec2 screenSize;
uniform vec4 baseColor;
uniform float alpha;

float hash(vec2 p) {
    p = fract(p * vec2(123.34, 345.45));
    p += dot(p, p + 34.345);
    return fract(p.x * p.y);
}

void main() {
    vec2 uv = gl_FragCoord.xy / screenSize;
    float t = time * 1.5;

    // Scanlines
    float scan = sin(uv.y * 420.0 + t * 8.0) * 0.15;

    // Cyber Grid
    vec2 gridUv = fract(uv * 30.0);
    float gridLine = max(smoothstep(0.92, 0.98, gridUv.x), smoothstep(0.92, 0.98, gridUv.y));

    // Glitch line displacement
    float row = floor(uv.y * 40.0);
    float glitch = step(0.96, hash(vec2(row, floor(t * 4.0))));
    float offset = (hash(vec2(row, t)) - 0.5) * 0.04 * glitch;

    // Energy pulse
    float wave = sin((uv.x + offset + uv.y) * 10.0 - t * 2.0) * 0.5 + 0.5;

    vec3 col = baseColor.rgb;
    col += vec3(gridLine * 0.65);
    col += vec3(wave * 0.35);
    col += vec3(glitch * 0.3);

    float finalAlpha = alpha * clamp(0.35 + gridLine * 0.5 + wave * 0.3 + scan, 0.0, 1.0);
    fragColor = vec4(col, finalAlpha);
}
