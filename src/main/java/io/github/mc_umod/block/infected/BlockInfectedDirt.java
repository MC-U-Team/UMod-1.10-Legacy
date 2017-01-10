package io.github.mc_umod.block.infected;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import io.github.mc_umod.block.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.init.*;

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
