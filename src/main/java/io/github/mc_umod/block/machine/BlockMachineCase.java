package io.github.mc_umod.block.machine;

import io.github.mc_umod.event.apis.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.common.*;

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
