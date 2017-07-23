package io.github.mc_umod.util.asm;

import java.lang.reflect.Method;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodInsnNode;

public class MethodInsInjector extends MethodInsnNode implements Opcodes {
	
	public MethodInsInjector(Method method) {
		this(INVOKESTATIC, method);
	}
	
	public MethodInsInjector(int opcode, Method method) {
		super(opcode, null, null, null, false);
		owner = method.getDeclaringClass().getName().replace(".", "/");
		name = method.getName();
		desc = org.objectweb.asm.commons.Method.getMethod(method).getDescriptor();
	}
	
}
