package abused_master.JATMA.GUI;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SingleItemSlot extends Slot {

    public SingleItemSlot(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
}