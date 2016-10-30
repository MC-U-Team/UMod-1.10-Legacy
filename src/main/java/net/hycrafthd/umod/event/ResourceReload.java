package net.hycrafthd.umod.event;

import net.hycrafthd.umod.ClientProxy;
import net.hycrafthd.umod.ObjRenderregister;
import net.minecraft.client.resources.*;

public class ResourceReload implements IResourceManagerReloadListener {
	
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		ClientProxy.regs = new ObjRenderregister();
	}
	
}
