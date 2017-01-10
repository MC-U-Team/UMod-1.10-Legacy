package io.github.mc_umod;

import io.github.mc_umod.tileentity.*;
import io.github.mc_umod.tileentity.rail.*;
import net.hycrafthd.corelib.registry.*;

public class UTiles {
	
	public UTiles() {
		register();
	}
	
	public void register() {
		TileEntityRegistry.register(TileEntityPulverizer.class, "tilepulver");
		TileEntityRegistry.register(TileEntitySolarPanel.class, "tilesolar");
		TileEntityRegistry.register(TileEntityCable.class, "tilepipe");
		TileEntityRegistry.register(TileEntityChargeStation.class, "tilecharge");
		TileEntityRegistry.register(TileEntityCraftFurnance.class, "tilecraftfurn");
		TileEntityRegistry.register(TileEntityEnergyPannel.class, "tileenergymonitor");
		TileEntityRegistry.register(TileEntityPainter.class, "tilepainter");
		TileEntityRegistry.register(TileEntityRail.class, "tilereail");
		TileEntityRegistry.register(TileEntityMagicCrafter.class, "tile_magic_crafter");
		TileEntityRegistry.register(TileEntityItemPipe.class, "tile_item_pipe");
		UMod.log.debug("Register TileEntitys");
	}
	
}
