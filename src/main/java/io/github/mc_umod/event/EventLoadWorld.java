package io.github.mc_umod.event;

import net.minecraft.world.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class EventLoadWorld {
	
	@SubscribeEvent
	public void onEvent(WorldEvent.Load event) {
		event.getWorld().getGameRules().addGameRule("allowExplosion", "true", GameRules.ValueType.BOOLEAN_VALUE);
	}
	
}
