#version 150

in vec2 TexCoord;

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

uniform mat4 ViewProj;
uniform mat4 InvViewProj;
uniform vec3 CamPos;

uniform vec3 ScanOrigin;
uniform float ScanRadius;
uniform float ScanWidth;
uniform float ScanTrail;
uniform vec3 WaveColor;
uniform vec3 GridColor;
uniform vec3 TrailColor;
uniform float GridIntensity;
uniform float ContourIntensity;
uniform float ScanAlpha;
uniform float Time;

out vec4 OutColor;

vec3 worldFromDepth(vec2 uv, float depth) {
    vec4 clip = vec4(uv * 2.0 - 1.0, depth * 2.0 - 1.0, 1.0);
    vec4 pos = InvViewProj * clip;
    return pos.xyz / pos.w;
}

void main() {
    vec4 src = texture(Sampler0, TexCoord);
    float depth = texture(Sampler1, TexCoord).r;

    if (depth >= 1.0 || ScanAlpha <= 0.001) {
        OutColor = src;
        return;
    }

    vec3 camRelPos = worldFromDepth(TexCoord, depth);
    vec3 worldPos = CamPos + camRelPos;

    float dist = length(worldPos - ScanOrigin);
    float diff = ScanRadius - dist;

    // Discard fragments far outside the active scan wave band
    if (diff < -ScanWidth || diff > ScanTrail) {
        OutColor = src;
        return;
    }

    // 1. Leading Wavefront Edge (neon pulse ring)
    float edge = 0.0;
    if (abs(diff) <= ScanWidth) {
        edge = smoothstep(ScanWidth, 0.0, abs(diff));
        edge = pow(edge, 1.8);
    }

    // 2. Trailing scan field behind the wavefront
    float trail = 0.0;
    if (diff >= 0.0 && diff <= ScanTrail) {
        trail = 1.0 - (diff / ScanTrail);
        trail = pow(trail, 1.3);
    }

    // 3. Voxel Block Grid (detects edges of Minecraft blocks in world space)
    float grid = 0.0;
    if (GridIntensity > 0.01 && trail > 0.01) {
        vec3 b = abs(fract(worldPos) - 0.5);
        vec3 edgeDist = 0.5 - b;
        float minEdge = min(min(edgeDist.x, edgeDist.y), edgeDist.z);
        grid = smoothstep(0.045, 0.005, minEdge) * GridIntensity;
    }

    // 4. Elevation Contours & Sonar Pulse
    float contour = 0.0;
    if (ContourIntensity > 0.01 && trail > 0.01) {
        float yContour = smoothstep(0.06, 0.0, abs(fract(worldPos.y) - 0.5) - 0.44);
        float sonarPulse = 0.5 + 0.5 * sin(dist * 3.14159 * 1.5 - Time * 6.0);
        contour = (yContour * 0.7 + sonarPulse * 0.3) * ContourIntensity;
    }

    // 5. Digital Hex/Dot Radar Pattern
    float pattern = 0.0;
    if (trail > 0.01) {
        vec2 hex = fract(worldPos.xz * 2.0);
        float dots = smoothstep(0.18, 0.0, length(hex - 0.5));
        pattern = dots * 0.2;
    }

    // 6. Holographic Component Lighting with Customizable Colors
    vec3 waveHighlight = mix(WaveColor, vec3(1.0), edge * 0.65) * (edge * 2.4);
    vec3 gridHighlight = GridColor * (grid * 1.15);
    vec3 contourHighlight = mix(GridColor, TrailColor, 0.5) * (contour * 0.5);
    vec3 trailHighlight = TrailColor * (trail * 0.12 + pattern);

    vec3 scanLight = (waveHighlight + (gridHighlight + contourHighlight + trailHighlight) * trail) * ScanAlpha;

    vec3 finalColor = src.rgb + scanLight;

    OutColor = vec4(clamp(finalColor, 0.0, 1.0), src.a);
}
