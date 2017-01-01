package io.github.mc_umod.gui.mode;

import java.util.*;
import java.util.function.Consumer;

import com.google.common.collect.Lists;

import io.github.mc_umod.UReference;
import io.github.mc_umod.gui.GuiBase;
import io.github.mc_umod.gui.inventory.BaseSlot;
import io.github.mc_umod.gui.items.ImplGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@SuppressWarnings("deprecation")
public class ModeTabs extends Gui {
	
	private ItemStack stack;
	private String name;
	private int y;
	private ImplGui gui;
	private ResourceLocation background;
	private Container container;
	private GuiBase base_gui;
	private Consumer<BaseSlot> consumer;
	
	public ModeTabs(ItemStack stack, String name, ImplGui gui, ResourceLocation location,GuiBase base_gui,Consumer<BaseSlot> consumer,int y, boolean use) {
		this.stack = stack;
		this.name = name;
		this.y = y;
		this.gui = gui;
		this.background = location;
		this.container = base_gui.container;
		this.base_gui = base_gui;
		this.consumer = consumer;
		if(use){
			base_gui.activeTab = this;
		}
	}
	
	public ModeTabs(ItemStack stack, String name, ImplGui gui, ResourceLocation location,GuiBase base_gui,int y, boolean use) {
		this(stack,name,gui,location,base_gui,null, y, use);
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
	
	public boolean isInUse() {
		return this.equals(base_gui.activeTab);
	}
	
	public void render(int i) {
		int x = 28 * i;
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tabs.png"));
		if (!isInUse()) {
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
	
	public void renderToolTip(int i,int mouseX, int mouseY, int guileft, int guitop) {
		int x = 28 * i;
		int mx = (int) (mouseX - guileft);
		int my = (int) (mouseY - guitop);
		if (mx > x && mx < x + 28 && my > y - 33 && my < y) {
			this.drawHoveringText(Lists.newArrayList(name), mouseX + 4, mouseY + 4, Minecraft.getMinecraft().getRenderManager().getFontRenderer());
		}
	}
	
	public void handelMouseInput(int i,int mouseX, int mouseY, int guileft, int guitop) {
		int x = 28 * i;
		int mx = (int) (mouseX - guileft);
		int my = (int) (mouseY - guitop);
		if (mx > x && mx < x + 28 && my > y - 28 && my < y) {
			onClick();
		}
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
	
	public void onClick(){
		this.base_gui.activeTab = this;
		for(Slot slot : container.inventorySlots){
			if(slot instanceof BaseSlot){
				((BaseSlot) slot).setVisible(false);
			}
		}
		if(consumer != null){
			for(Slot slot : container.inventorySlots){
				if(slot instanceof BaseSlot)
				consumer.accept((BaseSlot) slot);
			}
		}
		this.base_gui.loc = background;
	}
	
	public ImplGui getGui(){
		return gui;
	}
	
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
