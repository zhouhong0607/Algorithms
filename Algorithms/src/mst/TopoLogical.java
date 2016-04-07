package mst;

import java.util.Stack;

/**
 * 拓扑排序
 * @author Administrator
 *
 */
public class TopoLogical
{
	private boolean marked[];
	private Stack<Integer> order;
	
	public TopoLogical(EdgeWeightedDigraph digraph)
	{
		// TODO Auto-generated constructor stub
		int n=digraph.vNum();
		marked=new boolean[n];
		order=new Stack<>();
		for(int i=0;i<n;i++)
		{
			if(!isMarked(i))
			{
				dfs(digraph,i);
				order.push(i);
			}
		}
		
		
	}
	
	private void dfs(EdgeWeightedDigraph digraph,int v)
	{
		marked[v]=true;
		for(DirectedEdge edge:digraph.adjacency(v))
		{
			int w=edge.to();
			if(!isMarked(w))
			{
				dfs(digraph, w);
				
			}
		}
		
		order.push(v);
	}
	
	public Stack<Integer> order()
	{
		return order;
	}
	private boolean isMarked(int v)
	{
		return marked[v];
	}
}
