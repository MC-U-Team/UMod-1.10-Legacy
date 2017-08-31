package io.github.mc_umod.network.message;

import io.github.mc_umod.api.energy.IEnergyGUI;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageUpdateEnergyOnGUI implements IMessage, IMessageHandler<MessageUpdateEnergyOnGUI, IMessage>{
	
	public float energy;
	public float time;

	public MessageUpdateEnergyOnGUI() {}
	
	public MessageUpdateEnergyOnGUI(float time,float energy) {
		this.energy = energy;
		this.time = time;
	}
	
	@Override
	public IMessage onMessage(MessageUpdateEnergyOnGUI message, MessageContext ctx) {
		GuiScreen scrn = Minecraft.getMinecraft().currentScreen;
		if(scrn != null && scrn instanceof IEnergyGUI){
			((IEnergyGUI) scrn).update(message.time, message.energy);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.energy = buf.readFloat();
		this.time = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(this.energy);
		buf.writeFloat(this.time);
	}

}
