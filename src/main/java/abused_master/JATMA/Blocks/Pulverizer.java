package abused_master.JATMA.Blocks;

import abused_master.JATMA.JATMA;
import abused_master.JATMA.GUI.GuiHandler;
import abused_master.JATMA.TE.TilePulverizer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Pulverizer extends BlockContainer {
	
	public Pulverizer(Material material) {
		super(material);
		this.setCreativeTab(JATMA.JATMA);
		this.setUnlocalizedName("Pulverizer");
		this.setHardness(2.0F);
	}
	
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TilePulverizer();
	}
	
	
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side,    
    		float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
	        player.openGui(JATMA.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
        }
		return true;
	}
	
	
	
}
