package io.github.mc_umod.tileentity;

import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.utils.WorldUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntitySolarPanel extends TileEntity implements ITickable, IEnergyProvider {
	
	
	private String error = null;
	private int pertick;
	private Energy energy;
	
	public TileEntitySolarPanel() {}
	
	public TileEntitySolarPanel(int pertick, int max, String str) {
		this.pertick = pertick;
		this.energy = new Energy(max, true, false);
	}
	
	@Override
	public void update() {
		if(this.energy == null)return;  
		if (!WorldUtils.isBlockover(worldObj, pos)) {
			this.error = "Can't see sky";
			return;
		}
		if (!this.worldObj.provider.isSurfaceWorld()) {
			this.error = "Your not in surface";
			return;
		}
		if (WorldUtils.isNight(worldObj)) {
			this.error = "It's night";
			return;
		}
		if (this.worldObj.isRaining()) {
			this.error = "It's rainig";
			return;
		}
		if (this.energy.receiveEnergy(this.pertick, true) >= this.pertick) {
			this.energy.receiveEnergy(this.pertick, false);
			this.error = null;
		} else {
			this.error = "Maximum storage reached";
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		this.energy.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.energy.readFromNBT(compound, this.worldObj);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tagCom = pkt.getNbtCompound();
		this.readFromNBT(tagCom);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tagCom = new NBTTagCompound();
		this.writeToNBT(tagCom);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tagCom);
	}

	@Override
	public Energy getEnergy() {
		return this.energy;
	}
	
}
