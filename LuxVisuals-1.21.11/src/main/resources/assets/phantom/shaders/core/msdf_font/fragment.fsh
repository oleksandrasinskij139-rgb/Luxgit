#version 150

in vec2 TexCoord;
in vec4 FragColor;
in vec2 GlobalPos;

uniform sampler2D Sampler0;
uniform float Range;
uniform float Thickness;
uniform float Smoothness;
uniform bool StrokeEnabled;
uniform float StrokeThickness;
uniform vec4 StrokeColor;
uniform vec4 ColorModulator;

uniform bool EnableFadeout;
uniform float FadeoutStart;
uniform float FadeoutEnd;
uniform float MaxWidth;
uniform float TextPosX;

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

    // Apply horizontal fadeout
    if (EnableFadeout) {
        float fadeAlpha = 1.0;
        // Вычисляем позицию относительно начала текста
        float relativeX = GlobalPos.x - TextPosX;
        // Нормализуем относительно ширины текста
        float normalizedX = relativeX / max(MaxWidth, 0.0001);
        if (normalizedX > FadeoutStart) {
            fadeAlpha = 1.0 - smoothstep(FadeoutStart, FadeoutEnd, normalizedX);
        }
        color.a *= fadeAlpha;
    }

    OutColor = color * ColorModulator;
}