package io.github.mc_umod.event.apis;

import java.util.List;

import io.github.mc_umod.entity.EntityFX;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventRenderRegistery {

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
