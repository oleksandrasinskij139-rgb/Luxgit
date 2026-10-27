#ifdef GL_ES
precision mediump float;
#endif

uniform float time;
uniform vec2 resolution;

// Оптимизированный градиентный шейдер для главного меню
void main(void) {
    vec2 uv = gl_FragCoord.xy / resolution.xy;
    
    // Анимированные цвета
    vec3 color1 = vec3(0.1, 0.2, 0.5); // Темно-синий
    vec3 color2 = vec3(0.5, 0.1, 0.3); // Темно-фиолетовый
    vec3 color3 = vec3(0.1, 0.3, 0.4); // Темно-бирюзовый
    
    // Плавное движение градиента
    float t = time * 0.1;
    float mixValue = sin(uv.y * 2.0 + t) * 0.5 + 0.5;
    float mixValue2 = cos(uv.x * 2.0 - t) * 0.5 + 0.5;
    
    // Смешиваем 3 цвета
    vec3 finalColor = mix(color1, color2, mixValue);
    finalColor = mix(finalColor, color3, mixValue2 * 0.5);
    
    // Добавляем легкий виньетинг
    float vignette = 1.0 - length(uv - 0.5) * 0.8;
    finalColor *= vignette;
    
    gl_FragColor = vec4(finalColor, 1.0);
}
