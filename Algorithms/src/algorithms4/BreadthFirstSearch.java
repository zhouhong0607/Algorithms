package algorithms4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch
{
//	int count;
//	boolean[] marked;
//	StringBuilder sBuilder;
//	
//	public BreadthFirstSearch(Graph graph,int s)
//	{
//		// TODO Auto-generated constructor stub
//		sBuilder=new StringBuilder();
//		sBuilder.append("DFS Path:");
//		marked=new boolean[graph.V()];
//		
//		
//		bfs(graph,s);
//	}
//	
//	private void bfs(Graph graph,int s)
//	{
//		if(!isMarked(s))
//		{
//		marked[s]=true;
//		sBuilder.append(" "+s);
//		count++;
//		}
//		
//		if(allMarked(graph,s))
//		{
//			return;
//		}else
//		{
//			for(Integer i:graph.adjacency(s))
//			{
//				if(!isMarked(i))
//				{
//					marked[i]=true;
//					sBuilder.append(" "+i);
//					count++;
//				}
//			}
//			for(Integer k:graph.adjacency(s))
//			{
//				bfs(graph, k);
//			}
//		}
//	}
//	
//	private boolean allMarked(Graph graph, int v)
//	{
//		boolean allMarked=true;
//		for(Integer i:graph.adjacency(v))
//		{
//			if(!isMarked(i))
//				allMarked=false;
//		}
//		return allMarked;
//	}
//	
//	
//	private boolean isMarked(int  v)
//	{
//		return marked[v];
//	}
//	
//	public String result()
//	{
//		sBuilder.append("\tConnected ?:"+String.valueOf(count==marked.length));
//		return sBuilder.toString();
//	}
	
	private boolean[] marked;
	private final int source;
	private int preVertex[];
	
	public BreadthFirstSearch(Graph graph,int s)
	{
		// TODO Auto-generated constructor stub
		marked=new boolean[graph.V()];
		this.source=s;
		preVertex=new int[graph.V()];
		bfs(graph,s);
		
	}
	private void bfs(Graph graph,int s)
	{
		Queue<Integer> queue=new LinkedList<>();
		queue.add(s);
		marked[s]=true;
//		System.out.print(s);
		while(!queue.isEmpty())
		{
			int k=queue.poll();//出队列
			for(Integer i:graph.adjacency(k))
			{
				if(!isMarked(i))
				{
				queue.add(i);
				marked[i]=true;
				preVertex[i]=k;
//				System.out.print("-"+i);
				}
				
			}
		}
	}
	
	private boolean isMarked(int i)
	{
		return marked[i];
	}
	public boolean hasPathTo(int v)
	{
		return marked[v];
	}
	/**
	 * source到v的所有值
	 * @param v
	 * @return
	 */
	public Stack<Integer> pathTo(int v)
	{
		if(!hasPathTo(v)) return null;
		Stack<Integer> stack=new Stack<>();
		for(int i=v;i!=source;i=preVertex[i])
		{
			stack.push(i);
		}
		stack.push(source);
		return stack;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
