package io.github.mc_umod.event;

import io.github.mc_umod.*;
import io.github.mc_umod.obj.*;
import net.minecraft.client.resources.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ResourceReload  implements IResourceManagerReloadListener{

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		UReference.getClientProxy().getObjRenderList().reload();
	}
	
}
