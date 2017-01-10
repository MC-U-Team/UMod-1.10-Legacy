package io.github.mc_umod.block.machine;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import io.github.mc_umod.block.*;
import net.minecraft.block.material.*;

public class BlockConduit extends BlockBase implements IConduitBlock {
	
	public BlockConduit() {
		super(Material.IRON);
		this.setCreativeTab(UReference.maschines);
	}
	
}
