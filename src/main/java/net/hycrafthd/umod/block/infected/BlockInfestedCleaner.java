package net.hycrafthd.umod.block.infected;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IInfectedBlock;
import net.hycrafthd.umod.block.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Biomes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

public class BlockInfestedCleaner extends BlockBase {
	
	public BlockInfestedCleaner() {
		super(Material.IRON);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(1F);
		this.setSoundType(SoundType.ANVIL);
		this.setCreativeTab(UReference.maschines);
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		clean(worldIn, pos);
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState s) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState s) {
		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	private void clean(final World world, BlockPos blockPos) {
		if (!world.isRemote) {
			final Chunk chunk = world.getChunkFromBlockCoords(blockPos);
			final int x1 = chunk.xPosition * 16;
			final int y1 = 1;
			final int z1 = chunk.zPosition * 16;
			
			final int x2 = chunk.xPosition * 16 + 15;
			final int y2 = 255;
			final int z2 = chunk.zPosition * 16 + 15;
			
			final byte[] biome = new byte[256];
			
			for (int i = 0; i < 256; i++) {
				biome[i] = (byte) Biome.getIdForBiome(Biomes.PLAINS);
			}
			
			// System.out.println(x1 + " " + z1);
			// System.out.println(x2 + " " + z2);
			
			// world.setBlockState(new BlockPos(x1, blockPos.getY(), z1), Blocks.diamond_block.getDefaultState());
			// world.setBlockState(new BlockPos(x2, blockPos.getY(), z2), Blocks.diamond_block.getDefaultState());
			
			new Thread() {
				
				@Override
				public void run() {
					chunk.setBiomeArray(biome);
					for (int i = x1; i <= x2; i++)
						for (int k = z1; k <= z2; k++)
							for (int j = y1; j <= y2; j++) {
								if (world.getBlockState(new BlockPos(i, j, k)).getBlock() instanceof IInfectedBlock) {
									IInfectedBlock infectedBlock = (IInfectedBlock) world.getBlockState(new BlockPos(i, j, k)).getBlock();
									if (infectedBlock.getNormalBlock() == null)
										continue;
									world.setBlockState(new BlockPos(i, j, k), infectedBlock.getNormalBlock().getDefaultState());
								}
							}
				};
			}.start();
		}
	}
	
}
