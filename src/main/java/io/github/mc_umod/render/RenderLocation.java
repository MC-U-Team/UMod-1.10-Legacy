package io.github.mc_umod.render;

import io.github.mc_umod.UReference;
import net.minecraft.util.ResourceLocation;

public class RenderLocation extends ResourceLocation {
	
	public RenderLocation(String st) {
		super(UReference.modid, "textures/render/" + st);
	}
	
}
