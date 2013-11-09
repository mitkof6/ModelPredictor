package main;

import java.io.FileNotFoundException;

import opengl.Animator;
import opengl.Axis;
import opengl.Grid;
import skeleton.JointHierarchy;
import skeleton.SkeletonSequence;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		JointHierarchy jh = new JointHierarchy();
		
		jh.loadHierarchy("hierarchy.txt");
		//jh.printHierarchy();
		
		SkeletonSequence ss = new SkeletonSequence(jh);
		ss.loadSequence("skeleton.txt");
		//System.out.println(ss.getSequence().size());
		
		Animator animator = new Animator();
		animator.addToDraw(ss);
		animator.addToDraw(new Grid(Constant.GRID_SIZE, Constant.GRID_LINE_WIDTH));
		animator.addToDraw(new Axis(Constant.AXIS_LENGTH, Constant.AXIS_WIDTH));
		animator.start();
		animator.setVisible(true);
		
	}

}
