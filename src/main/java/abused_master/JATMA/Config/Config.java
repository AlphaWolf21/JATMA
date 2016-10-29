package abused_master.JATMA.Config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
	
	private static final String MOD_JATMA = "JATMA";
	
	public static String configpath;
	public static Configuration config;
	
	public static boolean Pulverizer;
	
	public static void init(FMLPreInitializationEvent event) {

		configpath = event.getModConfigurationDirectory().getAbsolutePath() + File.separator;
		config = new Configuration(new File(configpath + "JATMA.cfg"));
		try {
			config.load();
			Config.configure(config);
		} catch (Exception e1) {
			System.out.println("Error Loading Config File: JATMA.cfg");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

	public static void configure(Configuration config) {
		
		config.addCustomCategoryComment(MOD_JATMA, "JATMA Config");
		Pulverizer = config.getBoolean("Pulverizer", MOD_JATMA, true, "Set to false if you want the Pulverizer to be Disabled");
	}	
}
