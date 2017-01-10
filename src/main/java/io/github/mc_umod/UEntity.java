package io.github.mc_umod;

import io.github.mc_umod.entity.EntityFX;
import io.github.mc_umod.entity.EntityGenerator;
import io.github.mc_umod.entity.EntityInfectedCow;
import io.github.mc_umod.entity.EntityInfectedCreeper;
import io.github.mc_umod.entity.EntityInfectedZombie;
import io.github.mc_umod.entity.EntityNukePrimed;
import io.github.mc_umod.entity.rail.EntityRailFX;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class UEntity {
	
	public UEntity() {
		register();
	}
	
	private static int ids = 0;
	
	private void register() {
		reg(EntityInfectedCow.class, "InfectedCow", 16, 1, true);
		reg(EntityInfectedCreeper.class, "InfectedCreeper", 24, 1, true);
		reg(EntityNukePrimed.class, "PrimedNuke", 16, 1, true);
		reg(EntityInfectedZombie.class, "InfectedZombie", 24, 1, true);
		reg(EntityRailFX.class, "SWELL", 16, 1, false);
		reg(EntityFX.class, "TileFX", 16, 1, false);
		reg(EntityGenerator.class, "Generator", 16, 1, false);
		UMod.log.debug("Register Entitys");
	}
	
	public void reg(Class<? extends Entity> clZZ, String name, int r, int hz, boolean up, int sc, int spc, boolean hegg) {
		EntityRegistry.registerModEntity(clZZ, name, ids, UReference.modid, r, hz, up);
		if (hegg) {
			EntityRegistry.registerEgg(clZZ, sc, spc);
		}
		ids++;
	}
	
	public void reg(Class<? extends Entity> clZZ, String name, int r, int hz, boolean up) {
		reg(clZZ, name, r, hz, up, 0, 0, false);
	}
}