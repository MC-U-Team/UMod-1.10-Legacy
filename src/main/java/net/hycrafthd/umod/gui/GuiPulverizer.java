package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.gui.container.ContainerBase.Mode;
import net.hycrafthd.umod.gui.container.ContainerPulverizer;
import net.hycrafthd.umod.network.PacketHandler;
import net.hycrafthd.umod.network.message.MessageIOMode;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiPulverizer extends GuiBase {
	
	public BlockPos pos;
	
	public GuiPulverizer(EntityPlayer player, BlockPos pos) {
		super(new GuiRescources("pulver.png"), player, pos, new ContainerPulverizer(player, pos));
		this.pos = pos;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		box.setOnListClicked(new Runnable() {
			
			@Override
			public void run() {

				worldObj.markChunkDirty(pos, tile);
				worldObj.updateComparatorOutputLevel(pos, tile.getBlockType());
			}
		});
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		if (this.container.mode.equals(Mode.NORMAL)) {
			fontRendererObj.drawString(((TileEntityPulverizer) this.tile).getTime() + "%", this.xSize / 2 - 5, this.ySize / 2 - 62, 0x00000);
		}
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
		box2.getItems().add("Input");
		box2.getItems().add("Outputs");
		box2.setSelected(0);
	}
	
	@Override
	public void onIOModeSwitched() {
		
	}
	
}
