package io.github.mc_umod.renderapi;

import net.minecraft.util.ResourceLocation;

/**
 * Location of all Textures
 * 
 * @author MrTroble
 */

public class MapResource extends ResourceLocation {
	
	public MapResource(String mod, String name) {
		super(mod, "textures/maps/" + name);
	}
	
}
