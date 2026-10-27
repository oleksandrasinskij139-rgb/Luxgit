#version 150

in vec2 TexCoord;

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

uniform mat4 ViewProj;
uniform mat4 InvViewProj;
uniform vec3 CamPos;

uniform vec4 Nova0_Data;
uniform vec4 Nova0_Param;
uniform vec4 Nova0_Color;

uniform vec4 Nova1_Data;
uniform vec4 Nova1_Param;
uniform vec4 Nova1_Color;

uniform vec4 Nova2_Data;
uniform vec4 Nova2_Param;
uniform vec4 Nova2_Color;

uniform vec4 Nova3_Data;
uniform vec4 Nova3_Param;
uniform vec4 Nova3_Color;

out vec4 OutColor;

vec3 worldFromDepth(vec2 uv, float depth) {
    vec4 clip = vec4(uv * 2.0 - 1.0, depth * 2.0 - 1.0, 1.0);
    vec4 pos = InvViewProj * clip;
    return pos.xyz / pos.w;
}

void evaluateAegis(vec4 data, vec4 param, vec4 col, vec3 camRelPos, vec3 rayDir, float geomDist, inout vec3 accumGlow) {
    if (col.a < 0.5 || data.w >= 1.0) {
        return;
    }

    vec3 fxOrigin = data.xyz;
    float progress = clamp(data.w, 0.0, 1.0);
    float scale = max(param.y, 0.2);
    float intensity = param.z;
    float time = param.w;
    vec3 color = col.rgb;
    float fade = 1.0 - progress;
    vec3 fxRelPos = fxOrigin - CamPos;

    // Center cocoon at player torso/chest
    vec3 aegisCenter = fxRelPos + vec3(0.0, 0.9 * scale, 0.0);
    vec3 rad = vec3(0.88 * scale, 1.25 * scale, 0.88 * scale);

    // Ray vs Ellipsoid intersection
    vec3 ro = -aegisCenter / rad;
    vec3 rd = rayDir / rad;
    float aQuad = dot(rd, rd);
    float bQuad = 2.0 * dot(ro, rd);
    float cQuad = dot(ro, ro) - 1.0;
    float disc = bQuad * bQuad - 4.0 * aQuad * cQuad;

    if (disc > 0.0) {
        float tHit = (-bQuad - sqrt(disc)) / (2.0 * aQuad);
        if (tHit > 0.05) {
            vec3 pHit = rayDir * tHit;
            if (length(pHit) < geomDist) {
                vec3 sUnit = ro + rd * tHit;
                vec3 norm = normalize(sUnit / rad);

                // Neon Fresnel rim glow along silhouette
                float fresnel = pow(1.0 - abs(dot(rayDir, norm)), 2.4);

                // Procedural animated hexagonal lattice (honeycomb shield)
                vec2 hexUv = vec2(atan(sUnit.z, sUnit.x) * 3.5, sUnit.y * 3.8);
                vec2 rH = vec2(1.0, 1.7320508);
                vec2 hH = rH * 0.5;
                vec2 aH = mod(hexUv, rH) - hH;
                vec2 bH = mod(hexUv - hH, rH) - hH;
                vec2 gv = dot(aH, aH) < dot(bH, bH) ? aH : bH;
                float hexDist = max(abs(gv.x), abs(gv.x) * 0.5 + abs(gv.y) * 0.8660254);
                float hexLine = smoothstep(0.41, 0.48, hexDist);

                // Ascending vertical scanline pulse
                float scan = exp(-pow((sUnit.y - (mod(time * 3.5, 2.6) - 1.3)) * 7.0, 2.0));

                // Glowing cell interior when scan sweeps past
                float cellGlow = smoothstep(0.40, 0.0, hexDist) * scan * 0.55;

                // Cyber dither dissolve on fade out
                float dither = fract(sin(dot(floor(hexUv * 4.0), vec2(12.9898, 78.233))) * 43758.5453);
                float dissolveMask = smoothstep(0.0, 0.35, (1.0 - progress) * 1.35 - dither);

                float shield = (hexLine * 1.85 + cellGlow + scan * 2.3 + fresnel * 1.6) * dissolveMask * fade;
                vec3 shieldCol = mix(color, vec3(1.0), (hexLine + scan) * 0.45);
                accumGlow += shieldCol * (shield * intensity);
            }
        }
    }
}

void main() {
    vec4 src = texture(Sampler0, TexCoord);
    float depth = texture(Sampler1, TexCoord).r;

    vec3 camRelPos;
    vec3 rayDir;
    float geomDist;

    if (depth < 1.0) {
        camRelPos = worldFromDepth(TexCoord, depth);
        geomDist = length(camRelPos);
        rayDir = geomDist > 1e-5 ? camRelPos / geomDist : vec3(0.0, 0.0, -1.0);
    } else {
        camRelPos = worldFromDepth(TexCoord, 0.5);
        geomDist = 1e6;
        float len = length(camRelPos);
        rayDir = len > 1e-5 ? camRelPos / len : vec3(0.0, 0.0, -1.0);
    }

    vec3 fxGlow = vec3(0.0);

    evaluateAegis(Nova0_Data, Nova0_Param, Nova0_Color, camRelPos, rayDir, geomDist, fxGlow);
    evaluateAegis(Nova1_Data, Nova1_Param, Nova1_Color, camRelPos, rayDir, geomDist, fxGlow);
    evaluateAegis(Nova2_Data, Nova2_Param, Nova2_Color, camRelPos, rayDir, geomDist, fxGlow);
    evaluateAegis(Nova3_Data, Nova3_Param, Nova3_Color, camRelPos, rayDir, geomDist, fxGlow);

    if (length(fxGlow) < 1e-5) {
        OutColor = src;
        return;
    }

    vec3 finalColor = src.rgb + fxGlow;
    OutColor = vec4(clamp(finalColor, 0.0, 1.0), src.a);
}
