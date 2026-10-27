package code.miaware.client.features.modules.render.targetesp;

import code.miaware.api.event.K8d7165024ff567f5e7;
import code.miaware.api.event.Ka68e0b23b1084c01e4;
import code.miaware.api.event.events.player.other.K584aa6c028ebeb26be;
import code.miaware.api.event.events.player.world.K59e91c1b6e54ef922d;
import code.miaware.api.event.events.render.Ke390ca39bb18b9e97e;
import code.miaware.api.module.Kab8a8c478f7d0077cd;
import code.miaware.api.module.Kf76f042931183af5ba;
import code.miaware.api.module.ModuleRegister;
import code.miaware.api.module.setting.K00fef91b3d4d1b3ecc;
import code.miaware.api.module.setting.Kb9e875ead00e0d03bd;
import code.miaware.api.module.setting.Kbd66519397e9a89967;
import code.miaware.api.module.setting.Kdced9e23f1e229ba9d;
import code.miaware.client.features.modules.render.targetesp.modes.K061cf4a0fb9013634e;
import code.miaware.client.features.modules.render.targetesp.modes.K68336bb745c58b1b21;
import code.miaware.client.features.modules.render.targetesp.modes.K7008defff6ec6a6379;
import code.miaware.client.features.modules.render.targetesp.modes.K81d8b18ef55e9e4171;
import code.miaware.client.features.modules.render.targetesp.modes.K8d77eef01276514f09;
import code.miaware.client.features.modules.render.targetesp.modes.K8e21e481d8cc09697d;
import code.miaware.client.features.modules.render.targetesp.modes.Kdd381d681f391b4ad7;
import lombok.Generated;

@ModuleRegister(
   name = "Target Esp",
   category = Kf76f042931183af5ba.RENDER,
   description = "Подсвечивает цель в бою специальным эффектом"
)
public class K34929def563f4a28cf extends Kab8a8c478f7d0077cd {
   private static final K34929def563f4a28cf instance = new K34929def563f4a28cf();
   private final K7008defff6ec6a6379 espComets = new K7008defff6ec6a6379();
   private final K061cf4a0fb9013634e espTexture = new K061cf4a0fb9013634e();
   private final Ke99984f8c7995a1b31 espCrystal = new Ke99984f8c7995a1b31();
   private final K8d77eef01276514f09 espPentagram = new K8d77eef01276514f09();
   private final K68336bb745c58b1b21 espRing = new K68336bb745c58b1b21();
   private final K8e21e481d8cc09697d espChain = new K8e21e481d8cc09697d();
   private final Kdd381d681f391b4ad7 espSkull = new Kdd381d681f391b4ad7();
   private final K81d8b18ef55e9e4171 espGarland = new K81d8b18ef55e9e4171();
   private K57a68ff1a2b51cf90d currentMode;
   public final Kbd66519397e9a89967 mode;
   public final K00fef91b3d4d1b3ecc playersOnly;
   public final Kbd66519397e9a89967 markerTexture;
   public final Kbd66519397e9a89967 markerAnimation;
   public final K00fef91b3d4d1b3ecc markerRedOnImpact;
   public final Kb9e875ead00e0d03bd cometSize;
   public final Kb9e875ead00e0d03bd rotationSpeed;
   public final Kb9e875ead00e0d03bd cometTrailLength;
   public final Kb9e875ead00e0d03bd cometTrailDensity;
   public final Kb9e875ead00e0d03bd ringSize;
   public final Kb9e875ead00e0d03bd ringSpeed;
   public final K00fef91b3d4d1b3ecc ringParticles;
   public final Kb9e875ead00e0d03bd ringParticleHeight;
   public final Kb9e875ead00e0d03bd ringParticleLife;
   public final K00fef91b3d4d1b3ecc ringRedOnImpact;
   public final Kb9e875ead00e0d03bd chainWidth;
   public final Kb9e875ead00e0d03bd chainSpeed;
   public final K00fef91b3d4d1b3ecc chainRedOnImpact;
   public final Kb9e875ead00e0d03bd pentagramSize;
   public final Kb9e875ead00e0d03bd pentagramSpeed;
   public final K00fef91b3d4d1b3ecc pentagramBloom;
   public final Kb9e875ead00e0d03bd pentagramBloomSize;
   public final Kb9e875ead00e0d03bd crystalSpeed;
   public final K00fef91b3d4d1b3ecc crystalBloom;
   public final Kb9e875ead00e0d03bd crystalBloomSize;
   public final K00fef91b3d4d1b3ecc crystalRedOnImpact;
   public final K00fef91b3d4d1b3ecc skullAnimation;
   public final Kbd66519397e9a89967 skullRotationType;
   public final Kb9e875ead00e0d03bd skullRotationSpeed;
   public final Kb9e875ead00e0d03bd garlandLightsCount;
   public final Kb9e875ead00e0d03bd garlandSpirals;
   public final Kb9e875ead00e0d03bd garlandSpeed;

   public K34929def563f4a28cf() {
      this.currentMode = this.espTexture;
      this.mode = (new Kbd66519397e9a89967("Mode")).value("Маркер").values("Маркер", "Кометы", "Кольцо", "Цепь", "Кристалл", "Пентаграмма", "Череп", "Гирлянда").onAction(() -> {
         this.currentMode = this.K68d71b21b47865615c();
         K57a68ff1a2b51cf90d.resetState();
      });
      this.playersOnly = (new K00fef91b3d4d1b3ecc("Только игроки")).value(false);
      this.markerTexture = (new Kbd66519397e9a89967("Marker Texture")).value("1").values("1", "2").setVisible(() -> this.mode.is("Маркер"));
      this.markerAnimation = (new Kbd66519397e9a89967("Marker Animation")).value("Статичный").values("Статичный", "Крутилка", "Прыжок", "Материя").setVisible(() -> this.mode.is("Маркер"));
      this.markerRedOnImpact = (new K00fef91b3d4d1b3ecc("Marker Red on Impact")).value(true).setVisible(() -> this.mode.is("Маркер"));
      this.cometSize = (new Kb9e875ead00e0d03bd("Comet Size")).value(0.25F).range(0.05F, 1.0F).step(0.01F).setVisible(() -> this.mode.is("Кометы"));
      this.rotationSpeed = (new Kb9e875ead00e0d03bd("Rotation Speed")).value(15.0F).range(1.0F, 50.0F).step(0.5F).setVisible(() -> this.mode.is("Кометы"));
      this.cometTrailLength = (new Kb9e875ead00e0d03bd("Длина следа")).value(21.0F).range(5.0F, 50.0F).step(1.0F).setVisible(() -> this.mode.is("Кометы"));
      this.cometTrailDensity = (new Kb9e875ead00e0d03bd("Плотность следа")).value(1.0F).range(1.0F, 5.0F).step(1.0F).setVisible(() -> this.mode.is("Кометы"));
      this.ringSize = (new Kb9e875ead00e0d03bd("Размер кольца")).value(1.0F).range(0.5F, 2.0F).step(0.05F).setVisible(() -> this.mode.is("Кольцо"));
      this.ringSpeed = (new Kb9e875ead00e0d03bd("Скорость кольца")).value(4.0F).range(0.5F, 12.0F).step(0.5F).setVisible(() -> this.mode.is("Кольцо"));
      this.ringParticles = (new K00fef91b3d4d1b3ecc("Частицы кольца")).value(true).setVisible(() -> this.mode.is("Кольцо"));
      this.ringParticleHeight = (new Kb9e875ead00e0d03bd("Высота частиц")).value(1.0F).range(0.1F, 1.0F).step(0.05F).setVisible(() -> this.mode.is("Кольцо") && (Boolean)this.ringParticles.getValue());
      this.ringParticleLife = (new Kb9e875ead00e0d03bd("Время жизни частиц")).value(1.0F).range(0.3F, 3.0F).step(0.1F).setVisible(() -> this.mode.is("Кольцо") && (Boolean)this.ringParticles.getValue());
      this.ringRedOnImpact = (new K00fef91b3d4d1b3ecc("Красный при ударе")).value(true).setVisible(() -> this.mode.is("Кольцо"));
      this.chainWidth = (new Kb9e875ead00e0d03bd("Chain Width")).value(1.0F).range(0.5F, 2.0F).step(0.1F).setVisible(() -> this.mode.is("Цепь"));
      this.chainSpeed = (new Kb9e875ead00e0d03bd("Chain Speed")).value(2.0F).range(0.5F, 10.0F).step(0.5F).setVisible(() -> this.mode.is("Цепь"));
      this.chainRedOnImpact = (new K00fef91b3d4d1b3ecc("Chain Red on Impact")).value(true).setVisible(() -> this.mode.is("Цепь"));
      this.pentagramSize = (new Kb9e875ead00e0d03bd("Pentagram Size")).value(0.5F).range(0.2F, 3.0F).step(0.1F).setVisible(() -> this.mode.is("Пентаграмма"));
      this.pentagramSpeed = (new Kb9e875ead00e0d03bd("Pentagram Speed")).value(3.0F).range(0.5F, 10.0F).step(0.5F).setVisible(() -> this.mode.is("Пентаграмма"));
      this.pentagramBloom = (new K00fef91b3d4d1b3ecc("Pentagram Bloom")).value(true).setVisible(() -> this.mode.is("Пентаграмма"));
      this.pentagramBloomSize = (new Kb9e875ead00e0d03bd("Pentagram Bloom Size")).value(0.3F).range(0.1F, 0.8F).step(0.1F).setVisible(() -> this.mode.is("Пентаграмма") && (Boolean)this.pentagramBloom.getValue());
      this.crystalSpeed = (new Kb9e875ead00e0d03bd("Crystal Speed")).value(6.0F).range(1.0F, 20.0F).step(0.5F).setVisible(() -> this.mode.is("Кристалл"));
      this.crystalBloom = (new K00fef91b3d4d1b3ecc("Crystal Bloom")).value(true).setVisible(() -> this.mode.is("Кристалл"));
      this.crystalBloomSize = (new Kb9e875ead00e0d03bd("Crystal Bloom Size")).value(1.0F).range(0.3F, 2.0F).step(0.1F).setVisible(() -> this.mode.is("Кристалл") && (Boolean)this.crystalBloom.getValue());
      this.crystalRedOnImpact = (new K00fef91b3d4d1b3ecc("Crystal Red on Impact")).value(true).setVisible(() -> this.mode.is("Кристалл"));
      this.skullAnimation = (new K00fef91b3d4d1b3ecc("Анимация черепа")).value(true).setVisible(() -> this.mode.is("Череп"));
      this.skullRotationType = (new Kbd66519397e9a89967("Тип вращения")).value("Качание").values("Качание", "Вращение").setVisible(() -> this.mode.is("Череп") && (Boolean)this.skullAnimation.getValue());
      this.skullRotationSpeed = (new Kb9e875ead00e0d03bd("Скорость вращения")).value(2.0F).range(0.5F, 10.0F).step(0.5F).setVisible(() -> this.mode.is("Череп") && (Boolean)this.skullAnimation.getValue());
      this.garlandLightsCount = (new Kb9e875ead00e0d03bd("Количество лампочек")).value(30.0F).range(10.0F, 50.0F).step(1.0F).setVisible(() -> this.mode.is("Гирлянда"));
      this.garlandSpirals = (new Kb9e875ead00e0d03bd("Количество витков")).value(3.0F).range(1.0F, 5.0F).step(1.0F).setVisible(() -> this.mode.is("Гирлянда"));
      this.garlandSpeed = (new Kb9e875ead00e0d03bd("Скорость")).value(1.0F).range(0.2F, 3.0F).step(0.1F).setVisible(() -> this.mode.is("Гирлянда"));
      this.addSettings(new Kdced9e23f1e229ba9d[]{this.mode, this.playersOnly, this.markerTexture, this.markerAnimation, this.markerRedOnImpact, this.cometSize, this.rotationSpeed, this.cometTrailLength, this.cometTrailDensity, this.ringSize, this.ringSpeed, this.ringParticles, this.ringParticleHeight, this.ringParticleLife, this.ringRedOnImpact, this.chainWidth, this.chainSpeed, this.chainRedOnImpact, this.pentagramSize, this.pentagramSpeed, this.pentagramBloom, this.pentagramBloomSize, this.crystalSpeed, this.crystalBloom, this.crystalBloomSize, this.crystalRedOnImpact, this.skullAnimation, this.skullRotationType, this.skullRotationSpeed, this.garlandLightsCount, this.garlandSpirals, this.garlandSpeed});
   }

   public void onEnable() {
      this.currentMode = this.K68d71b21b47865615c();
      K57a68ff1a2b51cf90d.resetState();
   }

   public void onDisable() {
      K57a68ff1a2b51cf90d.resetState();
   }

   public void onEvent() {
      K8d7165024ff567f5e7 render3DEvent = Ke390ca39bb18b9e97e.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         K57a68ff1a2b51cf90d.updatePositions();
         this.currentMode.onRender3D(event);
      }));
      K8d7165024ff567f5e7 updateEvent = K584aa6c028ebeb26be.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         this.currentMode.updateAnimation(150L, "None", 1.0F, 0.0F, 2.0F);
         this.currentMode.updateTarget();
         this.currentMode.onUpdate();
      }));
      K8d7165024ff567f5e7 attackEvent = K59e91c1b6e54ef922d.getInstance().subscribe(new Ka68e0b23b1084c01e4((event) -> {
         K57a68ff1a2b51cf90d patt0$temp = this.currentMode;
         if (patt0$temp instanceof K061cf4a0fb9013634e textureMode) {
            if (K061cf4a0fb9013634e.currentTarget != null && event.entity() == K061cf4a0fb9013634e.currentTarget) {
               textureMode.onPlayerAttackTarget();
            }
         }

      }));
      this.addEvents(new K8d7165024ff567f5e7[]{render3DEvent, updateEvent, attackEvent});
   }

   private K57a68ff1a2b51cf90d K68d71b21b47865615c() {
      Object var10000;
      switch ((String)this.getMode().getValue()) {
         case "Кометы" -> var10000 = this.espComets;
         case "Кольцо" -> var10000 = this.espRing;
         case "Цепь" -> var10000 = this.espChain;
         case "Кристалл" -> var10000 = this.espCrystal;
         case "Пентаграмма" -> var10000 = this.espPentagram;
         case "Череп" -> var10000 = this.espSkull;
         case "Гирлянда" -> var10000 = this.espGarland;
         default -> var10000 = this.espTexture;
      }

      return (K57a68ff1a2b51cf90d)var10000;
   }

   @Generated
   public static K34929def563f4a28cf getInstance() {
      return instance;
   }

   @Generated
   public Kbd66519397e9a89967 getMode() {
      return this.mode;
   }
}
