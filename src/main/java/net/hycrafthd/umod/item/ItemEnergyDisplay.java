package net.hycrafthd.umod.item;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.block.BlockBaseMachine;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemEnergyDisplay extends ItemBase {
	
	public ItemEnergyDisplay() {
		this.setMaxStackSize(1);
		setCreativeTab(UReference.maschines);
	}
	
	public static final String NBT_TAG = "coorde";
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			Block b = worldIn.getBlockState(pos).getBlock();
			if (b instanceof BlockBaseMachine) {
				NBTTagCompound comp = new NBTTagCompound();
				comp.setInteger("X", pos.getX());
				comp.setInteger("Y", pos.getY());
				comp.setInteger("Z", pos.getZ());
				stack.setTagInfo(NBT_TAG, comp);
				playerIn.addChatComponentMessage(new TextComponentString("Add To " + pos.toString()));
				return EnumActionResult.SUCCESS;
			}
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
}
