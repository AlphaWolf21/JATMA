package abused_master.JATMA.Registry;

import abused_master.JATMA.Items.ChargedCrystal;
import abused_master.JATMA.Items.Wrench;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	//public static Item Wrench = new Wrench();
	public static Item Wrench;
	public static Item ChargedCrystal;
	
	public static void init() {
		
		//GameRegistry.register(Wrench.setRegistryName("Wrench"));
	    GameRegistry.registerItem(Wrench = new Wrench(), "Wrench");
	    GameRegistry.registerItem(ChargedCrystal = new ChargedCrystal(), "ChargedCrystal");

	}
	
	public static void RegisterRender() {
		
		reg(Wrench);
		reg(ChargedCrystal);
		
	}
	
	public static void reg(Item item) {
        ModelResourceLocation res = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");

        ModelLoader.setCustomModelResourceLocation(item, 0, res);
    }
	
}
