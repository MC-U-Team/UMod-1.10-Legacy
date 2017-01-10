package io.github.mc_umod.event;

import io.github.mc_umod.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.*;

public class EventPlayerJoin {
	
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		player.addStat(UAchievement.firstjoin, 1);
	}
	
}
