package io.github.mc_umod.gui;

import io.github.mc_umod.gui.container.ContainerMagicCrafter;
import io.github.mc_umod.gui.items.*;
import io.github.mc_umod.tileentity.TileEntityMagicCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class GuiMagicCrafter extends GuiBase {
	
	public GuiMagicCrafter(EntityPlayer player, BlockPos pos) {
		super(new GuiRescources("magic_crafter.png"), player, pos, new ContainerMagicCrafter(player, pos));
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		if (this.tile instanceof TileEntityMagicCrafter) {
			TileEntityMagicCrafter mg = (TileEntityMagicCrafter) this.tile;
			drawTexturedModalRect(this.width / 2, this.height / 2, 177, 1, 7, (mg.getCount() / 4));
		}
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onIOModeSwitched() {
		// TODO Auto-generated method stub
	}
	
}
