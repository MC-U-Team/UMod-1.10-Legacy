package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IConduitBlock;
import net.minecraft.block.ITileEntityProvider;
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
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World world, BlockPos pos) {
		return Container.calcRedstone(world.getTileEntity(pos));
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity ent = world.getTileEntity(pos);
		compound = new NBTTagCompound();
		if (ent == null)
			return;
		ent.writeToNBT(compound);
		world.removeTileEntity(pos);
	}
	
	public boolean canProvidePower() {
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
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
	public boolean isFullCube(IBlockState state) {
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
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity ent = worldIn.getTileEntity(pos);
		if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("NBTS")) {
			NBTTagCompound comp = stack.getTagCompound().getCompoundTag("NBTS");
			ent.readFromNBT(comp);
		}
		ent.setPos(pos);
		ent.setWorldObj(worldIn);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
}
