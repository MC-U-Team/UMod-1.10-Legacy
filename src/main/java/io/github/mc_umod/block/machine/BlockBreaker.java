package io.github.mc_umod.block.machine;

import io.github.mc_umod.UReference;
import io.github.mc_umod.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

public class BlockBreaker extends BlockBase {
	
	public static final PropertyEnum FACING = PropertyEnum.create("type", EnumFacing.class);
	
	public BlockBreaker() {
		super(Material.ROCK);
		this.setCreativeTab(UReference.maschines);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		placer.getHorizontalFacing().getOpposite();
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing type = EnumFacing.getFront(meta);
		return this.getDefaultState().withProperty(FACING, type);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing type = (EnumFacing) state.getValue(FACING);
		return type.getIndex();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
}
