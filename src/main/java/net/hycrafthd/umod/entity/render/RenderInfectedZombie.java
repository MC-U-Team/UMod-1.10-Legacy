package net.hycrafthd.umod.entity.render;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.entity.EntityInfectedZombie;
import net.hycrafthd.umod.entity.model.ModelInfectedZombie;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderInfectedZombie extends RenderLiving<EntityInfectedZombie> implements IRenderFactory<EntityInfectedZombie>{
	
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
