package abused_master.JATMA.Registry;

import java.util.Random;

import abused_master.JATMA.Config.Config;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class JATMAGeneration implements IWorldGenerator {
	
	private WorldGenerator ChargedCrystal;
	
	
	public JATMAGeneration() {
		
	    this.ChargedCrystal = new WorldGenMinable(ModBlocks.Crystal.getDefaultState(), 2);
	}
    
    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i ++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

        switch (world.provider.getDimension()) {
        case 0: //Overworld
        	
        	
        	this.runGenerator(this.ChargedCrystal, world, random, chunkX, chunkZ, Config.CrystalRate, 0, 256);
        	
        	
        	break;
        case -1: //Nether

            break;
        case 1: //End

            break;
        }
    }
	
}
