package net.hycrafthd.umod.entity.render;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.entity.EntityGenerator;
import net.hycrafthd.umod.obj.ObjInterpretter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class RenderGenerator extends Render<EntityGenerator>{

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
		BlockPos pos = entity.getPosition().add(0, -1, 0);
		TileEntity ent = entity.getEntityWorld().getTileEntity(pos);
		if (ent == null) {
			entity.setDead();
			return;
		}	
		Tessellator tes = Tessellator.getInstance();
		ObjInterpretter itp = UReference.getClientProxy().getObjRenderList().GENERATOR.getInterpretter();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.pushMatrix();
		itp.drawOnlyArea(tes.getBuffer());
		GlStateManager.cullFace(GlStateManager.CullFace.BACK);
		GlStateManager.popMatrix();
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableBlend();
		tes.draw();
	}
	
}
