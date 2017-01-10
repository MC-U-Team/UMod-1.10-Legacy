package io.github.mc_umod.block.machine;

import io.github.mc_umod.UReference;
import io.github.mc_umod.enumtype.EnumTypeGui;
import io.github.mc_umod.tileentity.TileEntityChargeStation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockChargeStation extends BlockBaseMachine {
	
	public BlockChargeStation() {
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityChargeStation();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.openGui(UReference.modid, EnumTypeGui.CHARGESTATION.getID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
}
