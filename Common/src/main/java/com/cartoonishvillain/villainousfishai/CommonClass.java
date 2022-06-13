package com.cartoonishvillain.villainousfishai;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class CommonClass {

    public static void init() {
    }

    public static boolean avoidsinking(@Nullable Entity entity) {
        return entity != null && entity.isInWater() && entity.getDeltaMovement() != Vec3.ZERO;
    }
}