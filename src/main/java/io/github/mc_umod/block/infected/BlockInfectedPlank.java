package io.github.mc_umod.block.infected;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import io.github.mc_umod.block.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.init.*;

public class BlockInfectedPlank extends BlockBase implements IInfectedBlock {
	
	public BlockInfectedPlank() {
		super(Material.WOOD);
		this.setHarvestLevel("axe", 0);
		this.setHardness(2.0F);
		this.setCreativeTab(UReference.infected);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.WOOD);
		Blocks.FIRE.setFireInfo(this, 5, 20);
	}
	
	@Override
	public Block getNormalBlock() {
		return Blocks.PLANKS;
	}
	
}
