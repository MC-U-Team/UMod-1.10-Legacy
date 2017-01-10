package io.github.mc_umod.api;

import java.util.*;

import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;

public class ProcessHandler {
	
	private static List<IProcess> processes = new ArrayList<IProcess>();
	private static List<IProcess> newProcesses = new ArrayList<IProcess>();
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			
			Iterator<IProcess> i = processes.iterator();
			
			while (i.hasNext()) {
				IProcess process = i.next();
				if (process.isDead())
					i.remove();
				else
					process.updateProcess();
			}
			
			if (!newProcesses.isEmpty()) {
				processes.addAll(newProcesses);
				newProcesses.clear();
			}
		}
	}
	
	@SubscribeEvent
	public void onWorldClose(WorldEvent.Unload event) {
		processes.clear();
		newProcesses.clear();
	}
	
	public static void addProcess(IProcess process) {
		newProcesses.add(process);
	}
}
