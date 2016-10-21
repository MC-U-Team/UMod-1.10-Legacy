package net.hycrafthd.umod.block;

import java.util.Random;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if(blockAccess instanceof World){
	    World worldIn = (World) blockAccess;
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();
		
		if (worldIn.getBlockState(pos.offset(side.getOpposite())) != iblockstate) {
			return true;
		}
		
		if (block == this) {
			return false;
		}
		}
		return blockState.getBlock() == this ? false : super.shouldSideBeRendered(blockState,blockAccess, pos, side);
	}
	
	@Override
	public boolean canSilkHarvest() {
		return true;
	}
	
}
