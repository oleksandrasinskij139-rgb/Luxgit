#version 150

uniform sampler2D Sampler0;
uniform float Saturation;
uniform float Brightness;
uniform float Contrast;
uniform float Vibrance;

in vec2 TexCoord;
in vec4 FragColor;

out vec4 OutColor;

void main() {
    vec4 src = texture(Sampler0, TexCoord);
    vec3 color = src.rgb;
    float luma = dot(color, vec3(0.299, 0.587, 0.114));
    float strongest = max(color.r, max(color.g, color.b));
    float average = (color.r + color.g + color.b) / 3.0;
    float adaptive = clamp((strongest - average) * -Vibrance, -0.35, 0.0);

    color = mix(vec3(luma), color, max(0.0, Saturation + adaptive));
    color = (color - 0.5) * Contrast + 0.5;
    color *= Brightness;

    OutColor = vec4(clamp(color, 0.0, 1.0), src.a) * FragColor;
}
