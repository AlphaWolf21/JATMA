package abused_master.JATMA.Registry;

import abused_master.JATMA.ConfigHandler;
import abused_master.JATMA.Blocks.Crystal;
import abused_master.JATMA.Blocks.MainItemBlock;
import abused_master.JATMA.Blocks.SawMill;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
		
	public static Block Crystal;
	public static Block SawMill = new SawMill(Material.ROCK);
	
	public static void init() {
		
	GameRegistry.registerBlock(Crystal = new Crystal(Material.ROCK), "Crystal");
	
    //if (ConfigHandler.SawMill) {
	GameRegistry.register(SawMill.setRegistryName("SawMill"));
	GameRegistry.register(new MainItemBlock(SawMill).setRegistryName(SawMill.getRegistryName()));
	  //}
	}
	
	
	public static void RegisterRender() {
		
		reg(Crystal);
		//if (ConfigHandler.SawMill) {
		reg(SawMill);
		//}
	}
	
	 public static void reg(Block block) {
		 /**
	        Item item = Item.getItemFromBlock(block);

	        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	             .register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		*/ 
		
		 ModelResourceLocation res = new
				 ModelResourceLocation(block.getRegistryName().toString(), "inventory");
		 ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, res);
	 
	 }
	 
	
	
}
