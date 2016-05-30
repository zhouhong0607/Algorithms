package algorithms4;

public class DepthFirstSearch
{
	int count;//搜索到的顶点数量
	boolean marked[];//顶点标记
	StringBuilder sBuilder;
	
	public DepthFirstSearch(Graph graph,int s)
	{
		// TODO Auto-generated constructor stub
		sBuilder=new StringBuilder();
		sBuilder.append("DFS Path:");
		
		
		marked=new boolean[graph.V()];
		dfs(graph, s);
	}
	
	private void dfs(Graph graph,int s)
	{
		sBuilder.append(" "+s);
		
		count++;
		marked[s]=true;
		for(Integer i:graph.adjacency(s))
			if(!isMarked(i))
			dfs(graph, i);
	}
	
	private boolean isMarked(int v)
	{
		return marked[v];
	}
	
	public String result()
	{
		sBuilder.append("\tConnected ?:"+String.valueOf(count==marked.length));
		return sBuilder.toString();
	}

}
