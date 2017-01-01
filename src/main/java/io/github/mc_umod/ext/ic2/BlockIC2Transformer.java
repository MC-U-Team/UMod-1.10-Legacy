package io.github.mc_umod.ext.ic2;

import io.github.mc_umod.block.machine.BlockBaseMachine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockIC2Transformer extends BlockBaseMachine{

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityIC2Transformer();
	}
	
}
