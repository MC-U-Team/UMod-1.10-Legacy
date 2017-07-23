package io.github.mc_umod.gui.items;

import java.awt.Color;

import io.github.mc_umod.util.RGBA;

public class ComboboxItem {
	
	public static final ComboboxItem OUTPUT = new ComboboxItem("Output", new RGBA(Color.RED));
	public static final ComboboxItem INPUT = new ComboboxItem("Input", new RGBA(Color.GREEN));
	public static final ComboboxItem CHOOSE = new ComboboxItem("Choose", new RGBA(Color.DARK_GRAY));
	
	public final String item;
	public final RGBA color;
	
	public ComboboxItem(String item,RGBA color) {
		this.item = item;
		this.color = color;
	}
	
}
