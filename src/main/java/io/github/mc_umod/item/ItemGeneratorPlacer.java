package io.github.mc_umod.item;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import io.github.mc_umod.UBlocks;
import io.github.mc_umod.UReference;
import io.github.mc_umod.entity.EntityGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
