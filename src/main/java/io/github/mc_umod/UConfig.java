package io.github.mc_umod;

import java.io.*;

import io.github.mc_umod.entity.*;
import net.minecraftforge.common.config.*;

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
