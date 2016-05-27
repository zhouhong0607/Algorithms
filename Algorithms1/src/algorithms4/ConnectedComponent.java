package algorithms4;
/**
 * 连通分量
 * @author Administrator
 *
 */
public class ConnectedComponent
{
	private int[] id;//每个顶点  的连通分量id
	private boolean[] marked;
	private int count;//连通分量计数
	
	public ConnectedComponent(Graph graph)
	{
		// TODO Auto-generated constructor stub
		int n=graph.V();
		id=new int[n];
		marked=new boolean[n];
		for(int k=0;k<n;k++)
		{
			if(!isMarked(k))
			{
				count++;
				dfs(graph,k);
			}
		}
	}
	private void dfs(Graph graph,int v)
	{
		marked[v]=true;
		id[v]=count;
		for(int i:graph.adjacency(v))
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
	
	public boolean connected(int i,int j)
	{
		return id[i]==id[j];
	}
	public int idOf(int v)
	{
		return id[v];
	}
	public int allCC()
	{
		return count;
	}
	
	
	
}
