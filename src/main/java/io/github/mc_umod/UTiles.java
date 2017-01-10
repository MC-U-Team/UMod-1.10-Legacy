package io.github.mc_umod;

import io.github.mc_umod.tileentity.TileEntityCable;
import io.github.mc_umod.tileentity.TileEntityChargeStation;
import io.github.mc_umod.tileentity.TileEntityCraftFurnance;
import io.github.mc_umod.tileentity.TileEntityEnergyPannel;
import io.github.mc_umod.tileentity.TileEntityItemPipe;
import io.github.mc_umod.tileentity.TileEntityMagicCrafter;
import io.github.mc_umod.tileentity.TileEntityPainter;
import io.github.mc_umod.tileentity.TileEntityPulverizer;
import io.github.mc_umod.tileentity.TileEntitySolarPanel;
import io.github.mc_umod.tileentity.rail.TileEntityRail;
import net.hycrafthd.corelib.registry.TileEntityRegistry;

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
