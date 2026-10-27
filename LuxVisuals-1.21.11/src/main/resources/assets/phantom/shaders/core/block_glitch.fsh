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
    float t = time * 2.0;

    float row = floor(uv.y * 35.0);
    float gActive = step(0.93, hash(vec2(row, floor(t * 6.0))));
    float shift = (hash(vec2(row, floor(t * 12.0))) - 0.5) * 0.12 * gActive;

    vec2 gUv = vec2(uv.x + shift, uv.y);
    float blockNoise = hash(floor(gUv * vec2(20.0, 35.0)) + floor(t * 4.0));

    vec3 col = baseColor.rgb;
    if (gActive > 0.5) {
        col = mix(col, vec3(1.0) - col, 0.6);
    }
    col += vec3(blockNoise * 0.35);

    float finalAlpha = alpha * clamp(0.3 + gActive * 0.4 + blockNoise * 0.3, 0.0, 1.0);
    fragColor = vec4(col, finalAlpha);
}
