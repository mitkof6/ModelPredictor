package skeleton;

import java.util.HashMap;

public class Skeleton {

	private HashMap<String, Joint> joints;
	private long timeStamp;
	
	public Skeleton(long timeStamp){
		this.timeStamp = timeStamp;
		
		joints = new HashMap<>();
	}
	
	public void add(Joint joint){
		joints.put(joint.getName(), joint);
	}
	
	public Joint getJoint(String name){
		return joints.get(name);
	}
	
	public long getTimeStamp(){
		return timeStamp;
	}
}
