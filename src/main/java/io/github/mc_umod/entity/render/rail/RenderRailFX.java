package io.github.mc_umod.entity.render.rail;

import io.github.mc_umod.UReference;
import io.github.mc_umod.entity.rail.EntityRailFX;
import io.github.mc_umod.render.RailRenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRailFX extends Render<EntityRailFX> implements IRenderFactory<EntityRailFX> {
	
	public RenderRailFX(RenderManager r) {
		super(r);
	}
	
	@Override
	public void doRender(EntityRailFX entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		new RailRenderHelper(UReference.getClientProxy().getGLHelper()).drawSwell("textures/blocks/stone.png", x, y - 0.5, z);
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
