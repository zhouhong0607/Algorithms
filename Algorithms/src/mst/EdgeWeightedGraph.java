package mst;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class EdgeWeightedGraph
{
	private final int vNum;// 顶点数量
	private int eNum;// 边的数量
	private HashSet<Edge>[] adj;// 邻接表

	/**
	 * 构造vNum个顶点的权重图
	 * 
	 * @param vNum
	 */
	public EdgeWeightedGraph(int vNum)
	{
		// TODO Auto-generated constructor stub
		this.vNum = vNum;
		this.eNum = 0;
		adj = new HashSet[vNum];
		for (int i = 0; i < vNum; i++)
			adj[i] = new HashSet<>();
	}

	public EdgeWeightedGraph(String filePath) throws Exception
	{
		// TODO Auto-generated constructor stub
		FileInputStream fileIn = new FileInputStream(filePath);
		Scanner scanner = new Scanner(fileIn);
		vNum = scanner.nextInt();
		int edgeNum = scanner.nextInt();
		eNum = 0;
		adj = new HashSet[vNum];
		for (int k = 0; k < vNum; k++)
		{
			adj[k] = new HashSet<>();
		}
		for (int i = 0; i < edgeNum; i++)
		{
			Edge edge = new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble());
			addEdge(edge);
		}
	}

	public int vNum()
	{
		return this.vNum;
	}

	public int eNum()
	{
		return this.eNum();
	}

	private void addEdge(Edge e)
	{
		int v = e.either(); 
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		eNum++;
	}

	public Iterable<Edge> adjacency(int v)
	{
		return adj[v];
	}

	public Iterable<Edge> edges()
	{
		HashSet<Edge> allEdges=new HashSet<>();
		for(int i=0;i<adj.length;i++)
		{
			for(Edge e:adj[i])
			{
				int w=e.other(i);
				if(w>i) allEdges.add(e);
			}
		}
		return allEdges;
	}
	
	public String toString()
	{
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < this.vNum; i++)
		{
			sBuilder.append(i + ":");
			for (Edge j : this.adjacency(i))
			{
				sBuilder.append(" " + j.toString());
			}
			sBuilder.append("\n");
		}
		return sBuilder.toString();
	}

	public static void main(String[] args) throws Exception
	{
		 String filePath="src/data/tinyEWG.txt";
		 EdgeWeightedGraph edgeWeightedGraph=new EdgeWeightedGraph(filePath);
		// System.out.print(edgeWeightedGraph.toString());
//		 PrimMST primMST=new PrimMST(edgeWeightedGraph);
//		 System.out.print(primMST.toString());
		 KruskalMST kruskalMST=new KruskalMST(edgeWeightedGraph);
		 System.out.print(kruskalMST.toString());
	
	}

}
