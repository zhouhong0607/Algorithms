package mst;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
/**
 * 有向权重图
 * @author Administrator
 *
 */
public class EdgeWeightedDigraph
{
	private int vNum;
	private int eNum;
	HashSet<DirectedEdge>[] adj;

	public EdgeWeightedDigraph(int n)
	{
		// TODO Auto-generated constructor stub
		this.vNum = n;
		this.eNum = 0;
		adj = new HashSet[n];
		for (int i = 0; i < adj.length; i++)
		{
			adj[i] = new HashSet<>();
		}
	}

	public EdgeWeightedDigraph(String filePath) throws Exception
	{
		// TODO Auto-generated constructor stub
		FileInputStream fileIn = new FileInputStream(filePath);
		Scanner scanner = new Scanner(fileIn);
		int n = scanner.nextInt();
		int e = scanner.nextInt();
		this.vNum = n;
		this.eNum = 0;
		adj = new HashSet[n];
		for (int i=0;i<adj.length;i++)
		{
			adj[i]=new HashSet<>();
		}
		for (int i = 0; i < e; i++)
		{
			DirectedEdge edge = new DirectedEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble());
			this.addEdge(edge);
		}

	}

	public int vNum()
	{
		return this.vNum;
	}

	public int eNum()
	{
		return this.eNum;
	}

	public Iterable<DirectedEdge> adjacency(int v)
	{
		return adj[v];
	}

	public void addEdge(DirectedEdge edge)
	{
		adj[edge.from()].add(edge);
		eNum++;
	}

	public String toString()
	{
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < adj.length; i++)
		{
			sBuilder.append(i + ":");
			for (DirectedEdge edge : this.adjacency(i))
			{
				sBuilder.append(" " + edge.toString());
			}
			sBuilder.append("\n");
		}
		return sBuilder.toString();

	}

	public static void  main(String[] args) throws Exception
	{
		String filePath="src/data/tinyEWDAG.txt";
		EdgeWeightedDigraph edgeWeightedDigraph=new EdgeWeightedDigraph(filePath);
//		System.out.print(edgeWeightedDigraph.toString());
//		Dijkstra dijkstra=new Dijkstra(edgeWeightedDigraph, 0);
//		System.out.print(dijkstra.toString());
		
		AcyclicSP acyclicSP=new AcyclicSP(edgeWeightedDigraph, 5);
		System.out.print(acyclicSP.toString());
		
	}

}
