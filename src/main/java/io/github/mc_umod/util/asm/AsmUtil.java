package io.github.mc_umod.util.asm;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class AsmUtil {
	
	public static boolean useSrgNames() {
		Boolean deobfuscated = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		return deobfuscated == null || !deobfuscated;
	}
	
	public static String getMappedName(String clsName) {
		return useSrgNames() ? FMLDeobfuscatingRemapper.INSTANCE.unmap(clsName) : clsName;
	}
	
}
