package io.github.mc_umod.block.infected;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import io.github.mc_umod.block.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class BlockInfectedGrass extends BlockBase implements IInfectedBlock {
	
	public BlockInfectedGrass() {
		super(Material.GRASS);
		this.setCreativeTab(UReference.infected);
		this.setHarvestLevel("spade", 2);
		this.setHardness(0.6F);
		this.setSoundType(SoundType.GROUND);
		this.setTickRandomly(true);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			BlockPos BlockPos2 = pos.add(0, 1, 0);
			if (worldIn.getBlockState(BlockPos2).getBlock() != Blocks.AIR) {
				worldIn.setBlockState(pos, UBlocks.infectedDirt.getDefaultState());
			} else {
				if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
					for (int i = 0; i < 4; i++) {
						BlockPos blockPos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
						Block block = worldIn.getBlockState(blockPos1.up()).getBlock();
						IBlockState iBlockState1 = worldIn.getBlockState(blockPos1);
						if (iBlockState1.getBlock() == UBlocks.infectedDirt && worldIn.getLightFromNeighbors(blockPos1.up()) >= 4 && block.getLightOpacity(state, worldIn, blockPos1.up()) <= 2) {
							BlockPos pBlockPos2 = blockPos1.add(0, 1, 0);
							if (worldIn.getBlockState(pBlockPos2).getBlock() == Blocks.AIR) {
								worldIn.setBlockState(blockPos1, UBlocks.infectedGrass.getDefaultState());
							}
						}
					}
				}
			}
		}
		super.updateTick(worldIn, pos, state, rand);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return UBlocks.infectedDirt.getItemDropped(Blocks.DIRT.getDefaultState(), rand, fortune);
	}
	
	@Override
	public Block getNormalBlock() {
		return Blocks.GRASS;
	}
	
}
