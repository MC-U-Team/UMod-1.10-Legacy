package io.github.mc_umod.renderapi;

import io.github.mc_umod.*;
import net.minecraft.util.*;

/**
 * Location of all Textures
 * @author MrTroble
 */

public class MapResource extends ResourceLocation{

	public MapResource(String name) {
		super(UReference.modid, "textures/maps/" + name);
	}

}
