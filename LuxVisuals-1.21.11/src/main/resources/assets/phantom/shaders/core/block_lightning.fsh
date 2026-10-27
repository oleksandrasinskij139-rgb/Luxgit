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

float fbm(vec2 p) {
    float v = 0.0;
    float a = 0.5;
    for (int i = 0; i < 4; i++) {
        v += noise(p) * a;
        p = p * 2.05 + vec2(1.4, 3.7);
        a *= 0.5;
    }
    return v;
}

void main() {
    vec2 uv = gl_FragCoord.xy / screenSize;
    float t = time * 3.5;

    vec2 p = uv * 4.0;
    float spark1 = abs(sin(p.x * 4.0 + fbm(p * 2.5 + vec2(t * 4.0, t * 2.0)) * 5.0));
    float spark2 = abs(cos(p.y * 4.0 + fbm(p * 2.5 + vec2(-t * 3.0, t * 4.0)) * 5.0));
    float bolt1 = 1.0 / (spark1 * 25.0 + 1.0);
    float bolt2 = 1.0 / (spark2 * 25.0 + 1.0);
    float totalBolt = bolt1 + bolt2;

    vec3 col = baseColor.rgb * 0.8 + vec3(totalBolt * 0.8);
    col += vec3(1.0) * pow(clamp(totalBolt * 0.5, 0.0, 1.0), 3.0);

    float finalAlpha = alpha * clamp(0.25 + totalBolt * 0.8, 0.0, 1.0);
    fragColor = vec4(col, finalAlpha);
}
