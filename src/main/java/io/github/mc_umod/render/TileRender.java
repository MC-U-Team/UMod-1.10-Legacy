package io.github.mc_umod.render;

import io.github.mc_umod.UReference;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public abstract class TileRender {
	
	public final GLHelper help;
	public final ConduitRender crender;
	
	public TileRender(GLHelper help) {
		this.help = help;
		this.crender = new ConduitRender(this.help,UReference.getClientProxy().getModelRenderHelper());
	}
	
	public void render(TileEntity tile,double posX, double posY, double posZ) {
		if (crender.render(tile,Minecraft.getMinecraft().thePlayer, posX, posY, posZ))
			return;
		this.renderTileEntityAt(tile,posX, posY, posZ);
	}
	
	public abstract void renderTileEntityAt(TileEntity tile,double posX, double posY, double posZ);
	
}
