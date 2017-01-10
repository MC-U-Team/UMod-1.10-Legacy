package io.github.mc_umod.block.infected;

import java.util.Random;

import io.github.mc_umod.UBlocks;
import io.github.mc_umod.UReference;
import io.github.mc_umod.api.IInfectedBlock;
import io.github.mc_umod.world.GenInfectedTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockInfectedSapling extends BlockBush implements IGrowable, IInfectedBlock {
	
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	
	public BlockInfectedSapling() {
		super();
		this.setCreativeTab(UReference.infected);
		this.setLightOpacity(1);
		this.setSoundType(SoundType.PLANT);
		// this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		this.setDefaultState(this.getDefaultState().withProperty(STAGE, Integer.valueOf(0)));
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);
			
			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				this.grow(worldIn, pos, state, rand);
			}
		}
	}
	
	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (((Integer) state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		} else {
			this.generateTree(worldIn, pos, state, rand);
		}
	}
	
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		new GenInfectedTree(true, MathHelper.getRandomIntegerInRange(rand, 4, 6), 0, 0, false).generate(world, rand, pos);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		Block ground = world.getBlockState(pos).getBlock();
		return ground == UBlocks.infectedDirt || ground == UBlocks.infectedGrass;
	}
	
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}
	
	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return worldIn.rand.nextFloat() < 0.75D;
	}
	
	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		this.grow(worldIn, pos, state, rand);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i |= ((Integer) state.getValue(STAGE)).intValue() << 3;
		return i;
	}
	
	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE });
	}
	
	@Override
	public Block getNormalBlock() {
		return Blocks.SAPLING;
	}
	
}