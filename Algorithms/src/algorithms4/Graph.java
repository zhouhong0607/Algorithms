package algorithms4;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Graph
{
	private final int vNum;// 顶点的数量
	private int eNum;// 边的数量
	private HashSet<Integer>[] adj;// 邻接表

	public Graph(int v)
	{
		// TODO Auto-generated constructor stub
		this.vNum = v;
		this.eNum = 0;
		adj = (HashSet<Integer>[]) new HashSet[vNum];
		for(int i=0;i<adj.length;i++)
			adj[i]=new HashSet<>();
		
	
		
	}
	public Graph(Scanner scanner) 
	{
		this(scanner.nextInt());
		int n=scanner.nextInt();
		for(int i=0;i<n;i++)
		{
			int v1=scanner.nextInt();
			int v2=scanner.nextInt();
			addEdge(v1, v2);
		}
		
	}
	
	public void addEdge(int v,int w)
	{
		adj[v].add(w);
		adj[w].add(v);
		eNum++;
	}
	
	public int E()
	{
		return this.eNum;
	}
	
	public int V()
	{
		return this.vNum;
	}
	/**
	 * 返回顶点v的邻接顶点
	 * @param v
	 * @return
	 */
	public Iterable<Integer> adjacency(int v)
	{
		return adj[v];
	}
	
	public String toString()
	{
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append(vNum+"\t顶点\t"+eNum+"\t条边\n");
		for(int i=0;i<vNum;i++)
		{
			sBuilder.append(i+":"+adjacency(i).toString()+"\n");
		}
		
		return sBuilder.toString();
		
		
	}
	
	public static void  main(String[] args)throws Exception
	{
		FileInputStream fileIn=new FileInputStream("src/algorithms4/testGraph2.txt");
		Scanner scanner=new Scanner(fileIn);
		Graph graph=new Graph(scanner);
//		System.out.println(graph.toString());
		
		int source=0;
//		DepthFirstSearch dSearch=new DepthFirstSearch(graph, source);
		BreadthFirstSearch bSearch=new BreadthFirstSearch(graph, source);
		ConnectedComponent cc=new ConnectedComponent(graph);
		CircleTest cTest=new CircleTest(graph);
		System.out.println("circle?:"+cTest.hasCircle());
		TwoColorTest colorTest=new TwoColorTest(graph);
		System.out.println("bipartite?:"+colorTest.isTwoColor());
		
//		System.out.print("num of cc:"+cc.allCC());
		
//		for(int i=0;i<graph.V();i++)
//		{
//			System.out.print(source+" to "+i+": ");
//			Stack<Integer> stack=bSearch.pathTo(i);
//			while(!stack.isEmpty())
//			{
//				int k=stack.pop();
//				if(k==source) 
//					System.out.print(source);
//				else
//					 System.out.print("-"+k);
//			}
//			System.out.println();
//		}
//		
		
		
//		Paths allPaths=new Paths(graph, 0);
//		System.out.println(dSearch.result());
		
		
		
	}

}
