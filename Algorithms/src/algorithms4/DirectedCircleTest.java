package algorithms4;

import java.util.Stack;

public class DirectedCircleTest
{
	private boolean[] markded;
	private boolean[] onStack;
	private int[] preVertex;
	private Stack<Integer> circle;
	
	public DirectedCircleTest(Digraph digraph)
	{
		// TODO Auto-generated constructor stub
		
	
		
		markded=new boolean[digraph.vNum()];
		onStack=new boolean[digraph.vNum()];
		
		preVertex=new int[digraph.vNum()];
		for(int i=0;i<digraph.vNum();i++)
		{
			if(!isMarked(i))
				dfs(digraph,i);
		}
	}
	
	private void dfs(Digraph digraph,int v)
	{
		markded[v]=true;
		onStack[v]=true;
		for(int i:digraph.adjacency(v))
		{
			if(hasCircle())
			{
				return ;
			}else if (!isMarked(i))
			{
				preVertex[i]=v;
				dfs(digraph, i);
			}else if (onStack[i])//找到环
			{
				circle=new Stack<>();
				for(int k=v;k!=i;k=preVertex[k])
				{
					circle.push(k);
				}
				circle.push(i);
				circle.push(v);
			}
			
		}
		onStack[v]=false;
		
		
	}
	
	public boolean hasCircle()
	{
		return circle!=null;
	}
	
	private boolean isMarked(int v)
	{
		return markded[v];
	}
	public Iterable<Integer> getCircle()
	{
		return circle;
	}
}
