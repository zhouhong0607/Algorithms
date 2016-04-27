package mst;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;


public class Digraph
{
	private final int vNum;
	private int eNum;
	private HashSet<Integer>[] adj;

	/**
	 * 建立 n个顶点的 无边有向图
	 * 
	 * @param n
	 */
	public Digraph(int n)
	{
		// TODO Auto-generated constructor stub
		this.vNum = n;
		this.eNum = 0;
		adj = (HashSet<Integer>[]) new HashSet[n];
		for (int i = 0; i < n; i++)
			adj[i] = new HashSet<>();
	}

	/**
	 * 根据文件名构建一个有向图
	 * 
	 * @param fileName
	 * @param sp
	 */
	public Digraph(String fileName, String sp) throws Exception
	{
		// TODO Auto-generated constructor stub
		FileInputStream fileIn = new FileInputStream(fileName);
		Scanner scanner = new Scanner(fileIn);
		int v = scanner.nextInt();
		int e = scanner.nextInt();

		this.vNum = v;
		this.eNum = 0;
		adj = (HashSet<Integer>[]) new HashSet[v];
		for (int k = 0; k < v; k++)
			adj[k] = new HashSet<>();

		for (int m = 0; m < e; m++)
		{
			int i = scanner.nextInt();
			int j = scanner.nextInt();
			this.addEdge(i, j);
		}
	}

	/**
	 * 添加一条边
	 * 
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w)
	{
		adj[v].add(w);
		eNum++;
	}

	public int vNum()
	{
		return vNum;
	}

	public int eNum()
	{
		return eNum;
	}

	public Iterable<Integer> adjacency(int v)
	{
		return adj[v];
	}

	public Digraph reverse()
	{
		int n = this.vNum();
		Digraph digraph = new Digraph(n);
		for (int i = 0; i < n; i++)
		{
			for (Integer j : this.adjacency(i))
			{
				digraph.addEdge(j, i);
			}
		}

		return digraph;
	}

	public String toString()
	{
		int n = vNum();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Vertexex:" + n + "\tEdges" + eNum() + "\n");
		for (int i = 0; i < n; i++)
		{
			sBuilder.append(i + ":");
			for (Integer j : this.adjacency(i))
			{
				sBuilder.append(" " + j);
			}
			sBuilder.append("\n");
		}
		return sBuilder.toString();

	}

	public static void main(String[] args) throws Exception
	{
		String fileName = "src/algorithms4/DGOrderTest.txt";
		String sp = " ";
		Digraph digraph = new Digraph(fileName, sp);
//		System.out.println(digraph.toString());
//		HashSet<Integer> source=new HashSet<>();
//		source.add(1);
//		source.add(6);
//		source.add(2);
//		DirectedDFS dfs=new DirectedDFS(digraph,source);
//		DirectedBFS bfs=new DirectedBFS(digraph, 0);
		
//		DirectedCircleTest circleTest=new DirectedCircleTest(digraph);
//		for(int i:circleTest.getCircle())
//		{
//			System.out.print(" "+i);
//		}
//		System.out.println(bfs.toString());
		
//		DepthFirstOrder dOrder=new DepthFirstOrder(digraph);
//		for(int i:dOrder.getPro())
//		{
//			System.out.print(" "+i);
//		}
//		System.out.println();
//		for(int i:dOrder.getPost())
//		{
//			System.out.print(" "+i);
//		}
//		System.out.println();
//		Stack<Integer> stack=dOrder.getReversePost();
//		while(!stack.isEmpty())
//		{
//			System.out.print(" "+stack.pop());
//		}
//		System.out.println();
		

		
		
	}
}
