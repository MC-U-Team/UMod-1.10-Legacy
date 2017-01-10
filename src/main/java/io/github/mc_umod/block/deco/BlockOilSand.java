package io.github.mc_umod.block.deco;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.init.*;

public class BlockOilSand extends BlockFalling implements IInfectedBlock {
	
	public BlockOilSand() {
		super(Material.SAND);
		this.setCreativeTab(UReference.blocks);
		this.setHarvestLevel("spade", 2);
		this.setHardness(0.8F);
		this.setSoundType(SoundType.SAND);
	}
	
	@Override
	public Block getNormalBlock() {
		return Blocks.SAND;
	}
	
}
