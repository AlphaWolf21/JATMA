package abused_master.JATMA;

import java.io.File;

import abused_master.JATMA.Registry.ModItems;
import abused_master.JATMA.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Info.MODID, version = Info.VERSION, name = Info.MODNAME)
public class JATMA {
	
	//public static File configLoc;
	
    @Instance
    public static JATMA instance;
	
	@SidedProxy(clientSide="abused_master.JATMA.proxy.ClientProxy", serverSide="abused_master.JATMA.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		this.proxy.preInit(e);
		
		
		/**
        configLoc = new File(e.getModConfigurationDirectory() + "/" + Info.MODID);
        configLoc.mkdirs();
        ConfigHandler.init(new File(configLoc.getPath(), Info.MODID + ".cfg"));
	*/
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		this.proxy.init(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		this.proxy.postInit(e);
	}
	
    public static CreativeTabs JATMA = new CreativeTabs("JATMA")
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.Wrench;
        }
        
    };	
	
}
