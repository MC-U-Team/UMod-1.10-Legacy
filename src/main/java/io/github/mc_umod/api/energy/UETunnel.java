package io.github.mc_umod.api.energy;

import java.util.*;

import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class UETunnel extends ArrayList<BlockPos> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6142151165474828749L;
	private int id = -1;
	private World w;
	
	public UETunnel(World w) {
		this.w = w;
	}
	
	public boolean add(ICabel e) {
		if (TunnelHolder.contains(e.getPos()))
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
					if (ent != null && ent instanceof IPowerProvieder && ((IPowerProvieder) ent).isOutput()) {
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
				if (ent != null && ent instanceof IPowerProvieder && ((IPowerProvieder) ent).isInput()) {
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
		if (TunnelHolder.remove(id))
			return;
		ICabel[] outs = this.getOutput();
		ICabel[] inpts = this.getInput();
		double max = 0;
		for (ICabel cab : inpts) {
			ArrayList<BlockPos> ins = new ArrayList<BlockPos>();
			for (EnumFacing face : EnumFacing.values()) {
				TileEntity ent = cab.getWorld().getTileEntity(cab.getPos().add(face.getDirectionVec()));
				if (ent != null && ent instanceof IPowerProvieder && ((IPowerProvieder) ent).isInput()) {
					ins.add(ent.getPos());
				}
			}
			for (BlockPos p : ins) {
				IPowerProvieder pro = (IPowerProvieder) this.w.getTileEntity(p);
				if (0 <= pro.getStoredPower() - cab.getRate()) {
					pro.getPower(cab.getRate());
					max += cab.getRate();
				} else {
					double d = pro.getStoredPower();
					pro.getPower(d);
					max += d;
				}
			}
		}
		for (ICabel cab : outs) {
			ArrayList<BlockPos> out = new ArrayList<BlockPos>();
			for (EnumFacing face : EnumFacing.values()) {
				TileEntity ent = cab.getWorld().getTileEntity(cab.getPos().add(face.getDirectionVec()));
				if (ent != null && ent instanceof IPowerProvieder && ((IPowerProvieder) ent).isOutput()) {
					out.add(ent.getPos());
				}
			}
			for (BlockPos p : out) {
				IPowerProvieder pro = (IPowerProvieder) this.w.getTileEntity(p);
				if (max <= 0)
					return;
				if (pro.getMaximalPower() > pro.getStoredPower() + cab.getRate()) {
					if (max < cab.getRate()) {
						pro.addPower(max);
						max = 0;
						return;
					}
					pro.addPower(cab.getRate());
					max -= cab.getRate();
				} else {
					double d = pro.getMaximalPower() - pro.getStoredPower();
					if (max < d) {
						pro.addPower(max);
						max = 0;
						return;
					}
					pro.addPower(d);
					max -= d;
				}
			}
		}
		if (max > 0) {
			for (ICabel cab : inpts) {
				ArrayList<BlockPos> ins = new ArrayList<BlockPos>();
				for (EnumFacing face : EnumFacing.values()) {
					TileEntity ent = cab.getWorld().getTileEntity(cab.getPos().add(face.getDirectionVec()));
					if (ent != null && ent instanceof IPowerProvieder && ((IPowerProvieder) ent).isInput()) {
						ins.add(ent.getPos());
					}
				}
				for (BlockPos p : ins) {
					IPowerProvieder pro = (IPowerProvieder) this.w.getTileEntity(p);
					if (max <= 0)
						return;
					if (pro.getMaximalPower() > pro.getStoredPower() + max) {
						pro.addPower(max);
						max = 0;
						return;
					} else {
						double d = pro.getMaximalPower() - pro.getStoredPower();
						pro.addPower(d);
						max -= d;
					}
				}
			}
		}
	}
}
