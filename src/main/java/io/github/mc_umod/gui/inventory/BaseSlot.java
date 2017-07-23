package io.github.mc_umod.gui.inventory;

import java.awt.Color;

import io.github.mc_umod.api.render.StringMethod;
import io.github.mc_umod.renderapi.draw.Quad;
import io.github.mc_umod.util.RGBA;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.*;

public class BaseSlot extends Slot {
	
	private boolean visible = true;
	private RGBA nstart = null;
	private RGBA nend = null;
	private RGBA start = null;
	private RGBA end = null;
	private StringMethod ret = null;
	
	public BaseSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	public void setVisible(boolean vis) {
		this.visible = vis;
	}
	
	@Override
	public boolean getHasStack() {
		return super.getHasStack() && isVisible();
	}
	
	@Override
	public ItemStack getStack() {
		return isVisible() ? super.getStack() : null;
	}
	
	public void setHoverColor(RGBA nstart, RGBA nend, RGBA start, RGBA end) {
		this.start = start;
		this.end = end;
		this.nstart = nstart;
		this.nend = nend;
	}
	
	public boolean hasColor() {
		return start != null && end != null;
	}
	
	public boolean hasString() {
		return ret != null && ret.getString() != null;
	}
	
	public String getString() {
		return ret.getString();
	}
	
	public void setStringRet(StringMethod returnm) {
		ret = returnm;
	}
	
	public boolean hasMoreLines() {
		return ret.getString().contains("\n");
	}
	
	public int getFontColor() {
		return 0xFFFFFF;
	}
	
	@SideOnly(Side.CLIENT)
	public void render(){
		GlStateManager.disableLighting();
		GlStateManager.disableDepth();
		int j1 = this.xDisplayPosition;
		int k1 = this.yDisplayPosition;
		GlStateManager.colorMask(true, true, true, false);
		new Quad(j1, k1, j1 + 16, k1 + 16, start,end,start, end);
		GlStateManager.colorMask(true, true, true, true);
		GlStateManager.enableLighting();
		GlStateManager.enableDepth();
	}
	
	@SideOnly(Side.CLIENT)
	public void renderOverlay(){
		int j1 = this.xDisplayPosition;
		int k1 = this.yDisplayPosition;
		if (this.hasColor()) {
			new Quad(j1, k1, j1 + 16, k1 + 16, nstart, nend, nstart, nend);
		} 
		int xc = j1 - 1,yc = k1,widthc = 18,heightc = 18;
		RGBA rgbc = new RGBA(Color.DARK_GRAY);
		new Quad(xc - 1, yc - 2,xc + widthc + 1, yc, rgbc);
		new Quad(xc - 1, yc + heightc - 2,xc + widthc + 1, yc + heightc, rgbc);
		new Quad(xc - 1, yc,xc + 1, yc + heightc, rgbc);
		new Quad(xc + widthc - 1, yc ,xc + widthc + 1,yc + heightc, rgbc);
	}
	
	@SideOnly(Side.CLIENT)
	public void renderTooltip(int mouseX,int mouseY,int z,FontRenderer fontRendererObj){
		GlStateManager.pushMatrix();
		Tessellator ts = Tessellator.getInstance();
		RGBA sl1 = start;
		RGBA sli = new RGBA(sl1.toAWTColor().darker()).setAlpha(180);
		String[] lines = ret.getString().split("\n");
		int height = (fontRendererObj.FONT_HEIGHT + 4) * lines.length;
		int width = 0;
		for(String str : lines)
			width = Math.max(width, fontRendererObj.getStringWidth(str));
		width += 4;
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.shadeModel(7425);
		VertexBuffer rend = ts.getBuffer();
		rend.begin(7, DefaultVertexFormats.POSITION_COLOR);
		rend.pos(mouseX + width, mouseY, z).color(sl1.getRed(), sl1.getGreen(), sl1.getBlue(), sl1.getAlpha()).endVertex();
		rend.pos(mouseX, mouseY, z).color(sli.getRed(), sli.getGreen(), sli.getBlue(), sli.getAlpha()).endVertex();
		rend.pos(mouseX, mouseY + height, z).color(sli.getRed(), sli.getGreen(), sli.getBlue(), sli.getAlpha()).endVertex();
		rend.pos(mouseX + width, mouseY + height, z).color(sl1.getRed(), sl1.getGreen(), sl1.getBlue(), sl1.getAlpha()).endVertex();
		ts.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
		if (this.hasMoreLines()) {
			String[] str = this.getString().split("\n");
			for (int i = 0; i < str.length; i++)
				fontRendererObj.drawString(str[i], mouseX + 4, mouseY + 4 + (i * 16), this.getFontColor());
		} else {
			fontRendererObj.drawString(this.getString(), mouseX + 4, mouseY + 4, this.getFontColor());
		}
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
		GlStateManager.enableDepth();
	}
}
