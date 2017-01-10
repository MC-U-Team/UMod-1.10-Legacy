package io.github.mc_umod.biome;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.entity.*;
import io.github.mc_umod.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.*;

public class BiomeInfected extends Biome {
	
	private final String name = "Infected Biome";
	private GenInfectedTree tree;
	
	@SuppressWarnings("unchecked")
	public BiomeInfected(BiomeProperties id) {
		super(id);
		this.tree = new GenInfectedTree(false);
		this.topBlock = UBlocks.infectedGrass.getDefaultState();
		this.fillerBlock = UBlocks.infectedDirt.getDefaultState();
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.theBiomeDecorator.treesPerChunk = 4;
		this.spawnableMonsterList.add(new SpawnListEntry(EntityInfectedCow.class, 10, 5, 20));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityInfectedCreeper.class, 10, 5, 10));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityInfectedZombie.class, 10, 5, 10));
	}
	
	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return rand.nextInt(3) > 0 ? this.tree : super.genBigTreeChance(rand);
	}
	
	@Override
	public int getSkyColorByTemp(float p_76731_1_) {
		return 0xA5F2CF;
	}
	
	@Override
	public int getWaterColorMultiplier() {
		return 0x52DB4D;
	}
	
}
