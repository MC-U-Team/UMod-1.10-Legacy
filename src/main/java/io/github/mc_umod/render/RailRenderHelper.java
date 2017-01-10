package io.github.mc_umod.render;

import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

public class RailRenderHelper {
	
	private GLHelper helper;
	
	public RailRenderHelper(GLHelper helper) {
		this.helper = helper;
	}
	
	public void drawSwell(String text, double x, double y, double z) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y, z);
		GlStateManager.enableNormalize();
		RenderHelper.disableStandardItemLighting();
		
		double u = 2, v = 2;
		
		GlStateManager.rotate(-90, 1.0F, 0, 0);
		
		this.helper.drawTexture(new ResourceLocation(text), 2, 0.4, -1, -0.2, 0, 2, 0.4, 0, 0);
		
		GlStateManager.rotate(180, 1.0F, 0, 0);
		
		this.helper.drawTexture(new ResourceLocation(text), u, v, -0.8, -0.1, -0.2, 1.6, 0.2, 0, 0);
		
		GlStateManager.rotate(-90, 1.0F, 0, 0);
		
		Vec3d corn13 = new Vec3d(0.8, 0.2, 0.1);
		Vec3d corn23 = new Vec3d(0.8, 0.2, -0.1);
		Vec3d corn33 = new Vec3d(1, 0, 0.2);
		Vec3d corn43 = new Vec3d(1, 0, -0.2);
		this.helper.drawTexturePoints(text, corn23, corn13, corn33, corn43, u, v);
		
		Vec3d corn1 = new Vec3d(-0.8, 0.2, 0.1);
		Vec3d corn2 = new Vec3d(0.8, 0.2, 0.1);
		Vec3d corn3 = new Vec3d(-1, 0, 0.2);
		Vec3d corn4 = new Vec3d(1, 0, 0.2);
		this.helper.drawTexturePoints(text, corn2, corn1, corn3, corn4, u, v);
		
		GlStateManager.rotate(180F, 0F, 1.0F, 0F);
		
		Vec3d corn15 = new Vec3d(0.8, 0.2, 0.1);
		Vec3d corn25 = new Vec3d(0.8, 0.2, -0.1);
		Vec3d corn35 = new Vec3d(1, 0, 0.2);
		Vec3d corn45 = new Vec3d(1, 0, -0.2);
		this.helper.drawTexturePoints(text, corn25, corn15, corn35, corn45, u, v);
		
		Vec3d corn12 = new Vec3d(-0.8, 0.2, 0.1);
		Vec3d corn22 = new Vec3d(0.8, 0.2, 0.1);
		Vec3d corn32 = new Vec3d(-1, 0, 0.2);
		Vec3d corn42 = new Vec3d(1, 0, 0.2);
		this.helper.drawTexturePoints(text, corn22, corn12, corn32, corn42, u, v);
		
		GlStateManager.popMatrix();
	}
	
}
