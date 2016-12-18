package net.hycrafthd.umod.event;

import java.util.List;

import net.hycrafthd.umod.api.energy.ICabel;
import net.hycrafthd.umod.api.energy.TunnelHolder;
import net.hycrafthd.umod.api.energy.UETunnel;
import net.hycrafthd.umod.entity.EntityFX;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EventRegRegistery {
	
	@SubscribeEvent
	public void onRegisterEnergy(EnergyRegisterEvent ev){
		ICabel ccab = ev.cab;
		World worldObj = ev.w;
		BlockPos pos = ccab.getPos();
		TileEntity[] args = new TileEntity[] { worldObj.getTileEntity(pos.up()), worldObj.getTileEntity(pos.down()), worldObj.getTileEntity(pos.north()), worldObj.getTileEntity(pos.south()), worldObj.getTileEntity(pos.east()), worldObj.getTileEntity(pos.west()) };
		if (ccab.getTunnelIDofCabel() > -1) {
			for (TileEntity ent : args) {
				if (ent != null && ent instanceof ICabel) {
					ICabel cab = (ICabel) ent;
					if (cab.getTunnelIDofCabel() != ccab.getTunnelIDofCabel()) {
						if (cab.getTunnelIDofCabel() > -1) {
							TunnelHolder.merge(ccab.getTunnelIDofCabel(), cab.getTunnelIDofCabel(), worldObj);
						}
					}
				}
			}
		}
		if (ccab.getTunnelIDofCabel() > -1)
			return;
		for (TileEntity ent : args) {
			if (ent != null && ent instanceof ICabel) {
				ICabel cab = (ICabel) ent;
				if (cab.getTunnelIDofCabel() > -1 && TunnelHolder.getUETunnel(cab.getTunnelIDofCabel()) != null) {
					TunnelHolder.getUETunnel(cab.getTunnelIDofCabel()).add(ccab);
					return;
				}
			}
		}
		if (ccab.getTunnelIDofCabel() > -1)
			return;
		if (TunnelHolder.contains(pos)) {
			ccab.setTunnelID(TunnelHolder.getTunnelFromPos(pos));
			return;
		}
		UETunnel tnl = new UETunnel(worldObj);
		TunnelHolder.getUETunnel(TunnelHolder.addUETunnel(tnl)).add(ccab);
	}
	
	@SubscribeEvent
	public void onRegisterRender(RenderEntityRegisterEvent e){
		e.worldObj.spawnEntityInWorld(new EntityFX(e.worldObj, e.pos));
	}
	
	@SubscribeEvent
	public void onRegisterRender(RenderEntityClearEvent e){
		@SuppressWarnings("unchecked")
		List<EntityFX> p = e.worldObj.getEntitiesWithinAABB(EntityFX.class, new AxisAlignedBB(e.pos, e.pos.add(1, 1, 1)));
		for (EntityFX fx : p) {
			fx.setDead();
		}
	}
}
