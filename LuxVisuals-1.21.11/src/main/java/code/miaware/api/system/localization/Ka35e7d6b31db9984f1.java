package code.miaware.api.system.localization;

import code.miaware.api.system.backend.K93d0ef925a2fcdf0d6;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class Ka35e7d6b31db9984f1 {
   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
   private static final Path FILE_PATH;
   private static final Ka35e7d6b31db9984f1 instance;
   private final Map<String, String> enToRu = new HashMap();
   private final Map<String, String> ruToEn = new HashMap();
   private K17ee0c48cbe939a34b currentLanguage;
   private boolean featureTranslationsEnabled;

   private Ka35e7d6b31db9984f1() {
      this.currentLanguage = K17ee0c48cbe939a34b.RUSSIAN;
      this.featureTranslationsEnabled = true;
      this.K1cb954ef1168147007();
   }

   public void load() {
      try {
         Files.createDirectories(FILE_PATH.getParent());
         if (!Files.exists(FILE_PATH, new LinkOption[0])) {
            this.save();
            return;
         }

         JsonObject json = (JsonObject)GSON.fromJson(Files.readString(FILE_PATH), JsonObject.class);
         if (json != null && json.has("language")) {
            this.currentLanguage = K17ee0c48cbe939a34b.fromCode(json.get("language").getAsString());
         }

         if (json != null && json.has("featureTranslationsEnabled")) {
            this.featureTranslationsEnabled = json.get("featureTranslationsEnabled").getAsBoolean();
         }
      } catch (Exception var2) {
         this.currentLanguage = K17ee0c48cbe939a34b.RUSSIAN;
         this.featureTranslationsEnabled = false;
      }

   }

   public void save() {
      try {
         Files.createDirectories(FILE_PATH.getParent());
         JsonObject json = new JsonObject();
         json.addProperty("language", this.currentLanguage.getCode());
         json.addProperty("featureTranslationsEnabled", this.featureTranslationsEnabled);
         Files.writeString(FILE_PATH, GSON.toJson(json));
      } catch (IOException var2) {
      }

   }

   public void toggle() {
      this.currentLanguage = this.currentLanguage.toggle();
      this.save();
   }

   public boolean isRussian() {
      return this.currentLanguage == K17ee0c48cbe939a34b.RUSSIAN;
   }

   public boolean isFeatureTranslationsEnabled() {
      return this.featureTranslationsEnabled;
   }

   public String translate(String text) {
      if (text != null && !text.isEmpty()) {
         String var10000;
         switch (this.currentLanguage.ordinal()) {
            case 0 -> var10000 = (String)this.enToRu.getOrDefault(text, text);
            case 1 -> var10000 = (String)this.ruToEn.getOrDefault(text, text);
            default -> throw new MatchException((String)null, (Throwable)null);
         }

         return var10000;
      } else {
         return text;
      }
   }

   public String translateFeature(String text) {
      return this.featureTranslationsEnabled ? this.translate(text) : text;
   }

   public String formatModuleToggle(String moduleName, boolean enabled) {
      String var10000 = this.translate(moduleName);
      return var10000 + ": " + this.translate(enabled ? "enabled" : "disabled");
   }

   public void toggleFeatureTranslations() {
      this.featureTranslationsEnabled = !this.featureTranslationsEnabled;
      this.save();
   }

   private void add(String english, String russian) {
      this.enToRu.put(english, russian);
      this.ruToEn.put(russian, english);
   }

   private void K1cb954ef1168147007() {
      this.add("Language", "Язык");
      this.add("Russian", "Русский");
      this.add("English", "Английский");
      this.add("Search...", "Поиск...");
      this.add("Search", "Поиск");
      this.add("enabled", "включен");
      this.add("disabled", "выключен");
      this.add("Bind:", "Бинд:");
      this.add("Binding:", "Назначение:");
      this.add("Press a key...", "Нажмите клавишу...");
      this.add("Enter name...", "Введите имя...");
      this.add("Preview notification", "Превью уведомления");
      this.add("Enter nickname...", "Введите никнейм...");
      this.add("COMBAT", "БОЙ");
      this.add("MOVEMENT", "ДВИЖЕНИЕ");
      this.add("PLAYER", "ИГРОК");
      this.add("OTHER", "ДРУГОЕ");
      this.add("FUNCTION", "ФУНКЦИИ");
      this.add("MANAGER", "МЕНЕДЖЕР");
      this.add("USER", "ПОЛЬЗОВАТЕЛЬ");
      this.add("Widgets", "Виджеты");
      this.add("ArmorHit", "Удар по броне");
      this.add("KeyStrokes", "Нажатия клавиш");
      this.add("Potion", "Зелья");
      this.add("Coordinates", "Координаты");
      this.add("Armor", "Броня");
      this.add("InvView", "Просмотр инвентаря");
      this.add("KeyCaps", "Клавиши");
      this.add("Time", "Время");
      this.add("Always visible", "Всегда видимый");
      this.add("Success", "Успех");
      this.add("Info", "Инфо");
      this.add("Warning", "Предупреждение");
      this.add("Alert", "Ошибка");
      this.add("Widget settings", "Настройки виджета");
      this.add("Show FPS", "Показать FPS");
      this.add("Real client frame rate", "Реальный FPS клиента");
      this.add("Show TPS", "Показать TPS");
      this.add("Real server TPS sync", "Реальный TPS сервера");
      this.add("Show server", "Показать сервер");
      this.add("Server IP or singleplayer label", "IP сервера или одиночная игра");
      this.add("Spec request", "Запрос спека");
      this.add("Alert when chat asks for spec", "Оповещение когда в чате просят спек");
      this.add("Module state", "Состояние модулей");
      this.add("Show toggle notifications from modules", "Показывать уведомления о переключении модулей");
      this.add("Potion alerts", "Оповещения эффектов");
      this.add("Warn before important effects end", "Предупреждать перед окончанием важных эффектов");
      this.add("Low durability", "Низкая прочность");
      this.add("Warn when held items get low", "Предупреждать когда прочность предметов низкая");
      this.add("Target Info", "Инфо о цели");
      this.add("Dryn", "Друн");
      this.add("Radar", "Радар");
      this.add("HealthInfo", "Инфо о здоровье");
      this.add("Chat Widget", "Виджет чата");
      this.add("Notifications", "Уведомления");
      this.add("Keybinds", "Бинды");
      this.add("Cooldowns", "Перезарядки");
      this.add("ArrayList", "Список модулей");
      this.add("Watermark", "Водяной знак");
      this.add("FPS", "ФПС");
      this.add("BPS", "БПС");
      this.add("XYZ", "Координаты");
      this.add("Ping", "Пинг");
      this.add("Music Info", "Инфо о музыке");
      this.add("BossBar", "Полоса босса");
      this.add("Boss bar", "Босс бар");
      this.add("Cooldowns", "Перезарядки");
      this.add("Potions", "Эффекты");
      this.add("Hold to drag", "Удерживайте для перемещения");
      this.add("Release to place", "Отпустите для размещения");
      this.add("Right click to edit, hold to drag", "ПКМ для редактирования, удерживайте для перемещения");
      this.add("Right click to edit", "ПКМ для редактирования");
      this.add("Notifications", "Уведомления");
      this.add("Nothing playing", "Ничего не играет");
      this.add("Open chat preview", "Открыть предпросмотр чата");
      this.add("Waiting for media", "Ожидание медиа");
      this.add("Chat preview", "Предпросмотр чата");
      this.add("Theme", "Тема");
      this.add("Theme editor", "Редактор темы");
      this.add("Minimal HUD", "Минимальный HUD");
      this.add("Cinema", "Кинотеатр");
      this.add("Mode", "Режим");
      this.add("Color", "Цвет");
      this.add("Size", "Размер");
      this.add("Scale", "Масштаб");
      this.add("Range", "Дальность");
      this.add("Duration", "Длительность");
      this.add("Animation", "Анимация");
      this.add("Texture", "Текстура");
      this.add("Speed", "Скорость");
      this.add("Count", "Количество");
      this.add("Volume", "Громкость");
      this.add("GUI opening sound", "Звук открытия GUI");
      this.add("GUI hover sound", "Звук наведения");
      this.add("GUI click sound", "Звук кликов");
      this.add("Description typing sound", "Звук печати описания");
      this.add("Delay", "Задержка");
      this.add("Fill", "Заливка");
      this.add("Top Face", "Верхняя грань");
      this.add("Full block", "Полный блок");
      this.add("Material", "Материал");
      this.add("HighPolygonal", "Высокополигональный");
      this.add("Antialiasing", "Сглаживание");
      this.add("OnPlayers", "На игроках");
      this.add("OnFriends", "На друзьях");
      this.add("OnSelf", "На себе");
      this.add("ShowOnFirstPerson", "Показывать от первого лица");
      this.add("Self", "Себя");
      this.add("Players", "Игроки");
      this.add("Friends", "Друзья");
      this.add("Length", "Длина");
      this.add("In first person", "От первого лица");
      this.add("Physics", "Физика");
      this.add("Fade Time", "Время затухания");
      this.add("Last position", "Последняя позиция");
      this.add("In size", "Размер входа");
      this.add("Out size", "Размер выхода");
      this.add("Marker Texture", "Текстура маркера");
      this.add("Marker Red on Impact", "Красный маркер при ударе");
      this.add("Marker Impact Fade In", "Появление удара маркера");
      this.add("Marker Impact Fade Out", "Исчезновение удара маркера");
      this.add("Marker Impact Intensity", "Интенсивность удара маркера");
      this.add("Comet Size", "Размер кометы");
      this.add("Comet Width", "Ширина кометы");
      this.add("Comet Height", "Высота кометы");
      this.add("Comet Count", "Количество комет");
      this.add("Trail Length", "Длина следа");
      this.add("Trail Min Size", "Минимальный размер следа");
      this.add("Trail Fade Start", "Начало затухания следа");
      this.add("Orbit Radius", "Радиус орбиты");
      this.add("Orbit Y Offset", "Смещение орбиты по Y");
      this.add("Vertical Amplitude", "Вертикальная амплитуда");
      this.add("Vertical Speed", "Вертикальная скорость");
      this.add("Rotation Speed", "Скорость вращения");
      this.add("Reverse Rotation", "Обратное вращение");
      this.add("Symmetric Bounce", "Симметричный отскок");
      this.add("Red on Impact", "Красный при ударе");
      this.add("Impact Fade In", "Появление удара");
      this.add("Impact Fade Out", "Исчезновение удара");
      this.add("Impact Intensity", "Интенсивность удара");
      this.add("Pulse Effect", "Эффект пульсации");
      this.add("Pulse Speed", "Скорость пульсации");
      this.add("Pulse Intensity", "Интенсивность пульсации");
      this.add("Ghost Vertical Amplitude", "Вертикальная амплитуда призрака");
      this.add("Ghost Trail Length", "Длина следа призрака");
      this.add("Ghost Red on Impact", "Красный призрак при ударе");
      this.add("Ghost Impact Fade In", "Появление удара призрака");
      this.add("Ghost Impact Fade Out", "Исчезновение удара призрака");
      this.add("Circle Size", "Размер круга");
      this.add("Circle Speed", "Скорость круга");
      this.add("Circle Bloom", "Свечение круга");
      this.add("Circle Bloom Size", "Размер свечения круга");
      this.add("Circle Red on Impact", "Красный круг при ударе");
      this.add("Circle Impact Fade In", "Появление удара круга");
      this.add("Circle Impact Fade Out", "Исчезновение удара круга");
      this.add("Chain Width", "Ширина цепи");
      this.add("Chain Speed", "Скорость цепи");
      this.add("Chain Red on Impact", "Красная цепь при ударе");
      this.add("Chain Impact Fade In", "Появление удара цепи");
      this.add("Chain Impact Fade Out", "Исчезновение удара цепи");
      this.add("Crystal Speed", "Скорость кристалла");
      this.add("Crystal Bloom", "Свечение кристалла");
      this.add("Crystal Bloom Size", "Размер свечения кристалла");
      this.add("Crystal Red on Impact", "Красный кристалл при ударе");
      this.add("Crystal Impact Fade In", "Появление удара кристалла");
      this.add("Crystal Impact Fade Out", "Исчезновение удара кристалла");
      this.add("Screen preview", "Превью на экране");
      this.add("World preview", "Превью в мире");
      this.add("Dropped items", "Выброшенные предметы");
      this.add("Only with aura", "Только с аурой");
      this.add("Strength", "Сила");
      this.add("Slow", "Медленно");
      this.add("Out animation", "Анимация выхода");
      this.add("Life time", "Время жизни");
      this.add("Slow Speed", "Медленная скорость");
      this.add("Players mode", "Режим игроков");
      this.add("Animals mode", "Режим животных");
      this.add("Mobs mode", "Режим мобов");
      this.add("Friends mode", "Режим друзей");
      this.add("Items mode", "Режим предметов");
      this.add("Player color", "Цвет игрока");
      this.add("Radius", "Радиус");
      this.add("Motion", "Движение");
      this.add("Time", "Время");
      this.add("Weather", "Погода");
      this.add("Custom fog", "Пользовательский туман");
      this.add("Fog color", "Цвет тумана");
      this.add("Fog distance", "Дальность тумана");
      this.add("Fog density", "Плотность тумана");
      this.add("HUD Scale", "Масштаб HUD");
      this.add("Glass", "Стекло");
      this.add("Blur Passes", "Проходы размытия");
      this.add("Blur Offset", "Смещение размытия");
      this.add("Disable Blur", "Отключить Blur");
      this.add("Disable ShulkerViewer", "Отключить ShulkerViewer");
      this.add("Optimize modules", "Оптимизировать модули");
      this.add("Don't render mobs through walls", "Не рендерить мобов через стены");
      this.add("Don't render items through walls", "Не рендерить предметы через стены");
      this.add("GPU Optimization", "GPU Оптимизация");
      this.add("Batch Rendering", "Батчинг рендера");
      this.add("Frustum Culling", "Отсечение вне камеры");
      this.add("LOD System", "Система LOD");
      this.add("Reduce Particles", "Снизить частицы");
      this.add("Optimize Translucency", "Оптимизация прозрачности");
      this.add("Cache GL States", "Кэш GL состояний");
      this.add("Skip Hidden Renders", "Пропуск скрытых рендеров");
      this.add("Item entities", "Сущности предметов");
      this.add("Information", "Информация");
      this.add("Glassy", "Стеклянность");
      this.add("Text color", "Цвет текста");
      this.add("Ratio", "Соотношение");
      this.add("Custom ratio", "Пользовательское соотношение");
      this.add("Wind bind", "Бинд ветра");
      this.add("Exp bind", "Бинд опыта");
      this.add("Legit exp", "Легитный опыт");
      this.add("Exp pitch", "Наклон опыта");
      this.add("Actions", "Действия");
      this.add("Attack", "Атака");
      this.add("Use", "Использование");
      this.add("Attack delay", "Задержка атаки");
      this.add("Use delay", "Задержка использования");
      this.add("Sound", "Звук");
      this.add("Smooth", "Плавный");
      this.add("Celestial", "Целестиал");
      this.add("Nursultan", "Нурсултан");
      this.add("Akrien", "Акриен");
      this.add("Tech", "Техно");
      this.add("Blop", "Блоп");
      this.add("Only friends", "Только друзья");
      this.add("Name", "Имя");
      this.add("Hide friends", "Скрыть друзей");
      this.add("Self chat", "Свой чат");
      this.add("Send friends", "Отправить друзьям");
      this.add("Friend delay", "Задержка друзей");
      this.add("Launch key", "Клавиша запуска");
      this.add("Throw key", "Клавиша броска");
      this.add("Default", "По умолчанию");
      this.add("Remove", "Убрать");
      this.add("Fire overlay", "Наложение огня");
      this.add("Hurt camera", "Камера урона");
      this.add("Water overlay", "Наложение воды");
      this.add("Scoreboard", "Таблица счета");
      this.add("Glow effect", "Эффект свечения");
      this.add("Bad effects", "Плохие эффекты");
      this.add("Boss bar", "Полоса босса");
      this.add("Clear lava", "Убрать лаву");
      this.add("Ender pearl", "Эндер жемчуг");
      this.add("Trident", "Трезубец");
      this.add("Arrow", "Стрела");
      this.add("Splash potion", "Взрывное зелье");
      this.add("Held items", "Предметы в руках");
      this.add("Through walls", "Сквозь стены");
      this.add("Friendly indicator", "Индикатор друзей");
      this.add("Hit highlight", "Подсветка попадания");
      this.add("Particle Mode", "Режим партиклов");
      this.add("Particle Size", "Размер партиклов");
      this.add("Particle Density", "Плотность партиклов");
      this.add("Mixed", "Смешанный");
      this.add("Star", "Звезда");
      this.add("Heart", "Сердце");
      this.add("Snowflake", "Снежинка");
      this.add("Dollar", "Доллар");
      this.add("Spark", "Искра");
      this.add("Ball", "Шар");
      this.add("Glow", "Свечение");
      this.add("Items", "Предметы");
      this.add("Potions", "Зелья");
      this.add("Options", "Опции");
      this.add("Special items", "Особые предметы");
      this.add("Enchants", "Зачарования");
      this.add("Only hands", "Только руки");
      this.add("Hands parser", "Парсер рук");
      this.add("Friend color", "Цвет друга");
      this.add("Targets", "Цели");
      this.add("Animals", "Животные");
      this.add("Mobs", "Мобы");
      this.add("Hides", "Скрытие");
      this.add("Nickname", "Никнейм");
      this.add("Spawn", "Спавн");
      this.add("World", "Мир");
      this.add("Hit", "Удар");
      this.add("Cube", "Куб");
      this.add("Distance", "Дистанция");
      this.add("Height", "Высота");
      this.add("Rise Speed", "Скорость подъема");
      this.add("Fly", "Полет");
      this.add("Drop", "Падение");
      this.add("Client", "Клиент");
      this.add("Custom", "Пользовательский");
      this.add("In", "Вход");
      this.add("Out", "Выход");
      this.add("Rainbow", "Радуга");
      this.add("Single", "Одиночный");
      this.add("1", "1");
      this.add("2", "2");
      this.add("3", "3");
      this.add("4", "4");
      this.add("16:9", "16:9");
      this.add("16:10", "16:10");
      this.add("21:9", "21:9");
      this.add("4:3", "4:3");
      this.add("Bamboo", "Бамбук");
      this.add("Carpet", "Ковер");
      this.add("Metal", "Металл");
      this.add("Turbine", "Турбина");
      this.add("Illusion", "Иллюзия");
      this.add("Magma", "Магма");
      this.add("Dirty", "Грязный");
      this.add("Foil", "Фольга");
      this.add("SUNNY", "Солнечно");
      this.add("RAIN", "Дождь");
      this.add("THUNDER", "Гроза");
      this.add("No change", "Без изменений");
      this.add("Dawn", "Рассвет");
      this.add("Day", "День");
      this.add("Noon", "Полдень");
      this.add("Dusk", "Сумерки");
      this.add("Night", "Ночь");
      this.add("Mid Night", "Полночь");
      this.add("Sunny", "Солнечно");
      this.add("Rainy", "Дождь");
      this.add("Snowy", "Снег");
      this.add("Thunder", "Гроза");
      this.add("Spawn duration", "Длительность появления");
      this.add("Dying duration", "Длительность исчезновения");
      this.add("Lean", "Наклон");
      this.add("Glow", "Свечение");
      this.add("Pentagram", "Пентаграмма");
      this.add("Cubes", "Кубы");
      this.add("Rotate", "Вращение");
      this.add("Trail", "След");
      this.add("Trail length", "Длина следа");
      this.add("Dying effect", "Эффект исчезновения");
      this.add("Spark", "Искра");
      this.add("Star", "Звезда");
      this.add("Heart", "Сердце");
      this.add("Dollar", "Доллар");
      this.add("Snowflake", "Снежинка");
      this.add("Ball", "Шар");
      this.add("World: Texture", "Мир: Текстура");
      this.add("World: Count", "Мир: Количество");
      this.add("World: Size", "Мир: Размер");
      this.add("World: Life time", "Мир: Время жизни");
      this.add("World: Spawn duration", "Мир: Длительность появления");
      this.add("World: Dying duration", "Мир: Длительность исчезновения");
      this.add("World: Rotate", "Мир: Вращение");
      this.add("World: Trail", "Мир: След");
      this.add("World: Trail length", "Мир: Длина следа");
      this.add("World: Dying effect", "Мир: Эффект исчезновения");
      this.add("World: Distance", "Мир: Дистанция");
      this.add("World: Height", "Мир: Высота");
      this.add("World: Gravity", "Мир: Гравитация");
      this.add("Hit: Texture", "Удар: Текстура");
      this.add("Hit: Count", "Удар: Количество");
      this.add("Hit: Size", "Удар: Размер");
      this.add("Hit: Life time", "Удар: Время жизни");
      this.add("Hit: Spawn duration", "Удар: Длительность появления");
      this.add("Hit: Dying duration", "Удар: Длительность исчезновения");
      this.add("Hit: Rotate", "Удар: Вращение");
      this.add("Hit: Trail", "Удар: След");
      this.add("Hit: Trail length", "Удар: Длина следа");
      this.add("Hit: Dying effect", "Удар: Эффект исчезновения");
      this.add("Hit: Physics", "Удар: Физика");
      this.add("Hit: Motion", "Удар: Движение");
      this.add("Cubes: Texture", "Кубы: Текстура");
      this.add("Cubes: Count", "Кубы: Количество");
      this.add("Cubes: Size", "Кубы: Размер");
      this.add("Cubes: Life time", "Кубы: Время жизни");
      this.add("Cubes: Spawn duration", "Кубы: Длительность появления");
      this.add("Cubes: Dying duration", "Кубы: Длительность исчезновения");
      this.add("Cubes: Rotate", "Кубы: Вращение");
      this.add("Cubes: Trail", "Кубы: След");
      this.add("Cubes: Trail length", "Кубы: Длина следа");
      this.add("Cubes: Dying effect", "Кубы: Эффект исчезновения");
      this.add("Cubes: Distance", "Кубы: Дистанция");
      this.add("Cubes: Rise Speed", "Кубы: Скорость подъема");
      this.add("Cubes: Rotate Speed", "Кубы: Скорость вращения");
      this.add("Cubes: Glow", "Кубы: Свечение");
      this.add("Cubes: Glow Size", "Кубы: Размер свечения");
      this.add("Gravity", "Гравитация");
      this.add("Rotate Speed", "Скорость вращения");
      this.add("Glow Size", "Размер свечения");
      this.add("Mode 1", "Режим 1");
      this.add("Mode 2", "Режим 2");
      this.add("Mode 3", "Режим 3");
      this.add("Mode 4", "Режим 4");
      this.add("Mode 5", "Режим 5");
      this.add("Marker", "Маркер");
      this.add("Comets", "Кометы");
      this.add("Ghost", "Призрак");
      this.add("Circle", "Круг");
      this.add("Chain", "Цепь");
      this.add("Crystal", "Кристалл");
      this.add("Skull", "Череп");
      this.add("Ghost Size", "Размер призрака");
      this.add("Ghost Count", "Количество призраков");
      this.add("Ghost Orbit Radius", "Радиус орбиты призрака");
      this.add("Ghost Impact Intensity", "Интенсивность удара призрака");
      this.add("Circle Impact Intensity", "Интенсивность удара круга");
      this.add("Chain Impact Intensity", "Интенсивность удара цепи");
      this.add("Crystal Impact Intensity", "Интенсивность удара кристалла");
      this.add("Friend bind", "Бинд друга");
      this.add("Pearl bind", "Бинд жемчуга");
      this.add("Alpha", "Прозрачность");
      this.add("Brightness", "Яркость");
      this.add("Outline Width", "Толщина контура");
      this.add("Animals color", "Цвет животных");
      this.add("Items color", "Цвет предметов");
      this.add("Mobs color", "Цвет мобов");
      this.add("Friends color", "Цвет друзей");
      this.add("ClickGUI Scale", "Масштаб ClickGUI");
      this.add("Swap key", "Клавиша смены");
      this.add("Bind", "Бинд");
      this.add("key", "клавиша");
      this.add("ScrollUp", "Скролл вверх");
      this.add("ScrollDown", "Скролл вниз");
      this.add("Main Hand X", "Основная рука X");
      this.add("Main Hand Y", "Основная рука Y");
      this.add("Main Hand Z", "Основная рука Z");
      this.add("Off Hand X", "Второстепенная рука X");
      this.add("Off Hand Y", "Второстепенная рука Y");
      this.add("Off Hand Z", "Второстепенная рука Z");
      this.add("Reset position", "Сброс позиции");
      this.add("FTHelper", "FT Помощник");
      this.add("Use by bind", "Использование по бинду");
      this.add("Fire Charge Key", "Кнопка взрывной штучки");
      this.add("Stan Key", "Кнопка стана");
      this.add("Trap Key", "Кнопка взрывной трапки");
      this.add("HWHelper", "HW Помощник");
      this.add("Авто перемещение в хотбар", "Авто перемещение в хотбар");
      this.add("Взрывная штучка", "Взрывная штучка");
      this.add("Стан", "Стан");
      this.add("Взрывная трапка", "Взрывная трапка");
   }

   @Generated
   public Map<String, String> getEnToRu() {
      return this.enToRu;
   }

   @Generated
   public Map<String, String> getRuToEn() {
      return this.ruToEn;
   }

   @Generated
   public K17ee0c48cbe939a34b getCurrentLanguage() {
      return this.currentLanguage;
   }

   @Generated
   public static Ka35e7d6b31db9984f1 getInstance() {
      return instance;
   }

   static {
      FILE_PATH = Path.of(K93d0ef925a2fcdf0d6.CONFIG_PATH_OTHER, "language.json");
      instance = new Ka35e7d6b31db9984f1();
   }
}
