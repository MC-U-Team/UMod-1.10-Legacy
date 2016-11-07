package net.hycrafthd.umod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public abstract class TileRender {
	
	protected GLHelper help;
	
	public TileRender(GLHelper help) {
		this.help = help;
	}
	
	public void render(TileEntity p_180535_1_, double posX, double posY, double posZ) {
		if (new ConduitRender().render(p_180535_1_, Minecraft.getMinecraft().thePlayer, posX, posY, posZ))
			return;
		this.renderTileEntityAt(p_180535_1_, posX, posY, posZ);
	}
	
	public abstract void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posY, double posZ);
	
}
