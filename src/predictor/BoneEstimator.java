package predictor;

import java.util.HashMap;

import skeleton.Joint;
import skeleton.Skeleton;
import skeleton.SkeletonSequence;

public class BoneEstimator {

	private HashMap<String, Double> bones;
	private SkeletonSequence sequence;
	
	public BoneEstimator(SkeletonSequence sequence){
		this.sequence = sequence;
		
		bones = new HashMap<>();
	}
	
	public void estimateBonesLength(){
		for(String type : sequence.getJointType()){
			double sum = 0;
			for(Skeleton skel : sequence.getSequence()){
				Joint child = skel.getJoint(type);
				Joint parent = skel.getJoint(child.getParent());
				
				sum += child.getPosition().distance(parent.getPosition());
			}
			bones.put(type, sum/sequence.getSequence().size());
			//System.out.println(type+": "+bones.get(type));
		}
	}
}
