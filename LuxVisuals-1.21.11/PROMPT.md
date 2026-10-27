Ты портируешь Fabric-мод «LuxVisuals» (визуал, HUD, косметика; клиентский, Java 21) с Minecraft 1.21.4 на 1.21.11.
Проект: этот репозиторий (Gradle + Loom). Исходники получены декомпиляцией, поэтому они обфусцированы: классы мода называются вроде K5c460ed4a42f0797d6, а имена Minecraft даны в intermediary (class_310, method_1507, field_1724).

СДЕЛАНО: каркас проекта, build.gradle, fabric.mod.json под 1.21.11, tools/remap_sources.py, раскладка файлов по ярусам:
- Tier A (src/main/java): код без рендер-API и миксинов, должен собираться после ремапа и мелких правок.
- Tier B (src/main/java): миксины и мелочь. Миксины не зарегистрированы, кандидаты в tools/mixin_candidates.json.
- Tier C (src/main/java): зависит от рендер-хелперов, исключён из сборки через tools/tier_c_excludes.txt.
- port_queue/java: тяжёлый рендер (RenderSystem, GL, BufferBuilder, ShaderProgram, Framebuffer) и его зависимости.
- excluded_dead_code: мёртвый код (CatBoost, ротации), не трогать и не портировать.
Ничего не компилировалось, версии в gradle.properties надо проверить.

ТВОЯ РАБОТА, строго по порядку:
1. Ремап. Скачай yarn 1.21.4 v2 (tiny, intermediary → named) и запусти `python3 tools/remap_sources.py mappings.tiny src/main/java port_queue/java`. Затем `./gradlew migrateMappings --mappings 1.21.11+build.<последний>`. Версии loom, loader, fabric-api возьми с fabricmc.net/develop и впиши в gradle.properties.
2. Собери Tier A+B. Исправь дрейф API 1.21.4→1.21.11 по реальным исходникам и маппингам 1.21.11, не угадывай. Что проверить в первую очередь: переписанный рендер-пайплайн 1.21.5 (RenderSystem.setShader, ShaderProgram, BufferRenderer ушли; теперь RenderPipeline и RenderPass), компоненты данных и предметы 1.21.5, изменения HUD и DrawContext 1.21.6, события ввода (KeyInput, MouseInput, Click) и переписанный world render 1.21.9, изменения рендер-событий Fabric API, работа с Identifier.
3. Зарегистрируй миксины из tools/mixin_candidates.json в src/main/resources/luxvisuals.mixins.json только после проверки: цель и точка внедрения должны существовать в 1.21.11. Access widener перенеси из port_queue/original_configs/luxphantom.accesswidener с именами yarn и проверь каждую запись.
4. Сначала прочитай PORT_MAP.md (рендер-ядро портируется первым, порядок и список 108 рендер-файлов там). Портируй port_queue по порядку: 2D HUD и ClickGUI, затем рендер-хелперы, затем мировые эффекты (JumpEffect, Trails, Wings, Hit Waves, Target Esp, China Hat), затем шейдеры в assets/luxvisuals/shaders (перепиши под формат пайплайна 1.21.11). После каждого портированного хелпера убирай связанные строки из tools/tier_c_excludes.txt и добивайся сборки.
5. Встроенные раньше Sodium, IAS, Figura, mixinextras больше не вкладываются: сделай их необязательными зависимостями или убери функцию. Поддержку Figura убери, если она не нужна. nv-websocket-client и Discord RPC оставь, если используются.
6. Точки входа в fabric.mod.json (MiaWare, LuxPlus, LuxBridge): проверь, что классы собираются, иначе мод не загрузится.
7. Постепенно давай осмысленные имена классам K*, начиная с тех, которые трогаешь (подсказки: строки name = "..." и загружаемые текстуры).
Вехи: сборка проходит; клиент доходит до главного меню; каждый модуль включается без вылета; визуальная проверка каждого модуля. Коммить после каждой вехи. В конце напиши, что не удалось проверить.
Правила: не меняй поведение несвязанных функций, не порть excluded_dead_code, сохраняй авторство и лицензию. Если у функции нет аналога в 1.21.11, спроси меня.
