package io.github.mc_umod.api.energy;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

public class UETunnel extends ArrayList<BlockPos> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6142151165474828749L;
	private int id = -1;
	private World w;
	private TunnelHolder holder;
	
	public UETunnel(World w) {
		this.w = w;
		this.holder = TunnelHolder.INSTANCE;
	}
	
	public boolean add(ICabel e) {
		if (e == null || this.holder == null || this.holder.contains(e.getPos()))
			return false;
		e.setTunnel(this.id);
		return this.add(e.getPos());
	}
	
	public ICabel[] getOutput() {
		ArrayList<ICabel> cabs = new ArrayList<ICabel>();
		ArrayList<BlockPos> remos = new ArrayList<BlockPos>();
		for (BlockPos pos : this) {
			ICabel cab = (ICabel) w.getTileEntity(pos);
			if (cab == null) {
				remos.add(pos);
			} else {
				for (EnumFacing face : EnumFacing.values()) {
					TileEntity ent = cab.getWorld().getTileEntity(cab.getPos().add(face.getDirectionVec()));
					if (ent != null && ent instanceof IEnergyProvider && ((IEnergyProvider) ent).getEnergy().canReceive()) {
						cabs.add(cab);
					}
				}
			}
		}
		for (BlockPos pos : remos) {
			this.remove(pos);
		}
		ICabel[] outputs = new ICabel[cabs.size()];
		int i = 0;
		for (ICabel cab : cabs) {
			outputs[i] = cab;
			i++;
		}
		return outputs;
	}
	
	public ICabel[] getInput() {
		ArrayList<ICabel> cabs = new ArrayList<ICabel>();
		for (BlockPos pos : this) {
			ICabel cab = (ICabel) w.getTileEntity(pos);
			ArrayList<BlockPos> ins = new ArrayList<BlockPos>();
			for (EnumFacing face : EnumFacing.values()) {
				TileEntity ent = cab.getWorld().getTileEntity(cab.getPos().add(face.getDirectionVec()));
				if (ent != null && ent instanceof IEnergyProvider && ((IEnergyProvider) ent).getEnergy().canExtract()) {
					cabs.add(cab);
				}
			}
		}
		ICabel[] inputs = new ICabel[cabs.size()];
		int i = 0;
		for (ICabel cab : cabs) {
			inputs[i] = cab;
			i++;
		}
		return inputs;
	}
	
	public void setID(int id) {
		this.id = id;
		for (BlockPos pos : this) {
			ICabel cab = (ICabel) w.getTileEntity(pos);
			if (cab != null) {
				cab.setTunnel(id);
			}
		}
	}
	
	public int getID() {
		return id;
	}
	
	public World getWorld() {
		return w;
	}
	
	public void onTick() {
		if (this.holder.remove(id))
			return;
		ICabel[] outs = this.getOutput();
		ICabel[] inpts = this.getInput();
		int max = 0;
		int acmax = 0;
		for (ICabel cab : inpts) {
			ArrayList<BlockPos> ins = new ArrayList<BlockPos>();
			for (EnumFacing face : EnumFacing.values()) {
				TileEntity ent = cab.getWorld().getTileEntity(cab.getPos().offset(face));
				if (ent != null && ent instanceof IEnergyProvider && ((IEnergyProvider) ent).getEnergy().canExtract()) {
					ins.add(ent.getPos());
				}
			}
			for (BlockPos p : ins) {
				IEnergyStorage pro = ((IEnergyProvider) this.w.getTileEntity(p)).getEnergy();
				max += pro.extractEnergy(cab.getRate(), true);
			}
		}
		for (ICabel cab : outs) {
			ArrayList<BlockPos> out = new ArrayList<BlockPos>();
			for (EnumFacing face : EnumFacing.values()) {
				TileEntity ent = cab.getWorld().getTileEntity(cab.getPos().offset(face));
				if (ent != null && ent instanceof IEnergyProvider && ((IEnergyProvider) ent).getEnergy().canReceive()) {
					out.add(ent.getPos());
				}
			}
			for (BlockPos p : out) {
				IEnergyStorage pro = ((IEnergyProvider) this.w.getTileEntity(p)).getEnergy();
				int enex = pro.receiveEnergy(max, false);
				acmax += enex;
				max -= enex;
			}
		}
		for (ICabel cab : inpts) {
			ArrayList<BlockPos> ins = new ArrayList<BlockPos>();
			for (EnumFacing face : EnumFacing.values()) {
				TileEntity ent = cab.getWorld().getTileEntity(cab.getPos().offset(face));
				if (ent != null && ent instanceof IEnergyProvider && ((IEnergyProvider) ent).getEnergy().canExtract()) {
					ins.add(ent.getPos());
				}
			}
			for (BlockPos p : ins) {
				IEnergyStorage pro = ((IEnergyProvider) this.w.getTileEntity(p)).getEnergy();
				acmax -= pro.extractEnergy(acmax, false);
			}
		}
	}
}
