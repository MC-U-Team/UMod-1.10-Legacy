package io.github.mc_umod.network.message;

import io.github.mc_umod.api.render.*;
import io.github.mc_umod.utils.*;
import io.netty.buffer.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageSliderAdd implements IMessage, IMessageHandler<MessageSliderAdd, IMessage> {
	
	public int id, val;
	public BlockPos pos;
	
	public MessageSliderAdd() {
	}
	
	public MessageSliderAdd(int id, int val, BlockPos ps) {
		this.id = id;
		this.val = val;
		this.pos = ps;
	}
	
	@Override
	public IMessage onMessage(MessageSliderAdd message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			World w = ctx.getServerHandler().playerEntity.worldObj;
			TileEntity ent = w.getTileEntity(message.pos);
			if (ent instanceof ISliderEntry) {
				ISliderEntry en = (ISliderEntry) ent;
				en.storeValueForId(message.id, message.val);
			}
		}
		return null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = NetworkUtil.getPosFromBuffer(buf);
		this.id = buf.readInt();
		this.val = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		NetworkUtil.addPosToBuffer(buf, pos);
		buf.writeInt(id);
		buf.writeInt(val);
	}
	
}
