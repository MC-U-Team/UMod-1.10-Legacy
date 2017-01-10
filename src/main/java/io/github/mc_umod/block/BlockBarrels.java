package io.github.mc_umod.block;

import java.util.*;

import io.github.mc_umod.enumtype.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;

public class BlockBarrels extends BlockBase {
	
	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumTypeBarrels.class);
	
	public BlockBarrels() {
		super(Material.ROCK);
		this.setResistance(5.0F);
		this.setHardness(2.0F);
		this.setHarvestLevel("axe", 0);
		this.setSoundType(SoundType.METAL);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		EnumTypeBarrels type = (EnumTypeBarrels) state.getValue(TYPE);
		return type.getID();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativetab, List list) {
		for (int i = 0; i < EnumTypeBarrels.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumTypeBarrels type = EnumTypeBarrels.byID(meta);
		return this.getDefaultState().withProperty(TYPE, type);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumTypeBarrels type = (EnumTypeBarrels) state.getValue(TYPE);
		return type.getID();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}
	
}
