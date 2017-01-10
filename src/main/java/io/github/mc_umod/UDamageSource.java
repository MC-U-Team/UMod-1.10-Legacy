package io.github.mc_umod;

import io.github.mc_umod.damagesource.*;
import net.minecraft.util.*;

public class UDamageSource {
	
	public static DamageSource radiationDamageSource;
	public static DamageSource electroshock;
	public static DamageSource nuclearExplosion;
	
	public UDamageSource() {
		init();
	}
	
	private void init() {
		radiationDamageSource = new DamageSourceRadiation();
		electroshock = new DamageSourceElectroShock();
		nuclearExplosion = new DamageSourceNuclearExplosion();
		UMod.log.debug("Init DamageSources");
	}
	
}
