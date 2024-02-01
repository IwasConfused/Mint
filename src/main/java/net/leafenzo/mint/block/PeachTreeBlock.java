package net.leafenzo.mint.block;

import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class PeachTreeBlock extends TwoTallCropBlock {
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    public PeachTreeBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape[] AGE_TO_SHAPE_UPPER = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 0.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 0.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 0.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0)
    };
    private static final VoxelShape[] AGE_TO_SHAPE_LOWER = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    };
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HALF) == DoubleBlockHalf.UPPER ? AGE_TO_SHAPE_UPPER[this.getAge(state)] : AGE_TO_SHAPE_LOWER[this.getAge(state)];
    }
    @Override
    public int getAgeToGrowUpperHalf() {
        return 3;
    }
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.PEACH_PIT;
    }
}
