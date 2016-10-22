package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IConduitBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
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
		
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		ItemStack stack = new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
		stack.setTagInfo("NBTS", compound);
		spawnAsEntity(worldIn, pos, stack);
	}
	
}
