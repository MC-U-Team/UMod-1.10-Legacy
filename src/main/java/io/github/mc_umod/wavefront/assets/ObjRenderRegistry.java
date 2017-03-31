package io.github.mc_umod.wavefront.assets;

import net.minecraft.client.resources.*;

public class ObjRenderRegistry implements IResourceManagerReloadListener{
	
	public WavefrontLoader GENERATOR;
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		GENERATOR = new WavefrontLoader("generator");
	}
}
