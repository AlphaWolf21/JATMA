package abused_master.JATMA.TE.CraftingHandlers;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class RecipePulverizer {
	
	private static final RecipePulverizer PULVERIZING_BASE = new RecipePulverizer();
    private final Map<ItemStack, ItemStack> pulvList = Maps.<ItemStack, ItemStack>newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    /**
     * Returns the Instance
     */
    public static RecipePulverizer instance()
    {
        return PULVERIZING_BASE;
    }

    private RecipePulverizer()
    {
    	this.addPulverizingRecipeForBlock(Blocks.COBBLESTONE, new ItemStack(Blocks.GRAVEL), 1.0F);
    	this.addPulverizingRecipeForBlock(Blocks.GRAVEL, new ItemStack(Blocks.SAND), 1.0F);
    	this.addPulverizingRecipeForBlock(Blocks.COBBLESTONE, new ItemStack(Blocks.GRAVEL), 1.0F);

    }

    /**
     * Block Input
     */
    public void addPulverizingRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        this.addPulverizingRecipeForItem(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Item Input
     */
    public void addPulverizingRecipeForItem(Item input, ItemStack stack, float experience)
    {
        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }
    
    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        if (getPulverizingResult(input) != null) { net.minecraftforge.fml.common.FMLLog.info("Ignored pulverizing recipe with conflicting input: " + input + " = " + stack); return; }
        this.pulvList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }

    
    @Nullable
    public ItemStack getPulverizingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.pulvList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return (ItemStack)entry.getValue();
            }
        }

        return null;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return this.pulvList;
    }

    public float getPulverizingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }

        return 0.0F;
    }
	
}
