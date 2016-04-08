package mst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BellmanFordSP
{
	private DirectedEdge[] edgeTo;
	private Double[] distTo;
	private boolean[] onQ;
	private Queue<Integer> queue;
	private int cost;
	private Stack<DirectedEdge> cycle;
	
	public BellmanFordSP(EdgeWeightedDigraph digraph,int source)
	{
		// TODO Auto-generated constructor stub
		int n=digraph.vNum();
		edgeTo=new DirectedEdge[n];
		distTo=new Double[n];
		onQ=new boolean[n];
		queue=new LinkedList<>();
		cost=0;
		cycle=null;
		for(int i=0;i<n;i++)
		{
			distTo[i]=Double.POSITIVE_INFINITY;
		}
		distTo[source]=0.0;
		queue.add(source);
		onQ[source]=true;
		while(!queue.isEmpty()&&!hasNegativeCycle())
		{
			int v=queue.poll();
			onQ[v]=false;
			relax(digraph,v);
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
				if(!onQ[w])
				{
					queue.add(w);
					onQ[w]=true;
				}
			}
			if(cost++%digraph.vNum()==0)
			{
				findNegativeCycle();
			}
			
		}
		
	}
	public boolean hasNegativeCycle()
	{
		return cycle!=null;
	}
	private void findNegativeCycle()
	{
		int v=edgeTo.length;
		EdgeWeightedDigraph spt=new EdgeWeightedDigraph(v);
		for(int i=0;i<v;i++)
		{
			if(edgeTo[i]!=null)
				spt.addEdge(edgeTo[i]);
		}
		EdgeWeightedCycleFinder cf=new EdgeWeightedCycleFinder(spt);
		cycle=cf.cycle();
	}
	public Stack<DirectedEdge> negativeCycle()
	{
		return cycle;
	}
}
