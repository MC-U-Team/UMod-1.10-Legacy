package io.github.mc_umod.util;

import com.google.common.eventbus.EventBus;

import net.minecraftforge.fml.common.*;

public abstract class CoreDummyModContainer extends DummyModContainer {
	
	public CoreDummyModContainer(ModMetadata metadata) {
		super(metadata);
	}
	
	@Override
	public final boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}
	
}
