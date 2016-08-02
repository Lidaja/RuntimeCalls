nodeIPs = "10.10.30.235,10.10.20.10-10.10.30.30,10.10.30.236,10.10.30.237-10.10.30.246"
nodes = []
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
print nodes
