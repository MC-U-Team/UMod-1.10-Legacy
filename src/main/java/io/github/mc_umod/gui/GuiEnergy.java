package io.github.mc_umod.gui;

import java.io.IOException;

import io.github.mc_umod.api.energy.IPowerProvieder;
import io.github.mc_umod.block.machine.BlockSolarPanel;
import io.github.mc_umod.block.machine.BlockSolarPanel.EnumTypeSolarPanel;
import io.github.mc_umod.gui.items.GuiRescources;
import io.github.mc_umod.utils.EnergyUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiEnergy extends GuiScreen {
	
	private int xSize;
	private int ySize;
	private IPowerProvieder tile;
	private World worldObj;
	private boolean back;
	
	public GuiEnergy(EntityPlayer player,BlockPos pos, boolean back) {
		super();
		this.xSize = 176;
		this.ySize = 166;
		this.worldObj = player.worldObj;
		this.tile = (IPowerProvieder) worldObj.getTileEntity(pos);
		this.back = back;
		super.initGui();
	}
	
	public GuiEnergy(EntityPlayer player,BlockPos pos) {
		this(player,pos, true);
	}
	
	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) throws IOException {
		super.keyTyped(p_73869_1_, p_73869_2_);
		if (p_73869_1_ == 'e') {
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new GuiRescources("solar.png"));
		
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		if (back) {
			this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		}
		
		int high = 0;
		if (tile.hasPower()) {
			double ps = tile.getStoredPower() * 100 / tile.getMaximalPower();
			high = (int) (ps * 0.01 * 152);
		}
		
		this.drawStorage(k, l, high);
		IBlockState ste = worldObj.getBlockState(((TileEntity) tile).getPos());
		EnumTypeSolarPanel type = EnumTypeSolarPanel.byMetadata(ste.getBlock().getMetaFromState(ste));
		
		this.drawCenteredString(this.fontRendererObj, I18n.format(ste.getBlock().getUnlocalizedName() + (ste.getBlock() instanceof BlockSolarPanel ? type.getName() : "") + ".name"), k + xSize / 2 - 37 / 2, l + 10, 4210752, false);
		int maxstringlength = 119;
		String s1 = "Generate: ";
		String s2 = "Stored: ";
		String s3 = "Status: ";
		String s4 = "Error: ";
		this.fontRendererObj.drawSplitString(s1 + (tile.isWorking() ? (EnergyUtils.translate(tile.getPowerProducNeeds()) + " UE/t") : "0 UE/t"), k + 10, l + 50, maxstringlength, 4210752);
		this.fontRendererObj.drawSplitString(s2 + EnergyUtils.translate(tile.getStoredPower()) + " / " + EnergyUtils.translate(tile.getMaximalPower()), k + 10, l + 80, maxstringlength, 4210752);
		this.fontRendererObj.drawSplitString(s3 + (tile.isWorking() ? "On" : "Off"), k + 10, l + 110, maxstringlength, 4210752);
		if (!tile.isWorking() && tile.getErrorMessage() != null && tile.getErrorMessage() != "") {
			this.fontRendererObj.drawSplitString(s4 + tile.getErrorMessage(), k + 10, l + 140, maxstringlength, 4210752);
		}
		
	}
	
	public int drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
		return fontRendererIn.drawString(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color, shadow);
	}
	
	public void drawStorage(int l, int k, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		int x = l + 139, y = k;
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 159, 0);
		Tessellator ts = Tessellator.getInstance();
		VertexBuffer ren = ts.getBuffer();
		ren.begin(7, DefaultVertexFormats.POSITION_TEX);
		ren.pos(30D, -(double) height, 0D).tex(206D * f, 6D * f1).endVertex();
		ren.pos(0D, -(double) height, 0D).tex(176D * f, 6D * f1).endVertex();
		ren.pos(0D, 0, 0D).tex(176D * f, (6D + (double) height) * f1).endVertex();
		ren.pos(30D, 0, 0D).tex(206D * f, (6D + (double) height) * f1).endVertex();
		ts.draw();
		GlStateManager.popMatrix();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
