package io.github.mc_umod.ext.ic2;

import io.github.mc_umod.block.machine.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;

public class BlockIC2Transformer extends BlockBaseMachine{

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityIC2Transformer();
	}
	
}
