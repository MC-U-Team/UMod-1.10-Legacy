package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.gui.container.ContainerChargeStation;
import net.hycrafthd.umod.gui.items.GuiCombobox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiChargstation extends GuiBase {
	
	public GuiChargstation(EntityPlayer player, BlockPos pos) {
		super(new ResourceLocation(UReference.modid, "textures/gui/charge.png"), player, pos, new ContainerChargeStation(player, pos));
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
	}
	
	@Override
	public void onIOModeSwitched() {
	}
}
