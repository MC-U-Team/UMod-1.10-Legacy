package io.github.mc_umod.block.machine;

import io.github.mc_umod.UReference;
import io.github.mc_umod.api.IConduitBlock;
import io.github.mc_umod.block.BlockBase;
import net.minecraft.block.material.Material;

public class BlockConduit extends BlockBase implements IConduitBlock {
	
	public BlockConduit() {
		super(Material.IRON);
		this.setCreativeTab(UReference.maschines);
	}
	
}
