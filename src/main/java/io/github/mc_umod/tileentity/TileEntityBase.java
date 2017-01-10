package io.github.mc_umod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;

public abstract class TileEntityBase extends TileEntity implements ISidedInventory, IInteractionObject {
	
	protected String customname = null;
	
	@Override
	public final boolean hasCustomName() {
		return customname != null;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
	}
	
	@Override
	public final ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}
	
	public final String ITEM_NBT = "items_nbt", ENERGY_NBT = "energy_nbt", INT_ENERGY = "Energy", IO_NBT = "io_nbt", OTHER_NBT = "other_nbt";
	
	@Override
	public final void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tagCom = pkt.getNbtCompound();
		this.readFromNBT(tagCom);
	}
	
	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tagCom = new NBTTagCompound();
		this.writeToNBT(tagCom);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tagCom);
	}
	
	@Override
	public final NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
}
