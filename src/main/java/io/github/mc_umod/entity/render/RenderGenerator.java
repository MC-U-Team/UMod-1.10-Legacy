package io.github.mc_umod.entity.render;

import io.github.mc_umod.UReference;
import io.github.mc_umod.entity.EntityGenerator;
import io.github.mc_umod.obj.ObjInterpretter;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderGenerator extends Render<EntityGenerator> implements IRenderFactory<EntityGenerator>{

	public RenderGenerator(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGenerator entity) {
		return null;
	}
	
	@Override
	public void doRender(EntityGenerator entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (entity == null || entity.getPosition() == null)
			return;
		ObjInterpretter itp = UReference.getClientProxy().getObjRenderList().GENERATOR.getInterpretter();
		Tessellator tes = Tessellator.getInstance();
		VertexBuffer bf = tes.getBuffer();
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
        itp.drawOnlyArea(bf);
        tes.draw();
		GlStateManager.popMatrix();

/**		Tessellator tes = Tessellator.getInstance();
//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
//		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
//		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.shadeModel(7425);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		itp.drawOnlyArea(tes.getBuffer());
		tes.draw();
		GlStateManager.cullFace(GlStateManager.CullFace.BACK);
		GlStateManager.popMatrix();
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableBlend();
		GlStateManager.shadeModel(7424);
**/
	}

	@Override
	public Render<? super EntityGenerator> createRenderFor(RenderManager manager) {
		return new RenderGenerator(manager);
	}
	
}