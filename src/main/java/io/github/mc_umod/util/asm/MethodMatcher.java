package io.github.mc_umod.util.asm;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class MethodMatcher {
	
	private String className;
	private String methodDescription;
	private String methodMcpName;
	private String methodSrgName;
	
	public MethodMatcher(String className, String methodDescription, String methodMcpName, String methodSrgName) {
		this.className = className;
		this.methodDescription = methodDescription;
		this.methodMcpName = methodMcpName;
		this.methodSrgName = methodSrgName;
	}
	
	public MethodMatcher(MappedType mappedType, String methodDescription, String methodMcpName, String methodSrgName) {
		this(mappedType.getName(), methodDescription, methodMcpName, methodSrgName);
	}
	
	public MethodMatcher(MappedType mappedType, Type type, String methodMcpName, String methodSrgName) {
		this(mappedType.getName(), type.getDescriptor(), methodMcpName, methodSrgName);
	}
	
	public MethodMatcher(ClassNode classNode, Type type, String methodMcpName, String methodSrgName) {
		this(classNode.name, type.getDescriptor(), methodMcpName, methodSrgName);
	}
	
	public boolean match(MethodNode node) {
		return match(node.name, node.desc);
	}
	
	public boolean match(MethodInsnNode node) {
		return match(node.name, node.desc);
	}
	
	public boolean match(String matchMethodName, String matchMethodDescription) {
		if (!matchMethodDescription.equals(methodDescription)) {
			return false;
		}
		if (matchMethodName.equals(this.methodMcpName)) {
			return true;
		}
		if (!AsmUtil.useSrgNames()) {
			return false;
		}
		String mapped = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(className, matchMethodName, matchMethodDescription);
		return mapped.equals(this.methodSrgName);
	}
	
}