package net.hycrafthd.umod.block;

import net.hycrafthd.umod.event.apis.CaseDestroyEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockMachineCase extends Block{

	public BlockMachineCase() {
		super(Material.IRON);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		if(!MinecraftForge.EVENT_BUS.post(new CaseDestroyEvent(worldIn, pos, state))){
			worldIn.setBlockState(pos, state);
		}
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
}
