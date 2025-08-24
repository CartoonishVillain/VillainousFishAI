package com.cartoonishvillain.villainousfishai.mixin;

import com.cartoonishvillain.villainousfishai.CommonClass;
import com.cartoonishvillain.villainousfishai.FishAvoidGoal;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class FishAIMixin {

    @Inject(at = @At("HEAD"), method = "tick()V")
    private void fishtick(CallbackInfo info) {
        Mob livingEntity = ((Mob) (Object) this);
        if(livingEntity instanceof AbstractFish && !livingEntity.level().isClientSide && livingEntity.tickCount == 2){
            GoalSelector goalSelector = ((FishGoalAccessor) livingEntity).fishAIAccessGoalSelector();
            for(Goal goal : goalSelector.getAvailableGoals()) {
                if (goal instanceof AvoidEntityGoal) {
                    goalSelector.removeGoal(goal);
                }
            }
            goalSelector.addGoal(2, new FishAvoidGoal<Player>(livingEntity, Player.class, 6.0f, 7.5f, CommonClass::avoidSinkingPlayer));
            goalSelector.addGoal(3, new FishAvoidGoal<ItemEntity>(livingEntity, ItemEntity.class, 6.0f, 2f, CommonClass::avoidSinking));
            goalSelector.addGoal(3, new FishAvoidGoal<Projectile>(livingEntity, Projectile.class, 6.0f, 2.5f, CommonClass::avoidSinking));
            goalSelector.addGoal(3, new FishAvoidGoal<PrimedTnt>(livingEntity, PrimedTnt.class, 6.0f, 5f, CommonClass::avoidSinking));
        }

    }
}