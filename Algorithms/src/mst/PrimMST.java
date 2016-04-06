package mst;

import java.util.HashSet;
import java.util.PriorityQueue;
import javax.naming.ldap.SortControl;

public class PrimMST
{

	private boolean[] marked;// 标记该顶点是否在树中
	private Edge[] edgeTo;// 到达该顶点的边
	private Double[] distTo;// 距离该顶点的距离

	public PrimMST(EdgeWeightedGraph eGraph)
	{
		// TODO Auto-generated constructor stub
		int n = eGraph.vNum();

		marked = new boolean[n];
		edgeTo = new Edge[n];
		distTo = new Double[n];
		for (int i = 0; i < distTo.length; i++)
		{
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		prim(eGraph);
	}

	private void prim(EdgeWeightedGraph eGraph)
	{
		marked[0] = true;
		distTo[0] = 0.0;
		IndexMinPQ<Double> minPQ = new IndexMinPQ<>(eGraph.vNum());
		minPQ.enqueue(0, 0.0);
		while (!minPQ.isEmpty())
		{
			int v = minPQ.dequeue();
			marked[v] = true;
			for (Edge e : eGraph.adjacency(v))
			{
				int w = e.other(v);
				if (isMarked(w))
					continue;
				if (e.weight() < distTo[w])
				{
					edgeTo[w] = e;
					distTo[w] = e.weight();
					if (minPQ.contains(w))
					{
						minPQ.change(w, e.weight());
					} else
					{
						minPQ.enqueue(w, e.weight());
					}
				}
			}
		}
	}

	private boolean isMarked(int v)
	{
		return marked[v];
	}

	public Double weight()
	{
		Double weight = 0.0;
		for (int i = 0; i < distTo.length; i++)
		{

			weight += distTo[i];
		}
		return weight;
	}

	public String toString()
	{
		StringBuilder sBuilder=new StringBuilder();
		for(int i=1;i<edgeTo.length;i++)
		{
			if(edgeTo[i]!=null)
			{
				sBuilder.append(edgeTo[i].other(i)+"--"+i+"\n");
			}
		}
		sBuilder.append("weight:"+weight()+"\n");
		return sBuilder.toString();
	}

}
