package net.hycrafthd.umod.ext.enderio;

import net.hycrafthd.umod.block.machine.BlockBaseMachine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnderIOTransformer extends BlockBaseMachine {
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEnderIOTransformer();
	}
	
}
