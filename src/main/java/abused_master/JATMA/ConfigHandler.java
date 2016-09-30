package abused_master.JATMA;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
		
    public static Configuration config;
    
    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }
    
    public static boolean SawMill;
    
    public static void syncConfig() {
        String category;

        category = "SawMill";
        config.addCustomCategoryComment(category, "Saw Mill Settings");
        SawMill = config.getBoolean("SawMill", category, true, "Enables/Disabled the Saw Mill in game.");
        
        config.save();
    }
}
