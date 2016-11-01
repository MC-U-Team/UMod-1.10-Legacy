package net.hycrafthd.umod.ext.enderio;

import crazypants.enderio.BlockEio;
import net.hycrafthd.umod.block.BlockBaseMachine;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnderIOTransformer extends BlockBaseMachine {
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEnderIOTransformer();
	}
	
}
