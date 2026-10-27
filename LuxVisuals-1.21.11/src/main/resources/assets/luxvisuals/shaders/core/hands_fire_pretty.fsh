#version 150

uniform sampler2D Sampler0; // with hand
uniform sampler2D Sampler1; // before hand
uniform float uTime;
uniform float uHeight;      // fire height
uniform float uSpeed;       // fire animation speed
uniform float uIntensity;   // fire intensity
uniform float uWind;        // wind strength
uniform float uWave;        // wave strength
uniform vec4 uColor1;       // bottom fire color (yellow/orange)
uniform vec4 uColor2;       // top fire color (red/orange)

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

// Процедурный шум для огня (simplified Perlin-like)
float hash(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    
    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));
    
    vec2 u = f * f * (3.0 - 2.0 * f);
    return mix(a, b, u.x) + (c - a) * u.y * (1.0 - u.x) + (d - b) * u.x * u.y;
}

// Фрактальный шум (FBM)
float fbm(vec2 p) {
    float value = 0.0;
    float amplitude = 0.5;
    float frequency = 1.0;
    
    for (int i = 0; i < 4; i++) {
        value += amplitude * noise(p * frequency);
        frequency *= 2.0;
        amplitude *= 0.5;
    }
    
    return value;
}

// Огненный эффект
vec3 getFireEffect(vec2 uv, float mask) {
    if (mask < 0.01) return vec3(0.0);
    
    // Параметры огня
    float time = uTime * uSpeed;
    
    // Огонь идет снизу вверх
    vec2 fireUV = uv;
    
    // Расстояние от низа экрана
    float fromBottom = 1.0 - fireUV.y;
    
    // Проверяем, находимся ли мы в зоне огня
    if (fromBottom > uHeight) {
        return vec3(0.0);
    }
    
    // Нормализованная высота в пределах зоны огня (0 = низ, 1 = верх)
    float heightInFire = fromBottom / uHeight;
    
    // Добавляем искажение ветром
    float windOffset = sin(time * 0.5 + fireUV.y * 3.0) * 0.08 * uWind;
    fireUV.x += windOffset;
    
    // Генерируем турбулентность для огня
    vec2 noiseCoord = fireUV * vec2(4.0, 8.0);
    noiseCoord.y += time * 1.2;
    
    float turbulence = fbm(noiseCoord);
    turbulence += fbm(noiseCoord * 2.0 - time * 0.3) * 0.5;
    
    // Добавляем волны
    float wave = sin(fireUV.x * 12.0 + time * 2.5) * 0.5 + 0.5;
    turbulence = mix(turbulence, wave, 0.15 * uWave);
    
    // Создаем форму огня - ярче внизу, затухает вверху
    float fireShape = turbulence * (1.0 - heightInFire * 0.7);
    fireShape = smoothstep(0.2, 0.7, fireShape) * uIntensity;
    
    // Применяем маску руки
    fireShape *= mask;
    
    // Градиент цвета от низа к верху
    vec3 fireColor = mix(uColor1.rgb, uColor2.rgb, heightInFire);
    
    // Добавляем яркость внизу
    float brightness = 1.5 - heightInFire * 0.5;
    fireColor *= brightness;
    
    return fireColor * fireShape;
}

void main() {
    vec2 uv = TexCoord;
    
    // Определяем маску руки
    float handMask = getHandMask(uv);
    
    vec4 handColor = texture(Sampler0, uv);
    
    // Получаем эффект огня
    vec3 fireEffect = getFireEffect(uv, handMask);
    
    // Смешиваем аддитивно
    vec3 finalColor = handColor.rgb + fireEffect;
    
    OutColor = vec4(finalColor, 1.0);
}
