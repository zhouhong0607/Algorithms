package algorithms4;

import java.util.Stack;

public class Paths
{
  private	String[] allPaths;
  private boolean marked[];
  Stack<Integer> stack;//轨迹栈
  
	public Paths(Graph graph,int s)
	{
		// TODO Auto-generated constructor stub
		allPaths=new String[graph.V()];
		for(int i=0;i<allPaths.length;i++)
		{
			allPaths[i]=new String();
		}
		marked=new boolean[graph.V()];
		stack=new Stack<>();
		
		dfs(graph,s);
	}
	
	private void dfs(Graph graph,int v)
	{
		marked[v]=true;
		stack.push(v);
		allPaths[v]=stack.toString();
		
		for(Integer i:graph.adjacency(v))
		{
			if(!isMarked(i))
			{
				dfs(graph, i);
			}
			
		}
	}
	
	private boolean isMarked(int i)
	{
		return marked[i];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
