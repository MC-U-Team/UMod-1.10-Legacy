package net.hycrafthd.umod.event;

import net.minecraft.block.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventChestInventory {
	
	public static final String KEY = "key.chest";
	
	@SubscribeEvent
	public void onChestDrop(BlockEvent.BreakEvent ev) {
		if (ev.getState().getBlock() instanceof ITileEntityProvider) {
			TileEntity et = ev.getWorld().getTileEntity(ev.getPos());
			if (et != null) {
				NBTTagCompound comp = new NBTTagCompound();
				et.writeToNBT(comp);
				if (et instanceof IInventory)
					((IInventory) et).clear();
				ItemStack st = new ItemStack(ev.getState().getBlock(), 1, ev.getState().getBlock().damageDropped(ev.getState()));
				st.setTagInfo(KEY, comp);
				Block.spawnAsEntity(ev.getWorld(), ev.getPos(), st);
				ev.getWorld().setBlockToAir(ev.getPos());
				ev.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onChestPlaced(BlockEvent.PlaceEvent ev) {
		if (ev.getState().getBlock() instanceof ITileEntityProvider) {
			TileEntity et = ev.getWorld().getTileEntity(ev.getPos());
			if (et != null) {
				NBTTagCompound comp = ev.getItemInHand().getSubCompound(KEY, false);
				if (comp != null) {
					et.readFromNBT(comp);
					et.setPos(ev.getPos());
					et.setWorldObj(ev.getWorld());
					EntityPlayer pl = ev.getPlayer();
					ItemStack st = pl.getHeldItemMainhand();
					if (st != null && pl.capabilities.isCreativeMode) {
						if (st.stackSize > 1) {
							ItemStack std = pl.inventory.mainInventory[pl.inventory.currentItem];
							pl.inventory.mainInventory[pl.inventory.currentItem] = new ItemStack(std.getItem(), std.stackSize - 1, std.getMetadata());
						} else {
							pl.inventory.mainInventory[pl.inventory.currentItem] = null;
						}
					}
				}
			}
		}
	}
}
