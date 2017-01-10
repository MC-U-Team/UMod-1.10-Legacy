package io.github.mc_umod.gui;

import io.github.mc_umod.gui.container.*;
import io.github.mc_umod.gui.items.*;
import io.github.mc_umod.gui.mode.*;
import io.github.mc_umod.tileentity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiPulverizer extends GuiBase {
	
	public BlockPos pos;
	
	public GuiPulverizer(EntityPlayer player, BlockPos pos) {
		super(new GuiRescources("pulver.png"), player, pos, new ContainerPulverizer(player, pos));
		this.pos = pos;
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		if (this.activeTab != null && this.activeTab.getGui() instanceof ModeNormal) {
			fontRendererObj.drawString(((TileEntityPulverizer) this.tile).getTime() + "%", this.xSize / 2 - 5, this.ySize / 2 - 62, 0x00000);
		}
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
		box2.getItems().add(ComboboxItem.INPUT);
		box2.getItems().add(ComboboxItem.OUTPUT);
	}
	
	@Override
	public void onIOModeSwitched() {
		
	}
	
}
