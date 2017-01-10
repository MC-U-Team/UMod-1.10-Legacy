package io.github.mc_umod.block.machine;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.block.*;
import io.github.mc_umod.utils.*;
import net.minecraft.block.material.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
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
	public int tickRate(World worldIn) {
		return 10;
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		System.out.println("s");
		if (worldIn.isBlockPowered(pos)) {
			EnumFacing type = (EnumFacing) state.getValue(FACING);
			// BlockPos po = DirectionUtils.getPosfromFacing(type, pos);
			// IBlockState st = worldIn.getBlockState(po);
			// if(worldIn.getBlockState(po) != null && !(st.getBlock() instanceof BlockAir)){
			// worldIn.setBlockToAir(po);
			// worldIn.playSound(po.getX(), po.getY(), po.getZ(), s.soundName, s.volume, s.frequency, true);
			// spawnAsEntity(worldIn, po, new ItemStack(st.getBlock(),1,st.getBlock().getMetaFromState(st)));
			// System.out.println("Hollo");
			// }
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing type = DirectionUtils.getFacingFromShort((short) meta);
		return this.getDefaultState().withProperty(FACING, type);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing type = (EnumFacing) state.getValue(FACING);
		return DirectionUtils.getShortFromFacing(type);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
}
