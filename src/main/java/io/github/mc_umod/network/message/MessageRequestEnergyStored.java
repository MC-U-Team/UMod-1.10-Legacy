package io.github.mc_umod.network.message;

import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.utils.NetworkUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageRequestEnergyStored implements IMessage, IMessageHandler<MessageRequestEnergyStored, MessageUpdateEnergyOnGUI>{

	public BlockPos pos;
	
	public MessageRequestEnergyStored() {}
	
	public MessageRequestEnergyStored(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public MessageUpdateEnergyOnGUI onMessage(MessageRequestEnergyStored message, MessageContext ctx) {
		TileEntity ent = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.pos);
		if(ent == null)return null;
		float time = 0;
		if(ent instanceof ITime) {
			time = ((ITime)ent).getTime();
		}
		float energy = 0;
		if(ent instanceof IEnergyProvider) {
			energy = (float)((IEnergyProvider)ent).getEnergy().getEnergyStored() / ((IEnergyProvider)ent).getEnergy().getMaxEnergyStored();
		}
		return new MessageUpdateEnergyOnGUI(time, energy);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = NetworkUtil.getPosFromBuffer(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NetworkUtil.addPosToBuffer(buf, this.pos);
	}

}
