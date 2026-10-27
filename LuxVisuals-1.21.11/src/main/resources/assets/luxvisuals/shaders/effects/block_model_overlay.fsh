#version 330
layout(std140) uniform OverlayData {
    mat4 modelView;
    mat4 projection;
    vec4 tint;
    vec4 effect;
    vec4 style;
    vec4 boxMin;
    vec4 boxMax;
};
uniform sampler2D Sampler0;
in vec2 texCoord;
in vec2 quadUV;
in vec3 localPosition;
out vec4 fragColor;
float hash(vec3 p) {
    p = fract(p * 0.3183099 + vec3(0.17,0.31,0.53));
    p *= 17.0;
    return fract(p.x * p.y * p.z * (p.x + p.y + p.z));
}
float noise(vec3 p) {
    vec3 i = floor(p), f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    return mix(mix(mix(hash(i),hash(i+vec3(1,0,0)),f.x),
                   mix(hash(i+vec3(0,1,0)),hash(i+vec3(1,1,0)),f.x),f.y),
               mix(mix(hash(i+vec3(0,0,1)),hash(i+vec3(1,0,1)),f.x),
                   mix(hash(i+vec3(0,1,1)),hash(i+vec3(1,1,1)),f.x),f.y),f.z);
}
float clouds(vec3 p) {
    float value = 0.0, amplitude = 0.55;
    for (int i=0;i<4;i++) {
        value += noise(p) * amplitude;
        p = p * 2.03 + vec3(3.1,1.7,2.4);
        amplitude *= 0.5;
    }
    return value;
}
void main() {
    // форма берётся прямо из текстуры блока: прозрачные пиксели (цепь, решётки, растения) отрезаются
    vec4 tex = texture(Sampler0, texCoord);
    if (tex.a < 0.1) discard;

    float time = effect.x * effect.y * 0.35;
    vec3 p = localPosition * effect.z + vec3(time,-time*0.6,time*0.35);
    float cloud = clouds(p + vec3(clouds(p+4.7),clouds(p-3.2),clouds(p+8.1)) * 0.9);
    float lightCloud = smoothstep(0.35,0.72,cloud);
    // Both cloud tones follow the live theme; retain contrast without a fixed blue/green palette.
    vec3 deep = tint.rgb * 0.42;
    vec3 bright = mix(tint.rgb,vec3(1.0),0.32);
    vec3 fill = mix(deep,bright,lightCloud);
    if (style.w > 0.5) fill = mix(tint.rgb,vec3(1.0),0.18);
    // детали текстуры проступают сквозь заливку
    float lum = dot(tex.rgb, vec3(0.299,0.587,0.114));
    fill *= mix(0.72, 1.22, lum);

    // контур: края граней модели + силуэт текстуры (переход непрозрачный → прозрачный)
    vec2 edgePixels = min(quadUV,1.0-quadUV) / max(fwidth(quadUV),vec2(0.00001));
    float distanceToEdge = min(edgePixels.x,edgePixels.y);
    float faceLine = style.x > 0.0 ? 1.0-smoothstep(max(0.0,style.x-0.75),style.x+0.75,distanceToEdge) : 0.0;
    float alphaEdge = style.x > 0.0 ? clamp(fwidth(tex.a) * 1.4, 0.0, 1.0) : 0.0;
    float line = max(faceLine, alphaEdge);
    float halo = style.x > 0.0 ? exp(-distanceToEdge/max(1.0,style.x*2.3)) * style.z * 0.20 : 0.0;
    vec3 outline = tint.a > 0.5 ? mix(tint.rgb,vec3(1.0),0.25) : vec3(1.0);
    float fillAlpha = style.w > 1.5 ? 0.0 : effect.w * (0.88+lightCloud*0.12);
    float edgeAlpha = clamp(line*style.y + halo*style.y,0.0,1.0);
    float alpha = edgeAlpha + fillAlpha*(1.0-edgeAlpha);
    vec3 rgb = (outline*edgeAlpha + fill*fillAlpha*(1.0-edgeAlpha))/max(alpha,0.0001);
    rgb += outline * halo * 0.30;
    alpha *= boxMax.w;                      // плавное появление/исчезание
    if (alpha < 0.001) discard;
    fragColor = vec4(clamp(rgb,0.0,1.0),alpha);
}
