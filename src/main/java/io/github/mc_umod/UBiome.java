package io.github.mc_umod;

import io.github.mc_umod.biome.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.biome.Biome.*;
import net.minecraftforge.common.*;
import net.minecraftforge.common.BiomeManager.*;

public class UBiome {
	
	public static Biome infectedBiomBase;
	public static BiomeProperties infectedBiomId;
	
	public UBiome() {
		init();
		register();
	}
	
	private void init() {
		infectedBiomId = new BiomeProperties("Infected Biome");
		infectedBiomId.setBaseHeight(0.5F);
		infectedBiomBase = new BiomeInfected(infectedBiomId);
		UMod.log.debug("Init Bioms");
	}
	
	private void register() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(infectedBiomBase, 8));
		UMod.log.debug("Register Bioms");
	}
}
