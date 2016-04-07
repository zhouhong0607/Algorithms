package mst;

import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra
{
	private boolean[] marked;
	DirectedEdge[] edgeTo;
	Double[] distTo;

	public Dijkstra(EdgeWeightedDigraph dirGraph, int source)
	{
		// TODO Auto-generated constructor stub
		int n = dirGraph.vNum();
		marked = new boolean[n];
		edgeTo = new DirectedEdge[n];
		distTo = new Double[n];
		for(int k=0;k<distTo.length;k++)
		{
			distTo[k]=Double.POSITIVE_INFINITY;
		}
		
		
		
		distTo[source] = 0.0;

		IndexMinPQ<Double> minPQ = new IndexMinPQ<>(n);
		minPQ.enqueue(source, 0.0);
		while (!minPQ.isEmpty())
		{
			int v = minPQ.dequeue();
			if(isMakred(v)) continue;
			
			marked[v] = true;
			for (DirectedEdge edge : dirGraph.adjacency(v))
			{
				int w = edge.to();
				if (distTo[v] + edge.weight() < distTo[w])// relax
				{
					edgeTo[w] = edge;
					distTo[w]=distTo[v]+edge.weight();
					if (minPQ.contains(w))
					{
						minPQ.change(w, distTo[v] + edge.weight());
					} else
					{
						minPQ.enqueue(w, distTo[v] + edge.weight());
					}
				}

			}

		}

	}

	private boolean isMakred(int v)
	{
		return marked[v];
	}
	
	public boolean hasPathTo(int w)
	{
		return marked[w];
	}
	public Double weightTo(int w)
	{
		return distTo[w];
	}
	public Stack<DirectedEdge> pathTo(int w)
	{
		Stack<DirectedEdge> stack=new Stack<>();
		for(DirectedEdge edge=edgeTo[w];edge!=null;edge=edgeTo[edge.from()])
		{
			stack.push(edge);
		}
		return stack;
	}
	public String toString()
	{
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<marked.length;i++)
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
			
			sBuilder.append("\tallWeights:"+this.weightTo(i)+"\n");
		}
		return sBuilder.toString();
	}
}
