package io.github.mc_umod;

import io.github.mc_umod.enumtype.*;
import io.github.mc_umod.gui.*;
import io.github.mc_umod.gui.container.*;
import io.github.mc_umod.gui.inventory.InventoryBackPack;
import net.hycrafthd.corelib.util.ICustomGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UGuiHandler implements ICustomGuiHandler {
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		switch (EnumTypeGui.byID(ID)) {
		case PULVERISER:
			return new ContainerPulverizer(player, pos);
		case CHARGESTATION:
			return new ContainerChargeStation(player, pos);
		case CRAFTFURNANCE:
			return new ContainerCraftFurnace(player, pos);
		case BACKPACK:
			ItemStack itemstack = player.inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem().equals(UItems.backpack)) {
				EnumTypeBackPack type = EnumTypeBackPack.byMetadata(itemstack.getMetadata());
				InventoryBackPack inventory = new InventoryBackPack(itemstack, player, type.getCount());
				return new ContainerBackPack(inventory, player.inventory, type);
			}
			break;
		case PAINTER:
			return new ContainerPainter(player, pos);
		case MAGIC_CRAFTER:
			return new ContainerMagicCrafter(player, pos);
		}
		return null;
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);		
		switch (EnumTypeGui.byID(ID)) {
		case PULVERISER:
			return new GuiPulverizer(player, pos);
		case SOLARPANEL:
			return new GuiEnergy(player, pos);
		case CHARGESTATION:
			return new GuiChargstation(player, pos);
		case CRAFTFURNANCE:
			return new GuiCraftFurnance(player, pos);
		case BACKPACK:
			ItemStack itemstack = player.inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem().equals(UItems.backpack)) {
				EnumTypeBackPack type = EnumTypeBackPack.byMetadata(itemstack.getMetadata());
				InventoryBackPack inventory = new InventoryBackPack(itemstack, player, type.getCount());
				return new GuiBackPack(inventory, player.inventory, type);
			}
			break;
		case PAINTER:
			return new GuiPainter(player, pos);
		case MAGIC_CRAFTER:
			return new GuiMagicCrafter(player, pos);
		}
		return null;
		
	}
	
	@Override
	public String getMod() {
		return UReference.modid;
	}
	
}
