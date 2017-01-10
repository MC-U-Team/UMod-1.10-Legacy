package io.github.mc_umod.ext.enderio;

import io.github.mc_umod.block.machine.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;

public class BlockEnderIOTransformer extends BlockBaseMachine {
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEnderIOTransformer();
	}
	
}
