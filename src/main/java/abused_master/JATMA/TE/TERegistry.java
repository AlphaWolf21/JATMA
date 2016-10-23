package abused_master.JATMA.TE;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TERegistry {
	
	public static void RegisterTE() {
		GameRegistry.registerTileEntity(TilePulverizer.class, "TilePulverizer");
	}
	
}
