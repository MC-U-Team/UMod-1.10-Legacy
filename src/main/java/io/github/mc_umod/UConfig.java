package io.github.mc_umod;

import java.io.*;

import io.github.mc_umod.entity.*;
import net.minecraftforge.common.config.*;

public class UConfig {
	
	public Configuration config;
	private File configFile;
	
	public UConfig(File file) {
		this.configFile = file;
		config = new Configuration(this.configFile);
		init();
	}
	
	private void init() {
		UMod.log.debug("Loading Config now.");
		this.config.load();
		load();
		this.config.save();
		UMod.log.debug("Finished loading Config.");
	}
	
	private void load() {
		EntityNukePrimed.fuseSec = this.config.getInt("fuse", "Nuke", 24, 10, 60, "The fuse time for the Nuke in seconds");
		EntityNukePrimed.nukePower = this.config.getInt("power", "Nuke", 5000, 50, 15000, "The nuke power");
	}
	
}
