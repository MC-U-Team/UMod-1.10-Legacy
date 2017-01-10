package io.github.mc_umod.item;

import java.util.*;

import com.mojang.realmsclient.gui.*;

import io.github.mc_umod.*;
import io.github.mc_umod.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class ItemGeneratorPlacer extends ItemBase{
	
	
	public ItemGeneratorPlacer() {
		this.setCreativeTab(UReference.maschines);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		for(int y = 0;y < 2;y++){
			for(int x = 0;x < 4;x++){
				for(int z = 0;z < 2;z++){
					worldIn.setBlockState(pos.add(x,y,z), UBlocks.mcase.getDefaultState());
				}
			}
		}
		worldIn.spawnEntityInWorld(new EntityGenerator(worldIn,pos));
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		tooltip.add(ChatFormatting.GREEN + "Paces a Generator");
		tooltip.add(ChatFormatting.BLUE + "Produces 100UE\t");
	}
	
}
