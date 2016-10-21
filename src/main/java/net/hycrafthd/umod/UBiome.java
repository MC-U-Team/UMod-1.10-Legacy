package net.hycrafthd.umod;

import net.hycrafthd.umod.biome.BiomeInfected;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

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
