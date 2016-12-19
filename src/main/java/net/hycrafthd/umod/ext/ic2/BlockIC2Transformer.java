package net.hycrafthd.umod.ext.ic2;

import net.hycrafthd.umod.block.BlockBaseMachine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockIC2Transformer extends BlockBaseMachine{

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityIC2Transformer();
	}
	
}
