package io.github.mc_umod.network.message;

import io.github.mc_umod.gui.GuiBase;
import io.github.mc_umod.gui.mode.*;
import io.github.mc_umod.utils.DirectionUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageIOCallback implements IMessage, IMessageHandler<MessageIOCallback, IMessage> {
	
	public EnumFacing face;
	public int item;
	
	public MessageIOCallback() {
	}
	
	public MessageIOCallback(EnumFacing fc, int item) {
		this.face = fc;
		this.item = item;
	}
	
	@Override
	public IMessage onMessage(MessageIOCallback message, MessageContext ctx) {
		GuiScreen sc = Minecraft.getMinecraft().currentScreen;
		if (sc != null && sc instanceof GuiBase) {
			GuiBase bs = (GuiBase) sc;
			for(ModeTabs tab : bs.tabs){
				if(tab != null && tab.getGui() instanceof IOMode){
					((IOMode)tab.getGui()).checkAndAdd(message.face, message.item);
				}
			}
		}
		return null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.face = DirectionUtils.getFacingFromShort(buf.readShort());
		this.item = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeShort(DirectionUtils.getShortFromFacing(face));
		buf.writeInt(item);
	}
	
}
