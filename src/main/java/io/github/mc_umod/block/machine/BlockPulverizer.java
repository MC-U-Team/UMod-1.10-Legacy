package io.github.mc_umod.block.machine;

import java.util.Random;

import io.github.mc_umod.UReference;
import io.github.mc_umod.api.energy.IEnergyMessage;
import io.github.mc_umod.enumtype.EnumTypeGui;
import io.github.mc_umod.tileentity.TileEntityPulverizer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPulverizer extends BlockBaseMachine implements IEnergyMessage {
	
	public BlockPulverizer() {
		super();
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPulverizer();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.openGui(UReference.modid, EnumTypeGui.PULVERISER.getID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
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
	public String getMessage(int n) {
		return "Needs 10UE/t";
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		worldIn.spawnParticle(EnumParticleTypes.BLOCK_DUST, pos.getX(), pos.getX(), pos.getX(), 0, 0, 0, 120);
	}
	
}
