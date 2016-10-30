package net.hycrafthd.umod.entity.render;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.entity.EntityInfectedCreeper;
import net.hycrafthd.umod.entity.model.ModelInfectedCreeper;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.*;

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
