import java.io.*;
import java.util.*;
public class nodeTest {
	public static void main(String args[]){
		/*nodes = []
		nodeIPs = "10.10.30.235,10.10.20.10-10.10.30.30,10.10.30.236,10.10.30.237-10.10.30.246"
		for n in nodeIPs.split(","):
			if "-" not in n:
				nodes.append(n)
			else:
				m = n.split("-")
				first = ".".join(m[0].split(".")[:-1])
				start = m[0].split(".")[-1]
				end = m[1].split(".")[-1]
				for i in range(int(start),int(end)+1):
				nodes.append(first+"."+str(i))
		print nodes*/
		ArrayList<String> nodes = new ArrayList<String>();
		String nodeIPs = "10.10.30.235,10.10.20.10-10.10.30.30,10.10.30.236,10.10.30.237-10.10.30.246";
		String[] split = nodeIPs.split(",");
		for (String node: split){
			if (!node.contains("-")){
				nodes.add(node);
			}
			else{
				String[] m = node.split("-");
				//String[] first = m[0].split(".");
				String n = m[0];
				String[] c = n.split(".");
				//String first = c[0]+"."+c[1]+"."+c[2];
				String a = m[0];
				String b = m[1];
				System.out.println(Arrays.toString(m));
				System.out.println(n);
				System.out.println(Arrays.toString(c));				
				//System.out.println(first);
			}
		}
		String test = String.join(",",nodes);
		//System.out.println(test);
	}
}
