package net.hycrafthd.umod.event;

import net.hycrafthd.umod.UReference;
import net.minecraft.client.resources.*;

public class ResourceReload implements IResourceManagerReloadListener {
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		UReference.getClientProxy().getObjRenderList().reload();
	}
	
}
