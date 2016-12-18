package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.entity.EntityFX;
import net.hycrafthd.umod.event.EnergyRegisterEvent;
import net.hycrafthd.umod.event.RenderEntityClearEvent;
import net.hycrafthd.umod.event.RenderEntityRegisterEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityItemPipe extends TileEntity implements IPlugabel, IConduitProvider {
	
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
	
	@Override
	public void onLoad() {
		MinecraftForge.EVENT_BUS.post(new RenderEntityRegisterEvent(pos, worldObj));
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		MinecraftForge.EVENT_BUS.post(new RenderEntityClearEvent(pos, worldObj));
	}
	
	@Override
	public void setConduit(ItemStack b) {
		this.cond = b;
	}
	
}
