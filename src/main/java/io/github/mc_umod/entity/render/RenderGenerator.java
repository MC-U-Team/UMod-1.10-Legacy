package io.github.mc_umod.entity.render;

import static org.lwjgl.opengl.GL11.*;

import io.github.mc_umod.*;
import io.github.mc_umod.entity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.client.registry.*;

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
		glPushMatrix();
		glTranslated(x, y, z);
		glScaled(0.1, 0.1, 0.1);
		glEnable(GL_BLEND);
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.shadeModel(7425);
		UReference.getClientProxy().getObjRenderList().GENERATOR.draw();
		GlStateManager.shadeModel(7424);
		glDisable(GL_BLEND);
		glPopMatrix();
	}

	@Override
	public Render<? super EntityGenerator> createRenderFor(RenderManager manager) {
		return new RenderGenerator(manager);
	}
	
}
