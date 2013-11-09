package skeleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class JointHierarchy {

	public HashMap<String, String> jointH;
	
	public JointHierarchy(){
		jointH = new HashMap<>();
	}
	
	public String getParent(String child){
		return jointH.get(child);
	}
	
	public void loadHierarchy(String path) throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File(path));
		
		Stack<String> stack = new Stack<>();
		int depth = 0;
		while(scanner.hasNext()){
			String[] tok = scanner.nextLine().split(" ");
			
			if(tok.length==0) continue;
			
			if(tok.length == 1){//root
				stack.push(tok[0]);
				jointH.put(stack.peek(), stack.peek());//TODO null or st else
			}else{
				while(depth>=tok[0].length()){
					stack.pop();
					depth--;
				}
				jointH.put(tok[1], stack.peek());
				stack.push(tok[1]);
				depth++;
			}
			
		}
		
		scanner.close();
	}
	
	public void printHierarchy(){
		for(String key : jointH.keySet()){
			System.out.println(key+" parent: "+jointH.get(key));
		}
	}
}
