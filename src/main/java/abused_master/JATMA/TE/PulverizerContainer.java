package abused_master.JATMA.TE;

import javax.annotation.Nullable;

import abused_master.JATMA.GUI.RemoveOnlySlot;
import abused_master.JATMA.GUI.SlotInput;
import abused_master.JATMA.GUI.SlotValidated;
import abused_master.JATMA.GUI.SlotValidator;
import abused_master.JATMA.TE.CraftingHandlers.PulverizerRecipes;
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
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class PulverizerContainer extends Container implements SlotValidator {
	
	TilePulverizer TP;

	public PulverizerContainer( InventoryPlayer inventory, TileEntity tile) {
		super();
		TP = (TilePulverizer) tile;
		addSlotToContainer(new SlotInput(TP, 0, 56, 26));
		//addSlotToContainer(new Slot(TP, 0, 56, 26));
		addSlotToContainer(new RemoveOnlySlot(TP, 1, 116, 26));
		
		//Slot for under output
		//addSlotToContainer(new RemoveOnlySlot(TP, 2, 134, 26));

		for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }

	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(inventory, x, 8 + x * 18, 142));
	    }
	}
	
		@Override
		public boolean isItemValid(ItemStack stack) {
			return PulverizerRecipes.recipeExists(stack);
		}
	 
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return TP.canInteractWith(playerIn);
	}
	
	
	  @Override
	   public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slot) {
	      Slot slotObject = (Slot) inventorySlots.get(slot);
	      if(slotObject != null && slotObject.getHasStack()) {
	         ItemStack stackInSlot = slotObject.getStack();
	         ItemStack stack = stackInSlot.copy();
	         if(slot <= 1) {
	            if(!mergeItemStack(stackInSlot, 0, inventorySlots.size(), true))
	               return null;
	         } else {
	            return null;
	         }
	         
	         if(stackInSlot.stackSize == 0)
	            slotObject.putStack(null);
	         else
	            slotObject.onSlotChanged();
	          
	         return stack;
	      }
	      return null;
	  }
	  
	  protected boolean supportsShiftClick(EntityPlayer player, int slotIndex) {

			return supportsShiftClick(slotIndex);
		}

		protected boolean supportsShiftClick(int slotIndex) {

			return true;
		}

	  
}