package abused_master.JATMA.proxy;

import java.io.File;

import abused_master.JATMA.ConfigHandler;
import abused_master.JATMA.Info;
import abused_master.JATMA.Registry.ModBlocks;
import abused_master.JATMA.Registry.ModItems;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {	
	public void preInit(FMLPreInitializationEvent e) {
		ModItems.init();
		ModBlocks.init();
	}
	
	public void init(FMLInitializationEvent e) {
		
	}
	
	public void postInit(FMLPostInitializationEvent e) {
	}
	
}
