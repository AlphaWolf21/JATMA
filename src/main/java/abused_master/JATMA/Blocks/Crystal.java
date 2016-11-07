package abused_master.JATMA.Blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import abused_master.JATMA.JATMA;
import abused_master.JATMA.Registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Crystal extends Block {
	
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.24D, 0.03D, 0.24D, 0.76D, 0.51D, 0.76D);
	
	public Crystal(Material material) {
		super(material);
		this.setUnlocalizedName("Crystal");
		this.setCreativeTab(JATMA.JATMA);
		this.setHardness(2.5F);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}
	
	/*
	 * Block Drops
	 */
	
	 @Nullable
	    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return ModItems.ChargedCrystal;
	    }

	    public int quantityDroppedWithBonus(int fortune, Random random)
	    {
	        return this.quantityDropped(random) + random.nextInt(fortune + 1);
	    }

	    public int quantityDropped(Random random)
	    {
	        return 1 + random.nextInt(1);
	    }

	    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	    {
	        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
	    }

	    @Override
	    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
	    {
	        if (this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this))
	        {
	            return 1 + RANDOM.nextInt(3);
	        }
	        return 0;
	    }
	
	
}
