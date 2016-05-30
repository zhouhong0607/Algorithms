package algorithms1;

import java.sql.Date;

public class UnionFind
{
	int[] lineSet;//所有点  的连通分量标识
	int[] size;
	int count; //连同分量的书目
	public UnionFind(int n)
	{
		lineSet=new int[n];
		size=new int[n];
		for(int i=0;i<n;i++)
		{
			lineSet[i]=i;  //初始状态 每个点为 一个连通分量
			size[i]=1;
		}
		count=n;
	}
	
	public boolean isConnected(int p,int q)//判断p，q是否连通
	{
		return find(p)==find(q);
	}
	
	public void connect(int p,int q)//连接p，q 是p，q属于同一连通分量
	{
		int pRoot=find(p);
		int qRoot=find(q);
		
		if(pRoot==qRoot)
			return;
		
		if(size[pRoot]<size[qRoot])//将小树连接到大树上
		{
		lineSet[pRoot]=qRoot;
		size[qRoot]+=size[pRoot];
		}else
		{
			lineSet[qRoot]=pRoot;
			size[pRoot]=size[qRoot];
		}
		count--;
	}
	public int getCount()//获得连同分量的个数
	{
		return count;
	}
	public int find(int p)//获得p所属连通分量的标识
	{
		while(lineSet[p]!=p)
			p=lineSet[p];
		return p;
	}
	
	public static void main(String[] args)
	{
		
		
		
		UnionFind unionFind=new UnionFind(4);
		
		unionFind.connect(0, 2);
		unionFind.connect(2, 3);
		
		for(int i=0;i<4;i++)
		{
			System.out.println(unionFind.lineSet[i]);
		}
		
	}
}
