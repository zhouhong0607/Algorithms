package algorithms4;

import java.util.Stack;

public class KosarajuSCC
{
	private boolean[] marked;
	private int[] id;
	private int count;

	public KosarajuSCC(Digraph digraph)
	{
		// TODO Auto-generated constructor stub
		int n = digraph.vNum();
		marked = new boolean[n];
		id = new int[n];

		DepthFirstOrder order = new DepthFirstOrder(digraph.reverse());
		Stack<Integer> stack = order.getReversePost();
		while (!stack.isEmpty())
		{
			int i = stack.pop();
			if (!isMarked(i))
			{
				dfs(digraph, i);
				count++;
			}
		}

	}
	private void dfs(Digraph digraph, int v)
	{
		marked[v] = true;
		id[v] = count;
		for (int i : digraph.adjacency(v))
		{
			if (!isMarked(i))
				dfs(digraph, i);
		}
	}

	private boolean isMarked(int v)
	{
		return marked[v];
	}
	public boolean stronglyConnected(int v,int w)
	{
		return id[v]==id[w];
	}
	
	public int id(int v)
	{
		return id[v];
	}
	
	public int count()
	{
		return count;
	}

}
