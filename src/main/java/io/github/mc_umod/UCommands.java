package io.github.mc_umod;

import io.github.mc_umod.command.*;
import net.minecraftforge.fml.common.event.*;

public class UCommands {
	
	public UCommands(FMLServerStartingEvent e) {
		register(e);
	}
	
	private void register(FMLServerStartingEvent e) {
		// e.registerServerCommand(new CommandUSchematic());
		e.registerServerCommand(new CommandConduit());
	}
	
}
