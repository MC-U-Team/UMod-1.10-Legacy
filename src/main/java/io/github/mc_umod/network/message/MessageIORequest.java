package io.github.mc_umod.network.message;

import io.github.mc_umod.api.render.IIOMode;
import io.github.mc_umod.utils.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageIORequest implements IMessage, IMessageHandler<MessageIORequest, MessageIOCallback> {
	
	public BlockPos pos;
	public EnumFacing face;
	
	public MessageIORequest() {
	}
	
	public MessageIORequest(BlockPos pos, EnumFacing prov) {
		this.pos = pos;
		this.face = prov;
	}
	
	@Override
	public MessageIOCallback onMessage(MessageIORequest message, MessageContext ctx) {
		World w = ctx.getServerHandler().playerEntity.worldObj;
		TileEntity tile = w.getTileEntity(message.pos);
		if (message.face != null && tile != null && tile instanceof IIOMode) {
			IIOMode mode = (IIOMode) tile;
			if (mode.hasModeForFaceing(message.face)) {
				return new MessageIOCallback(message.face, mode.getModeFromFacing(message.face));
			} else {
				return new MessageIOCallback(message.face, Byte.MAX_VALUE);
			}
		}
		return null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = NetworkUtil.getPosFromBuffer(buf);
		this.face = DirectionUtils.getFacingFromShort(buf.readShort());
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		NetworkUtil.addPosToBuffer(buf, this.pos);
		buf.writeShort(DirectionUtils.getShortFromFacing(this.face));
	}
	
}
