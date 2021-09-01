package com.cartoonishvillain.villainousfishai;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = VillainousFishAI.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TheBigFishEvent {

    private static boolean avoidsinking(@Nullable Entity entity) {
        if(entity != null && entity.isInWater() && entity.getDeltaMovement() != Vec3.ZERO)return true;
        else return false;
    }


    @SubscribeEvent
    public static void Fish(LivingEvent.LivingUpdateEvent event){
        EntityType<?> entityType = event.getEntityLiving().getType();
        if(event.getEntityLiving().tickCount == 2 && (entityType == EntityType.COD || entityType == EntityType.SALMON || entityType == EntityType.TROPICAL_FISH || entityType == EntityType.PUFFERFISH)){
            AbstractFish entity = (AbstractFish) event.getEntityLiving();

            if(VillainousFishAI.config.AVOIDDROPS.get()) {
                entity.goalSelector.addGoal(3, new FishAvoidGoal<ItemEntity>(entity, ItemEntity.class, 6.0f, VillainousFishAI.config.FISHITEMSPEED.get(), TheBigFishEvent::avoidsinking));
            }

            if(VillainousFishAI.config.AVOIDPROJECTILES.get()){
                entity.goalSelector.addGoal(3, new FishAvoidGoal<Projectile>(entity, Projectile.class, 6.0f, VillainousFishAI.config.FISHPROJECTILESPEED.get(), TheBigFishEvent::avoidsinking));}

            if(VillainousFishAI.config.AVOIDTNT.get()) {
                entity.goalSelector.addGoal(3, new FishAvoidGoal<PrimedTnt>(entity, PrimedTnt.class, 6.0f, VillainousFishAI.config.FISHTNTSPEED.get(), TheBigFishEvent::avoidsinking));
            }
            entity.goalSelector.addGoal(3, new FishAvoidGoal<ItemEntity>(entity, ItemEntity.class, 6.0f, 1.4d, TheBigFishEvent::avoidsinking));
            entity.goalSelector.addGoal(3, new FishAvoidGoal<Projectile>(entity, Projectile.class, 6.0f, 1.5D, TheBigFishEvent::avoidsinking));
            entity.goalSelector.addGoal(3, new FishAvoidGoal<PrimedTnt>(entity, PrimedTnt.class, 6.0f, 1.6D, TheBigFishEvent::avoidsinking));

        }
    }
}
