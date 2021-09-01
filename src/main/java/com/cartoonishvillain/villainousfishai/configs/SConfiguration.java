package com.cartoonishvillain.villainousfishai.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SConfiguration {
        public static final String SCATEGORY_AITOGGLES = "AIToggles";
    public static final String SCATEGORY_AINUMBERS = "Speed Modifiers";

    public ConfigHelper.ConfigValueListener<Boolean> AVOIDDROPS;
    public ConfigHelper.ConfigValueListener<Boolean> AVOIDPROJECTILES;
    public ConfigHelper.ConfigValueListener<Boolean> AVOIDTNT;

    public ConfigHelper.ConfigValueListener<Double> FISHITEMSPEED;
    public ConfigHelper.ConfigValueListener<Double> FISHPROJECTILESPEED;
    public ConfigHelper.ConfigValueListener<Double> FISHTNTSPEED;


    public SConfiguration(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber){
        builder.comment("Area for toggles new next generation AI systems. - Changes to this config file will only apply on server reboot, or to fish that haven't been loaded yet!").push(SCATEGORY_AITOGGLES);
        this.AVOIDDROPS = subscriber.subscribe(builder.comment("Fish avoid items ").define("fishAvoidItems", true));
        this.AVOIDPROJECTILES = subscriber.subscribe(builder.comment("Fish avoid projectiles").define("fishAvoidProjectiles", true));
        this.AVOIDTNT = subscriber.subscribe(builder.comment("Fish avoid TNT").define("fishAvoidTNT", true));
        builder.pop();
        builder.comment("How fast fish swim away from the new stuff.").push(SCATEGORY_AINUMBERS);
        this.FISHITEMSPEED = subscriber.subscribe(builder.comment("Speed multiplier when fish swim away from items").defineInRange("fishAvoidItemMultiplier", 1.4, 0.5, 4.0));
        this.FISHPROJECTILESPEED = subscriber.subscribe(builder.comment("Speed multiplier when fish swim away from projectiles").defineInRange("fishAvoidProjectileMultiplier", 1.5, 0.5, 4.0));
        this.FISHTNTSPEED = subscriber.subscribe(builder.comment("Speed multiplier when fish swim away from TNT").defineInRange("fishAvoidTNTMultiplier", 1.8, 0.5, 4.0));
    }
}
