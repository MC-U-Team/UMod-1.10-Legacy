package net.hycrafthd.umod.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

public class BlockInfectedFruit extends BlockBase implements IInfectedBlock {
	
	public BlockInfectedFruit() {
		super(Material.PLANTS);
		//this.setBlockBounds(5.5F / 16F, 7.5F / 16F, 5.5F / 16F, 10.5F / 16F, 16F / 16F, 10.5F / 16F);
		this.setCreativeTab(UReference.infected);
	}
	
	@Override
	public void onNeighborChange(IBlockAccess w, BlockPos pos, BlockPos neighbor) {
		if(w instanceof World){
			World world = (World) w;
		    if (world.isAirBlock(pos.up())) {
			    this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
			    world.setBlockToAir(pos);
		    }
		}
		super.onNeighborChange(w, pos, neighbor);
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
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<ItemStack>(Arrays.asList(new ItemStack[] { new ItemStack(UItems.infectedcrop, MathHelper.getRandomIntegerInRange(new Random(), 1, 4)) }));
	}
	
	@Override
	public Block getNormalBlock() {
		return null;
	}
	
}
