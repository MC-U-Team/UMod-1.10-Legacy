package net.hycrafthd.umod.gui;

import java.util.*;

import com.google.common.collect.Lists;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.gui.container.ContainerBase.Mode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@SuppressWarnings("deprecation")
public abstract class ModeTabs extends Gui {
	
	private ItemStack stack;
	private String name;
	private Mode mode;
	private int x, y;
	private boolean isUsed;
	
	public ModeTabs(ItemStack stack, String name, Mode mode, int x, int y, boolean use) {
		this.stack = stack;
		this.name = name;
		this.mode = mode;
		this.x = x;
		this.y = y;
		this.isUsed = use;
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public void setStack(ItemStack stack) {
		this.stack = stack;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Mode getMode() {
		return mode;
	}
	
	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public boolean isInUse() {
		return isUsed;
	}
	
	public void render() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tabs.png"));
		if (!isUsed) {
			this.drawTexturedModalRect(x, y - 28, 0, 0, 28, 28);
			this.renderItemIntoGUI(stack, x + 6, y - 20);
		} else {
			this.zLevel = 300F;
			GlStateManager.color(1.2F, 1.2F, 1.2F);
			GlStateManager.enableLighting();
			if (x > 0) {
				this.drawTexturedModalRect(x, y - 28, 28, 32, 28, 32);
			} else {
				this.drawTexturedModalRect(x, y - 28, 0, 32, 28, 32);
			}
			this.renderItemIntoGUI(stack, x + 6, y - 20);
			this.zLevel = 0F;
		}
		
	}
	
	public void renderToolTip(int mouseX, int mouseY, int guileft, int guitop) {
		int mx = (int) (mouseX - guileft);
		int my = (int) (mouseY - guitop);
		if (mx > x && mx < x + 28 && my > y - 33 && my < y) {
			this.drawHoveringText(Lists.newArrayList(name), mouseX + 4, mouseY + 4, Minecraft.getMinecraft().getRenderManager().getFontRenderer());
		}
	}
	
	public void handelMouseInput(int mouseX, int mouseY, int guileft, int guitop) {
		int mx = (int) (mouseX - guileft);
		int my = (int) (mouseY - guitop);
		if (mx > x && mx < x + 28 && my > y - 28 && my < y) {
			onClick(mode);
			isUsed = !isUsed;
		}
	}
	
	public void setInUse(boolean b) {
		isUsed = b;
	}
	
	@SuppressWarnings("rawtypes")
	protected void drawHoveringText(List textLines, int x, int y, FontRenderer font) {
		if (!textLines.isEmpty()) {
			GlStateManager.disableRescaleNormal();
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableLighting();
			GlStateManager.disableDepth();
			int k = 0;
			Iterator iterator = textLines.iterator();
			
			while (iterator.hasNext()) {
				String s = (String) iterator.next();
				int l = font.getStringWidth(s);
				
				if (l > k) {
					k = l;
				}
			}
			
			int j2 = x + 12;
			int k2 = y - 12;
			int i1 = 8;
			
			if (textLines.size() > 1) {
				i1 += 2 + (textLines.size() - 1) * 10;
			}
			
			this.zLevel = 300.0F;
			Minecraft.getMinecraft().getRenderItem().zLevel = 300.0F;
			int j1 = -267386864;
			this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
			this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
			this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
			this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
			this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
			int k1 = 1347420415;
			int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
			this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
			this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
			this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
			this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);
			
			for (int i2 = 0; i2 < textLines.size(); ++i2) {
				String s1 = (String) textLines.get(i2);
				font.drawStringWithShadow(s1, j2, k2, -1);
				
				if (i2 == 0) {
					k2 += 2;
				}
				
				k2 += 10;
			}
			
			this.zLevel = 0.0F;
			Minecraft.getMinecraft().getRenderItem().zLevel = 0.0F;
			GlStateManager.enableLighting();
			GlStateManager.enableDepth();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableRescaleNormal();
		}
	}
	
	public abstract void onClick(Mode m);
	
	private void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d) {
		GlStateManager.translate((float) xPosition, (float) yPosition, 100.0F + this.zLevel);
		GlStateManager.translate(8.0F, 8.0F, 0.0F);
		GlStateManager.scale(2F, 2F, -2F);
		GlStateManager.scale(0.25F, 0.25F, 0.25F);
		
		if (isGui3d) {
			GlStateManager.scale(40.0F, 40.0F, 40.0F);
			GlStateManager.rotate(180, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(0, 0.0F, 1.0F, 0.0F);
			GlStateManager.enableLighting();
		} else {
			GlStateManager.rotate(180, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(64.0F, 64.0F, 64.0F);
			GlStateManager.disableLighting();
		}
	}
	
	private void renderItemIntoGUI(ItemStack stack, int x, int y) {
		GlStateManager.pushMatrix();
		this.setupGuiTransform(x, y, true);
		UReference.getClientProxy().getModelRenderHelper().renderItem(stack);
		GlStateManager.popMatrix();
	}
}
