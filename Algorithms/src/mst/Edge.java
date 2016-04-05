package mst;

public class Edge implements Comparable<Edge>
{
	private final Integer v;
	private final Integer w;
	private Double weight;
	public Edge(int v1,int w1,double weight1)
	{
		// TODO Auto-generated constructor stub
		this.v=v1;
		this.w=w1;
		this.weight=weight1;
	}
	public Integer either()
	{
		return v;
	}
	public Integer other(int v)
	{
		if(this.v==v) return this.w;
		else if(this.w==v) return this.v;
		return null;
	}
	public Double weight()
	{
		return this.weight;
	}
	@Override
	public int compareTo(Edge e)
	{
		if(this.weight<e.weight) return -1;
		else if (this.weight>e.weight) return 1;
		else return 0;
	}
	public String toString()
	{
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append(v+"to"+w+" weight:" +weight);
		return sBuilder.toString();
	}
	
	
}
