package abused_master.JATMA.TE.CraftingHandlers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import abused_master.JATMA.Registry.ModItems;
import gnu.trove.map.hash.THashMap;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class PulverizerRecipes {
	
	private static boolean allowOverwrite = false;
	public static final int DEFAULT_ENERGY = 3200;
	private static int oreMultiplier = 2;	
    private static final PulverizerRecipes PULVERIZING_BASE = new PulverizerRecipes();
    private final Map<ItemStack, ItemStack> PulverizingList = Maps.<ItemStack, ItemStack>newHashMap();
	private static ComparableItemStackPulverizer recipeMap = new ComparableItemStackPulverizer();

	
    public static PulverizerRecipes instance()
    {
        return PULVERIZING_BASE;
    }
	
	public PulverizerRecipes(ItemStack input, ItemStack primaryOutput, int energy) {
		
	}	

	public PulverizerRecipes() {
		
	}
	

	public static RecipePulverizer getRecipe(ItemStack input) {

		if (input == null) {
			return null;
		}
		ComparableItemStackPulverizer query = new ComparableItemStackPulverizer();

		RecipePulverizer recipe = recipeMap.get(query);

		if (recipe == null) {
			query.metadata = OreDictionary.WILDCARD_VALUE;
			recipe = recipeMap.get(query);
		}
		return recipe;
	}

	public static void addDefaultRecipes() {

		String comment;

		boolean recipeSandstone = true;
		boolean recipeNetherrack = true;
		boolean recipeWool = true;
		boolean recipeReed = true;
		boolean recipeBone = true;
		boolean recipeBlazeRod = true;
		
		
		addRecipe(3200, new ItemStack(Blocks.REDSTONE_LAMP), new ItemStack(Items.GLOWSTONE_DUST, 4));
		addRecipe(1800, new ItemStack(Blocks.BRICK_BLOCK), new ItemStack(Items.BRICK, 4));
		addRecipe(1800, new ItemStack(Blocks.NETHER_BRICK), new ItemStack(Items.NETHERBRICK, 4));
		
		for (int i = 0; i < 3; i++) {
			addRecipe(1800, new ItemStack(Blocks.QUARTZ_BLOCK, 1, i), new ItemStack(Items.QUARTZ, 4));
		}

		addRecipe(2400, new ItemStack(Blocks.BRICK_STAIRS), new ItemStack(Items.BRICK, 6));
		addRecipe(2400, new ItemStack(Blocks.NETHER_BRICK_STAIRS), new ItemStack(Items.NETHERBRICK, 6));
		addRecipe(2400, new ItemStack(Blocks.QUARTZ_STAIRS), new ItemStack(Items.QUARTZ, 6));

		addRecipe(1200, new ItemStack(Blocks.STONE_SLAB, 1, 4), new ItemStack(Items.BRICK, 2));
		addRecipe(1200, new ItemStack(Blocks.STONE_SLAB, 1, 6), new ItemStack(Items.NETHERBRICK, 2));
		addRecipe(1200, new ItemStack(Blocks.STONE_SLAB, 1, 7), new ItemStack(Items.QUARTZ, 2));
		
		addRecipe(3200, new ItemStack(Blocks.STONE), new ItemStack(Blocks.GRAVEL));
		addRecipe(3200, new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.SAND));
		addRecipe(3200, new ItemStack(Blocks.GRAVEL), new ItemStack(Items.FLINT));
		addRecipe(800, new ItemStack(Blocks.STONEBRICK), new ItemStack(Blocks.STONEBRICK, 1, 2));

		//addRecipe(2400, new ItemStack(Items.COAL, 1, 0), new ItemStack(ModItems.CoalDust, 1));
		//addRecipe(2400, new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.CoalDust));
		//addRecipe(4000, new ItemStack(Blocks.OBSIDIAN), new ItemStack(ModItems.ObsidianDust, 4));

		addRecipe(3200, new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.GRAVEL, 2));
		
		addRecipe(2400, new ItemStack(Blocks.COAL_ORE), new ItemStack(Items.COAL, 3, 0));
		addRecipe(2400, new ItemStack(Blocks.DIAMOND_ORE), new ItemStack(Items.DIAMOND, 2, 0));
		addRecipe(2400, new ItemStack(Blocks.EMERALD_ORE), new ItemStack(Items.EMERALD, 2, 0));
		addRecipe(2400, new ItemStack(Blocks.GLOWSTONE), new ItemStack(Items.GLOWSTONE_DUST, 4));
		addRecipe(2400, new ItemStack(Blocks.LAPIS_ORE), new ItemStack(Items.DYE, 12, 4));
		addRecipe(3200, new ItemStack(Blocks.REDSTONE_ORE), new ItemStack(Items.REDSTONE, 6));
		addRecipe(2400, new ItemStack(Blocks.QUARTZ_ORE), new ItemStack(Items.QUARTZ, 3));
		addRecipe(2400, new ItemStack(Blocks.WOOL), new ItemStack(Items.STRING, 4));
		
		/**
		 * MakeDusts
		int energy = 4000;
		
		addOreNameToDustRecipe(energy, "oreIron", ModItems.IronDust, 2);
		addOreNameToDustRecipe(energy, "oreGold", ModItems.GoldDust, 2);
		addOreNameToDustRecipe(energy, "oreCopper", ModItems.CopperDust, 2);
		addOreNameToDustRecipe(energy, "oreTin", ModItems.TinDust, 2);
		addOreNameToDustRecipe(energy, "oreSilver", ModItems.SilverDust, 2);
		addOreNameToDustRecipe(energy, "oreLead", ModItems.LeadDust, 12);

		energy = 2400;

		addIngotNameToDustRecipe(energy, "ingotIron", ModItems.IronDust);
		addIngotNameToDustRecipe(energy, "ingotGold", ModItems.GoldDust);
		addIngotNameToDustRecipe(energy, "ingotCopper", ModItems.CopperDust);
		addIngotNameToDustRecipe(energy, "ingotTin", ModItems.TinDust);
		addIngotNameToDustRecipe(energy, "ingotSilver", ModItems.SilverDust);
		addIngotNameToDustRecipe(energy, "ingotLead", ModItems.LeadDust);
		*/
	}
	
	
	public static boolean addRecipe(int energy, ItemStack input, ItemStack primaryOutput) {

		if (input == null || primaryOutput == null || energy <= 0 || !(allowOverwrite)) {
			return false;
		}
		PulverizerRecipes recipe = new PulverizerRecipes(input, primaryOutput, energy);
		return true;
	}
	
	public static void addDefaultOreDictionaryRecipe(String oreType) {

		addDefaultOreDictionaryRecipe(oreType, "");
	}
	public static void addDefaultOreDictionaryRecipe(String oreType, String relatedType) {

		if (oreType.length() <= 0) {
			return;
		}
		String oreName = "ore" + oreType;
		String dustName = "dust" + oreType;
		String ingotName = "ingot" + oreType;
		String relatedName = null;

		ArrayList<ItemStack> registeredOre = (ArrayList<ItemStack>) OreDictionary.getOres(oreName);
		ArrayList<ItemStack> registeredDust = (ArrayList<ItemStack>) OreDictionary.getOres(dustName);
		ArrayList<ItemStack> registeredIngot = (ArrayList<ItemStack>) OreDictionary.getOres(ingotName);
		ArrayList<ItemStack> registeredRelated = new ArrayList<ItemStack>();

		String clusterName = "cluster" + oreType;
		ArrayList<ItemStack> registeredCluster = (ArrayList<ItemStack>) OreDictionary.getOres(clusterName);

		if (relatedType != "") {
			relatedName = "dust" + relatedType;
			registeredRelated = (ArrayList<ItemStack>) OreDictionary.getOres(relatedName);
		}
		if (registeredDust.isEmpty()) {
			return;
		}
		
		if (registeredIngot.isEmpty()) {
			ingotName = null;
		}
		if (registeredOre.isEmpty()) {
			oreName = null;
		}
		if (registeredCluster.isEmpty()) {
			clusterName = null;
		}
		ItemStack related = null;
		if (related == null && !registeredRelated.isEmpty()) {
			related = registeredRelated.get(0);
		}
		addOreNameToDustRecipe(4000, oreName, dustName);
		addOreNameToDustRecipe(4800, clusterName, dustName);
		addIngotNameToDustRecipe(2400, ingotName, dustName);
	}


	private static void addOreNameToDustRecipe(int i, String clusterName, String dustName) {		
	}


	private static void addIngotNameToDustRecipe(int i, String ingotName, String dustName) {
	}
	
	public static boolean addRecipe1(int energy, ItemStack input, ItemStack primaryOutput) {

		return addRecipe(energy, input, primaryOutput);
	}
	
	public static class RecipePulverizer implements IPulverizerRecipe {

		final ItemStack input;
		final ItemStack primaryOutput;
		final ItemStack secondaryOutput;
		final int secondaryChance;
		final int energy;

		RecipePulverizer(ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance, int energy) {

			this.input = input;
			this.primaryOutput = primaryOutput;
			this.secondaryOutput = secondaryOutput;
			this.secondaryChance = secondaryChance;
			this.energy = energy;

			if (input.stackSize <= 0) {
				input.stackSize = 1;
			}
			if (primaryOutput.stackSize <= 0) {
				primaryOutput.stackSize = 1;
			}
			if (secondaryOutput != null && secondaryOutput.stackSize <= 0) {
				secondaryOutput.stackSize = 1;
			}
		}

		@Override
		public ItemStack getInput() {

			return input.copy();
		}

		@Override
		public ItemStack getPrimaryOutput() {

			return primaryOutput.copy();
		}

		@Override
		public ItemStack getSecondaryOutput() {

			if (secondaryOutput == null) {
				return null;
			}
			return secondaryOutput.copy();
		}

		@Override
		public int getSecondaryOutputChance() {

			return secondaryChance;
		}

		@Override
		public int getEnergy() {

			return energy;
		}
	}
	
	/*
	public static RecipePulverizer getRecipe(ItemStack input) {

		if (input == null) {
			return null;
		}
		return null;

	}
	*/
	public static boolean recipeExists(ItemStack input) {

		return getRecipe(input) != null;
	}

	public static RecipePulverizer[] getRecipeList() {
		
		return recipeMap.values().toArray(new RecipePulverizer[0]);

	}
	
	public static class ComparableItemStackPulverizer {

		public int metadata;

		public RecipePulverizer get(ComparableItemStackPulverizer query) {
			// TODO Auto-generated method stub
			return null;
		}

		public ArrayList<ItemStack> values() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
