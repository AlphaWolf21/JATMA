package abused_master.JATMA.TE;

import javax.annotation.Nullable;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.TileEnergyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TilePulverizer extends TileEnergyHandler implements IInventory {
	
	protected EnergyStorage storage = new EnergyStorage(50000);
    public static final int SIZE = 2;
    private ItemStack[] pulverizerItemStacks = new ItemStack[3];


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

	/* IEnergyConnection */
	@Override
	public boolean canConnectEnergy(EnumFacing from) {

		return true;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {

		return storage.receiveEnergy(maxReceive, simulate);
	}

	/* IEnergyProvider */
	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		return 0;
		//return storage.extractEnergy(maxExtract, simulate);
	}

	/* IEnergyHandler */
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
        // If we are too far away from this tile entity you cannot use it
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
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public Object getChargeSlot() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * just testing
    private boolean canPulverize()
    {
        if (this.pulverizerItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.pulverizerItemStacks[0]);
            if (itemstack == null) return false;
            if (this.pulverizerItemStacks[2] == null) return true;
            if (!this.pulverizerItemStacks[2].isItemEqual(itemstack)) return false;
            int result = pulverizerItemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.pulverizerItemStacks[2].getMaxStackSize();
        }
    }
	
	 public void PulverizeItem()
	    {
	        if (this.canPulverize())
	        {
	            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.pulverizerItemStacks[0]);

	            if (this.pulverizerItemStacks[2] == null)
	            {
	                this.pulverizerItemStacks[2] = itemstack.copy();
	            }
	            else if (this.pulverizerItemStacks[2].getItem() == itemstack.getItem())
	            {
	                this.pulverizerItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
	            }

	            if (this.pulverizerItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.SPONGE) && this.pulverizerItemStacks[0].getMetadata() == 1 && this.pulverizerItemStacks[1] != null && this.pulverizerItemStacks[1].getItem() == Items.BUCKET)
	            {
	                this.pulverizerItemStacks[1] = new ItemStack(Items.WATER_BUCKET);
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
