package com.cartoonishvillain.villainousfishai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = VillainousFishAI.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TheBigFishEvent {

    private static boolean avoidsinking(@Nullable Entity entity) {
        if(entity != null && entity.isInWater() && entity.getDeltaMovement() != Vector3d.ZERO)return true;
        else return false;
    }


    @SubscribeEvent
    public static void Fish(LivingEvent.LivingUpdateEvent event){
        EntityType<?> entityType = event.getEntityLiving().getType();
        if(event.getEntityLiving().tickCount == 2 && (entityType == EntityType.COD || entityType == EntityType.SALMON || entityType == EntityType.TROPICAL_FISH || entityType == EntityType.PUFFERFISH)){
            AbstractFishEntity entity = (AbstractFishEntity) event.getEntityLiving();
            if(VillainousFishAI.config.AVOIDDROPS.get()) {
                entity.goalSelector.addGoal(3, new FishAvoidGoal<ItemEntity>(entity, ItemEntity.class, 6.0f, VillainousFishAI.config.FISHITEMSPEED.get(), TheBigFishEvent::avoidsinking));
            }

            if(VillainousFishAI.config.AVOIDPROJECTILES.get()){
            entity.goalSelector.addGoal(3, new FishAvoidGoal<ProjectileEntity>(entity, ProjectileEntity.class, 6.0f, VillainousFishAI.config.FISHPROJECTILESPEED.get(), TheBigFishEvent::avoidsinking));}

            if(VillainousFishAI.config.AVOIDTNT.get()) {
                entity.goalSelector.addGoal(3, new FishAvoidGoal<TNTEntity>(entity, TNTEntity.class, 6.0f, VillainousFishAI.config.FISHTNTSPEED.get(), TheBigFishEvent::avoidsinking));
            }

        }
    }
}
