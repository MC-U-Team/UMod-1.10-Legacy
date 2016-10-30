package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

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
