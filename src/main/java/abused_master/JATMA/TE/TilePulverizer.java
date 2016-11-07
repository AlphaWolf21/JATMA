package abused_master.JATMA.TE;

import javax.annotation.Nullable;

import abused_master.JATMA.GUI.GuiPulverizer;
import abused_master.JATMA.Registry.ModBlocks;
import abused_master.JATMA.TE.CraftingHandlers.RecipePulverizer;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.TileEnergyHandler;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import scala.xml.persistent.SetStorage;

public class TilePulverizer extends TileEnergyHandler implements ISidedInventory {
	
	protected EnergyStorage storage = new EnergyStorage(50000);
    public static final int SIZE = 2;
    private ItemStack[] pulverizerItemStacks = new ItemStack[3];

    public TilePulverizer() {
    	super();
    }


	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		
		if (nbt.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
        }
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
        nbt.setTag("items", itemStackHandler.serializeNBT());
		return storage.writeToNBT(nbt);
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {

		return true;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(EnumFacing from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {

		return storage.getMaxEnergyStored();
	}
	
    private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            TilePulverizer.this.markDirty();
        }
    };
    
    
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) itemStackHandler;
        }
        return super.getCapability(capability, facing);
    } 
    
	@Override
	public int getSizeInventory() {
		return this.pulverizerItemStacks.length;
	}


	@Override
	public ItemStack getStackInSlot(int index) {
        return this.pulverizerItemStacks[index];
	}


	@Override
	public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.pulverizerItemStacks, index, count);
	}


	@Override
	public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.pulverizerItemStacks, index);
	}


	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {

        boolean flag = stack != null && stack.isItemEqual(this.pulverizerItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.pulverizerItemStacks[index]);
        this.pulverizerItemStacks[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
            this.markDirty();
        }		
	}


	@Override
	public int getInventoryStackLimit() {
		return 64;
	}


	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}


	@Override
	public void openInventory(EntityPlayer player) {
		
	}
	@Override
	public void closeInventory(EntityPlayer player) {
		
	}


	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}


	@Override
	public int getField(int id) {
		return 0;
	}


	@Override
	public void setField(int id, int value) {
		
	}


	@Override
	public int getFieldCount() {
		return 0;
	}


	@Override
	public void clear() {
        for (int i = 0; i < this.pulverizerItemStacks.length; ++i)
        {
            this.pulverizerItemStacks[i] = null;
        }		
	}


	@Override
	public String getName() {
		return ModBlocks.Pulverizer.getUnlocalizedName();
	}


	@Override
	public boolean hasCustomName() {
		return false;
	}


	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return null;
	}


	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
	}


	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}
	
	
	
	
	
	public void update() {
		
		
		
	}
	
	public boolean canPulverize() {
        ItemStack itemstack = RecipePulverizer.instance().getPulverizingResult(pulverizerItemStacks[0]);
        return true;
	}
	
	public void pulverizeItem() {
			
			ItemStack itemstack = RecipePulverizer.instance().getPulverizingResult(this.pulverizerItemStacks[0]);
	        this.pulverizerItemStacks[2] = itemstack.copy();
	        this.pulverizerItemStacks[2].stackSize += itemstack.stackSize;
	        --this.pulverizerItemStacks[0].stackSize;
	        this.pulverizerItemStacks[0] = null;
	}
	
	
	
	
	
	
	/*
	public void update()
    {
    	boolean flag1 = false;

        if (!this.worldObj.isRemote)
        {
            if (this.pulverizerItemStacks[1] != null && this.pulverizerItemStacks[0] != null)
            {
                if (!this.canPulverize())
                {
                }

                if (this.canPulverize())
                {
                        flag1 = true;
                    }
                }
                else
                {
                }
            }

        if (flag1)
        {
            this.markDirty();
        }
    }
    
    private boolean canPulverize()
    {
        if (this.pulverizerItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = RecipePulverizer.instance().getPulverizingResult(this.pulverizerItemStacks[0]);
            if (itemstack == null) return false;
            if (this.pulverizerItemStacks[2] == null) return true;
            if (!this.pulverizerItemStacks[2].isItemEqual(itemstack)) return false;
            int result = pulverizerItemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.pulverizerItemStacks[2].getMaxStackSize();
        }
    }

	public void pulverizeItem()
    {  
		if (this.canPulverize()) {
			
		ItemStack itemstack = RecipePulverizer.instance().getPulverizingResult(this.pulverizerItemStacks[0]);

        if (this.pulverizerItemStacks[2] == null)
        {
            this.pulverizerItemStacks[2] = itemstack.copy();
        }
        else if (this.pulverizerItemStacks[2].getItem() == itemstack.getItem())
        {
            this.pulverizerItemStacks[2].stackSize += itemstack.stackSize;
        }
        --this.pulverizerItemStacks[0].stackSize;

        if (this.pulverizerItemStacks[0].stackSize <= 0)
        {
            this.pulverizerItemStacks[0] = null;
           }
		}
    }
*/
}
