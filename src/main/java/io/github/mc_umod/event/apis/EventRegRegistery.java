package io.github.mc_umod.event.apis;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

import io.github.mc_umod.api.energy.ICabel;
import io.github.mc_umod.api.energy.TunnelHolder;
import io.github.mc_umod.api.energy.UETunnel;
import io.github.mc_umod.entity.EntityFX;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

public class EventRegRegistery {
	
	public HashMap<BlockPos,World> cabs = new HashMap<BlockPos,World>();
	
	@SubscribeEvent
	public void onTick(ServerTickEvent evt){
		cabs.forEach(new BiConsumer<BlockPos, World>() {

			@Override
			public void accept(BlockPos t, World u) {	
				ICabel ccab = (ICabel) u.getTileEntity(t);
				if(ccab == null)return;
				int tun = ccab.getTunnel();
				World worldObj = u;
				BlockPos pos = t;
				TileEntity[] args = new TileEntity[] { worldObj.getTileEntity(pos.up()), worldObj.getTileEntity(pos.down()), worldObj.getTileEntity(pos.north()), worldObj.getTileEntity(pos.south()), worldObj.getTileEntity(pos.east()), worldObj.getTileEntity(pos.west()) };
				if (tun > -1) {
					for (TileEntity ent : args) {
						if (ent != null && ent instanceof ICabel) {
							ICabel cab = (ICabel) ent;
							if (cab.getTunnel() != tun) {
								if (cab.getTunnel() > -1) {
									TunnelHolder.merge(tun, cab.getTunnel(), worldObj);
								}
							}
						}
					}
				}
				if (tun > -1)
					return;
				for (TileEntity ent : args) {
					if (ent != null && ent instanceof ICabel) {
						ICabel cab = (ICabel) ent;
						if (cab.getTunnel() > -1 && TunnelHolder.getUETunnel(cab.getTunnel()) != null) {
							TunnelHolder.getUETunnel(cab.getTunnel()).add(ccab);
							return;
						}
					}
				}
				if (tun > -1)
					return;
				if (TunnelHolder.contains(pos)) {
					ccab.setTunnel(TunnelHolder.getTunnelFromPos(pos));
					return;
				}
				UETunnel tnl = new UETunnel(worldObj);
				TunnelHolder.getUETunnel(TunnelHolder.addUETunnel(tnl)).add(ccab);
			}
		});
		for (int i = 0; i < TunnelHolder.getMax(); i++) {
			TunnelHolder.getUETunnel(i).onTick();
		}
	}
	
	@SubscribeEvent
	public void onRegisterEnergy(EnergyRegisterEvent ev){
		cabs.put(ev.pos,ev.worldObj);
	}
	
	@SubscribeEvent
	public void onUnregisterEnergy(EnergyUnregisterEvent ev){
		cabs.remove(ev.pos);
	}
	
	@SubscribeEvent
	public void onRegisterRender(RenderEntityRegisterEvent e){
		e.worldObj.spawnEntityInWorld(new EntityFX(e.worldObj, e.pos));
	}
	
	@SubscribeEvent
	public void onUnregisterRender(RenderEntityClearEvent e){
		@SuppressWarnings("unchecked")
		List<EntityFX> p = e.worldObj.getEntitiesWithinAABB(EntityFX.class, new AxisAlignedBB(e.pos, e.pos.add(1, 1, 1)));
		for (EntityFX fx : p) {
			fx.setDead();
		}
	}
}
