package abused_master.JATMA.GUI;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotValidated extends Slot {

	SlotValidator validator;

	public SlotValidated(SlotValidator validator, IInventory inventory, int index, int x, int y) {

		super(inventory, index, x, y);
		this.validator = validator;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {

		return validator.isItemValid(stack);
	}

}