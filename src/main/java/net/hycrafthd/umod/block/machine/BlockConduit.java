package net.hycrafthd.umod.block.machine;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IConduitBlock;
import net.hycrafthd.umod.block.BlockBase;
import net.minecraft.block.material.Material;

public class BlockConduit extends BlockBase implements IConduitBlock {
	
	public BlockConduit() {
		super(Material.IRON);
		this.setCreativeTab(UReference.maschines);
	}
	
}
