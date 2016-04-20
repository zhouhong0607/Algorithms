package chaptersix;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;

public class FlowNetwork
{
	private int vNum;
	private int eNum;
	private HashSet<FlowEdge>[] adj;

	public FlowNetwork(int n)
	{
		// TODO Auto-generated constructor stub
		this.vNum = n;
		this.eNum = 0;
		adj = new HashSet[vNum];
		for(int i=0;i<adj.length;i++)
			adj[i]=new HashSet<>();
	}

	public FlowNetwork(String filePath) throws Exception
	{
		// TODO Auto-generated constructor stub
		FileInputStream fileIn = new FileInputStream(filePath);
		Scanner scanner = new Scanner(fileIn);

		this.vNum = Integer.parseInt(scanner.nextLine());
		this.eNum = 0;
		adj = new HashSet[vNum];
		for(int i=0;i<adj.length;i++)
			adj[i]=new HashSet<>();
		
		int edges = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < edges; i++)
		{
			String[] parts = scanner.nextLine().split(" ");
			this.addEdge(new FlowEdge(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Double.parseDouble(parts[2])));
		}
		scanner.close();
	}

	public void addEdge(FlowEdge flowEdge)
	{
		this.adj[flowEdge.from()].add(flowEdge);
		this.eNum++;
	}

	public Iterable<FlowEdge> adjacent(int v)
	{
		return adj[v];
	}

	public int vNum()
	{
		return this.vNum;
	}

	public int eNum()
	{
		return this.eNum;
	}

}
