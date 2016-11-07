package net.hycrafthd.umod.item;

import net.hycrafthd.umod.UReference;
import net.minecraft.item.Item;

public class ItemSwellPlacer extends Item {
	
	public ItemSwellPlacer() {
		this.setCreativeTab(UReference.rail);
	}
	
	/*
	 * @Override public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World world, EntityPlayer playerIn, EnumHand hand) { BlockPos start = null; if (side.equals(EnumFacing.UP)) { start = pos.up(); } if (side.equals(EnumFacing.DOWN)) { start = pos.down(); } if (side.equals(EnumFacing.EAST)) { start = pos.east(); } if (side.equals(EnumFacing.NORTH)) { start = pos.north(); } if (side.equals(EnumFacing.SOUTH)) { start = pos.south(); } if (side.equals(EnumFacing.WEST)) { start = pos.west(); } if (start == null) return false; if (world.getBlockState(start.add(0, -1, 0)).getBlock().isFullBlock(state) && world.getBlockState(start.add(1, -1, 0)).getBlock().isSolidFullCube() && !(world.getBlockState(start.add(1, 0, 0)).getBlock().isSolidFullCube())) { world.setBlockState(start, UBlocks.rail.getDefaultState()); world.setBlockState(start.add(1, 0, 0), UBlocks.rail2.getDefaultState()); UBlocks.rail.onBlockPlacedBy(world, start, UBlocks.rail.getDefaultState(), playerIn, stack); return true; } if (world.getBlockState(start.add(0, -1, 0)).getBlock().isSolidFullCube() && world.getBlockState(start.add(-1, -1, 0)).getBlock().isSolidFullCube() && !(world.getBlockState(start.add(-1, 0, 0)).getBlock().isSolidFullCube())) { world.setBlockState(start.add(-1, 0, 0), UBlocks.rail.getDefaultState()); world.setBlockState(start, UBlocks.rail2.getDefaultState()); UBlocks.rail.onBlockPlacedBy(world, start.add(-1, 0, 0), UBlocks.rail.getDefaultState(), playerIn, stack); return true; } return false; }
	 */
	
}
