package algorithms4;

public class TwoColorTest
{
	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColor;

	public TwoColorTest(Graph graph)
	{
		// TODO Auto-generated constructor stub
		int n = graph.V();
		marked = new boolean[n];
		color = new boolean[n];
		isTwoColor = true;
		for (int i = 0; i < n; i++)
		{
			if (!isMarked(i))
			{
				dfs(graph, i);
			}
		}
	}

	private void dfs(Graph graph, int v)
	{
		marked[v] = true;
		for (int i : graph.adjacency(v))
		{
			if (!isMarked(i))
			{
				color[i] = !color[v];
				dfs(graph, i);
			} else if (color[i] == color[v])
			{
				isTwoColor = false;

			}

		}

	}

	private boolean isMarked(int v)
	{
		return marked[v];
	}

	public boolean isTwoColor()
	{
		return isTwoColor;
	}
	
}
