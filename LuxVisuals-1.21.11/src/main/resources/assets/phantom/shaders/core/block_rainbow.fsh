#version 150

in vec2 texCoord;
out vec4 fragColor;

uniform float time;
uniform vec2 screenSize;
uniform vec4 baseColor;
uniform float alpha;

vec3 hsv2rgb(vec3 c) {
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

void main() {
    vec2 uv = gl_FragCoord.xy / screenSize;
    float t = time * 0.8;

    float hue = fract((uv.x + uv.y) * 0.8 + t * 0.3);
    vec3 rainbow = hsv2rgb(vec3(hue, 0.85, 1.0));

    float wave = 0.5 + 0.5 * sin((uv.x - uv.y) * 15.0 + t * 3.0);
    vec3 col = mix(rainbow, baseColor.rgb, 0.2) + vec3(wave * 0.25);

    float finalAlpha = alpha * clamp(0.4 + wave * 0.4, 0.0, 1.0);
    fragColor = vec4(col, finalAlpha);
}
