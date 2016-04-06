package mst;

import java.util.HashSet;
import java.util.PriorityQueue;

public class KruskalMST
{
	HashSet<Edge> mst;

	public KruskalMST(EdgeWeightedGraph eGraph)
	{
		// TODO Auto-generated constructor stub
		mst = new HashSet<>();
		PriorityQueue<Edge> minPQ = new PriorityQueue<>();
		WeightedUF uf = new WeightedUF(eGraph.vNum());
		for (Edge e : eGraph.edges())
		{
			minPQ.add(e);
		}
		while (!minPQ.isEmpty() && mst.size() < eGraph.vNum() - 1)
		{
			Edge edge = minPQ.poll();
			int v = edge.either();
			int w = edge.other(v);
			if (uf.isConnected(v, w))
				continue;
			uf.connect(v, w);
			mst.add(edge);

		}

	}
	public Double weight()
	{
		Double weight=0.0;
		for(Edge e:mst)
		{
			
			weight+=e.weight();
				
		}
		return weight;
	}
	
	public String toString()
	{
		StringBuilder sBuilder=new StringBuilder();
	
		for(Edge e:mst)
		{
			
				sBuilder.append(e.either()+"--"+e.other(e.either())+"\n");
				
		}
		sBuilder.append("weight:"+weight()+"\n");
		return sBuilder.toString();
	}
}
