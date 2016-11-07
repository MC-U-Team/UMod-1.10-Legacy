package net.hycrafthd.umod.ext.ic2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import ic2.core.block.BlockTileEntity;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.item.block.ItemBlockTileEntity;
import ic2.core.item.tool.ItemElectricTool.HarvestLevel;
import ic2.core.ref.TeBlock.DefaultDrop;
import ic2.core.ref.TeBlock.HarvestTool;
import ic2.core.ref.TeBlock.ITePlaceHandler;
import net.hycrafthd.umod.block.BlockBaseMachine;
import net.hycrafthd.umod.ext.enderio.TileEntityEnderIOTransformer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BlockIC2Transformer extends BlockBaseMachine{

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityIC2Transformer();
	}

	
}
