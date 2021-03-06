package abused_master.JATMA;

import java.io.File;

import abused_master.JATMA.Config.Config;
import abused_master.JATMA.Registry.MobDrops;
import abused_master.JATMA.Registry.ModItems;
import abused_master.JATMA.TE.CraftingHandlers.PulverizerRecipes;
import abused_master.JATMA.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Info.MODID, version = Info.VERSION, name = Info.MODNAME)
public class JATMA {
	
    @Instance
    public static JATMA instance;
	
	@SidedProxy(clientSide="abused_master.JATMA.proxy.ClientProxy", serverSide="abused_master.JATMA.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		Config.init(e);
		this.proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		this.proxy.init(e);
		MinecraftForge.EVENT_BUS.register(new MobDrops());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		this.proxy.postInit(e);
	}
	

	@EventHandler
	public void loadComplete(FMLLoadCompleteEvent event) {
		
		PulverizerRecipes.addDefaultRecipes();
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
