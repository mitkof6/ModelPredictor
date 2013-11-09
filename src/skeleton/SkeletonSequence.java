package skeleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.media.opengl.GL2;

import opengl.Drawable;

public class SkeletonSequence extends Drawable{

	private Vector<Skeleton> sequence;
	private Vector<String> jointType;
	private int FPS, SPF;
	
	public SkeletonSequence(){
		
		sequence = new Vector<>();
		jointType = new Vector<>();
	}
	
	public void add(Skeleton skeleton){
		sequence.add(skeleton);
	}
	
	public Vector<Skeleton> getSequence(){
		return sequence;
	}
	
	public int getFPS(){
		return FPS;
	}
	
	public int getSPF(){
		return SPF;
	}
	
	public void loadSequence(String path) throws FileNotFoundException{
		
		Scanner scaner = new Scanner(new File(path));
		
		Skeleton skel = null;
		while(scaner.hasNext()){
			String[] tok = scaner.nextLine().split(" ");
			
			if(tok.length<2) continue;
			
			if(tok[0].equalsIgnoreCase("SPF")){
				SPF = Integer.parseInt(tok[1]);
			}else if(tok[0].equalsIgnoreCase("FPS")){
				FPS = Integer.parseInt(tok[1]);
			}else if(tok[0].equalsIgnoreCase("jt")){
				jointType.add(tok[1]);
			}else if(tok[0].equalsIgnoreCase("t")){
				skel = new Skeleton(Long.parseLong(tok[1]));
			}else if(tok[0].equalsIgnoreCase("j")){
				skel.add(constructJoint(tok));
			}
		}
		
		scaner.close();
	}
	
	private Joint constructJoint(String[] tok){
		
		return new Joint(
				tok[1], 
				Double.parseDouble(tok[2]),
				Double.parseDouble(tok[3]),
				Double.parseDouble(tok[4]),
				Double.parseDouble(tok[5]));//TODO orientation
	}

	@Override
	public void draw(GL2 gl) {
		
		
	}
}
