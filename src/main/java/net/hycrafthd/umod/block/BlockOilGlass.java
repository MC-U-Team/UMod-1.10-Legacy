package net.hycrafthd.umod.block;

import java.util.Random;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOilGlass extends BlockBase {
	
	public BlockOilGlass() {
		super(Material.GLASS);
        this.setSoundType(SoundType.GLASS);	
        this.setCreativeTab(UReference.blocks);
	}
	
	 public int quantityDropped(Random random)
	 {
	        return 0;
	 }

	 protected boolean canSilkHarvest()
	 {
	           return true;
	 }
	 
	 @Override
		public boolean isOpaqueCube(IBlockState s) {
			return false;
		}
		
		@Override
		public boolean isFullCube(IBlockState s) {
			return true;
		}
		
		@Override
		public boolean isFullBlock(IBlockState s) {
			return true;
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
