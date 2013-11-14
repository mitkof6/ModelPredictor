package main;

import java.io.FileNotFoundException;

import mesh.PointCloudSequence;
import opengl.Animator;
import opengl.Axis;
import opengl.Grid;
import predictor.BoneEstimator;
import skeleton.JointHierarchy;
import skeleton.SkeletonSequence;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		JointHierarchy jh = new JointHierarchy();
		
		jh.loadHierarchy(Constant.SKELETON_HIERARCHY_FILE);
		//jh.printHierarchy();
		
		SkeletonSequence ss = new SkeletonSequence(jh);
		ss.loadSequence(Constant.SKELETON_FILE);
		System.out.println("SPC: "+ss.getSequence().size());
		//ss.applyFilter();
		
		PointCloudSequence ps = new PointCloudSequence();
		ps.loadSequence(Constant.POINT_CLOUD_FILE);
		System.out.println("PC: "+ps.getSequence().size());
		
		BoneEstimator be = new BoneEstimator(ss);
		be.estimateBonesLength();
		
		Animator animator = new Animator();
		animator.addToDraw(ss);
		animator.addToDraw(ps);
		animator.addToDraw(new Grid(Constant.GRID_SIZE, Constant.GRID_LINE_WIDTH));
		animator.addToDraw(new Axis(Constant.AXIS_LENGTH, Constant.AXIS_WIDTH));
		animator.start();
		animator.setVisible(true);
		
	}

}
