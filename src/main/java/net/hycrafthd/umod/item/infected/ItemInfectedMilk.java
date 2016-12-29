package net.hycrafthd.umod.item.infected;

import net.hycrafthd.umod.*;
import net.hycrafthd.umod.item.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemInfectedMilk extends ItemBase {
	
	public ItemInfectedMilk() {
		this.setMaxStackSize(1);
		this.setCreativeTab(UReference.infected);
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		if (!playerIn.capabilities.isCreativeMode) {
			--stack.stackSize;
		}
		
		if (!worldIn.isRemote) {
			playerIn.curePotionEffects(stack);
			playerIn.addPotionEffect(new PotionEffect(UPotion.radiationPotion, 100, 0));
		}
		
		return stack.stackSize <= 0 ? new ItemStack(Items.BUCKET) : stack;
	}
	
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		// TODO set in use
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
}