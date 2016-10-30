package net.hycrafthd.umod.obj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.corelib.via.Vertex;
import net.hycrafthd.umod.UMod;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

public class ObjInterpretter extends FileInputStream{

	private ArrayList<Vec3d> vert = new ArrayList<Vec3d>();
	private ArrayList<ObjArea> area = new ArrayList<ObjArea>();

	public ObjInterpretter(File fl) throws FileNotFoundException {
		super(fl);
		try {
		Scanner sc = new Scanner(this);
		while(sc.hasNextLine()){
			String stc = sc.nextLine();
			if(stc.startsWith("v ")){
				String[] st = stc.replace("v ", "").split(" ");
				vert.add(new Vec3d(Double.valueOf(st[0]),Double.valueOf(st[1]),Double.valueOf(st[2])));
			}
			if(stc.startsWith("f ")){
				area.add(new ObjArea(stc.replace("f ", "").split(" ")));
			}
		}
		sc.close();
			this.close();
		} catch (IOException e1) {
			UMod.log.error("Error Loading Model " + fl.getName() + " (IO Failer)", e1);
		}catch(NumberFormatException ex){
			UMod.log.error("Error Loading Model " + fl.getName() + " (Number Failer)", ex);
		}catch(ArrayIndexOutOfBoundsException e){
			UMod.log.error("Error Loading Model " + fl.getName() + " (Array Failer)", e);
		}
	}
	
	public void draw(VertexBuffer bf,RGBA c){
		bf.begin(7, DefaultVertexFormats.POSITION_COLOR);
		for(ObjArea are : this.area){
			are.addVertices(bf, this.vert, c);
		}
	}
	
}
