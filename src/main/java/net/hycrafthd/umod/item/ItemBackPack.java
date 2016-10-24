package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.container.ContainerBackPack;
import net.hycrafthd.umod.enumtype.*;
import net.hycrafthd.umod.utils.NBTUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemBackPack extends ItemBase {
	
	public ItemBackPack() {
		super();
		this.setMaxStackSize(1);
		this.hasSubtypes = true;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		EnumTypeBackPack type = EnumTypeBackPack.byMetadata(itemstack.getMetadata());
		return "item.backpack" + type.getName();
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs creativetab, List list) {
		for (int i = 0; i < EnumTypeBackPack.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		playerIn.openGui(UReference.modid, EnumTypeGui.BACKPACK.getID(), worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int itemslot, boolean isSelected) {
		if (itemstack.getTagCompound() == null) {
			itemstack.setTagCompound(new NBTTagCompound());
		}
		
		NBTUtils.removeCustomName(itemstack);
		
		if (world.isRemote || !isSelected) {
			return;
		}
		
		if (((EntityPlayer) entity).openContainer == null || ((EntityPlayer) entity).openContainer instanceof ContainerPlayer) {
			return;
		}
		if (((EntityPlayer) entity).openContainer instanceof ContainerBackPack) {
			
			ContainerBackPack container = (ContainerBackPack) ((EntityPlayer) entity).openContainer;
			if (container.updateNotification) {
				container.saveToNBT(itemstack);
				container.updateNotification = false;
			}
		}
	}
}
