package abused_master.JATMA.Blocks;

import abused_master.JATMA.JATMA;
import abused_master.JATMA.TE.TileSawMill;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SawMill extends BlockContainer {
	
    public static final int GUI_ID = 1;
	
	public SawMill(Material material) {
		super(material);
		this.setCreativeTab(JATMA.JATMA);
		this.setUnlocalizedName("SawMill");
		this.setHardness(2.0F);
	}
	
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileSawMill();
	}
	
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side,
                float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof TileSawMill)) {
            return false;
        }
        player.openGui(JATMA.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
	
}
