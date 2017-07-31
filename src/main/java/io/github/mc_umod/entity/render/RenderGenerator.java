package io.github.mc_umod.entity.render;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import io.github.mc_umod.UReference;
import io.github.mc_umod.entity.EntityGenerator;
import net.minecraft.client.renderer.GlStateManager;
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
		GlStateManager.pushMatrix();
		GlStateManager.enableAlpha();
		GlStateManager.color(2F, 2F, 2F);
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(1, 1, 1);
		GlStateManager.rotate(-90, 0, 1, 0);
        GlStateManager.enableLighting();
        GlStateManager.enableLight(0);
        GlStateManager.enableLight(1);
        GlStateManager.enableColorMaterial();
        GlStateManager.colorMaterial(1032, 5634);
        GlStateManager.glLight(GL_LIGHT0, GL_AMBIENT, asBuffer(1F,1F,1F,1F));
        GlStateManager.glLight(GL_LIGHT1, GL_AMBIENT, asBuffer(1F,1F,1F,1F));
        GlStateManager.glLight(GL_LIGHT0, GL_POSITION, asBuffer(5F,5F,5F,5F));
        GlStateManager.glLight(GL_LIGHT1, GL_POSITION, asBuffer(-1F,-1F,-1F,1F));
        FloatBuffer buffer = BufferUtils.createFloatBuffer(4);
        GL11.glGetLight(GL_LIGHT0, GL_POSITION, buffer);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
       // GlStateManager.glLight(GL_LIGHT0, GL_SPOT_DIRECTION, asBuffer((float)x, (float)y, (float)z, 1F));
        GlStateManager.shadeModel(7424);

		UReference.getClientProxy().getObjRenderList().GENERATOR.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();

		GlStateManager.popMatrix();
	}

	@Override
	public Render<? super EntityGenerator> createRenderFor(RenderManager manager) {
		return new RenderGenerator(manager);
	}
	
	private FloatBuffer asBuffer(float ... flts){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(flts.length);
		buffer.put(flts);
		buffer.flip();
		return buffer;
	}
	
}
