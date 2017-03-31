package io.github.mc_umod.tileentity;

import java.util.*;

import io.github.mc_umod.api.*;
import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.event.apis.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.play.server.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.common.*;

public class TileEntityCable extends TileEntity implements IPlugabel, ICabel, IConduitProvider,ITickable{
	
	public ItemStack conduit = null;
	public int tun = -1;
	public double rate;
	
	public TileEntityCable() {}
	
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
	
	public TileEntityCable(double maxpower, int pipelooseone) {
		rate = maxpower;
	}
	
	@Override
	public void setConduit(ItemStack b) {
		conduit = b;
	}
	
	@Override
	public boolean hasConduit() {
		return conduit != null;
	}
	
	@Override
	public ItemStack getConduit() {
		return conduit;
	}
	
	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		TileEntity et = w.getTileEntity(p);
		if (et instanceof IPowerProvieder || et instanceof ICabel) {
			return true;
		}
		return false;
	}
	
	@Override
	public NBTTagCompound getTileData() {
		return ((SPacketUpdateTileEntity) getUpdatePacket()).getNbtCompound();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setDouble("Rate", rate);
		super.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.rate = compound.getDouble("Rate");
		super.readFromNBT(compound);
	}
			
	@Override
	public int getTunnel() {
		return this.tun;
	}
		
	@Override
	public void setTunnel(int i) {
		this.tun = i;
	}
	
	@Override
	public double getRate() {
		return rate;
	}
	
	@Override
	public void onLoad() {
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		MinecraftForge.EVENT_BUS.post(new RenderEntityClearEvent(worldObj,pos));		
		MinecraftForge.EVENT_BUS.post(new EnergyUnregisterEvent(worldObj,pos));
	}

	private boolean firsttick = true;
	
	@Override
	public void update() {
		if(firsttick){
			MinecraftForge.EVENT_BUS.post(new RenderEntityRegisterEvent(worldObj,pos));
			MinecraftForge.EVENT_BUS.post(new EnergyRegisterEvent(worldObj,pos));
			firsttick = false;
		}
	}
	
	
	
}
