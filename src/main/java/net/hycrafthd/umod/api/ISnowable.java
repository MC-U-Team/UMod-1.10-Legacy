package net.hycrafthd.umod.api;

import net.hycrafthd.umod.render.TileRender;

public interface ISnowable {
	
	/**
	 * Future API for snow on costume render
	 * @author MrTroble
	 */
	
	public TileRender getRender();
	
	public boolean hasSnow();
	
	public void setSnow();
}
