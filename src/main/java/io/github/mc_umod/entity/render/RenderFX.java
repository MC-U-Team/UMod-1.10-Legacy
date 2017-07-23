package io.github.mc_umod.entity.render;

import java.util.HashMap;

import io.github.mc_umod.entity.EntityFX;
import io.github.mc_umod.render.TileRender;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFX extends Render<EntityFX> implements IRenderFactory<EntityFX> {
	
	private static HashMap<Class<? extends TileEntity>, TileRender> list = new HashMap<Class<? extends TileEntity>, TileRender>();
	
	public RenderFX(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}
	
	@Override
	public void doRender(EntityFX entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		if (entity == null || entity.getPosition() == null)
			return;
		BlockPos pos = entity.getPosition().add(0, -1, 0);
		TileEntity ent = entity.getEntityWorld().getTileEntity(pos);
		if (ent == null) {
			entity.setDead();
			return;
		}
		TileRender ren = list.get(ent.getClass());
		if (ren == null)
			return;
		Vec3d vc = new Vec3d(x - 0.5, y - 0.5, z - 0.5);
		ren.render(ent, vc.xCoord, vc.yCoord, vc.zCoord);
	}
	
	public static void register(Class<? extends TileEntity> ent, TileRender rend) {
		list.put(ent, rend);
	}
	
	@Override
	public Render<? super EntityFX> createRenderFor(RenderManager manager) {
		return new RenderFX(manager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityFX entity) {
		return null;
	}
	
}
