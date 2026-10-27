# Карта порта рендера (108 файлов)
Граф импортов у мода циклический (всё достижимо из MiaWare), поэтому «сколько файлов разблокирует» считать бессмысленно. Порядок задаёт инициализация.
## 1. Рендер-ядро (портировать первым)
Инициализируется в `MiaWare.postLoad()`: шейдеры (`K745964…loadAndSetupPrograms`), `Kecb75e…load`, `Kefb558…init`, `K2516fc…init`, `K3dbb0e…init`. Это обёртки над RenderSystem/шейдерами/Framebuffer. Перепиши их под RenderPipeline 1.21.11 и сохрани публичные сигнатуры, тогда остальные 100+ файлов правятся точечно.
## 2. Что используют 108 файлов
RenderSystem/GL: 95, BufferBuilder/Tesselator: 61, шейдеры: 21, Framebuffer: 13.
## 3. По папкам (файл — строки — что использует)

### code/miaware/client/features/modules/render (25)
- K1a5541f874ac8aca42.java — 766 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- Ke3847952eb862510db.java — 625 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K31b67d2e365a61bf94.java — 622 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K9b230e9e951a95fb0f.java — 410 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K1162659203460bc8c0.java — 320 стр. — BufferBuilder/Tesselator
- K06c5fe9af11c5525ae.java — 318 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K58d4d66f061a2a6fed.java — 302 стр. — RenderSystem/GL
- Ke4080118fb4a1a51fb.java — 292 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K7f7d5fe93b117b66e5.java — 268 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K79e0f6624e5ddb7ac3.java — 264 стр. — Framebuffer
- K45c34df60baf36141a.java — 263 стр. — RenderSystem/GL
- K8fbb97c2e28afe7ed7.java — 233 стр. — RenderSystem/GL
- Ke3e0017c49d95e97e2.java — 208 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- Kcc60133648b8ff2d03.java — 181 стр. — RenderSystem/GL
- K9826f34fa75d74c813.java — 146 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K4fe6907c39a36ee432.java — 143 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K4d69a1bf9db467dafb.java — 141 стр. — BufferBuilder/Tesselator
- K503fbacce527792274.java — 138 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K3c7165dabd6caf193e.java — 136 стр. — RenderSystem/GL
- K419138a37ac07d97bc.java — 135 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- Kec179db56117a0f1bd.java — 99 стр. — RenderSystem/GL
- Kc341cd7df53a24fc87.java — 98 стр. — RenderSystem/GL
- K051deaddf79ee0bf21.java — 70 стр. — BufferBuilder/Tesselator
- K96b2df1735cfd9ad42.java — 54 стр. — RenderSystem/GL, Шейдеры
- Kf7a85a9ade792fc05f.java — 51 стр. — BufferBuilder/Tesselator

### code/miaware/api/utils/render (9)
- Ke8433384857ddcab3d.java — 247 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Framebuffer
- K357718bb1c119935c3.java — 190 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K1f4d66c1a0cf52e6aa.java — 184 стр. — RenderSystem/GL, Framebuffer
- Kecb75e73598eb5799c.java — 127 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры, Framebuffer
- Kb7eb39acab6bd86adf.java — 117 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K5b5bf2e4f791eb67a2.java — 92 стр. — RenderSystem/GL
- K300f7955bbb2ec50a2.java — 64 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K92ffe79dd4f363a0ce.java — 47 стр. — RenderSystem/GL, Framebuffer
- Kddf5cf479d75866b66.java — 15 стр. — BufferBuilder/Tesselator

### code/miaware/client/features/modules/render/targetesp/modes (8)
- K8d77eef01276514f09.java — 439 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K68336bb745c58b1b21.java — 253 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K061cf4a0fb9013634e.java — 198 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K81d8b18ef55e9e4171.java — 143 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K7008defff6ec6a6379.java — 141 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- Kdd381d681f391b4ad7.java — 140 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K8e21e481d8cc09697d.java — 128 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- Kcb7b8bdbc7b44bb0ac.java — 102 стр. — RenderSystem/GL, BufferBuilder/Tesselator

### code/miaware/api/utils/render/display (8)
- K9fca83c9ab54ebe745.java — 330 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K4b843475c2462d48b1.java — 196 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры
- Kbab85e821f770b3523.java — 150 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры, Framebuffer
- K9a2b4acec2c36f99d1.java — 92 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K44d968e8d80cb06add.java — 76 стр. — RenderSystem/GL
- Kd372127b914786d2c2.java — 73 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры
- Ka9c28a2b9fee5a34d6.java — 67 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры
- Kcbe53000d5ac31f9fb.java — 62 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры

### code/miaware/inject/render (7)
- MixinGameRenderer.java — 179 стр. — RenderSystem/GL, Шейдеры
- MixinLivingEntityRendererPlayerShader.java — 135 стр. — Framebuffer
- MixinGameRendererHandBlur.java — 111 стр. — Шейдеры, Framebuffer
- MixinRenderPhaseHudTarget.java — 43 стр. — RenderSystem/GL
- MixinLivingEntityRendererFriend.java — 43 стр. — RenderSystem/GL
- MixinBuiltBufferCentroids.java — 28 стр. — BufferBuilder/Tesselator
- MixinRenderSystem.java — 23 стр. — RenderSystem/GL

### code/miaware/client/util/render/effects (5)
- K035b86a6e761d0d2cb.java — 230 стр. — RenderSystem/GL
- K210427f95fa84672e0.java — 158 стр. — RenderSystem/GL, Framebuffer
- K6588cb1ea6dceff65b.java — 148 стр. — RenderSystem/GL
- Kb74d5d2c9e20d74d0d.java — 75 стр. — RenderSystem/GL
- K2c4b0ab5cb7fa68cf2.java — 52 стр. — RenderSystem/GL

### code/miaware/client/ui/widget/overlay (5)
- Kcf5c3bed12d824555f.java — 326 стр. — RenderSystem/GL
- Kfbf316195041468d42.java — 320 стр. — RenderSystem/GL
- K26da897597ee71d7fd.java — 255 стр. — RenderSystem/GL
- Ke7bc31f4b136a6abd9.java — 252 стр. — RenderSystem/GL
- K49cedf6bb2f6b45927.java — 238 стр. — RenderSystem/GL

### code/miaware/client/features/modules/player (5)
- K6f46e31d3de2b9b245.java — 709 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- Kd36858e45faa4e69f0.java — 578 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K6d925cb38008efb1ff.java — 428 стр. — RenderSystem/GL
- K2050dbfe56d4d85122.java — 334 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K3772938c260ac1ad93.java — 152 стр. — RenderSystem/GL

### ru/phantom/mixin/minecraft/client/render (5)
- GameRendererMixin.java — 279 стр. — RenderSystem/GL, Шейдеры, ResourceManager
- EntityHitboxMixin.java — 224 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- ChatHudMixin.java — 114 стр. — RenderSystem/GL
- PlayerListHudMixin.java — 59 стр. — RenderSystem/GL
- WorldRendererMixin.java — 39 стр. — Шейдеры

### code/miaware/client/features/modules/movement (4)
- Ka09756d64144ec388c.java — 275 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K4dad1424dccce51a10.java — 151 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K0574a7e784c41d227d.java — 134 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- Kc0c84ddcf14906c88d.java — 88 стр. — RenderSystem/GL, BufferBuilder/Tesselator

### code/miaware/client/util/render (3)
- Kefb5586baca5cdbb77.java — 331 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры, Framebuffer
- K3dbb0e5dfbba4d83dc.java — 171 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры
- K2516fc881f143ca1ea.java — 139 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры, Framebuffer

### code/miaware/api/utils/render/fonts (3)
- K5fe84dad3db1af4bb7.java — 349 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры
- K57b57df54f7cd42184.java — 112 стр. — RenderSystem/GL
- K11ef91e33d46fe9b37.java — 94 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры

### code/miaware/client/ui/clickgui (2)
- ScreenIRCGui.java — 3540 стр. — RenderSystem/GL
- K64dfc1330d745b4d7f.java — 258 стр. — RenderSystem/GL

### code/miaware/api/utils/render/shader (2)
- Ka4ccb14c907d3c4f51.java — 63 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры
- K745964d54ca1b172b8.java — 55 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры

### code/miaware/api/utils/render/elytra (2)
- K54fd1c8282fb3dd387.java — 64 стр. — RenderSystem/GL
- K27418430766eb72d11.java — 50 стр. — BufferBuilder/Tesselator

### code/miaware/client/ui/theme (2)
- Kfa308fa591bea5a311.java — 127 стр. — RenderSystem/GL
- K3195adfcc0c0135ece.java — 60 стр. — Framebuffer

### code/miaware/client/features/modules/render/particles (2)
- Kbd989b7b534ee9cdce.java — 540 стр. — RenderSystem/GL, BufferBuilder/Tesselator
- K9757f4fdb908dd4583.java — 229 стр. — RenderSystem/GL, BufferBuilder/Tesselator

### code/miaware/client/ui/widget/target (1)
- K677fcc4f631147f8f0.java — 125 стр. — RenderSystem/GL, BufferBuilder/Tesselator

### code/miaware/client/ui/widget (1)
- Kdd908967ec41b41bcf.java — 83 стр. — RenderSystem/GL

### code/miaware/client/ui/journey (1)
- K1b44e53424f6810c1d.java — 90 стр. — RenderSystem/GL, BufferBuilder/Tesselator

### code/miaware/client/features/modules/render/killeffect (1)
- K540b1eed9037d7ce28.java — 80 стр. — RenderSystem/GL, BufferBuilder/Tesselator

### ru/phantom (1)
- Phantom.java — 922 стр. — RenderSystem/GL

### code/miaware/client/features/modules/player/invsave (1)
- K402a602b5f2f2b781f.java — 290 стр. — RenderSystem/GL, Шейдеры, Framebuffer

### code/miaware/client/features/modules/combat/hitbox (1)
- K622b5654eecec333de.java — 109 стр. — RenderSystem/GL, BufferBuilder/Tesselator, Шейдеры

### ru/phantom/mixin/minecraft/client/gui/screen (1)
- ChatScreenMixin.java — 136 стр. — RenderSystem/GL

### ru/phantom/mixin/minecraft/client/accessors (1)
- ShaderProgramAccessor.java — 13 стр. — Шейдеры

### ru/phantom/mixin/minecraft/client (1)
- SkyRenderingMixin.java — 72 стр. — RenderSystem/GL

### code/miaware/client/features/modules/render/targetesp (1)
- Ke99984f8c7995a1b31.java — 180 стр. — RenderSystem/GL, BufferBuilder/Tesselator

## 4. Самый тяжёлый одиночный файл
`ScreenIRCGui.java` (3540 строк, чат-экран) — выноси в отдельную задачу.
