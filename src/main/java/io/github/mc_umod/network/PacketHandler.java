package io.github.mc_umod.network;

import io.github.mc_umod.network.message.MessageIOCallback;
import io.github.mc_umod.network.message.MessageIOMode;
import io.github.mc_umod.network.message.MessageIORequest;
import io.github.mc_umod.network.message.MessageSliderAdd;
import io.github.mc_umod.network.message.MessageSliderGet;
import io.github.mc_umod.network.message.MessageSliderRequest;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("umod");
	
	public PacketHandler() {
		INSTANCE.registerMessage(MessageIOMode.class, MessageIOMode.class, 0, Side.SERVER);
		INSTANCE.registerMessage(MessageIORequest.class, MessageIORequest.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageIOCallback.class, MessageIOCallback.class, 2, Side.CLIENT);
		INSTANCE.registerMessage(MessageSliderAdd.class, MessageSliderAdd.class, 3, Side.SERVER);
		INSTANCE.registerMessage(MessageSliderRequest.class, MessageSliderRequest.class, 4, Side.SERVER);
		INSTANCE.registerMessage(MessageSliderGet.class, MessageSliderGet.class, 5, Side.CLIENT);
	}
	
}
