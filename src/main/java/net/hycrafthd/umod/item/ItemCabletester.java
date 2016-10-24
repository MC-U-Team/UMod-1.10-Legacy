package net.hycrafthd.umod.item;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemCabletester extends ItemBase {
	
	public ItemCabletester() {
		this.setMaxDamage(400);
		this.setCreativeTab(UReference.maschines);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity ent = worldIn.getTileEntity(pos);
		if (ent != null && ent instanceof TileEntityCable) {
			IPowerProvieder pro = (IPowerProvieder) ent;
			playerIn.addChatMessage(new TextComponentString(ChatFormatting.GREEN.toString() + pro.getStoredPower() + ChatFormatting.RESET + "/" + ChatFormatting.BLUE + pro.getMaximalPower()));
			if (!playerIn.capabilities.isCreativeMode) {
				stack.setItemDamage(stack.getItemDamage() + 1);
			}
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
