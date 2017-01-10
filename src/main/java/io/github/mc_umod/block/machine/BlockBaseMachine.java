package io.github.mc_umod.block.machine;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import io.github.mc_umod.block.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.inventory.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public abstract class BlockBaseMachine extends BlockBase implements ITileEntityProvider, IConduitBlock {
	
	private NBTTagCompound compound;
	
	public BlockBaseMachine() {
		super(Material.IRON);
		this.isBlockContainer = true;
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(5);
		this.setCreativeTab(UReference.maschines);
		this.setSoundType(SoundType.METAL);
	}
	
	@Override
	public int getComparatorInputOverride(IBlockState blockState, World world, BlockPos pos) {
		return Container.calcRedstone(world.getTileEntity(pos));
	}
	
	public boolean canProvidePower() {
		return true;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState s) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState s) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
}
