package io.github.mc_umod.gui;

import io.github.mc_umod.gui.container.ContainerCraftFurnace;
import io.github.mc_umod.gui.items.ComboboxItem;
import io.github.mc_umod.gui.items.GuiCombobox;
import io.github.mc_umod.gui.items.GuiRescources;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
