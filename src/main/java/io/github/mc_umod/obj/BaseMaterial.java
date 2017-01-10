package io.github.mc_umod.obj;

import java.awt.*;

import net.hycrafthd.corelib.util.*;

public class BaseMaterial extends Material {
	
	public BaseMaterial() {
		super("BaseMaterial");
		this.setColor(new RGBA(Color.WHITE).setAlpha(125));
	}
	
}
