package net.hycrafthd.umod.block;

import java.util.Random;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.*;

public class BlockMagicGlass extends BlockBase {
	
	
	public BlockMagicGlass() {
		super(Material.GLASS);
		this.setHardness(0.6F);
		this.setSoundType(SoundType.GLASS);
		this.setCreativeTab(UReference.magic);
	}
	
	public int quantityDropped(Random random) {
		return 0;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState s) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState s) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();
		
		if (blockState != iblockstate) {
			return true;
		}
		
		if (block == this) {
			return false;
		}
		
		return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
	
	@Override
	public boolean canSilkHarvest() {
		return true;
	}
	
}
