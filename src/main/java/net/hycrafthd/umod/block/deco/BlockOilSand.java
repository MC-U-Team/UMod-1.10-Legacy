package net.hycrafthd.umod.block.deco;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IInfectedBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

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
