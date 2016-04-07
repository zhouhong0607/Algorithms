package mst;
/**
 * 有向权重边
 * @author Administrator
 *
 */
public class DirectedEdge implements Comparable<DirectedEdge>
{
	private int v;
	private int w;
	private Double weight;
	
	public DirectedEdge(int from,int to,Double weigh)
	{
		// TODO Auto-generated constructor stub
		this.v=from;
		this.w=to;
		this.weight=weigh;
	}
	
	public int from()
	{
		return this.v;
	}
	public int to()
	{
		return this.w;
	}
	public Double weight()
	{
		return this.weight;
	}
	@Override 
	public int compareTo(DirectedEdge e)
	{
		if(this.weight<e.weight) return -1;
		
		if(this.weight>e.weight) return 1;
		
		return 0;
	}
	
	public String toString()
	{
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append(this.from()+"->"+this.to()+" weight:"+this.weight);
		return stringBuilder.toString();
	}
	
}
