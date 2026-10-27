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

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));
    return mix(mix(a, b, f.x), mix(c, d, f.x), f.y);
}

void main() {
    vec2 uv = gl_FragCoord.xy / screenSize;
    float t = time * 2.2;

    vec2 fireUv = vec2(uv.x * 3.0, uv.y * 3.0 - t);
    float n1 = noise(fireUv * 2.0);
    float n2 = noise(fireUv * 4.0 + vec2(n1 * 1.5, 0.0));
    float flame = 1.0 - abs(noise(fireUv * 3.0 + vec2(0.0, n2 * 1.2)) * 2.0 - 1.0);
    flame = pow(clamp(flame, 0.0, 1.0), 1.6);

    vec3 col = mix(baseColor.rgb * 0.4, baseColor.rgb * 1.2, clamp(flame * 1.4, 0.0, 1.0));
    col += vec3(1.0, 0.9, 0.6) * pow(clamp((flame - 0.5) * 2.5, 0.0, 1.0), 2.2);

    float finalAlpha = alpha * clamp(0.3 + flame * 0.7, 0.0, 1.0);
    fragColor = vec4(col, finalAlpha);
}
