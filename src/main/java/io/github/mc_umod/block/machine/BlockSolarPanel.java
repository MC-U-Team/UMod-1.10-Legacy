package io.github.mc_umod.block.machine;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.block.*;
import io.github.mc_umod.enumtype.*;
import io.github.mc_umod.tileentity.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class BlockSolarPanel extends BlockBase implements ITileEntityProvider, IBlockInformation {
	
	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumTypeSolarPanel.class);
	
	public BlockSolarPanel() {
		super(Material.IRON);
		this.setCreativeTab(UReference.maschines);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		EnumTypeSolarPanel type = (EnumTypeSolarPanel) state.getValue(TYPE);
		return type.getMetadata();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativetab, List list) {
		for (int i = 0; i < EnumTypeSolarPanel.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumTypeSolarPanel type = EnumTypeSolarPanel.byMetadata(meta);
		return this.getDefaultState().withProperty(TYPE, type);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumTypeSolarPanel type = (EnumTypeSolarPanel) state.getValue(TYPE);
		return type.getMetadata();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		switch (EnumTypeSolarPanel.byMetadata(meta)) {
		case LOWVOLTAGE:
			return new TileEntitySolarPanel(2, 10000, this.getUnlocalizedName());
		case MEDIUMVOLTAGE:
			return new TileEntitySolarPanel(20, 100000, this.getUnlocalizedName());
		case HIGHVOLTAGE:
			return new TileEntitySolarPanel(200, 1000000, this.getUnlocalizedName());
		case ULTRAVOLTAGE:
			return new TileEntitySolarPanel(2000, 10000000, this.getUnlocalizedName());
		case EXTREMEVOLTAGE:
			return new TileEntitySolarPanel(20000, 100000000, this.getUnlocalizedName());
		}
		return null;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			playerIn.openGui(UReference.modid, EnumTypeGui.SOLARPANEL.getID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState s) {
		return true;
	}
	
	@Override
	public boolean isFullBlock(IBlockState s) {
		return true;
	}
	
	@Override
	public boolean isFullCube(IBlockState s) {
		return true;
	}
	
	public enum EnumTypeSolarPanel implements IStringSerializable {
		
		LOWVOLTAGE(0, "lowvoltage"),
		MEDIUMVOLTAGE(1, "mediumvoltage"),
		HIGHVOLTAGE(2, "highvoltage"),
		ULTRAVOLTAGE(3, "ultravoltage"),
		EXTREMEVOLTAGE(4, "extremevoltage");
		
		public int getMetadata() {
			return this.meta;
		}
		
		public String getName() {
			return this.name;
		}
		
		public String toString() {
			return this.name;
		}
		
		public static EnumTypeSolarPanel byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}
			
			return META_LOOKUP[meta];
		}
		
		private final int meta;
		private final String name;
		
		private static final EnumTypeSolarPanel[] META_LOOKUP = new EnumTypeSolarPanel[values().length];
		
		private EnumTypeSolarPanel(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}
		
		static {
			for (EnumTypeSolarPanel type : values()) {
				META_LOOKUP[type.getMetadata()] = type;
			}
		}
		
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tip, boolean advanced) {
		switch (EnumTypeSolarPanel.byMetadata(stack.getMetadata())) {
		case LOWVOLTAGE:
			tip.add("Products 2UE/t");
			break;
		case MEDIUMVOLTAGE:
			tip.add("Products 20UE/t");
			break;
		case HIGHVOLTAGE:
			tip.add("Products 200UE/t");
			break;
		case ULTRAVOLTAGE:
			tip.add("Products 2000UE/t");
			break;
		case EXTREMEVOLTAGE:
			tip.add("Products 20000UE/t");
			break;
		}
	}
	
}
