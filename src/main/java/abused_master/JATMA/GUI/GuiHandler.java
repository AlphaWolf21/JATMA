package abused_master.JATMA.GUI;

import abused_master.JATMA.TE.TilePulverizer;
import abused_master.JATMA.TE.Container.PulverizerContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int GUI_Pulverizer = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		 BlockPos pos = new BlockPos(x, y, z);
	        TileEntity te = world.getTileEntity(pos);
	        if (te instanceof TilePulverizer) {
	            return new PulverizerContainer(player.inventory, (TilePulverizer) te, te);
	        }
	       
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TilePulverizer) {
        	TilePulverizer containerTileEntity = (TilePulverizer) te;
            return new GuiPulverizer(containerTileEntity, new PulverizerContainer(player.inventory, containerTileEntity, te));
        }
		return null;
	}
}