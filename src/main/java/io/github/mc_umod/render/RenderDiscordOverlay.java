package io.github.mc_umod.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import io.github.mc_umod.UReference;
import io.github.mc_umod.gui.items.GuiRescources;
import io.github.mc_umod.util.RGBA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.*;

public class RenderDiscordOverlay {

	
	public RenderDiscordOverlay() {
	}
	
	private float xS = -200;
	private float time = 0;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void unpause(KeyInputEvent event){
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(UReference.getClientProxy().getInfoBinding().getKeyCode())){
			this.reset();
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void renderEvent(RenderGameOverlayEvent event){
		String invite = "discord.gg/QXbWS36";
		if(xS < 0 && time <= 100)xS += 0.1;else if(time <= 100)time += 0.01; else xS -= 0.1;
		RGBA rgb = new RGBA(Color.DARK_GRAY);
		double d = Minecraft.getMinecraft().displayWidth/2;
		GlStateManager.pushMatrix();
		GlStateManager.translate(d, xS, 0);
		GlStateManager.disableLighting();
		Tessellator tes = Tessellator.getInstance();
		VertexBuffer buffer = tes.getBuffer();
		buffer.begin(6, DefaultVertexFormats.POSITION_COLOR);
		buffer.pos(200, 75, 0).color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getAlpha()).endVertex();
		buffer.pos(-200, 75, 0).color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getAlpha()).endVertex();
		buffer.pos(-200, 175, 0).color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getAlpha()).endVertex();
		buffer.pos(200, 175, 0).color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getAlpha()).endVertex();
		tes.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.enableAlpha();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new GuiRescources("logo.png"));
		buffer.begin(6, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(175, 90, 0).tex(0, 0).endVertex();
		buffer.pos(110, 90, 0).tex(1, 0).endVertex();
		buffer.pos(110, 160, 0).tex(1, 1).endVertex();
		buffer.pos(175, 160, 0).tex(0, 1).endVertex();
		tes.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new GuiRescources("server.png"));
		buffer.begin(6, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-110, 90, 0).tex(0, 0).endVertex();
		buffer.pos(-175, 90, 0).tex(1, 0).endVertex();
		buffer.pos(-175, 160, 0).tex(1, 1).endVertex();
		buffer.pos(-110, 160, 0).tex(0, 1).endVertex();
		tes.draw();
		GlStateManager.scale(2, 2, 0);
		Minecraft.getMinecraft().fontRendererObj.drawString(invite, -50, 70, 0xFFFFFF);
		GlStateManager.scale(2, 2, 0);
		Minecraft.getMinecraft().fontRendererObj.drawString("HyCraftHD", -25, 23, 0xFFFFFF);
		GlStateManager.popMatrix();	
	}
	
	public void reset(){
		time = 0;
	}
	
}
