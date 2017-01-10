package io.github.mc_umod.entity.render;

import io.github.mc_umod.*;
import io.github.mc_umod.entity.*;
import io.github.mc_umod.entity.model.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.relauncher.*;

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
