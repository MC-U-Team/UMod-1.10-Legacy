package io.github.mc_umod.util;

import java.io.InputStream;
import java.lang.reflect.Field;

import net.minecraftforge.fml.common.*;

public class ModMetadataFetcher {
	
	private ModMetadata modmeta;
	
	public ModMetadataFetcher(String modid) {
		this("/mcmod.info", modid);
	}
	
	public ModMetadataFetcher(String injarpath, String modid) {
		this(ModMetadataFetcher.class.getResourceAsStream(injarpath), modid);
	}
	
	public ModMetadataFetcher(InputStream inputstream, String modid) {
		ModMetadata meta = MetadataCollection.from(inputstream, modid).getMetadataForId(modid, null);
		if (meta != null) {
			modmeta = meta;
		} else {
			modmeta = new ModMetadata();
			modmeta.modId = modid;
		}
	}
	
	public ModMetadata getModmeta() {
		return modmeta;
	}
	
	public void applyMetadata(ModMetadata modmetatoapply) {
		try {
			Class<ModMetadata> clazz = ModMetadata.class;
			for (Field field : clazz.getDeclaredFields()) {
				field.set(modmetatoapply, field.get(getModmeta()));
			}
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}
	
}
