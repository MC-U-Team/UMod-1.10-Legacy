package net.hycrafthd.umod.gui;

import java.awt.Color;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.gui.container.ContainerCraftFurnace;
import net.hycrafthd.umod.gui.items.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiCraftFurnance extends GuiBase {
	
	public GuiCraftFurnance(EntityPlayer player, BlockPos pos) {
		super(new GuiRescources("craftfurn.png"), player, pos, new ContainerCraftFurnace(player, pos));
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
		box2.getItems().add(ComboboxItem.OUTPUT);
	}
	
	@Override
	public void onIOModeSwitched() {
		// TODO Auto-generated method stub
	}
	
}
