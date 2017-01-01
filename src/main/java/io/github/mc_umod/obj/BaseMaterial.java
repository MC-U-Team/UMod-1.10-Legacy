package io.github.mc_umod.obj;

import java.awt.Color;

import net.hycrafthd.corelib.util.RGBA;

public class BaseMaterial extends Material {
	
	public BaseMaterial() {
		super("BaseMaterial");
		this.setColor(new RGBA(Color.WHITE).setAlpha(125));
	}
	
}
