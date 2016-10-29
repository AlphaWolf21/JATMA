package abused_master.JATMA.proxy;

import java.io.File;

import abused_master.JATMA.Info;
import abused_master.JATMA.JATMA;
import abused_master.JATMA.GUI.GuiHandler;
import abused_master.JATMA.Registry.ModBlocks;
import abused_master.JATMA.Registry.ModItems;
import abused_master.JATMA.TE.TERegistry;
import abused_master.JATMA.TE.CraftingHandlers.PulverizerRecipes;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {	
	public void preInit(FMLPreInitializationEvent e) {
		ModItems.init();
		ModBlocks.init();
	}
	
	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(JATMA.instance, new GuiHandler());
		TERegistry.RegisterTE();
	}
	
	public void postInit(FMLPostInitializationEvent e) {
	}
	
}
