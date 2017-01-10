package io.github.mc_umod.item;

import com.mojang.realmsclient.gui.*;

import io.github.mc_umod.*;
import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.tileentity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraft.world.*;

public class ItemCabletester extends ItemBase {
	
	public ItemCabletester() {
		this.setMaxDamage(400);
		this.setCreativeTab(UReference.maschines);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
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
