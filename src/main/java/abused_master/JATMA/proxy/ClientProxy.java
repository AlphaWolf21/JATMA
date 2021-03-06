package abused_master.JATMA.proxy;

import abused_master.JATMA.Info;
import abused_master.JATMA.Registry.ModBlocks;
import abused_master.JATMA.Registry.ModItems;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		ModItems.RegisterRender();
		ModBlocks.RegisterRender();
	    OBJLoader.INSTANCE.addDomain(Info.MODID);
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		//ModBlocks.RegisterRender();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
	
}
