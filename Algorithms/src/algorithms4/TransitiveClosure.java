package algorithms4;
/**
 * 两点可达性
 * @author Administrator
 *
 */
public class TransitiveClosure
{
	private DirectedDFS[] all;
	public TransitiveClosure(Digraph digraph)
	{
		// TODO Auto-generated constructor stub
		int n=digraph.vNum();
		all=new DirectedDFS[n];
		for(int i=0;i<n;i++)
			all[i]=new DirectedDFS(digraph, i);
	}
	
	public boolean reachable(int v,int w)
	{
		return all[v].isMarked(v);
	}
	
}
