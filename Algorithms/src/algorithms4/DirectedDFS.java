package algorithms4;

public class DirectedDFS
{
	private boolean[] marked;
	
	public DirectedDFS(Digraph digraph,int v)
	{
		// TODO Auto-generated constructor stub
		marked=new boolean[digraph.vNum()];
		dfs(digraph,v);
	}
	public DirectedDFS(Digraph digraph,Iterable<Integer> sources)
	{
		marked=new boolean[digraph.vNum()];
		for(Integer i:sources)
		{
			if(!isMarked(i))
				dfs(digraph, i);
		}
		
	}
	
	
	
	private void dfs(Digraph digraph,int v)
	{
		marked[v]=true;
		for(Integer i:digraph.adjacency(v))
		{
			if(!isMarked(i))
			{
				dfs(digraph, i);
			}
		}
	}
	public boolean isMarked(int v)
	{
		return marked[v];
	}
	public String toString()
	{
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<marked.length;i++)
		{
			if(marked[i])
				sBuilder.append(" "+i);
		}
		return sBuilder.toString();
	}
}
