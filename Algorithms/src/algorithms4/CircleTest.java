package algorithms4;

public class CircleTest
{
	private boolean[] marked;
	private boolean hasCircle;
	public CircleTest(Graph graph)
	{
		// TODO Auto-generated constructor stub
		int n=graph.V();
		marked=new boolean[n];
		for(int i=0;i<n;i++)
		{
			if(!isMarked(i))
			{
				dfs(graph,i,i);
			}
		}
		
		
	}
	
	private void dfs(Graph graph, int v,int u)
	{
		marked[v]=true;
		for(int i:graph.adjacency(v))
		{
			if(!isMarked(i))
			{
				dfs(graph, i, v);
			} else if (i!=u) 
			{
				hasCircle=true;
			}
			
		}
		
	}
	
	private boolean isMarked(int v)
	{
		return marked[v];
	}
	
	public boolean hasCircle()
	{
		return hasCircle;
	}
	
}
