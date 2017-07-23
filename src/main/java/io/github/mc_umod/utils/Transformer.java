package io.github.mc_umod.utils;

import java.util.ArrayList;

public class Transformer<T> {
	
	public final ArrayList<T> toFrom;
	
	public Transformer(ArrayList<T> toForm) {
		this.toFrom = toForm;
	}
	
	public T[] transform(){
		Object[] obj = new Object[toFrom.size()];
		int i = 0;
		for(Object o : toFrom){
			obj[i] = o;
		}
		return (T[]) obj;
	}
}
