package io.github.mc_umod.api;

import io.github.mc_umod.render.*;

public interface ISnowable {
	
	/**
	 * Future API for snow on costume render
	 * @author MrTroble
	 */
	
	public TileRender getRender();
	
	public boolean hasSnow();
	
	public void setSnow();
}
