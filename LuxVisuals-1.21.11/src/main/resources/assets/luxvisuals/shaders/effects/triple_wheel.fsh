#version 330 core
in vec2 vLocalPos;
in vec2 vSize;
out vec4 FragColor;

layout(std140) uniform WheelData {
    vec4 screen;
    vec4 rect;
    vec4 radii;
    vec4 style;
    vec4 border;
    vec4 sectors[3];
};
#define uCenter rect.zw
#define uScale vec2(1.0)
#define uOuterRadius radii.y
#define uInnerRadius radii.x
#define uGapDeg radii.z
#define uSectorCount int(radii.w)
#define uHoveredIndex int(style.x)
#define uBorderWidth style.y
#define uBorderColor border.rgb
#define uFillAlpha style.z
#define uAlpha style.w

const float PI = 3.14159265359;

void main() {
    vec2 d = (vLocalPos - uCenter) / max(uScale, vec2(0.001));
    float radius = length(d);

    // 0° смотрит строго вверх (12 часов), по часовой стрелке
    float angleDeg = degrees(atan(d.x, -d.y));
    if (angleDeg < 0.0) angleDeg += 360.0;

    float sectorSpan = 360.0 / float(uSectorCount); // 45°

    // Смещение на пол-сектора (22.5°), чтобы сектор 0 был по центру сверху
    float shiftedAngle = mod(angleDeg + sectorSpan * 0.5, 360.0);
    int sectorIndex = int(floor(shiftedAngle / sectorSpan));
    float angleInSector = mod(shiftedAngle, sectorSpan);
    float distToEdgeDeg = min(angleInSector, sectorSpan - angleInSector);

    // Анимация наведения для сектора
    float hProg = (sectorIndex >= 0 && sectorIndex < 3) ? sectors[sectorIndex].w : 0.0;

    // Наведенный сектор (сама фигура/осколок) плавно увеличивается наружу и становится больше
    float curOuterRadius = uOuterRadius + (hProg * 9.0);
    float curInnerRadius = max(1.0, uInnerRadius - (hProg * 1.5));

    if (radius > curOuterRadius + 3.0 || radius < curInnerRadius - 3.0) {
        discard;
    }

    float halfGapDeg = uGapDeg * 0.5;

    // Мягкий зазор между секторами (в экранных пикселях)
    float arcDistToEdgePx = (distToEdgeDeg - halfGapDeg) * (PI / 180.0) * radius;
    float gapMask = smoothstep(-0.8, 0.8, arcDistToEdgePx);

    float outerMask = smoothstep(0.0, 1.5, curOuterRadius - radius);
    float innerMask = smoothstep(0.0, 1.5, radius - curInnerRadius);
    float alpha = gapMask * outerMask * innerMask;

    if (alpha <= 0.001) {
        discard;
    }

    // Расстояние до ближайшей ГРАНИЦЫ сектора в пикселях (для 100% непрозрачной обводки)
    float radialEdgePx = distToEdgeDeg * (PI / 180.0) * radius;
    float outerEdgePx  = curOuterRadius - radius;
    float innerEdgePx  = radius - curInnerRadius;
    float nearestEdgePx = min(radialEdgePx, min(outerEdgePx, innerEdgePx));

    bool isBorder = nearestEdgePx < uBorderWidth;

    // Радиальный градиент
    float radialT = clamp((radius - curInnerRadius) / max(1.0, (curOuterRadius - curInnerRadius)), 0.0, 1.0);
    vec3 base = (sectorIndex >= 0 && sectorIndex < 3) ? sectors[sectorIndex].rgb : vec3(0.50, 0.08, 0.12);
    vec3 innerCol = base * 0.35;
    vec3 fillColor = mix(innerCol, base, radialT);

    // Анимация наведения
    if (sectorIndex == uHoveredIndex || hProg > 0.01) {
        fillColor = mix(fillColor, fillColor * 1.45 + vec3(0.20, 0.03, 0.05), hProg);
    }

    vec3 borderColor = uBorderColor;
    if (sectorIndex == uHoveredIndex || hProg > 0.01) {
        borderColor = mix(borderColor, vec3(0.95, 0.24, 0.30), hProg);
    }

    vec3 finalColor = isBorder ? borderColor : fillColor;
    // Обводка = 100% непрозрачная, Заливка = полупрозрачная (uFillAlpha)
    float finalAlpha = isBorder ? (1.0 * alpha * uAlpha) : (uFillAlpha * alpha * uAlpha);

    FragColor = vec4(finalColor, finalAlpha);
}
