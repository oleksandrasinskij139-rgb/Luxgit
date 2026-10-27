#version 150

uniform sampler2D DiffuseSampler;
uniform float Saturation;
uniform float Brightness;
uniform float Contrast;
uniform vec3 ColorTint;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec4 color = texture(DiffuseSampler, texCoord);
    
    // Насыщенность
    float luminance = dot(color.rgb, vec3(0.2126, 0.7152, 0.0722));
    vec3 gray = vec3(luminance);
    vec3 result = mix(gray, color.rgb, Saturation);
    
    // Яркость
    result *= Brightness;
    
    // Контраст
    result = (result - 0.5) * Contrast + 0.5;
    
    // Цветовой оттенок (RGB tint)
    result *= ColorTint;
    
    // Ограничиваем значения
    result = clamp(result, 0.0, 1.0);
    
    fragColor = vec4(result, color.a);
}
