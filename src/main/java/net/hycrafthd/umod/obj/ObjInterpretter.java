package net.hycrafthd.umod.obj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.corelib.via.Vertex;
import net.hycrafthd.umod.UMod;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

public class ObjInterpretter extends FileInputStream{

	private ArrayList<Vec3d> tex = new ArrayList<Vec3d>();
	
	public ObjInterpretter(File fl) throws FileNotFoundException {
		super(fl);
		Scanner sc = new Scanner(this);
		String str = "";
		while(sc.hasNextLine()){
			str += sc.nextLine();
		}
		sc.close();
		try {
			this.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try{
		String[] args = str.replace("vn", "v").split("v ");
		for(String n : args){
			if(!n.equals(args[0])){
				String[] vls = n.split(" ");
				tex.add(new Vec3d(Double.valueOf(vls[0]), Double.valueOf(vls[1]), Double.valueOf(vls[2])));
			}
		}
		}catch(NumberFormatException ex){
			UMod.log.error("Error Loading Model (Number Failer)", ex);
		}catch(ArrayIndexOutOfBoundsException e){
			UMod.log.error("Error Loading Model (Array Failer)", e);
		}
	}
	
	public void draw(VertexBuffer v,RGBA rgb) {
		v.begin(13, DefaultVertexFormats.POSITION_COLOR);
		int i = 0;
		for(Vec3d vs : tex){
//			if(i % 2 == 0){
			v.pos(vs.xCoord, vs.yCoord, vs.zCoord).color(rgb.getRed(),rgb.getGreen(),rgb.getBlue(),rgb.getAlpha()).endVertex();
//			}
//			System.out.println(i);
			i++;
		}
//		i = 0;
		/*for(Vec3d vs : tex){
//			if(i % 2 != 0){
				v.pos(vs.xCoord, vs.yCoord, vs.zCoord).color(rgb.getRed(),rgb.getGreen(),rgb.getBlue(),rgb.getAlpha()).endVertex();
//			}
			System.out.println(i);
//			i++;
		}*/
	}
	
}
