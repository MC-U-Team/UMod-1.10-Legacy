package io.github.mc_umod.event;

import io.github.mc_umod.*;
import net.minecraft.client.resources.*;

public class ResourceReload  implements IResourceManagerReloadListener{

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		UReference.getClientProxy().getObjRenderList().reload();
	}
	
}
