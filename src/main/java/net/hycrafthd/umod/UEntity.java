package net.hycrafthd.umod;

import net.hycrafthd.umod.entity.*;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.hycrafthd.umod.utils.EntityUtils;
import net.hycrafthd.umod.utils.URegistryUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class UEntity {
	
	public UEntity() {
		register();
	}
	
	private static int ids = 255 - EntityList.ID_TO_CLASS.size();
	
	private void register() {
		reg(EntityInfectedCow.class, "InfectedCow", 16, 1, true);
		reg(EntityInfectedCreeper.class, "InfectedCreeper", 24, 1, true);
		reg(EntityNukePrimed.class, "PrimedNuke", 16, 1, true);
		reg(EntityInfectedZombie.class, "InfectedZombie", 24, 1,true);
		reg(EntityRailFX.class, "SWELL", 16, 1,false);
		reg(EntityFX.class, "TileFX", 16, 1,false);
		UMod.log.debug("Register Entitys");
	}
	
	public void reg(Class<? extends Entity> clZZ,String name,int r,int hz,boolean up,int sc,int spc,boolean hegg){
        int id = ids;
		EntityList.addMapping(clZZ, name, id);
		EntityRegistry.registerModEntity(clZZ, name, id, UReference.instance, r, hz, up);
		if (hegg) {
			EntityRegistry.registerEgg(clZZ, sc, spc);
		}
		ids++;
	}
	
	public void reg(Class<? extends Entity> clZZ,String name,int r,int hz,boolean up){
		reg(clZZ, name, r, hz, up,0,0,false);
	}
}