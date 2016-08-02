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
				node.add(node);
			}
		}
		System.out.println(Arrays.toString(split));
	}
}
