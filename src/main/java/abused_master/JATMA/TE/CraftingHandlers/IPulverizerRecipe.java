package abused_master.JATMA.TE.CraftingHandlers;

import net.minecraft.item.ItemStack;

public interface IPulverizerRecipe {

	ItemStack getInput();

	ItemStack getPrimaryOutput();

	ItemStack getSecondaryOutput();

	int getEnergy();

	int getSecondaryOutputChance();

}