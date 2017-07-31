package io.github.mc_umod.tileentity;

import java.util.List;

import io.github.mc_umod.api.render.IBoundsProvider;
import io.github.mc_umod.entity.EntityFX;
import io.github.mc_umod.item.ItemEnergyDisplay;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.*;

public class TileEntityEnergyPannel extends TileEntity implements ITickable, IBoundsProvider {
	
	private ItemStack stack = null;
	private BlockPos po = null;
	private double viewpointY = 0;
	
	public void setStack(EntityPlayer pl, ItemStack stack) {
		if (this.stack != null) {
			pl.inventory.addItemStackToInventory(this.stack);
			// pl.worldObj.playSoundAtEntity(pl, "random.pop", 0.2F, ((pl.getRNG().nextFloat() - pl.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
		}
		this.stack = stack;
	}
	
	@Override
	public void update() {
		List<EntityFX> p = worldObj.getEntitiesWithinAABB(EntityFX.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));
		if (p.size() <= 0) {
			this.worldObj.spawnEntityInWorld(new EntityFX(this.worldObj, this.pos));
		}
		if (stack != null && stack.getItem() instanceof ItemEnergyDisplay && stack.getTagCompound().hasKey(ItemEnergyDisplay.NBT_TAG)) {
			NBTTagCompound com = (NBTTagCompound) stack.getTagCompound().getTag(ItemEnergyDisplay.NBT_TAG);
			this.po = new BlockPos(com.getInteger("X"), com.getInteger("Y"), com.getInteger("Z"));
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
	public Vec3i getBounds() {
		return new Vec3i(6F, 4F, 0F);
	}
}
