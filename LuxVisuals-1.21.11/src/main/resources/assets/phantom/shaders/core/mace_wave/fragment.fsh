#version 150

in vec2 TexCoord;

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

uniform mat4 ViewProj;
uniform mat4 InvViewProj;
uniform vec3 CamPos;

uniform vec4 Wave0_Data;
uniform vec4 Wave0_Param;
uniform vec4 Wave0_Color;

uniform vec4 Wave1_Data;
uniform vec4 Wave1_Param;
uniform vec4 Wave1_Color;

uniform vec4 Wave2_Data;
uniform vec4 Wave2_Param;
uniform vec4 Wave2_Color;

uniform vec4 Wave3_Data;
uniform vec4 Wave3_Param;
uniform vec4 Wave3_Color;

out vec4 OutColor;

vec3 worldFromDepth(vec2 uv, float depth) {
    vec4 clip = vec4(uv * 2.0 - 1.0, depth * 2.0 - 1.0, 1.0);
    vec4 pos = InvViewProj * clip;
    return pos.xyz / pos.w;
}

void evaluateWave(vec4 data, vec4 param, vec4 col, vec3 camRelPos, vec3 rayDir, float geomDist, inout vec2 uvOffset, inout vec3 waveGlow) {
    if (col.a < 0.5 || param.y >= 1.0) {
        return;
    }

    vec3 waveRelCenter = data.xyz - CamPos;
    float radius = data.w;
    float thickness = max(param.x, 0.2);
    float progress = param.y;
    float intensity = param.z;
    float distortion = param.w;
    vec3 color = col.rgb;
    float fade = 1.0 - progress;

    // 1. Solid surface impact wave
    if (geomDist < 500.0) {
        float waveDist = length(camRelPos - waveRelCenter);
        float diff = abs(waveDist - radius);
        if (diff < thickness) {
            float normDiff = diff / thickness;
            float surfaceFactor = smoothstep(1.0, 0.0, normDiff);
            surfaceFactor = pow(surfaceFactor, 2.2);

            // Ground glow with hot core
            vec3 coreCol = mix(color, vec3(1.0), surfaceFactor * 0.6);
            waveGlow += coreCol * (surfaceFactor * 2.4 * intensity * fade);

            // Screen distortion along wave normal
            vec3 dirFromCenter = normalize(camRelPos - waveRelCenter + vec3(1e-4));
            vec4 projDir = ViewProj * vec4(dirFromCenter, 0.0);
            vec2 screenDir = length(projDir.xy) > 1e-5 ? normalize(projDir.xy) : vec2(0.0, 1.0);
            float distPulse = sin(normDiff * 3.14159) * distortion * 0.035 * fade * surfaceFactor;
            uvOffset += screenDir * distPulse;
        }

        // Secondary trailing reverberation ripple
        float echoRadius = radius * 0.62;
        float echoDiff = abs(waveDist - echoRadius);
        if (echoDiff < thickness * 0.75) {
            float echoNorm = echoDiff / (thickness * 0.75);
            float echoFactor = smoothstep(1.0, 0.0, echoNorm) * 0.35 * fade * intensity;
            waveGlow += color * echoFactor;
        }
    }

    // 2. Air spherical shockwave (volumetric bubble in front of geometry / sky)
    float tClosest = dot(rayDir, waveRelCenter);
    float d2 = dot(waveRelCenter, waveRelCenter) - tClosest * tClosest;
    float r2 = radius * radius;
    if (d2 < r2 && tClosest > 0.0) {
        float halfChord = sqrt(max(r2 - d2, 0.0));
        float tNear = tClosest - halfChord;
        if (tNear > 0.1 && tNear < geomDist) {
            vec3 hitPoint = rayDir * tNear;
            vec3 sphereNormal = normalize(hitPoint - waveRelCenter);

            // Fresnel rim on the air bubble
            float fresnel = 1.0 - abs(dot(rayDir, sphereNormal));
            fresnel = pow(clamp(fresnel, 0.0, 1.0), 3.0);

            // Air shockwave rim glow
            vec3 airColor = mix(color, vec3(1.0), fresnel * 0.5);
            waveGlow += airColor * (fresnel * 1.6 * intensity * fade);

            // Optical refraction across the air bubble
            vec4 projNorm = ViewProj * vec4(sphereNormal, 0.0);
            vec2 refrDir = length(projNorm.xy) > 1e-5 ? normalize(projNorm.xy) : vec2(0.0, 1.0);
            uvOffset += refrDir * (fresnel * distortion * 0.025 * fade);
        }
    }
}

void main() {
    vec4 src = texture(Sampler0, TexCoord);
    float depth = texture(Sampler1, TexCoord).r;

    // Calculate view ray and geometry distance
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

    vec2 uvOffset = vec2(0.0);
    vec3 waveGlow = vec3(0.0);

    evaluateWave(Wave0_Data, Wave0_Param, Wave0_Color, camRelPos, rayDir, geomDist, uvOffset, waveGlow);
    evaluateWave(Wave1_Data, Wave1_Param, Wave1_Color, camRelPos, rayDir, geomDist, uvOffset, waveGlow);
    evaluateWave(Wave2_Data, Wave2_Param, Wave2_Color, camRelPos, rayDir, geomDist, uvOffset, waveGlow);
    evaluateWave(Wave3_Data, Wave3_Param, Wave3_Color, camRelPos, rayDir, geomDist, uvOffset, waveGlow);

    if (length(uvOffset) < 1e-5 && length(waveGlow) < 1e-5) {
        OutColor = src;
        return;
    }

    // Sample distorted scene with subtle chromatic aberration
    vec2 finalUv = clamp(TexCoord + uvOffset, 0.0, 1.0);
    float chroma = length(uvOffset) * 1.8;
    vec3 baseColor;
    baseColor.r = texture(Sampler0, clamp(finalUv + vec2(chroma, 0.0), 0.0, 1.0)).r;
    baseColor.g = texture(Sampler0, finalUv).g;
    baseColor.b = texture(Sampler0, clamp(finalUv - vec2(chroma, 0.0), 0.0, 1.0)).b;

    vec3 finalColor = baseColor + waveGlow;
    OutColor = vec4(clamp(finalColor, 0.0, 1.0), src.a);
}
