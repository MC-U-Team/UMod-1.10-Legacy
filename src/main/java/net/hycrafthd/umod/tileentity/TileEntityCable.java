package net.hycrafthd.umod.tileentity;

import java.util.ArrayList;

import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.api.energy.*;
import net.hycrafthd.umod.event.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;

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
	public String getEnergyClass() {
		return "";
	}
	
	protected boolean isout = false, isin = false;
	
	@Override
	public boolean isInput() {
		return getInputs().length > 0;
	}
	
	@Override
	public boolean isOutput() {
		return getOutputs().length > 0;
	}
	
	@Override
	public void addToTunnel(ICabel cab) {
		TunnelHolder.getUETunnel(tun).add(cab);
	}
	
	@Override
	public ICabel[] getOutputsFromTunnel() {
		return TunnelHolder.getUETunnel(tun).getOutput();
	}
	
	@Override
	public ICabel[] getInputsFromTunnel() {
		return TunnelHolder.getUETunnel(tun).getInput();
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
	
	@Override
	public BlockPos[] getInputs() {
		ArrayList<BlockPos> ins = new ArrayList<BlockPos>();
		TileEntity[] args = new TileEntity[] { worldObj.getTileEntity(this.pos.up()), worldObj.getTileEntity(this.pos.down()), worldObj.getTileEntity(this.pos.north()), worldObj.getTileEntity(this.pos.south()), worldObj.getTileEntity(this.pos.east()), worldObj.getTileEntity(this.pos.west()) };
		for (TileEntity ent : args) {
			if (ent != null && ent instanceof IPowerProvieder && ((IPowerProvieder) ent).isInput()) {
				ins.add(ent.getPos());
			}
		}
		BlockPos[] posses = new BlockPos[ins.size()];
		int i = 0;
		for (BlockPos pos : ins) {
			posses[i] = pos;
			i++;
		}
		return posses;
	}
	
	@Override
	public BlockPos[] getOutputs() {
		ArrayList<BlockPos> ins = new ArrayList<BlockPos>();
		TileEntity[] args = new TileEntity[] { worldObj.getTileEntity(this.pos.up()), worldObj.getTileEntity(this.pos.down()), worldObj.getTileEntity(this.pos.north()), worldObj.getTileEntity(this.pos.south()), worldObj.getTileEntity(this.pos.east()), worldObj.getTileEntity(this.pos.west()) };
		for (TileEntity ent : args) {
			if (ent != null && ent instanceof IPowerProvieder && ((IPowerProvieder) ent).isOutput()) {
				ins.add(ent.getPos());
			}
		}
		BlockPos[] posses = new BlockPos[ins.size()];
		int i = 0;
		for (BlockPos pos : ins) {
			posses[i] = pos;
			i++;
		}
		return posses;
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
