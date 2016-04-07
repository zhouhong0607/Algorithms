package mst;

import java.util.Stack;

public class AcyclicSP
{
	private DirectedEdge[] edgeTo;
	private Double[] distTo;
	
	public AcyclicSP(EdgeWeightedDigraph digraph,int source)
	{
		// TODO Auto-generated constructor stub
		int n=digraph.vNum();
		edgeTo=new DirectedEdge[n];
		distTo=new Double[n];
		for(int i=0;i<distTo.length;i++)
			distTo[i]=Double.POSITIVE_INFINITY;
		
		distTo[source]=0.0;
		
		TopoLogical topo=new TopoLogical(digraph);
		Stack<Integer> topoOrder=topo.order();
		while(!topoOrder.isEmpty())
		{
			relax(digraph,topoOrder.pop());
		}
	}
	
	private void relax(EdgeWeightedDigraph digraph,int v)
	{
		for(DirectedEdge edge:digraph.adjacency(v))
		{
			int w=edge.to();
			if(distTo[v]+edge.weight()<distTo[w])
			{
				distTo[w]=distTo[v]+edge.weight();
				edgeTo[w]=edge;
			}
		}
		
	}
	public boolean hasPathTo(int v)
	{
		return distTo[v]<Double.POSITIVE_INFINITY;
	}
	public Double distTo(int v)
	{
		return distTo[v];
	}
	public Stack<DirectedEdge> pathTo(int v)
	{
		Stack<DirectedEdge> path=new Stack<>();
		for(DirectedEdge edge=edgeTo[v];edge!=null;edge=edgeTo[edge.from()])
		{
			path.push(edge);
		}
		return path;
	}
	
	public String toString()
	{
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<distTo.length;i++)
		{
			sBuilder.append("to "+i+":");
			if(hasPathTo(i))
			{
				Stack<DirectedEdge> stack=pathTo(i);
				while(!stack.isEmpty())
				{
				DirectedEdge edge=stack.pop();
				sBuilder.append(" "+edge.from()+"->"+edge.to());
				}
			}
			
			sBuilder.append("\tallWeights:"+this.distTo[i]+"\n");
		}
		return sBuilder.toString();
	}
	
}
