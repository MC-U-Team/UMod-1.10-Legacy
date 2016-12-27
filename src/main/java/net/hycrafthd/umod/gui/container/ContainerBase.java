package net.hycrafthd.umod.gui.container;

import net.hycrafthd.umod.api.energy.IBatteryProvider;
import net.hycrafthd.umod.gui.inventory.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerBase extends Container {
	
	public TileEntity tile;
	public EntityPlayer player;
	public BlockPos pos;
	public World worldObj;
	
	public ContainerBase(EntityPlayer pl, BlockPos pos) {
		this.worldObj = pl.getEntityWorld();
		this.tile = worldObj.getTileEntity(pos);
		this.player = pl;
		this.pos = pos;
		if (this.tile instanceof IBatteryProvider) {
			BaseBatteryInputSlot sl = new BaseBatteryInputSlot((IInventory) tile, ((IInventory) tile).getSizeInventory() - 1, 80, 28);
			sl.setVisible(false);
			super.addSlotToContainer(sl);
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	protected void retrySlotClick(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {
	}
	
	@Override
	public void onContainerClosed(EntityPlayer entityPlayer) {
		super.onContainerClosed(entityPlayer);
		((IInventory) this.tile).closeInventory(entityPlayer);
	}
	
	@Override
	public boolean canDragIntoSlot(Slot p_94531_1_) {
		return true;
	}
	
	public void setVisisble(int i, boolean b) {
		if (inventorySlots.get(i) instanceof BaseSlot) {
			((BaseSlot) inventorySlots.get(i)).setVisible(b);
		}
	}
	
	protected Slot addSlotToContainer(BaseSlot slotIn) {
		return super.addSlotToContainer(slotIn);
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		if(clickTypeIn.equals(ClickType.THROW))return super.slotClick(slotId, dragType, clickTypeIn, player);
		if (!(this.inventorySlots.get(slotId) instanceof BaseSlot) || ((BaseSlot)this.inventorySlots.get(slotId)).isVisible()) {
			return super.slotClick(slotId, dragType, clickTypeIn, player);
		}
		return null;
	}
	
}
