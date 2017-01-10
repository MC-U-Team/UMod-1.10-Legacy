package io.github.mc_umod.render;

import org.lwjgl.opengl.*;

import io.github.mc_umod.*;
import io.github.mc_umod.gui.items.*;
import io.github.mc_umod.tileentity.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.entity.item.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;

public class TileEntityPulverizerSpecialRender extends TileEntitySpecialRenderer<TileEntity> {
	
	private GLHelper help;
	
	public TileEntityPulverizerSpecialRender() {
		this.help = UReference.getClientProxy().getGLHelper();
	}
	
	public int time = 0;
	private EntityItem ent = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D);
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, final double posX, final double posY, final double posZ, float p_180535_8_, final int p_180535_9_) {
		InfoFieldRender.INSTANCE.render(tileEntity, posX, posY, posZ);
		TileEntityPulverizer pul = (TileEntityPulverizer) tileEntity;
		ItemStack ore = pul.getStackInSlot(3);
		
		if (ore != null) {
			GlStateManager.pushMatrix();
			if (pul.work) {
				if (time >= 360) {
					time = 0;
				}
				GlStateManager.translate(posX + 0.5, posY + 0.1, posZ + 0.5);
				GlStateManager.rotate(time * 2, 0F, 1.0F, 0F);
				this.help.drawTexturedCube(new GuiRescources("laser.png"), -0.025, 0, -0.025, 0.05, 0.8, 0.05);
				time++;
			}
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(posX + 0.5, posY, posZ + 0.5);
				GlStateManager.scale(1.5, 1.5, 1.5);
				
				ent.setEntityItemStack(new ItemStack(ore.getItem(), 1, ore.getMetadata()));
				GlStateManager.enableRescaleNormal();
				GL11.glPushMatrix();
				{
					GlStateManager.rotate(-(time), 0F, 1F, 0F);
					// Minecraft.getMinecraft().getRenderManager().renderEntityStatic(ent, 0, false);
					
				}
				GL11.glPopMatrix();
			}
			GlStateManager.popMatrix();
		}
		
	}
}
