package net.hycrafthd.umod.block.infected;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IInfectedBlock;
import net.hycrafthd.umod.block.BlockBase;
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
