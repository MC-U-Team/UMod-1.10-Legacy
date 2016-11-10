package net.hycrafthd.umod.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class CaseDestroyEvent extends Event{
	
	private World world;
	private BlockPos pos;
	private IBlockState state;
	
	public CaseDestroyEvent(World w,BlockPos pos,IBlockState state) {
		this.world = w;
		this.pos = pos;
		this.state = state;
	}
	
	
	public IBlockState getState() {
		return state;
	}
	
	public World getWorld() {
		return world;
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	@Override
	public boolean isCancelable() {
		return true;
	}
}
