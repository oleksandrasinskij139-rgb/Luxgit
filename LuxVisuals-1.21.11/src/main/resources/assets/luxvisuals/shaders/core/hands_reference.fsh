#version 150
// Порт HandShader из k3d ddl (1.21.11 → 1.21.4): UBO заменён обычными uniform-ами.
uniform vec4 uScreen;       // w, h, time, mode
uniform vec4 uTintColor;
uniform vec4 uMirror;       // mix, distortion, blur, brightness
uniform vec4 uLighting;     // glow, blur, innerGlow, outlineWidth
uniform vec4 uHistory;      // trail, decay, rise, wobble
uniform vec4 uMotion;       // turbulence, flicker, dt, trailMode
uniform vec4 uShader;       // speed, scale, glow, quality
uniform vec4 uShader2;      // opacity, stars, preserveShadows, secondColor
uniform vec4 uSecondTint;
uniform vec4 uCameraMotion; // dx, dy, cameraTrail
uniform sampler2D Sampler0; // after (с руками)
uniform sampler2D Sampler1; // before (без рук)
uniform sampler2D Sampler2; // history
in vec2 texCoord;
out vec4 fragColor;
const float PI=3.14159265359;
vec2 safeUV(vec2 uv){return clamp(uv,.5/uScreen.xy,1.-.5/uScreen.xy);}
float trailAt(vec2 uv){
 if(any(lessThan(uv,vec2(0.)))||any(greaterThan(uv,vec2(1.))))return 0.;
 return texture(Sampler2,clamp(uv,1./uScreen.xy,1.-1./uScreen.xy)).r;
}
float handMask(vec2 uv){
 if(any(lessThan(uv,vec2(0.)))||any(greaterThan(uv,vec2(1.))))return 0.;
 vec3 d=abs(texture(Sampler0,safeUV(uv)).rgb-texture(Sampler1,safeUV(uv)).rgb);
 return smoothstep(.006,.022,max(d.r,max(d.g,d.b)));
}
float hash(vec2 p){return fract(sin(dot(p,vec2(127.1,311.7)))*43758.5453);}
float noise(vec2 p){
 vec2 i=floor(p),f=fract(p);f=f*f*(3.-2.*f);
 return mix(mix(hash(i),hash(i+vec2(1,0)),f.x),mix(hash(i+vec2(0,1)),hash(i+1.),f.x),f.y);
}
float smoke(vec2 p){
 float value=0.,weight=.55;
 for(int i=0;i<5;i++){
  if(float(i)>uShader.w+1.)break;
  value+=noise(p)*weight;p=p*2.03+vec2(1.7,3.1);weight*=.5;
 }
 return value;
}
void main(){
 vec2 uv=texCoord,px=1./uScreen.xy;float time=uScreen.z,mode=uScreen.w;
 float mask=handMask(uv);
 if(mode<-.5){
  float dt=uMotion.z;
  vec2 drift=vec2(sin(uv.y*10.+time*1.8)*uHistory.w*.04,uHistory.z*.1)*dt;
  drift+=vec2(sin(uv.y*20.+time*2.),cos(uv.x*17.-time*1.4))*uMotion.x*dt;
  drift+=uCameraMotion.xy*uCameraMotion.z;
  float previous=trailAt(uv-drift);
  float decay=pow(max(.01,1.-uHistory.y),dt*60.);
  float value=uHistory.x>.5?max(mask,previous*decay):mask;
  fragColor=vec4(value,value,value,1.);return;
 }
 vec3 original=texture(Sampler0,safeUV(uv)).rgb;
 vec3 rgb=original;
 float dilated=mask,eroded=mask;
 float outlineRadius=max(.5,uLighting.w*2.);
#if 0
 if(uLighting.w>=.001 || uLighting.z*uLighting.x!=0.){
#endif
 for(int i=0;i<8;i++){
  float a=float(i)*PI*.25;vec2 delta=vec2(cos(a),sin(a))*px*outlineRadius;
  float edge=handMask(uv+delta);dilated=max(dilated,edge);eroded=min(eroded,edge);
 }
#if 0
 }
#endif
 float outline=(dilated-eroded)*step(.001,uLighting.w);
 float bloom=0.;float blurRadius=max(1.,uLighting.y*8.);
#if 0
 if(uLighting.x!=0.){
#endif
 for(int i=0;i<16;i++){
  float a=float(i)*PI*.125;vec2 dir=vec2(cos(a),sin(a));
  bloom+=trailAt(uv+dir*px*blurRadius*.45)*.035;
  bloom+=trailAt(uv+dir*px*blurRadius)*.021;
 }
 #if 0
 }
 #endif
 float plasma=0.,veins=0.;
 #if 0
 if((mode>1.5&&mode<2.5)||mode>3.5||(uHistory.x!=0.&&uMotion.w>.5)){
 #endif
 plasma=smoke(uv*vec2(uScreen.x/uScreen.y,1.)*uShader.y*8.+vec2(time*uShader.x*.15,-time*uShader.x*.2));
 veins=pow(.5+.5*sin(plasma*22.+uv.y*7.-time*uShader.x),5.);
 #if 0
 }
 #endif
 vec3 secondary=uShader2.w>.5?uSecondTint.rgb:mix(uTintColor.rgb,vec3(1.),.75);
 vec3 shaderRGB=mix(uTintColor.rgb*.2,secondary,plasma*.65+veins*.55)*uShader.z;
 if(uShader2.y>.5){
  vec2 grid=uv*uScreen.xy/28.;vec2 id=floor(grid),cell=fract(grid)-.5;
  float star=step(.95,hash(id))*exp(-dot(cell,cell)*220.)*(.5+.5*sin(time*2.+hash(id)*20.));
  shaderRGB+=star*2.;
 }
 if(uShader2.z>.5)shaderRGB*=.35+.65*dot(original,vec3(.299,.587,.114));
 vec3 reflected=vec3(0.);
 #if 0
 if(mode>2.5){
 #endif
 vec2 mirrorUV=vec2(1.-uv.x,uv.y);
 mirrorUV+=vec2(sin(uv.y*16.+time),cos(uv.x*18.-time))*.03*uMirror.y;
 reflected=texture(Sampler1,safeUV(mirrorUV)).rgb*.4;
 for(int i=0;i<4;i++){
  vec2 d=vec2(i==0?1:i==1?-1:0,i==2?1:i==3?-1:0)*px*uMirror.z*3.;
  reflected+=texture(Sampler1,safeUV(mirrorUV+d)).rgb*.15;
 }
 #if 0
 }
 #endif
 vec3 surface=original;
 if(mode>.5&&mode<1.5) surface=uTintColor.rgb*uMirror.w;
 if(mode>1.5&&mode<2.5) surface=shaderRGB*uMirror.w;
 if(mode>2.5&&mode<3.5) surface=mix(original,reflected,uMirror.x)*uMirror.w;
 if(mode>3.5) surface=mix(shaderRGB,reflected,uMirror.x)*uMirror.w;
 float fillOpacity=mode==1.?1.:uShader2.x;
 if(mode>.5)rgb=mix(rgb,surface,mask*fillOpacity);
 // Outline and inner glow follow the real silhouette, even in Nothing mode.
 vec3 pale=mix(uTintColor.rgb,vec3(1.),.25);
 float edgeLight=outline*uMirror.w*.75;
 float inner=(mask-eroded)*uLighting.z;
 edgeLight+=inner*uLighting.x*.4;
 rgb=mix(rgb,pale,1.-exp(-max(0.,edgeLight)));
 float trail=max(0.,trailAt(uv)-mask)*uHistory.x;
 if(uMotion.w>.5&&uMotion.w<1.5)trail*=.45+veins*.8;
 if(uMotion.w>1.5)trail*=.3+.7*pow(.5+.5*sin(uv.y*75.+plasma*13.-time*4.),3.);
 float pulse=1.-uMotion.y*.5+sin(time*5.+uv.y*4.)*uMotion.y*.5;
 // Bloom belongs outside the hands. Adding it to opaque skin bleaches the
 // entire hand when a dense trail crosses it or brightness is increased.
 float outerGlow=(trail*.32+bloom*(1.-mask)*uLighting.x*.7)*uMirror.w*pulse;
 rgb=mix(rgb,pale,1.-exp(-max(0.,outerGlow)));
 fragColor=vec4(rgb,1.);
}
