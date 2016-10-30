package net.hycrafthd.umod.entity.render.rail;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.hycrafthd.umod.entity.render.RailRenderHelper;
import net.hycrafthd.umod.render.GLHelper;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRailFX extends Render<EntityRailFX> implements IRenderFactory<EntityRailFX> {
	
	
	public RenderRailFX(RenderManager r) {
		super(r);
	}
	
	@Override
	public void doRender(EntityRailFX entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		new RailRenderHelper(UMod.getGLHelper()).drawSwell("textures/blocks/stone.png", x, y - 0.5, z);
		// new VIADrawer(fl).drawNormal("", x, y, z, new RGBA(Color.white));
	}
	
	@Override
	public Render<? super EntityRailFX> createRenderFor(RenderManager manager) {
		return new RenderRailFX(manager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityRailFX entity) {
		return null;
	}
}
