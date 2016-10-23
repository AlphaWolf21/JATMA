package abused_master.JATMA.Registry;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class PulverizerRecipes {

	 	private static final PulverizerRecipes Pulverizing_Base = new PulverizerRecipes();
	    private final Map<ItemStack, ItemStack> pulverizingList = Maps.<ItemStack, ItemStack>newHashMap();


	    /**
	     * Returns an instance of FurnaceRecipes.
	     */
	    public static PulverizerRecipes instance()
	    {
	        return Pulverizing_Base;
	    }

	    public PulverizerRecipes()
	    {
	        this.addBlockPulverizing(Blocks.IRON_ORE, new ItemStack(Items.IRON_INGOT, 2), 0.7F);
	    }
	    
	    public void addBlockPulverizing(Block input, ItemStack stack, float experience)
	    {
	        this.addItemPulverizing(Item.getItemFromBlock(input), stack, experience);
	    }

	    public void addItemPulverizing(Item input, ItemStack stack, float experience)
	    {
	        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
	    }

	    /**
	     * Adds a smelting recipe using an ItemStack as the input for the recipe.
	     */
	    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience)
	    {
	        if (getSmeltingResult(input) != null) { net.minecraftforge.fml.common.FMLLog.info("Ignored pulverizing recipe"); return; }
	        this.pulverizingList.put(input, stack);
	    }
	    @Nullable
	    public ItemStack getSmeltingResult(ItemStack stack)
	    {
	        for (Entry<ItemStack, ItemStack> entry : this.pulverizingList.entrySet())
	        {
	            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
	            {
	                return (ItemStack)entry.getValue();
	            }
	        }

	        return null;
	    }

	    /**
	     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
	     */
	    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	    {
	        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	    }

	    public Map<ItemStack, ItemStack> getSmeltingList()
	    {
	        return this.pulverizingList;
	    }
	
}
