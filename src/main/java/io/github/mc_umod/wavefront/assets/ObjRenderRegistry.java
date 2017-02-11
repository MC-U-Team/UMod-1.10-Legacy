package io.github.mc_umod.wavefront.assets;

public class ObjRenderRegistry {
	
	public WavefrontLoader GENERATOR;
	
	public ObjRenderRegistry() {
		this.reload();
	}
	
	public void reload() {
		GENERATOR = new WavefrontLoader("generator");
	}
}
