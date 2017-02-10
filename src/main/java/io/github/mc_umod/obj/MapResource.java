package io.github.mc_umod.obj;

import io.github.mc_umod.*;
import net.minecraft.util.*;

/**
 * TEMPORARY FOR {@link Texture}
 * <br>
 * @author MrTroble
 */

public class MapResource extends ResourceLocation{

	public MapResource(String name) {
		super(UReference.modid, "textures/maps/" + name);
	}

}
