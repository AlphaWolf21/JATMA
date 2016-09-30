package abused_master.JATMA.Items;

import java.util.ArrayList;

import abused_master.JATMA.JATMA;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class Wrench extends Item {

	public Wrench() {
		super();
		this.setCreativeTab(JATMA.JATMA);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("Wrench");
	}
	
	  @Override  
	  public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
	   
	    if (world.isRemote) {
	      //If its client side we have to return pass so this method is called on server, where we need to perform the op 
	      return EnumActionResult.PASS;
	    }
	    
	    final IBlockState blockState = world.getBlockState(pos);
	    IBlockState bs = blockState;
	    Block block = bs.getBlock();
	    boolean ret = false;
	    if (block != null) {
	      RightClickBlock e = new RightClickBlock(player, hand, player.getHeldItem(hand), pos,side, new Vec3d(hitX, hitY, hitZ));
	      if (MinecraftForge.EVENT_BUS.post(e) || e.getResult() == Result.DENY || e.getUseBlock() == Result.DENY || e.getUseItem() == Result.DENY) {
	        return EnumActionResult.PASS;
	      }
	      if (!player.isSneaking() && block.rotateBlock(world, pos, side)) {
	        ret = true;
	      }
	      ret = true;
	        }
	    if (ret) {
	      player.swingArm(hand);
	    }
	    return ret ? EnumActionResult.SUCCESS: EnumActionResult.PASS;
	  }
}
