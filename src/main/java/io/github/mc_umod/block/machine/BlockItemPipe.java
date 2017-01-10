package io.github.mc_umod.block.machine;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import io.github.mc_umod.api.IBlockInformation;
import io.github.mc_umod.api.IConduitBlock;
import io.github.mc_umod.api.IPlugabel;
import io.github.mc_umod.api.render.ISpiritProvider;
import io.github.mc_umod.tileentity.TileEntityItemPipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockItemPipe extends BlockBaseMachine implements ISpiritProvider, IConduitBlock, IPlugabel, IBlockInformation {
	
	private String spi;
	
	public BlockItemPipe(String sp) {
		this.spi = sp;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityItemPipe();
	}
	
	@Override
	public String getSpirte() {
		return spi;
	}
	
	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		return w.getTileEntity(p) instanceof ISidedInventory || w.getTileEntity(p) instanceof TileEntityItemPipe;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		collidingBoxes.add(this.getSelectedBoundingBox(state, worldIn, pos));
		super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn);
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}
	
	public boolean isFullCube() {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess w, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World w, BlockPos pos) {
		IPlugabel pip = (IPlugabel) w.getTileEntity(pos);
		if (pip != null) {
			boolean csouth = pip.canConnect(w, pos.south());
			boolean cnorth = pip.canConnect(w, pos.north());
			boolean cdown = pip.canConnect(w, pos.down());
			boolean cup = pip.canConnect(w, pos.up());
			boolean ceast = pip.canConnect(w, pos.east());
			boolean cwest = pip.canConnect(w, pos.west());
			float anfangunten = 0.3F;
			float anfnagoben = 0.7F;
			float anfangX = 0.3F;
			float endeX = 0.7F;
			float anfangZ = 0.3F;
			float endeZ = 0.7F;
			
			if (cup) {
				anfnagoben = 1;
			}
			if (cdown) {
				anfangunten = 0;
			}
			if (cwest) {
				anfangX = 0;
			}
			if (ceast) {
				endeX = 1;
			}
			if (cnorth) {
				anfangZ = 0;
			}
			if (csouth) {
				endeZ = 1;
			}
			
			return new AxisAlignedBB(anfangX, anfangunten, anfangZ, endeX, anfnagoben, endeZ);
		}
		return FULL_BLOCK_AABB;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tip, boolean advanced) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			tip.add("DONT USE IN DEV");
		} else {
			tip.add(ChatFormatting.RED + "LSHIFT" + ChatFormatting.GRAY + " for more Information");
		}
	}
	
}
