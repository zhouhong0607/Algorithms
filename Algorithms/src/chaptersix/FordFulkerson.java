package chaptersix;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson
{
	private boolean[] marked;//剩余网络中是否存在s到v的路径
	private FlowEdge[] edgeTo;//从s到v的最短路径上的最后一条边
	private double value;//当前最大流量
	
	public FordFulkerson(FlowNetwork graph,int s,int t)
	{
		// TODO Auto-generated constructor stub
		while(hasAugmentingPath(graph, s, t))
		{
			double bottle=Double.POSITIVE_INFINITY;//当前的瓶颈容量
			for(int v=t;v!=s;v=edgeTo[v].other(v))
				bottle=Math.min(bottle, edgeTo[v].residualCapacityTo(v));
			//增大流量
			for(int v=t;v!=s;v=edgeTo[v].other(v))
				edgeTo[v].addResidualFlowTo(v, bottle);

			value+=bottle;
			
		}
		
		
	}
	/**
	 * 是否存在增广路径  BFS
	 * @param graph
	 * @param s
	 * @param v
	 * @return
	 */
	private boolean hasAugmentingPath(FlowNetwork graph,int s,int t)
	{
		marked=new boolean[graph.vNum()];
		edgeTo=new FlowEdge[graph.vNum()];
		Queue<Integer> q=new LinkedList<>();
		marked[s]=true;
		q.offer(s);
		while(!q.isEmpty())
		{
			int v=q.poll();
			for(FlowEdge edge:graph.adjacent(v))
			{
				int w=edge.other(v);
				if(edge.residualCapacityTo(w)>0&&!marked[w])
				{
					edgeTo[w]=edge;
					marked[w]=true;
					q.offer(w);
				}
			}
		}
		
		return marked[t];
	}
	
	
	
	public double value()
	{
		return value;
	}
	public boolean inCut(int v)
	{
		return marked[v];
	}
	
	public static void main(String[] args)throws Exception
	{
		FlowNetwork flowNetwork=new FlowNetwork("src/data/tinyFN.txt");
		int s=0;
		int t=flowNetwork.vNum()-1;
		FordFulkerson maxFlow=new FordFulkerson(flowNetwork, s, t);
		
		System.out.println("Max flow from "+s+" to "+t);
		for(int v=0;v<flowNetwork.vNum();v++)
			for(FlowEdge edge:flowNetwork.adjacent(v))
				if((v==edge.from()&&edge.flow()>0))
					System.out.println("  "+edge);
		
		System.out.println("Max flow value ="+maxFlow.value());
		
		
	}
	
	
	
	
}
