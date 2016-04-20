package chaptersix;

public class FlowEdge
{
	private final int v;//from
	private	final int w;//to
	private final double cap;//capacity
	private double flow;
	public FlowEdge(int from,int to,double capacity)
	{
		// TODO Auto-generated constructor stub
		this.v=from;
		this.w=to;
		this.cap=capacity;
		this.flow=0.0;
	}
	public int from()
	{
		return v;
	}
	public int to()
	{
		return w;
	}
	public double capacity()
	{
		return this.cap;
	}
	public double flow()
	{
		return this.flow;
	}
	public int other(int vertex)
	{
		return vertex==this.v?this.w:this.v;
	}
	/**
	 * 剩余流量
	 * @param vertex
	 * @return
	 */
	public double residualCapacityTo(int vertex)
	{
		return vertex==this.v?this.flow:this.cap-this.flow;
	}
	public void addResidualFlowTo(int vertex,double delta)
	{
		if(vertex==this.v) this.flow-=delta;
		if(vertex==this.w) this.flow+=delta;
	}
	
	public String toString()
	{
		return String.format("%d->%d %.2f %.2f", v,w,cap,flow);
	}
}
