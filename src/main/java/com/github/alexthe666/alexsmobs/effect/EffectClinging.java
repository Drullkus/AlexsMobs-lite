package com.github.alexthe666.alexsmobs.effect;

import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.block.state.BlockState;

public class EffectClinging extends MobEffect {

    public EffectClinging() {
        super(MobEffectCategory.BENEFICIAL, 0XBD4B4B);
    }

    private static BlockPos getPositionUnderneath(Entity e) {
        return AMBlockPos.fromCoords(e.getX(), e.getBoundingBox().maxY + 1.51F, e.getZ());
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.refreshDimensions();
        entity.setNoGravity(false);

        if (isUpsideDown(entity)) {
            entity.fallDistance = 0;
            if (!entity.isShiftKeyDown()) {
                if (!entity.horizontalCollision) {
                    entity.setDeltaMovement(entity.getDeltaMovement().add(0, 0.3F, 0));
                }
                entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.998F, 1F, 0.998F));
            }
        }
    }

    public static boolean isUpsideDown(LivingEntity entity){
        BlockPos pos = getPositionUnderneath(entity);
        BlockState ground = entity.level().getBlockState(pos);
        return (entity.verticalCollision || ground.isFaceSturdy(entity.level(), pos, Direction.DOWN)) && !entity.onGround();
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMapIn) {
        super.removeAttributeModifiers(attributeMapIn);
        // FIXME
        //entityLivingBaseIn.refreshDimensions();
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

    public String getDescriptionId() {
        return "alexsmobs.potion.clinging";
    }

}