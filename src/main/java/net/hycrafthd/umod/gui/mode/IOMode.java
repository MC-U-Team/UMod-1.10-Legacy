package net.hycrafthd.umod.gui.mode;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.render.IIOMode;
import net.hycrafthd.umod.gui.*;
import net.hycrafthd.umod.gui.items.*;
import net.hycrafthd.umod.network.PacketHandler;
import net.hycrafthd.umod.network.message.MessageIOMode;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class IOMode extends ImplGui{

	public GuiCombobox box;
	private float sclax = 0, sclay = 0;
	private int posX,posY;

	public IOMode(GuiBase base_gui) {
		super(base_gui);
		int k = (base_gui.width - base_gui.xSize) / 2;
		int l = (base_gui.height - base_gui.ySize) / 2;
		box = new GuiCombobox(base_gui,k + 8, l + 7, 80, 12);
		base_gui.addToBox(box);
		box.getItems().add("Choose");
		box.setSelected(box.getItems().size() - 1);
	}

	@Override
	public void render(int mouseX, int mouseY) {
		int k = base_gui.guiLeft;
		int l = base_gui.guiTop;
		GlStateManager.enableDepth();
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		this.setupGuiTransform((base_gui.width / 2), (base_gui.height / 2) - (base_gui.ySize / 4));
		UReference.getClientProxy().getModelRenderHelper().renderItem(new ItemStack(base_gui.worldObj.getBlockState(base_gui.pos).getBlock()),TransformType.NONE);
		GlStateManager.popMatrix();
		GlStateManager.disableDepth();
		int kl = (base_gui.width - base_gui.xSize) / 2;
		base_gui.getFontRender().drawString(base_gui.hal.toString(), kl + 10, base_gui.height / 2 - 10, 0xFFFFFF);
		box.render(mouseX,mouseY);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) k, (float) l, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
	}
	
	private void setupGuiTransform(int xPosition, int yPosition) {
		GlStateManager.translate((float) xPosition, (float) yPosition, 100.0F + this.zLevel);
		GlStateManager.translate(8.0F, 8.0F, 0.0F);
		GlStateManager.scale(2.0F, 2.0F, -2.0F);
		GlStateManager.scale(0.5F, 0.5F, 0.5F);			
		GlStateManager.scale(40.0F, 40.0F, 40.0F);
		GlStateManager.rotate(sclay - 180, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(sclax, 0.0F, 1.0F, 0.0F);
		GlStateManager.enableLighting();
	}
	
	@Override
	public void onDrag(int mouseX, int mouseY) {
		if (mouseX > 7 + base_gui.guiLeft && mouseX < 169 + base_gui.guiLeft && mouseY > base_gui.guiTop + 6 && mouseY < base_gui.guiTop + 82) {
			if (sclax + (mouseX - posX) <= 90 && sclax + (mouseX - posX) >= -180) {
				sclax += mouseX - posX;
			}
			if (sclay + (mouseY - posY) <= 90 && sclay + (mouseY - posY) >= -90) {
				sclay += mouseY - posY;
			}
			posX = mouseX;
			posY = mouseY;
			if (sclay >= 45 && sclay <= 135) {
				base_gui.hal = EnumFacing.UP;
				base_gui.imp_facingchange();
			}
			if (sclay <= -45 && sclay >= -215) {
				base_gui.hal = EnumFacing.DOWN;
				base_gui.imp_facingchange();
			}
			if (sclay >= -45 && sclay <= 45) {
				if (sclax >= -45 && sclax <= 45) {
					base_gui.hal = EnumFacing.NORTH;
					base_gui.imp_facingchange();
				}
				if (sclax <= 135 && sclax >= 45) {
					base_gui.hal = EnumFacing.EAST;
					base_gui.imp_facingchange();
				}
				if (sclax <= -45 && sclax >= -135) {
					base_gui.hal = EnumFacing.WEST;
					base_gui.imp_facingchange();
				}
				if (sclax <= -135 && sclax >= -210) {
					base_gui.hal = EnumFacing.SOUTH;
					base_gui.imp_facingchange();
				}
			}
			base_gui.onIOModeSwitched();
		}
	}
	
	@Override
	public void onClick(int mouseX, int mouseY) {
		posX = mouseX;
		posY = mouseY;
		box.onClick(mouseX, mouseY);
	}
	
	public void checkAndAdd(EnumFacing fc, int item) {
		if (item == Byte.MAX_VALUE) {
			this.box.setSelected(this.box.getItems().size() - 1);
			return;
		}
		if (base_gui.hal.equals(fc)) {
			this.box.setSelected(item);
		} else {
			this.box.setSelected(0);
		}
	}
	
	public void addListener(Runnable run){
		box.setOnListClicked(new Runnable() {
			
			@Override
			public void run() {
				if(base_gui.tile instanceof IIOMode){
					PacketHandler.INSTANCE.sendToServer(new MessageIOMode(base_gui.pos, base_gui.hal, (box.getSelceted() < box.getItems().size() - 1 ? box.getSelceted():-1)));
				}
			}
		});
	}
}
