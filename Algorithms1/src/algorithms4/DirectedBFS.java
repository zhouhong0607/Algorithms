package algorithms4;

import java.util.LinkedList;
import java.util.Queue;

public class DirectedBFS
{
	private boolean[] marked;

	public DirectedBFS(Digraph digraph,int v)
	{
		// TODO Auto-generated constructor stub
		marked=new boolean[digraph.vNum()];
		bfs(digraph,v);
	}
	
	public DirectedBFS(Digraph digraph,Iterable<Integer> s)
	{
		// TODO Auto-generated constructor stub
		marked=new boolean[digraph.vNum()];
		for(int i:s)
		{
			if(!isMarked(i))
				bfs(digraph, i);
		}
	}
	
	
	private void bfs(Digraph digraph,int v)
	{
		Queue<Integer> queue=new LinkedList<>();
		queue.add(v);
		while(!queue.isEmpty())
		{
			int v1=queue.poll();
			marked[v1]=true;
			for(Integer i:digraph.adjacency(v1))
			{
				if(!isMarked(i))
					queue.add(i);
			}
		}
	}
	public boolean isMarked(int v)
	{
		return marked[v];
	}
	public String toString()
	{
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<marked.length;i++)
		{
			if(marked[i])
				sBuilder.append(" "+i);
		}
		return sBuilder.toString();
	}
}
