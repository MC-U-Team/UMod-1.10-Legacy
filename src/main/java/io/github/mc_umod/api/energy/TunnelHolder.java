package io.github.mc_umod.api.energy;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

@Singelton
public class TunnelHolder {
	
	private ArrayList<UETunnel> tunnels = new ArrayList<UETunnel>();
	
	public int getMax() {
		return tunnels.size();
	}
	
	public int addUETunnel(UETunnel tn) {
		int num = tunnels.size();
		tn.setID(num);
		tunnels.add(tn);
		return num;
	}
	
	public boolean contains(BlockPos p) {
		for (UETunnel tnl : tunnels) {
			if (tnl.contains(p)) {
				return true;
			}
		}
		return false;
	}
	
	public int getTunnelFromPos(BlockPos p) {
		for (UETunnel tnl : tunnels) {
			if (tnl.contains(p)) {
				return tnl.getID();
			}
		}
		return -1;
	}
	
	public UETunnel getUETunnel(int i) {
		if (i >= tunnels.size() || i < 0)
			return null;
		return tunnels.get(i);
	}
	
	public int merge(int i, int i2, World w) {
		if (i == i2)
			return i;
		if (this.getUETunnel(i) == null || this.getUETunnel(i2) == null)
			return -1;
		ArrayList<BlockPos> psses = new ArrayList<BlockPos>(this.getUETunnel(i));
		psses.addAll(this.getUETunnel(i2));
		if (i > i2) {
			tunnels.remove(i);
			tunnels.remove(i2);
		} else {
			tunnels.remove(i2);
			tunnels.remove(i);
		}
		int id = this.addUETunnel(new UETunnel(w));
		for (BlockPos pos : psses) {
			ICabel cab = (ICabel) w.getTileEntity(pos);
			this.getUETunnel(id).add(cab);
		}
		UMod.log.debug("Merged UETunnel " + i + " and " + i2);
		return id;
	}
	
	public int regenUETunnel(int id, World w) {
		tunnels.remove(id);
		for (int y = 0; y < tunnels.size(); y++) {
			tunnels.get(y).setID(y);
			UETunnel tnl = tunnels.get(y);
			for (BlockPos cab : tnl) {
				ICabel cabs = (ICabel) tnl.getWorld().getTileEntity((BlockPos) cab);
				cabs.setTunnel(tnl.getID());
			}
		}
		return id;
	}
	
	public boolean remove(int id) {
		if (this.getUETunnel(id) == null)
			return true;
		if (this.getUETunnel(id).size() <= 0) {
			tunnels.remove(id);
			for (int y = 0; y < tunnels.size(); y++) {
				tunnels.get(y).setID(y);
				UETunnel tnl = tunnels.get(y);
				for (BlockPos cab : tnl) {
					ICabel cabs = (ICabel) tnl.getWorld().getTileEntity((BlockPos) cab);
					if (cabs == null)
						continue;
					cabs.setTunnel(tnl.getID());
				}
			}
			return true;
		}
		return false;
	}
}
