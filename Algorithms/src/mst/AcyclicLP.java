package mst;

import java.util.Stack;

import javax.naming.ldap.SortControl;

/**
 * 有向无环图的最长路径
 * 
 * @author Administrator
 *
 */
public class AcyclicLP
{
	private DirectedEdge[] edgeTo;
	private Double[] distTo;

	public AcyclicLP(EdgeWeightedDigraph digraph, int source)
	{
		// TODO Auto-generated constructor stub
		int n = digraph.vNum();
		edgeTo = new DirectedEdge[n];
		distTo = new Double[n];
		for (int i = 0; i < n; i++)
			distTo[i] = Double.NEGATIVE_INFINITY;
		distTo[source] = 0.0;

		TopoLogical topo = new TopoLogical(digraph);
		Stack<Integer> topoOrder = topo.order();
		while (!topoOrder.isEmpty())
		{
			relax(digraph, topoOrder.pop());
		}
	}

	/**
	 * relax顶点v
	 * 
	 * @param digraph
	 * @param v
	 */
	private void relax(EdgeWeightedDigraph digraph, int v)
	{
		for (DirectedEdge edge : digraph.adjacency(v))
		{
			int w = edge.to();
			if (distTo[v] + edge.weight() > distTo[w])
			{
				edgeTo[w] = edge;
				distTo[w] = distTo[v] + edge.weight();
			}
		}
	}

	public boolean hasPathTo(int v)
	{
		return distTo[v] > Double.NEGATIVE_INFINITY;
	}

	public Stack<DirectedEdge> pathTo(int v)
	{
		Stack<DirectedEdge> path = new Stack<>();
		for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()])
		{
			path.push(edge);
		}
		return path;
	}
	public Double distTo(int v)
	{
		return distTo[v];
	}
}
