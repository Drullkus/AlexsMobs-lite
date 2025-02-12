package com.github.alexthe666.alexsmobs.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;

import java.util.List;

public class ItemModFishBucket extends MobBucketItem {

    public ItemModFishBucket(Holder<? extends EntityType<?>> fishTypeIn, Fluid fluid, Item.Properties builder) {
        super(fishTypeIn.value(), fluid, SoundEvents.BUCKET_EMPTY_FISH, builder.stacksTo(1));
    }

    //@OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        EntityType fishType = getFishType();
//        if (fishType == AMEntityRegistry.LOBSTER.get()) {
//            CompoundTag compoundnbt = stack.getTag();
//            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
//                int i = compoundnbt.getInt("BucketVariantTag");
//                String s = "entity.alexsmobs.lobster.variant_" + EntityLobster.getVariantName(i);
//                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
//            }
//        }
//        if (fishType == AMEntityRegistry.TERRAPIN.get()) {
//            CompoundTag compoundnbt = stack.getTag();
//            if (compoundnbt != null && compoundnbt.contains("TerrapinData")) {
//                int i = compoundnbt.getCompound("TerrapinData").getInt("TurtleType");
//                tooltip.add((Component.translatable(TerrapinTypes.values()[Mth.clamp(i, 0, TerrapinTypes.values().length - 1)].getTranslationName())).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
//            }
//        }
//        if (fishType == AMEntityRegistry.COMB_JELLY.get()) {
//            CompoundTag compoundnbt = stack.getTag();
//            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
//                int i = compoundnbt.getInt("BucketVariantTag");
//                String s = "entity.alexsmobs.comb_jelly.variant_" + i;
//                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
//            }
//        }
    }

    private EntityType getFishType() {
        return this.type;
    }

    @Override
    public void checkExtraContent(Player player, Level level, ItemStack stack, BlockPos pos) {
        if (level instanceof ServerLevel) {
            this.spawnFish((ServerLevel)level, stack, pos);
            level.gameEvent(player, GameEvent.ENTITY_PLACE, pos);
        }
    }

    private void spawnFish(ServerLevel serverLevel, ItemStack stack, BlockPos pos) {
        Entity entity = getFishType().spawn(serverLevel, stack, (Player)null, pos, MobSpawnType.BUCKET, true, false);
        if (entity instanceof Bucketable) {
            Bucketable bucketable = (Bucketable)entity;
            bucketable.loadFromBucketTag(stack.getOrCreateTag());
            bucketable.setFromBucket(true);
        }
        addExtraAttributes(entity, stack);
    }

    private void addExtraAttributes(Entity entity, ItemStack stack) {
        //if(entity instanceof EntityCatfish catfish){
        //    if(stack.is(AMItemRegistry.SMALL_CATFISH_BUCKET.value())){
        //        catfish.setCatfishSize(0);
        //    }else if(stack.is(AMItemRegistry.MEDIUM_CATFISH_BUCKET.value())){
        //        catfish.setCatfishSize(1);
        //    }else if(stack.is(AMItemRegistry.LARGE_CATFISH_BUCKET.value())){
        //        catfish.setCatfishSize(2);
        //    }
        //}
    }
}
