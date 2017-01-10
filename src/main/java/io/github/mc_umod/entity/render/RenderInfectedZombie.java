package io.github.mc_umod.entity.render;

import io.github.mc_umod.UReference;
import io.github.mc_umod.entity.EntityInfectedZombie;
import io.github.mc_umod.entity.model.ModelInfectedZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderInfectedZombie extends RenderLiving<EntityInfectedZombie> implements IRenderFactory<EntityInfectedZombie> {
	
	public RenderInfectedZombie(RenderManager r) {
		super(r, new ModelInfectedZombie(), 0);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityInfectedZombie entity) {
		return new ResourceLocation(UReference.resource + "textures/entity/InfectedZombie.png");
	}
	
	@Override
	public Render<? super EntityInfectedZombie> createRenderFor(RenderManager manager) {
		return new RenderInfectedZombie(manager);
	}
	
}
