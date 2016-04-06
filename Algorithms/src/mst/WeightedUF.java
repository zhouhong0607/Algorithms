package mst;

public class WeightedUF
{
	private  Integer[] id;
	private Integer[] size;
	private Integer count;
	public WeightedUF(Integer n)
	{
		// TODO Auto-generated constructor stub
		id=new Integer[n];
		size=new Integer[n];
		count=n;
		for(int i=0;i<n;i++)
		{
			id[i]=i;
			size[i]=1;
		}
	}
	private Integer find(int i)
	{
		
		while(id[i]!=i) i=id[i];
		return i;
	}
	
	public boolean isConnected(int i,int j)
	{
		return find(i)==find(j);
	}
	public void connect(int i,int j)
	{
		int rootI=find(i);
		int rootJ=find(j);
		if(rootI==rootJ) return;
		
		if(size[rootI]<size[rootJ])
		{
			size[rootJ]+=size[rootI];
			id[rootI]=rootJ;
			
		}else
		{
			size[rootI]+=size[rootJ];
			id[rootJ]=rootI;
		}
		count--;
	}
	public Integer count()
	{
		return count;
	}
	
}
