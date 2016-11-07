package abused_master.JATMA.Registry;

import abused_master.JATMA.Blocks.Crystal;
import abused_master.JATMA.Blocks.MainItemBlock;
import abused_master.JATMA.Blocks.Pulverizer;
import abused_master.JATMA.Config.Config;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
		
	public static Block Crystal = new Crystal(Material.ROCK);
	public static Block Pulverizer = new Pulverizer(Material.ROCK);
	
	public static void init() {
		
	GameRegistry.register(Crystal.setRegistryName("Crystal"));
	GameRegistry.register(new MainItemBlock(Crystal).setRegistryName(Crystal.getRegistryName()));

	if(Config.Pulverizer) {
	GameRegistry.register(Pulverizer.setRegistryName("Pulverizer"));
	GameRegistry.register(new MainItemBlock(Pulverizer).setRegistryName(Pulverizer.getRegistryName()));
	  }
	}
	
	
	public static void RegisterRender() {
		reg(Crystal);
		
		if(Config.Pulverizer) {
		reg(Pulverizer);
		}
	}
	
	 public static void reg(Block block) {
		
		 ModelResourceLocation res = new
				 ModelResourceLocation(block.getRegistryName().toString(), "inventory");
		 ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, res);
	 
	 }
	 
	
	
}
