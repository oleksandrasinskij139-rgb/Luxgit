#version 150

uniform sampler2D Sampler0; // with hand
uniform sampler2D Sampler1; // before hand
uniform vec4 uColor;        // outline color (r, g, b, a)
uniform float uWidth;       // outline width in pixels
uniform float uAlpha;       // outline alpha

in vec2 TexCoord;
in vec4 FragColor;
out vec4 OutColor;

// Определение маски руки
float getHandMask(vec2 uv) {
    vec4 withHand = texture(Sampler0, uv);
    vec4 beforeHand = texture(Sampler1, uv);
    
    float colorDiff = length(withHand.rgb - beforeHand.rgb);
    float alphaDiff = abs(withHand.a - beforeHand.a);
    
    float diff = max(colorDiff, alphaDiff * 0.5);
    return smoothstep(0.005, 0.08, diff);
}

// Детекция краев
float detectEdge(vec2 uv) {
    vec2 texelSize = 1.0 / vec2(textureSize(Sampler0, 0));
    float width = uWidth;
    
    float center = getHandMask(uv);
    
    // Семплируем маску вокруг текущего пикселя
    float top = getHandMask(uv + vec2(0, texelSize.y * width));
    float bottom = getHandMask(uv - vec2(0, texelSize.y * width));
    float left = getHandMask(uv - vec2(texelSize.x * width, 0));
    float right = getHandMask(uv + vec2(texelSize.x * width, 0));
    
    // Диагонали для более точного определения краев
    float topLeft = getHandMask(uv + vec2(-texelSize.x, texelSize.y) * width);
    float topRight = getHandMask(uv + vec2(texelSize.x, texelSize.y) * width);
    float bottomLeft = getHandMask(uv + vec2(-texelSize.x, -texelSize.y) * width);
    float bottomRight = getHandMask(uv + vec2(texelSize.x, -texelSize.y) * width);
    
    // Вычисляем разницу
    float edge = abs(center - top) + abs(center - bottom) + 
                 abs(center - left) + abs(center - right) +
                 abs(center - topLeft) * 0.7 + abs(center - topRight) * 0.7 +
                 abs(center - bottomLeft) * 0.7 + abs(center - bottomRight) * 0.7;
    
    return smoothstep(0.1, 0.5, edge);
}

void main() {
    vec2 uv = TexCoord;
    
    // Определяем маску руки
    float handMask = getHandMask(uv);
    
    vec4 handColor = texture(Sampler0, uv);
    
    // Детектим края
    float edge = detectEdge(uv);
    
    // Если есть край, рисуем обводку
    if (edge > 0.01 && handMask > 0.01) {
        vec3 outlineColor = mix(handColor.rgb, uColor.rgb, edge * uAlpha);
        OutColor = vec4(outlineColor, 1.0);
    } else {
        OutColor = handColor;
    }
}
