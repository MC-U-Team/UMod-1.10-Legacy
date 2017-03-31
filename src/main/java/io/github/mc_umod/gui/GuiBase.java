package io.github.mc_umod.gui;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.function.*;

import org.lwjgl.input.*;

import com.google.common.collect.*;
import com.mojang.realmsclient.gui.*;

import io.github.mc_umod.*;
import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.api.render.*;
import io.github.mc_umod.corelib.util.*;
import io.github.mc_umod.gui.container.*;
import io.github.mc_umod.gui.inventory.*;
import io.github.mc_umod.gui.items.*;
import io.github.mc_umod.gui.mode.*;
import io.github.mc_umod.render.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.inventory.Container;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

@SuppressWarnings("deprecation")
@SideOnly(Side.CLIENT)
public abstract class GuiBase extends GuiScreen {
	
	public ArrayList<ModeTabs> tabs = new ArrayList<ModeTabs>();
	public ResourceLocation loc, loc1, loc2, loc3;
	public static final ResourceLocation CLEAR_GUI = new GuiRescources("clear.png");
	public EntityPlayer player;
	public TileEntity tile;
	public BlockPos pos;
	public Slot hoveredSlot;
	public ContainerBase container;
	public World worldObj;
	public GLHelper help;
	public ModeTabs activeTab;
	
	public GuiBase(ResourceLocation loc,EntityPlayer player, BlockPos pos, Container con) {
		super();
		this.loc = loc;
		this.loc1 = loc;
		this.loc2 = new GuiRescources("battery.png");
		this.loc3 = new GuiRescources("IOMode.png");
		this.player = player;
		this.worldObj = this.player.getEntityWorld();
		this.tile = this.worldObj.getTileEntity(pos);
		this.pos = pos;
		this.container = (ContainerBase) con;
		this.help = UReference.getClientProxy().getGLHelper();
	}
	
	public int drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
		return fontRendererIn.drawString(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color, shadow);
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(loc);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
		
	@Override
	public void initGui() {
		super.initGui();
		tabs.clear();
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		tabs.add(new ModeTabs(new ItemStack(UBlocks.ores, 0, 5), "Normal Mode", new ModeNormal(this),this.loc1,this,new Consumer<BaseSlot>() {
			@Override
			public void accept(BaseSlot arg0) {
				if(!(arg0 instanceof BaseBatteryInputSlot)){
						arg0.setVisible(true);
				}
			}
		}, 0, true));
		if(this.tile instanceof IBatteryProvider)
		tabs.add(new ModeTabs(new ItemStack(UBlocks.charge), "Battery Mode",ImplGui.NULL, this.loc2,this,new Consumer<BaseSlot>() {
			@Override
			public void accept(BaseSlot arg0) {
				if(arg0 instanceof BaseBatteryInputSlot && arg0 instanceof BaseNormalSlot){
						arg0.setVisible(true);
				}
			}
		}, 0, false));
		if(this.tile instanceof IIOMode)
		tabs.add(new ModeTabs(new ItemStack(Blocks.HOPPER), "IO Mode",new IOMode(this),this.loc3,this, 0, false));
		if(this.tile instanceof IWorldView)
		tabs.add(new ModeTabs(new ItemStack(Blocks.WOOL, 0, EnumDyeColor.ORANGE.getDyeDamage()), "Panel Mode", new ModeColor(this),CLEAR_GUI,this, 0, false)); 
		if (this.tile instanceof IPowerProvieder)
		tabs.add(new ModeTabs(new ItemStack(UBlocks.solarpanel), "Energy Mode", new ModeEnergy(this),new GuiRescources("solar.png"),this, 0, false));
		this.player.openContainer = this.container;
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
	}
	
	public boolean canColorChange() {
		return true;
	}
	
	public abstract void addToBox(GuiCombobox box2);
	
	/** The X size of the inventory window in pixels. */
	public int xSize = 176;
	/** The Y size of the inventory window in pixels. */
	public int ySize = 166;
	/**
	 * Starting X position for the Gui. Inconsistent use for Gui backgrounds.
	 */
	public int guiLeft;
	/**
	 * Starting Y position for the Gui. Inconsistent use for Gui backgrounds.
	 */
	public int guiTop;
	/** holds the slot currently hovered */
	public Slot theSlot;
	/** Used when touchscreen is enabled. */
	public Slot clickedSlot;
	/** Used when touchscreen is enabled. */
	public boolean isRightMouseClick;
	/** Used when touchscreen is enabled */
	public ItemStack draggedStack;
	public int touchUpX;
	public int touchUpY;
	public Slot returningStackDestSlot;
	public long returningStackTime;
	/** Used when touchscreen is enabled */
	public ItemStack returningStack;
	public Slot currentDragTargetSlot;
	public long dragItemDropDelay;
	@SuppressWarnings("rawtypes")
	protected final Set<Slot> dragSplittingSlots = Sets.<Slot> newHashSet();
	protected boolean dragSplitting;
	public int dragSplittingLimit;
	public int dragSplittingButton;
	public boolean ignoreMouseUp;
	public int dragSplittingRemnant;
	public long lastClickTime;
	public Slot lastClickSlot;
	public int lastClickButton;
	public boolean doubleClick;
	public ItemStack shiftClickedSlot;
	public static final String __OBFID = "CL_00000737";
	public EnumFacing hal = EnumFacing.NORTH;
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int mousePX = mouseX;
		int mousePY = mouseY;
		this.drawDefaultBackground();
		int k = this.guiLeft;
		int l = this.guiTop;
		this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		GlStateManager.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableLighting();
		GlStateManager.disableDepth();
		int ks;
		
		for (ks = 0; ks < this.buttonList.size(); ++ks) {
			((GuiButton) this.buttonList.get(ks)).drawButton(this.mc, mouseX, mouseY);
		}
		
		for (ks = 0; ks < this.labelList.size(); ++ks) {
			((GuiLabel) this.labelList.get(ks)).drawLabel(this.mc, mouseX, mouseY);
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) k, (float) l, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
		this.theSlot = null;
		short short1 = 240;
		short short2 = 240;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) short1 / 1.0F, (float) short2 / 1.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		int k1;
		
			for (int ig1 = 0; ig1 < this.container.inventorySlots.size(); ig1++) {
				Slot slot = (Slot) this.container.inventorySlots.get(ig1);
				if (slot instanceof BaseSlot && ((BaseSlot) slot).hasColor() && ((BaseSlot) slot).isVisible() && (!this.isMouseOverSlot(slot, mouseX, mouseY) || !slot.canBeHovered())) {
					GlStateManager.disableLighting();
					GlStateManager.disableDepth();
					int j1 = slot.xDisplayPosition;
					k1 = slot.yDisplayPosition;
					GlStateManager.colorMask(true, true, true, false);
					RGBA st = ((BaseSlot) slot).getHoverColor(0);
					RGBA en = ((BaseSlot) slot).getHoverColor(1);
					this.help.drawGradientRect(j1, k1, j1 + 16, k1 + 16, st, en, this.zLevel);
					GlStateManager.colorMask(true, true, true, true);
					GlStateManager.enableLighting();
					GlStateManager.enableDepth();
				}
			}
		
		RenderHelper.disableStandardItemLighting();
		this.drawGuiContainerForegroundLayer(mouseX, mouseY);
		RenderHelper.enableGUIStandardItemLighting();
		InventoryPlayer inventoryplayer = this.mc.thePlayer.inventory;
		ItemStack itemstack = this.draggedStack == null ? inventoryplayer.getItemStack() : this.draggedStack;
		
		if (itemstack != null) {
			byte b0 = 8;
			k1 = this.draggedStack == null ? 8 : 16;
			String s = null;
			
			if (this.draggedStack != null && this.isRightMouseClick) {
				itemstack = itemstack.copy();
				itemstack.stackSize = MathHelper.ceiling_float_int((float) itemstack.stackSize / 2.0F);
			} else if (this.dragSplitting && this.dragSplittingSlots.size() > 1) {
				itemstack = itemstack.copy();
				itemstack.stackSize = this.dragSplittingRemnant;
				
				if (itemstack.stackSize == 0) {
					s = "" + ChatFormatting.YELLOW + "0";
				}
			}
			
			this.drawItemStack(itemstack, mouseX - k - b0, mouseY - l - k1, s);
		}
		
		if (this.returningStack != null) {
			float f1 = (float) (Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;
			
			if (f1 >= 1.0F) {
				f1 = 1.0F;
				this.returningStack = null;
			}
			
			k1 = this.returningStackDestSlot.xDisplayPosition - this.touchUpX;
			int j2 = this.returningStackDestSlot.yDisplayPosition - this.touchUpY;
			int l1 = this.touchUpX + (int) ((float) k1 * f1);
			int i2 = this.touchUpY + (int) ((float) j2 * f1);
			this.drawItemStack(this.returningStack, l1, i2, (String) null);
		}
		
		if (tabs != null) {
			int i = 0;
			for (ModeTabs tab : tabs) {
				tab.render(i);
				i++;
			}
		}
						
		if(this.activeTab != null && !(this.activeTab.getGui() instanceof ModeNormal))this.activeTab.getGui().render(mouseX, mouseY);
		
		if (inventoryplayer.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack()) {
			ItemStack itemstack1 = this.theSlot.getStack();
			this.renderToolTip(itemstack1, mouseX, mouseY);
		}
		GlStateManager.popMatrix();

		if (tabs != null) {
			int i = 0;
			for (ModeTabs tab : tabs) {
				tab.renderToolTip(i,mouseX, mouseY, this.guiLeft, this.guiTop);
				i++;
			}
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) k, (float) l, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) short1 / 1.0F, (float) short2 / 1.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		for (Object sl : this.container.inventorySlots) {
			Slot slot = (Slot) sl;
			if (!(slot instanceof BaseSlot) || ((BaseSlot) slot).isVisible()) {
					if (this.isMouseOverSlot(slot, mouseX, mouseY) && slot.canBeHovered()) {
						this.theSlot = slot;
						int j1 = slot.xDisplayPosition;
						k1 = slot.yDisplayPosition;
						if (slot instanceof BaseSlot && ((BaseSlot) slot).hasColor()) {
							RGBA st = ((BaseSlot) slot).getHoverColor(2);
							RGBA en = ((BaseSlot) slot).getHoverColor(3);
							this.help.drawGradientRect(j1, k1, j1 + 16, k1 + 16, st, en, this.zLevel);
						} else {
							this.drawGradientRect(j1, k1, j1 + 16, k1 + 16, -2130706433, -2130706433);
						}
						if (slot instanceof BaseSlot) {
							int xc = j1 - 1,yc = k1,widthc = 18,heightc = 18;
							RGBA rgbc = new RGBA(Color.DARK_GRAY);
							this.help.drawGradientRect(xc - 1, yc - 2,xc + widthc + 1, yc, rgbc);
							this.help.drawGradientRect(xc - 1, yc + heightc - 2,xc + widthc + 1, yc + heightc, rgbc);
							this.help.drawGradientRect(xc - 1, yc,xc + 1, yc + heightc, rgbc);
							this.help.drawGradientRect(xc + widthc - 1, yc ,xc + widthc + 1,yc + heightc, rgbc);
						}
					}
					this.drawSlot(slot);
			}
		}
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
		GlStateManager.enableDepth();
		for (Object sl : this.container.inventorySlots) {
			Slot slot = (Slot) sl;
			if (slot != null && this.isMouseOverSlot(slot, mouseX, mouseY) && slot.canBeHovered()) {
				if (slot instanceof BaseSlot && ((BaseSlot) slot).hasString() && ((BaseSlot) slot).isVisible() && !(Keyboard.isKeyDown(UReference.getClientProxy().getInfoBinding().getKeyCode()) && slot.getHasStack())) {
					GlStateManager.pushMatrix();
					Tessellator ts = Tessellator.getInstance();
					BaseSlot slt = (BaseSlot) slot;
					RGBA sl1 = slt.getHoverColor(0);
					RGBA sli = new RGBA(sl1.toAWTColor().darker()).setAlpha(180);
					GlStateManager.disableTexture2D();
					GlStateManager.enableBlend();
					GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
					GlStateManager.shadeModel(7425);
					VertexBuffer rend = ts.getBuffer();
					rend.begin(7, DefaultVertexFormats.POSITION_COLOR);
					rend.pos(mouseX + slt.getWidth(), mouseY, this.zLevel).color(sl1.getRed(), sl1.getGreen(), sl1.getBlue(), sl1.getAlpha()).endVertex();
					rend.pos(mouseX, mouseY, this.zLevel).color(sli.getRed(), sli.getGreen(), sli.getBlue(), sli.getAlpha()).endVertex();
					rend.pos(mouseX, mouseY + slt.getHeight(), this.zLevel).color(sli.getRed(), sli.getGreen(), sli.getBlue(), sli.getAlpha()).endVertex();
					rend.pos(mouseX + slt.getWidth(), mouseY + slt.getHeight(), this.zLevel).color(sl1.getRed(), sl1.getGreen(), sl1.getBlue(), sl1.getAlpha()).endVertex();
					ts.draw();
					GlStateManager.shadeModel(7424);
					GlStateManager.disableBlend();
					GlStateManager.enableAlpha();
					GlStateManager.enableTexture2D();
					if (((BaseSlot) slot).hasMoreLines()) {
						String[] str = ((BaseSlot) slot).getString().split("\n");
						for (int i = 0; i < str.length; i++)
							this.fontRendererObj.drawString(str[i], mouseX + 4, mouseY + 4 + (i * 16), ((BaseSlot) slot).getFontColor());
					} else {
						this.fontRendererObj.drawString(((BaseSlot) slot).getString(), mouseX + 4, mouseY + 4, ((BaseSlot) slot).getFontColor());
					}
					GlStateManager.popMatrix();
					GlStateManager.enableLighting();
					GlStateManager.enableDepth();
				} else if (slot != null && slot.getHasStack()) {
					this.renderToolTip(slot.getStack(), mouseX, mouseY);
				}
			}
		}
		RenderHelper.enableStandardItemLighting();
		
	}
	
	public EnumFacing getIOFaceing() {
		return hal;
	}	
	
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {}
	
	/**
	 * Render an ItemStack. Args : stack, x, y, format
	 */
	public void drawItemStack(ItemStack stack, int x, int y, String altText) {
		GlStateManager.translate(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		this.itemRender.zLevel = 200.0F;
		FontRenderer font = null;
		if (stack != null)
			font = stack.getItem().getFontRenderer(stack);
		if (font == null)
			font = fontRendererObj;
		this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
		this.itemRender.renderItemOverlayIntoGUI(font, stack, x, y - (this.draggedStack == null ? 0 : 8), altText);
		this.zLevel = 0.0F;
		this.itemRender.zLevel = 0.0F;
	}
	
	public void drawSlot(Slot slotIn) {
		int i = slotIn.xDisplayPosition;
		int j = slotIn.yDisplayPosition;
		ItemStack itemstack = slotIn.getStack();
		boolean flag = false;
		boolean flag1 = slotIn == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
		ItemStack itemstack1 = this.mc.thePlayer.inventory.getItemStack();
		String s = null;
		
		if (slotIn == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && itemstack != null) {
			itemstack = itemstack.copy();
			itemstack.stackSize /= 2;
		} else if (this.dragSplitting && this.dragSplittingSlots.contains(slotIn) && itemstack1 != null) {
			if (this.dragSplittingSlots.size() == 1) {
				return;
			}
			
			if (Container.canAddItemToSlot(slotIn, itemstack1, true) && this.container.canDragIntoSlot(slotIn)) {
				itemstack = itemstack1.copy();
				flag = true;
				Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, itemstack, slotIn.getStack() == null ? 0 : slotIn.getStack().stackSize);
				
				if (itemstack.stackSize > itemstack.getMaxStackSize()) {
					s = ChatFormatting.YELLOW + "" + itemstack.getMaxStackSize();
					itemstack.stackSize = itemstack.getMaxStackSize();
				}
				
				if (itemstack.stackSize > slotIn.getItemStackLimit(itemstack)) {
					s = ChatFormatting.YELLOW + "" + slotIn.getItemStackLimit(itemstack);
					itemstack.stackSize = slotIn.getItemStackLimit(itemstack);
				}
			} else {
				this.dragSplittingSlots.remove(slotIn);
				this.updateDragSplitting();
			}
		}
		
		this.zLevel = 100.0F;
		this.itemRender.zLevel = 100.0F;
		
		if (itemstack == null) {
			TextureAtlasSprite textureatlassprite = slotIn.getBackgroundSprite();
			
			if (textureatlassprite != null) {
				GlStateManager.disableLighting();
				this.mc.getTextureManager().bindTexture(slotIn.getBackgroundLocation());
				this.drawTexturedModalRect(i, j, textureatlassprite, 16, 16);
				GlStateManager.enableLighting();
				flag1 = true;
			}
		}
		
		if (!flag1) {
			if (flag) {
				drawRect(i, j, i + 16, j + 16, -2130706433);
			}
			GlStateManager.enableDepth();
			this.itemRender.renderItemAndEffectIntoGUI(itemstack, i, j);
			this.itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, itemstack, i, j, s);
		}
		
		this.itemRender.zLevel = 0.0F;
		this.zLevel = 0.0F;
	}
	
	@SuppressWarnings("rawtypes")
	public void updateDragSplitting() {
		ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();
		
		if (itemstack != null && this.dragSplitting) {
			this.dragSplittingRemnant = itemstack.stackSize;
			ItemStack itemstack1;
			int i;
			
			for (Iterator iterator = this.dragSplittingSlots.iterator(); iterator.hasNext(); this.dragSplittingRemnant -= itemstack1.stackSize - i) {
				Slot slot = (Slot) iterator.next();
				itemstack1 = itemstack.copy();
				i = slot.getStack() == null ? 0 : slot.getStack().stackSize;
				Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, itemstack1, i);
				
				if (itemstack1.stackSize > itemstack1.getMaxStackSize()) {
					itemstack1.stackSize = itemstack1.getMaxStackSize();
				}
				
				if (itemstack1.stackSize > slot.getItemStackLimit(itemstack1)) {
					itemstack1.stackSize = slot.getItemStackLimit(itemstack1);
				}
			}
		}
	}
	
	/**
	 * Returns the slot at the given coordinates or null if there is none.
	 */
	public Slot getSlotAtPosition(int x, int y) {
		for (int k = 0; k < this.container.inventorySlots.size(); ++k) {
			Slot slot = (Slot) this.container.inventorySlots.get(k);
			
			if (this.isMouseOverSlot(slot, x, y)) {
				return slot;
			}
		}
		
		return null;
	}
	
	public void handelMouseInput(int mouseX, int mouseY) {}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		if (this.activeTab != null && clickedMouseButton == 0)this.activeTab.getGui().onDrag(mouseX, mouseY);
		Slot slot = this.getSlotAtPosition(mouseX, mouseY);
		ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();
		
		if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
			if (clickedMouseButton == 0 || clickedMouseButton == 1) {
				if (this.draggedStack == null) {
					if (slot != this.clickedSlot && this.clickedSlot.getStack() != null) {
						this.draggedStack = this.clickedSlot.getStack().copy();
					}
				} else if (this.draggedStack.stackSize > 1 && slot != null && Container.canAddItemToSlot(slot, this.draggedStack, false)) {
					long i = Minecraft.getSystemTime();
					
					if (this.currentDragTargetSlot == slot) {
						if (i - this.dragItemDropDelay > 500L) {
							this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, ClickType.PICKUP);
							this.handleMouseClick(slot, slot.slotNumber, 1, ClickType.PICKUP);
							this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, ClickType.PICKUP);
							this.dragItemDropDelay = i + 750L;
							--this.draggedStack.stackSize;
						}
					} else {
						this.currentDragTargetSlot = slot;
						this.dragItemDropDelay = i;
					}
				}
			}
		} else if (this.dragSplitting && slot != null && itemstack != null && itemstack.stackSize > this.dragSplittingSlots.size() && Container.canAddItemToSlot(slot, itemstack, true) && slot.isItemValid(itemstack) && this.container.canDragIntoSlot(slot)) {
			this.dragSplittingSlots.add(slot);
			this.updateDragSplitting();
		}
	}
	
	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if (cal.get(Calendar.MONTH) == Calendar.APRIL && cal.get(Calendar.DAY_OF_MONTH) == 1) {
			this.worldObj.createExplosion(player, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 2.5F, false);
			this.player.addChatComponentMessage(new TextComponentString(ChatFormatting.YELLOW + "" + ChatFormatting.OBFUSCATED + "HOLLO" + ChatFormatting.RESET + " " + ChatFormatting.RED + "You be trolled " + ChatFormatting.GREEN + "" + ChatFormatting.OBFUSCATED + "HOLLO" + ChatFormatting.RESET));
		}
		if (mouseButton == 0) {
			if (tabs != null) {
				int i = 0;
				for (ModeTabs tab : tabs) {
					tab.handelMouseInput(i,mouseX, mouseY, this.guiLeft, this.guiTop);
					i++;
				}
			}
		}
		if (this.activeTab != null && mouseButton == 0 && !(this.activeTab.getGui() instanceof ModeNormal) && !this.activeTab.getGui().equals(ImplGui.NULL)){
			this.activeTab.getGui().onClick(mouseX, mouseY);
			return;
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
		boolean flag = this.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(mouseButton - 100);
		Slot slot = this.getSlotAtPosition(mouseX, mouseY);
		long i = Minecraft.getSystemTime();
		this.doubleClick = this.lastClickSlot == slot && i - this.lastClickTime < 250L && this.lastClickButton == mouseButton;
		this.ignoreMouseUp = false;
		
		if (mouseButton == 0 || mouseButton == 1 || flag) {
			int j = this.guiLeft;
			int k = this.guiTop;
			boolean flag1 = mouseX < j || mouseY < k || mouseX >= j + this.xSize || mouseY >= k + this.ySize;
			if (slot != null)
				flag1 = false; // Forge, prevent dropping of items through slots outside of GUI boundaries
			int l = -1;
			
			if (slot != null) {
				l = slot.slotNumber;
			}
			
			if (flag1) {
				l = -999;
			}
			
			if (this.mc.gameSettings.touchscreen && flag1 && this.mc.thePlayer.inventory.getItemStack() == null) {
				this.mc.displayGuiScreen((GuiScreen) null);
				return;
			}
			
			if (l != -1) {
				if (this.mc.gameSettings.touchscreen) {
					if (slot != null && slot.getHasStack()) {
						this.clickedSlot = slot;
						this.draggedStack = null;
						this.isRightMouseClick = mouseButton == 1;
					} else {
						this.clickedSlot = null;
					}
				} else if (!this.dragSplitting) {
					if (this.mc.thePlayer.inventory.getItemStack() == null) {
						if (this.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(mouseButton - 100)) {
							this.handleMouseClick(slot, l, mouseButton, ClickType.CLONE);
						} else {
							boolean flag2 = l != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
							ClickType clicktype = ClickType.PICKUP;
							
							if (flag2) {
								this.shiftClickedSlot = slot != null && slot.getHasStack() ? slot.getStack() : null;
								clicktype = ClickType.QUICK_MOVE;
							} else if (l == -999) {
								clicktype = ClickType.THROW;
							}
							
							this.handleMouseClick(slot, l, mouseButton, clicktype);
						}
						
						this.ignoreMouseUp = true;
					} else {
						this.dragSplitting = true;
						this.dragSplittingButton = mouseButton;
						this.dragSplittingSlots.clear();
						
						if (mouseButton == 0) {
							this.dragSplittingLimit = 0;
						} else if (mouseButton == 1) {
							this.dragSplittingLimit = 1;
						} else if (this.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(mouseButton - 100)) {
							this.dragSplittingLimit = 2;
						}
					}
				}
			}
		}
		
		this.lastClickSlot = slot;
		this.lastClickTime = i;
		this.lastClickButton = mouseButton;
	}
	
	public void onMouseClickMoved(int mouseX, int mouseY) {}
	
	public abstract void onIOModeSwitched();
	
	/**
	 * Called when a mouse button is released. Args : mouseX, mouseY, releaseButton
	 */
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state); // Forge, Call parent to release buttons
		Slot slot = this.getSlotAtPosition(mouseX, mouseY);
		int i = this.guiLeft;
		int j = this.guiTop;
		boolean flag = mouseX < i || mouseY < j || mouseX >= i + this.xSize || mouseY >= j + this.ySize;
		if (slot != null)
			flag = false; // Forge, prevent dropping of items through slots outside of GUI boundaries
		int k = -1;
		
		if (slot != null) {
			k = slot.slotNumber;
		}
		
		if (flag) {
			k = -999;
		}
		
		if (this.doubleClick && slot != null && state == 0 && this.container.canMergeSlot((ItemStack) null, slot)) {
			if (isShiftKeyDown()) {
				if (slot != null && slot.inventory != null && this.shiftClickedSlot != null) {
					for (Slot slot2 : this.container.inventorySlots) {
						if (slot2 != null && slot2.canTakeStack(this.mc.thePlayer) && slot2.getHasStack() && slot2.isSameInventory(slot) && Container.canAddItemToSlot(slot2, this.shiftClickedSlot, true)) {
							this.handleMouseClick(slot2, slot2.slotNumber, state, ClickType.QUICK_MOVE);
						}
					}
				}
			} else {
				this.handleMouseClick(slot, k, state, ClickType.PICKUP_ALL);
			}
			
			this.doubleClick = false;
			this.lastClickTime = 0L;
		} else {
			if (this.dragSplitting && this.dragSplittingButton != state) {
				this.dragSplitting = false;
				this.dragSplittingSlots.clear();
				this.ignoreMouseUp = true;
				return;
			}
			
			if (this.ignoreMouseUp) {
				this.ignoreMouseUp = false;
				return;
			}
			
			if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
				if (state == 0 || state == 1) {
					if (this.draggedStack == null && slot != this.clickedSlot) {
						this.draggedStack = this.clickedSlot.getStack();
					}
					
					boolean flag2 = Container.canAddItemToSlot(slot, this.draggedStack, false);
					
					if (k != -1 && this.draggedStack != null && flag2) {
						this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, ClickType.PICKUP);
						this.handleMouseClick(slot, k, 0, ClickType.PICKUP);
						
						if (this.mc.thePlayer.inventory.getItemStack() != null) {
							this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, ClickType.PICKUP);
							this.touchUpX = mouseX - i;
							this.touchUpY = mouseY - j;
							this.returningStackDestSlot = this.clickedSlot;
							this.returningStack = this.draggedStack;
							this.returningStackTime = Minecraft.getSystemTime();
						} else {
							this.returningStack = null;
						}
					} else if (this.draggedStack != null) {
						this.touchUpX = mouseX - i;
						this.touchUpY = mouseY - j;
						this.returningStackDestSlot = this.clickedSlot;
						this.returningStack = this.draggedStack;
						this.returningStackTime = Minecraft.getSystemTime();
					}
					
					this.draggedStack = null;
					this.clickedSlot = null;
				}
			} else if (this.dragSplitting && !this.dragSplittingSlots.isEmpty()) {
				this.handleMouseClick((Slot) null, -999, Container.getQuickcraftMask(0, this.dragSplittingLimit), ClickType.QUICK_CRAFT);
				
				for (Slot slot1 : this.dragSplittingSlots) {
					this.handleMouseClick(slot1, slot1.slotNumber, Container.getQuickcraftMask(1, this.dragSplittingLimit), ClickType.QUICK_CRAFT);
				}
				
				this.handleMouseClick((Slot) null, -999, Container.getQuickcraftMask(2, this.dragSplittingLimit), ClickType.QUICK_CRAFT);
			} else if (this.mc.thePlayer.inventory.getItemStack() != null) {
				if (this.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(state - 100)) {
					this.handleMouseClick(slot, k, state, ClickType.CLONE);
				} else {
					boolean flag1 = k != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
					
					if (flag1) {
						this.shiftClickedSlot = slot != null && slot.getHasStack() ? slot.getStack() : null;
					}
					
					this.handleMouseClick(slot, k, state, flag1 ? ClickType.QUICK_MOVE : ClickType.PICKUP);
				}
			}
		}
		
		if (this.mc.thePlayer.inventory.getItemStack() == null) {
			this.lastClickTime = 0L;
		}
		
		this.dragSplitting = false;
	}
	
	/**
	 * Returns if the passed mouse position is over the specified slot. Args : slot, mouseX, mouseY
	 */
	public boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY) {
		return this.isPointInRegion(slotIn.xDisplayPosition, slotIn.yDisplayPosition, 16, 16, mouseX, mouseY);
	}
	
	/**
	 * Test if the 2D point is in a rectangle (relative to the GUI). Args : rectX, rectY, rectWidth, rectHeight, pointX, pointY
	 */
	protected boolean isPointInRegion(int left, int top, int right, int bottom, int pointX, int pointY) {
		int k1 = this.guiLeft;
		int l1 = this.guiTop;
		pointX -= k1;
		pointY -= l1;
		return pointX >= left - 1 && pointX < left + right + 1 && pointY >= top - 1 && pointY < top + bottom + 1;
	}
	
	/**
	 * Fired when a key is typed (except F11 who toggle full screen). This is the equivalent of KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
	 */
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
			this.mc.thePlayer.closeScreen();
		}
		this.checkHotbarKeys(keyCode);
		
		if (this.theSlot != null && this.theSlot.getHasStack()) {
			if (keyCode == this.mc.gameSettings.keyBindPickBlock.getKeyCode()) {
				this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, ClickType.CLONE);
			} else if (keyCode == this.mc.gameSettings.keyBindDrop.getKeyCode()) {
				this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, isCtrlKeyDown() ? 1 : 0, ClickType.THROW);
			}
		}
	}
	
	protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
		if (slotIn != null) {
			slotId = slotIn.slotNumber;
		}
		this.mc.playerController.windowClick(this.container.windowId, slotId, mouseButton, type, this.mc.thePlayer);
	}
	
	/**
	 * This function is what controls the hotbar shortcut check when you press a number key when hovering a stack. Args : keyCode, Returns true if a Hotbar key is pressed, else false
	 */
	protected boolean checkHotbarKeys(int keyCode) {
		if (this.player.inventory.getItemStack() == null && this.theSlot != null) {
			for (int j = 0; j < 9; ++j) {
				if (keyCode == this.mc.gameSettings.keyBindsHotbar[j].getKeyCode()) {
					this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, j, ClickType.SWAP);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void onGuiClosed() {
		if (this.mc.thePlayer != null) {
			this.container.onContainerClosed(this.player);
		}
	}
	
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public void updateScreen() {
		super.updateScreen();
		if (!this.player.isEntityAlive() || this.player.isDead) {
			this.player.closeScreen();
		}
	}
	
	public FontRenderer getFontRender(){
		return fontRendererObj;
	}
	
}