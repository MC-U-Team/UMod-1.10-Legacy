package io.github.mc_umod.entity.render;

import io.github.mc_umod.UReference;
import io.github.mc_umod.entity.EntityInfectedCreeper;
import io.github.mc_umod.entity.model.ModelInfectedCreeper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderInfectedCreeper extends RenderLiving<EntityInfectedCreeper> implements IRenderFactory<EntityInfectedCreeper> {
	
	public RenderInfectedCreeper(RenderManager r) {
		super(r, new ModelInfectedCreeper(), 0);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityInfectedCreeper entity) {
		return new ResourceLocation(UReference.resource + "textures/entity/InfectedCreeper.png");
	}
	
	@Override
	public Render<? super EntityInfectedCreeper> createRenderFor(RenderManager manager) {
		return new RenderInfectedCreeper(manager);
	}
	
}
