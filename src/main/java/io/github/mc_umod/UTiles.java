package io.github.mc_umod;

import io.github.mc_umod.corelib.api.*;
import io.github.mc_umod.tileentity.*;
import io.github.mc_umod.tileentity.rail.*;

public class UTiles {
	
	public CommonRegistry reg;
	
	public UTiles() {
		register();
	}
	
	public void register() {
		this.reg.registerTileEntity(TileEntityPulverizer.class, "tilepulver");
		this.reg.registerTileEntity(TileEntitySolarPanel.class, "tilesolar");
		this.reg.registerTileEntity(TileEntityCable.class, "tilepipe");
		this.reg.registerTileEntity(TileEntityChargeStation.class, "tilecharge");
		this.reg.registerTileEntity(TileEntityCraftFurnance.class, "tilecraftfurn");
		this.reg.registerTileEntity(TileEntityEnergyPannel.class, "tileenergymonitor");
		this.reg.registerTileEntity(TileEntityPainter.class, "tilepainter");
		this.reg.registerTileEntity(TileEntityRail.class, "tilereail");
		this.reg.registerTileEntity(TileEntityMagicCrafter.class, "tile_magic_crafter");
		this.reg.registerTileEntity(TileEntityItemPipe.class, "tile_item_pipe");
		UMod.log.debug("Register TileEntitys");
	}
	
}
