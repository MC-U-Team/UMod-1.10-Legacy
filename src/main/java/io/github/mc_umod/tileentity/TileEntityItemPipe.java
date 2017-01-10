package io.github.mc_umod.tileentity;

import io.github.mc_umod.api.*;
import io.github.mc_umod.event.apis.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.common.*;

public class TileEntityItemPipe extends TileEntity implements IPlugabel, IConduitProvider, ITickable{
	
	public ItemStack cond = null;
	
	public TileEntityItemPipe() {
		
	}
	
	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		return ((IPlugabel) w.getBlockState(getPos()).getBlock()).canConnect(w, p);
	}
	
	@Override
	public ItemStack getConduit() {
		return cond;
	}
	
	@Override
	public boolean hasConduit() {
		return cond != null;
	}
	
    private boolean firsttick = true;
	
	@Override
	public void update() {
		if(firsttick){
		MinecraftForge.EVENT_BUS.post(new RenderEntityRegisterEvent(worldObj,pos));
		}
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		MinecraftForge.EVENT_BUS.post(new RenderEntityClearEvent(worldObj,pos));
	}
	
	@Override
	public void setConduit(ItemStack b) {
		this.cond = b;
	}
	
}
