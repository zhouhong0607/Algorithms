package mst;

import java.util.Stack;

public class EdgeWeightedCycleFinder
{
	private boolean[] marked;
	private DirectedEdge[] edgeTo;
	private Stack<DirectedEdge> cycle;
	private boolean[] onStack;

	public EdgeWeightedCycleFinder(EdgeWeightedDigraph digraph)
	{
		// TODO Auto-generated constructor stub
		int n = digraph.vNum();
		onStack = new boolean[n];
		edgeTo = new DirectedEdge[n];
		marked = new boolean[n];
		for (int i = 0; i < n; i++)
		{
			if (!isMarked(i))
				dfs(digraph, i);
		}
	}

	private void dfs(EdgeWeightedDigraph digraph, int v)
	{
		onStack[v] = true;
		marked[v] = true;
		for (DirectedEdge edge : digraph.adjacency(v))
		{
			int w = edge.to();
			if (this.hasCycle())
				return;
			else if (!isMarked(w))
			{
				edgeTo[w] = edge;
				dfs(digraph, w);
			} else if (onStack[w])
			{
				cycle = new Stack<>();
				while (edge!=null)
				{
					cycle.push(edge);
					edge=edgeTo[edge.from()];
				}
			}

		}
		onStack[v] = false;

	}

	private boolean isMarked(int v)
	{
		return marked[v];
	}

	public boolean hasCycle()
	{
		return cycle != null;
	}

	public Stack<DirectedEdge> cycle()
	{
		return cycle;
	}

}
