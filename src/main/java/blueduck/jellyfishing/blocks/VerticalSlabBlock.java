package blueduck.jellyfishing.blocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Direction.AxisDirection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class VerticalSlabBlock extends Block implements IWaterLoggable {
    public static final EnumProperty<VerticalSlabBlock.VerticalSlabType> TYPE = EnumProperty.create("type", VerticalSlabBlock.VerticalSlabType.class);
    public static final BooleanProperty WATERLOGGED;

    public VerticalSlabBlock(Properties properties) {
        super(properties);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with(TYPE, VerticalSlabBlock.VerticalSlabType.NORTH)).with(WATERLOGGED, false));
    }

    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(new Property[]{TYPE, WATERLOGGED});
    }

    public boolean isTransparent(BlockState state) {
        return state.get(TYPE) != VerticalSlabBlock.VerticalSlabType.DOUBLE;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return ((VerticalSlabBlock.VerticalSlabType)state.get(TYPE)).shape;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        BlockState blockstate = context.getWorld().getBlockState(blockpos);
        return blockstate.getBlock() == this ? (BlockState)((BlockState)blockstate.with(TYPE, VerticalSlabBlock.VerticalSlabType.DOUBLE)).with(WATERLOGGED, false) : (BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, context.getWorld().getFluidState(blockpos).getFluid() == Fluids.WATER)).with(TYPE, VerticalSlabBlock.VerticalSlabType.fromDirection(this.getDirectionForPlacement(context)));
    }

    private Direction getDirectionForPlacement(BlockItemUseContext context) {
        Direction face = context.getFace();
        if (face.getAxis() != Axis.Y) {
            return face;
        } else {
            Vector3d difference = context.getHitVec().subtract(Vector3d.copy(context.getPos())).subtract(0.5D, 0.0D, 0.5D);
            return Direction.fromAngle(-Math.toDegrees(Math.atan2(difference.getX(), difference.getZ()))).getOpposite();
        }
    }

    public boolean isReplaceable(BlockState state, @Nonnull BlockItemUseContext context) {
        VerticalSlabBlock.VerticalSlabType slabtype = (VerticalSlabBlock.VerticalSlabType)state.get(TYPE);
        return slabtype != VerticalSlabBlock.VerticalSlabType.DOUBLE && context.getItem().getItem() == this.asItem() && context.replacingClickedOnBlock() && context.getFace() == slabtype.direction && this.getDirectionForPlacement(context) == slabtype.direction;
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
    }

    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        return state.get(TYPE) != VerticalSlabBlock.VerticalSlabType.DOUBLE;
    }

    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return state.get(TYPE) != VerticalSlabBlock.VerticalSlabType.DOUBLE;
    }

    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return state;
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return type == PathType.WATER && worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
    }

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }

    public static enum VerticalSlabType implements IStringSerializable {
        NORTH(Direction.NORTH),
        SOUTH(Direction.SOUTH),
        WEST(Direction.WEST),
        EAST(Direction.EAST),
        DOUBLE((Direction)null);

        private final String name;
        @Nullable
        public final Direction direction;
        public final VoxelShape shape;

        private VerticalSlabType(@Nullable Direction direction) {
            this.direction = direction;
            this.name = direction == null ? "double" : direction.getString();
            if (direction == null) {
                this.shape = VoxelShapes.fullCube();
            } else {
                boolean isNegativeAxis = direction.getAxisDirection() == AxisDirection.NEGATIVE;
                double min = isNegativeAxis ? 8.0D : 0.0D;
                double max = isNegativeAxis ? 16.0D : 8.0D;
                this.shape = direction.getAxis() == Axis.X ? Block.makeCuboidShape(min, 0.0D, 0.0D, max, 16.0D, 16.0D) : Block.makeCuboidShape(0.0D, 0.0D, min, 16.0D, 16.0D, max);
            }

        }

        public static VerticalSlabBlock.VerticalSlabType fromDirection(Direction direction) {
            VerticalSlabBlock.VerticalSlabType[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                VerticalSlabBlock.VerticalSlabType type = var1[var3];
                if (type.direction != null && direction == type.direction) {
                    return type;
                }
            }

            return null;
        }

        public String toString() {
            return this.name;
        }

        public String getString() {
            return this.name;
        }
    }
}