#version 150

in vec2 texCoord;
out vec4 fragColor;

uniform float time;
uniform vec2 screenSize;
uniform vec4 baseColor;
uniform float alpha;

void main() {
    vec2 uv = gl_FragCoord.xy / screenSize;
    float t = time * 0.9;

    vec2 auUv = uv * 3.0;
    float a1 = sin(auUv.x * 2.5 + sin(auUv.y * 2.0 + t));
    float a2 = cos(auUv.y * 2.5 + sin(auUv.x * 2.0 - t * 0.8));
    float aurora = 0.5 + 0.25 * (a1 + a2);
    aurora = smoothstep(0.15, 0.85, aurora);

    float caustic = pow(0.5 + 0.5 * sin(auUv.x * 6.0 + auUv.y * 6.0 + t * 2.0), 3.0) * 0.5;

    vec3 secondary = vec3(baseColor.g, baseColor.b, baseColor.r);
    vec3 col = mix(baseColor.rgb, secondary, aurora) + vec3(caustic);

    float finalAlpha = alpha * clamp(0.35 + aurora * 0.65, 0.0, 1.0);
    fragColor = vec4(col, finalAlpha);
}
