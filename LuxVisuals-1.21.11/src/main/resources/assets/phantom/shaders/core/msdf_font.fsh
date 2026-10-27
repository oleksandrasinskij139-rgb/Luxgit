#version 150

in vec2 TexCoord;
in vec4 FragColor;

uniform sampler2D Sampler0;
uniform float Range; // distance field range of the msdf font texture
uniform float Thickness; // text thickness
uniform float Smoothness; // edge smoothness
uniform bool StrokeEnabled; // if false, outline computation will be ignored
uniform float StrokeThickness;
uniform vec4 StrokeColor;

out vec4 OutColor;

float median(vec3 color) {
    return max(min(color.r, color.g), min(max(color.r, color.g), color.b));
}

void main() {
    float dist = median(texture(Sampler0, TexCoord).rgb) - 0.5 + Thickness;
    
    vec2 unitRange = vec2(Range) / vec2(textureSize(Sampler0, 0));
    vec2 screenTexSize = vec2(1.0) / max(fwidth(TexCoord), vec2(0.000001));
    float screenPxRange = max(0.5 * dot(unitRange, screenTexSize), 1.0);

    float screenPxDistance = screenPxRange * dist;
    float halfWidth = max(Smoothness, 0.5);
    float alpha = smoothstep(-halfWidth, halfWidth, screenPxDistance);
    vec4 color = vec4(FragColor.rgb, FragColor.a * alpha);
    
    if (StrokeEnabled) {
        float strokeDist = (dist + StrokeThickness) * screenPxRange;
        float strokeAlpha = smoothstep(-halfWidth, halfWidth, strokeDist);
        color = mix(StrokeColor, FragColor, alpha);
        color.a *= strokeAlpha;
    }
    
    OutColor = color;
}