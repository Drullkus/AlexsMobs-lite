package com.github.alexthe666.alexsmobs.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AzaleaBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockBananaPeel extends BushBlock {
    public static final MapCodec<BlockBananaPeel> CODEC = MapCodec.unit(BlockBananaPeel::new);

    protected static final VoxelShape SHAPE_COLLISON = Block.box(0, 0, 0, 16.0D, 9.0D, 16.0D);
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);

    public BlockBananaPeel() {
        super(BlockBehaviour.Properties.of().dynamicShape().sound(SoundType.WET_GRASS).noCollission().requiresCorrectToolForDrops().strength(0.2F).friction(0.9999999999F));
    }

    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return canSupportRigidBlock(worldIn, pos);
    }

    public BlockBehaviour.OffsetType getOffsetType() {
        return BlockBehaviour.OffsetType.XZ;
    }

    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE_COLLISON;
    }

    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter reader, BlockPos pos) {
        return SHAPE_COLLISON;
    }

    public VoxelShape getVisualShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

}
