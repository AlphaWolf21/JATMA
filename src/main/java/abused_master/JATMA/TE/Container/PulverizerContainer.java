package abused_master.JATMA.TE.Container;

import javax.annotation.Nullable;

import abused_master.JATMA.GUI.SlotPulverizerOutput;
import abused_master.JATMA.TE.TilePulverizer;
import abused_master.JATMA.TE.CraftingHandlers.PulverizerRecipes;
import abused_master.JATMA.TE.CraftingHandlers.RecipePulverizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class PulverizerContainer extends Container {
	
	IInventory TilePulverizer;

	public PulverizerContainer(InventoryPlayer playerInv, IInventory pulvInv, TileEntity tile) {
		super();
		TilePulverizer = pulvInv;
	    
		addSlotToContainer(new Slot(TilePulverizer, 0, 56, 26));
		addSlotToContainer(new SlotPulverizerOutput(playerInv.player, TilePulverizer, 1, 116, 26));

		for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }

	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	    }
	}
	
	@Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 38, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (RecipePulverizer.instance().getPulverizingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 38, false))
                    {
                        return null;
                    }
                }
                else if (index >= 30 && index < 38 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 38, false))
            {
                return null;
            }
             
            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.TilePulverizer.isUseableByPlayer(playerIn);
	}
	
	
	
	
	
	
	/**
	 * Old Code to make it work, New code above, useless now, used for reference
	  TilePulverizer TP;
	  private final EntityPlayer thePlayer;

	public PulverizerContainer(InventoryPlayer playerInv, TileEntity tile) {
		super();
		TP = (TilePulverizer) tile;
	    thePlayer = playerInv.player;
	    
		addSlotToContainer(new Slot(TP, 0, 56, 26));
		addSlotToContainer(new Slot(TP, 1, 116, 26));

		for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }

	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	    }
	}
	*/
}