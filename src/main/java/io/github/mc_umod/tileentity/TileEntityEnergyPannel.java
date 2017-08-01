package io.github.mc_umod.tileentity;

import java.util.List;

import io.github.mc_umod.api.render.IBoundsProvider;
import io.github.mc_umod.entity.EntityFX;
import io.github.mc_umod.event.apis.RenderEntityRegisterEvent;
import io.github.mc_umod.item.ItemEnergyDisplay;
import io.github.mc_umod.renderapi.Vec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.*;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityEnergyPannel extends TileEntity implements ITickable, IBoundsProvider {
	
	private ItemStack stack = null;
	private BlockPos po = null;
	private double viewpointY = 0;
	
	public void setStack(EntityPlayer pl, ItemStack stack) {
		if (this.stack != null) {
			pl.inventory.addItemStackToInventory(this.stack);
		}
		this.stack = stack;
		NBTTagCompound com = (NBTTagCompound) stack.getTagCompound().getTag(ItemEnergyDisplay.NBT_TAG);
		if (stack.getItem() instanceof ItemEnergyDisplay && stack.getTagCompound().hasKey(ItemEnergyDisplay.NBT_TAG)) {
			this.po = new BlockPos(com.getInteger("X"), com.getInteger("Y"), com.getInteger("Z"));
		}
	}
	
	private boolean firsttick = true;
	
	@Override
	public void update() {
		if(firsttick) {
			MinecraftForge.EVENT_BUS.post(new RenderEntityRegisterEvent(this.worldObj, this.pos));
			firsttick = false;
		}
	}
	
	public void setView(double view) {
		viewpointY = view;
	}
	
	public double getView() {
		return viewpointY;
	}
	
	public BlockPos getPosOf() {
		return po;
	}
	
	public boolean hasBlockPos() {
		return po != null;
	}
	
	@Override
	public Vec3 getBounds() {
		return new Vec3(6F, 4F, 0F);
	}
}
