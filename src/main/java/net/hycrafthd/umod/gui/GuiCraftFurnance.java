package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.gui.container.ContainerCraftFurnace;
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
		box2.getItems().add("Choose");
		box2.getItems().add("Output");
	}
	
	@Override
	public void onIOModeSwitched() {
		// TODO Auto-generated method stub
	}
	
}
