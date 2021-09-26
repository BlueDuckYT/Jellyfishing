//import net.minecraft.block.BlockState;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.ISidedInventory;
//import net.minecraft.inventory.ItemStackHelper;
//import net.minecraft.inventory.container.Container;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.tileentity.ITickableTileEntity;
//import net.minecraft.tileentity.LockableTileEntity;
//import net.minecraft.util.Direction;
//import net.minecraft.util.IIntArray;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TranslationTextComponent;
//
//public class JellyMachineTE extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {
//
//    private static final int[] SLOTS_FOR_UP = new int[]{0, 2};
//    private static final int[] SLOTS_HORIZONTAL = new int[]{1};
//    private static final int[] SLOTS_FOR_DOWN = new int[]{3};
//    private InfusionTableRecipe currentRecipe;
//    private int infusingTime;
//    private int infusingTimeTotal = 200;
//    protected final IIntArray timeArray = new IIntArray() {
//        public int get(int index) {
//            switch(index) {
//                case 0:
//                    return JellyMachineTE.this.infusingTime;
//                case 1:
//                    return JellyMachineTE.this.infusingTimeTotal;
//                default:
//                    return 0;
//            }
//        }
//
//        public void set(int index, int value) {
//            switch(index) {
//                case 0:
//                    JellyMachineTE.this.infusingTime = value;
//                    break;
//                case 1:
//                    JellyMachineTE.this.infusingTimeTotal = value;
//            }
//
//        }
//
//        public int size() {
//            return 2;
//        }
//    };
//    protected NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
//
//    public JellyMachineTE() {
//        super(RegisterHandler.INFUSER_TILE_ENTITY.get());
//    }
//
//    @Override
//    public void read(BlockState state, CompoundNBT compound) {
//        super.read(state, compound);
//        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
//        ItemStackHelper.loadAllItems(compound, this.items);
//        this.infusingTime = compound.getInt("InfusingTime");
//        this.infusingTimeTotal = compound.getInt("InfusingTimeTotal");
//    }
//
//    @Override
//    public CompoundNBT write(CompoundNBT compound) {
//        super.write(compound);
//        compound.putInt("InfusingTime", this.infusingTime);
//        compound.putInt("InfusingTimeTotal", 200);
//        ItemStackHelper.saveAllItems(compound, this.items);
//        return compound;
//    }
//
//    @Override
//    protected ITextComponent getDefaultName() {
//        return new TranslationTextComponent("gui.mystical_pumpkins.infusion_table");
//    }
//
//    @Override
//    protected Container createMenu(int id, PlayerInventory player) {
//        return new InfusionTableContainer(id, player, this, timeArray);
//    }
//
//    @Override
//    public int[] getSlotsForFace(Direction side) {
//        return new int[0];
//		/*if (side == Direction.UP) {
//			return SLOTS_FOR_UP;
//		} else {
//			return side == Direction.DOWN ? SLOTS_FOR_DOWN : SLOTS_HORIZONTAL;
//		}*/
//    }
//
//    @Override
//    public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
//        return this.isItemValidForSlot(index, itemStackIn);
//    }
//
//    @Override
//    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
//        if (direction == Direction.DOWN && index == 3) {
//            return !stack.isEmpty();
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public int getSizeInventory() {
//        return this.items.size();
//    }
//
//    @Override
//    public boolean isEmpty() {
//        for(ItemStack itemstack : this.items) {
//            if (!itemstack.isEmpty()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public ItemStack getStackInSlot(int index) {
//        return this.items.get(index);
//    }
//
//    @Override
//    public ItemStack decrStackSize(int index, int count) {
//        return ItemStackHelper.getAndSplit(this.items, index, count);
//    }
//
//    @Override
//    public ItemStack removeStackFromSlot(int index) {
//        return ItemStackHelper.getAndRemove(this.items, index);
//    }
//
//    @Override
//    public void setInventorySlotContents(int index, ItemStack stack) {
//        ItemStack itemstack = this.items.get(index);
//        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
//        this.items.set(index, stack);
//        if (stack.getCount() > this.getInventoryStackLimit()) {
//            stack.setCount(this.getInventoryStackLimit());
//        }
//
//        if (!flag) {
//            this.markDirty();
//        }
//    }
//
//    @Override
//    public boolean isUsableByPlayer(PlayerEntity player) {
//        if (this.world != null && this.world.getTileEntity(this.pos) != this) {
//            return false;
//        } else {
//            return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 16.0D;
//        }
//    }
//
//    @Override
//    public void clear() {
//        this.items.clear();
//        this.infusingTime = 0;
//        this.infusingTimeTotal = 200;
//        this.currentRecipe = null;
//    }
//
//    @Override
//    public void tick() {
//        boolean dirty = false;
//        if (!world.isRemote) {
//            boolean isThereInput = !this.items.get(0).isEmpty();
//            boolean isThereFuel = !this.items.get(1).isEmpty();
//            boolean isThereSecondary = !this.items.get(2).isEmpty();
//            boolean isThereOutputAlready = !this.items.get(3).isEmpty();
//            if (isThereFuel && isThereInput && isThereSecondary && this.currentRecipe == null) {
//                this.currentRecipe = InfusionTableRecipeRegistry.searchRecipe(this.items.get(0), this.items.get(1).getCount(), this.items.get(2));
//            }
//            if (currentRecipe != null && (this.items.get(3).isItemEqual(currentRecipe.getOutput()) || !isThereOutputAlready)) {
//                if (infusingTime == 0) {
//                    this.items.get(0).setCount(this.items.get(0).getCount() - currentRecipe.getInput().getCount());
//                    this.items.get(1).setCount(this.items.get(1).getCount() - currentRecipe.getEssenceAmount());
//                    this.items.get(2).setCount(this.items.get(2).getCount() - currentRecipe.getSecondary().getCount());
//                    dirty = true;
//                }
//                ++this.infusingTime;
//                if (this.infusingTime == this.infusingTimeTotal) {
//                    if (!isThereOutputAlready)
//                        this.items.set(3, currentRecipe.getOutput());
//                    else
//                        this.items.get(3).setCount(this.items.get(3).getCount() + currentRecipe.getOutput().getCount());
//                    this.infusingTime = 0;
//                    this.currentRecipe = null;
//                    dirty = true;
//                }
//            }
//        }
//        if (dirty) {
//            this.markDirty();
//        }
//    }
