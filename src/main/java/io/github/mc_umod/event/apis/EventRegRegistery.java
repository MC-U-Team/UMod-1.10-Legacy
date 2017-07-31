package io.github.mc_umod.event.apis;

import java.util.ArrayList;

import io.github.mc_umod.api.energy.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EventRegRegistery {
	
	public ArrayList<BlockPos> cabs = new ArrayList<BlockPos>();
	private TunnelHolder holder;
	
	public EventRegRegistery() {
		this.holder = TunnelHolder.INSTANCE;
	}
	
	@SubscribeEvent
	public void onTick(WorldTickEvent evt){
		final World u = evt.world;
		if(u == null)return;
		for(BlockPos t : cabs) {
				TileEntity ths = (TileEntity) u.getTileEntity(t);
				if(ths == null || !(ths instanceof ICabel)) continue;
				ICabel ccab = (ICabel) u.getTileEntity(t);
				if(ccab == null)continue;
				int tun = ccab.getTunnel();
				World worldObj = u;
				BlockPos pos = t;
				if (tun > -1) {
					for (EnumFacing fac : EnumFacing.values()) {
						TileEntity ent = worldObj.getTileEntity(t.offset(fac));
						if (ent != null && ent instanceof ICabel) {
							ICabel cab = (ICabel) ent;
							if (cab.getTunnel() != tun) {
								if (cab.getTunnel() > -1) {
									holder.merge(tun, cab.getTunnel(), worldObj);
									return;
								}
							}
						}
					}
				}
				if (tun > -1)
					return;
				for (EnumFacing fac : EnumFacing.values()) {
					TileEntity ent = worldObj.getTileEntity(t.offset(fac));
					if (ent != null && ent instanceof ICabel) {
						ICabel cab = (ICabel) ent;
						if (cab.getTunnel() > -1 && holder.getUETunnel(cab.getTunnel()) != null) {
							holder.getUETunnel(cab.getTunnel()).add(ccab);
							continue;
						}
					}
				}
				if (tun > -1)
					continue;
				if (holder.contains(pos)) {
					ccab.setTunnel(holder.getTunnelFromPos(pos));
					continue;
				}
				UETunnel tnl = new UETunnel(worldObj);
				holder.getUETunnel(holder.addUETunnel(tnl)).add(ccab);
		}
		for (int i = 0; i < this.holder.getMax(); i++) {
			this.holder.getUETunnel(i).onTick();
		}
	}
	
	@SubscribeEvent
	public void onRegisterEnergy(EnergyRegisterEvent ev){
		cabs.add(ev.pos);
	}
	
	@SubscribeEvent
	public void onUnregisterEnergy(EnergyUnregisterEvent ev){
		cabs.remove(ev.pos);
	}
}
