package io.github.mc_umod;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class UConfig {
	
	private Configuration config;
	
	public UConfig(File file) {
		this.config = new Configuration(file);
		this.init();
	}
	
	private void init() {
		UMod.log.debug("Loading Config now.");
		this.config.load();
		UMod.log.debug("Finished loading Config.");
	}
	
	public Configuration getConfig(){
		return config;
	}
	
}
