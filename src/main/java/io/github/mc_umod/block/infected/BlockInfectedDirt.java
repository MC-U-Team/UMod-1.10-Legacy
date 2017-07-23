package io.github.mc_umod.block.infected;

import io.github.mc_umod.UReference;
import io.github.mc_umod.api.IInfectedBlock;
import io.github.mc_umod.block.BlockBase;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockInfectedDirt extends BlockBase implements IInfectedBlock {
	
	public BlockInfectedDirt() {
		super(Material.GROUND);
		this.setHarvestLevel("spade", 2);
		this.setHardness(0.6F);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(UReference.infected);
	}
	
	@Override
	public Block getNormalBlock() {
		return Blocks.DIRT;
	}
}
