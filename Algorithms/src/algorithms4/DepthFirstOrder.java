package algorithms4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstOrder
{
	private boolean[] marked;
	private Queue<Integer> pro;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	public DepthFirstOrder(Digraph digraph)
	{
		// TODO Auto-generated constructor stub
		int n=digraph.vNum();
		marked=new boolean[n];
		pro=new LinkedList<>();
		post=new LinkedList<>();
		reversePost=new Stack<>();
		for(int i=0;i<n;i++)
		{
			if(!isMarked(i))
			{
				
				dfs(digraph,i);
				
			}
		}
	}
	
	private void dfs(Digraph digraph,int v)
	{
		pro.add(v);
		marked[v]=true;
		for(Integer i:digraph.adjacency(v))
		{
			if(!isMarked(i))
			{
				dfs(digraph, i);
			}
		}
		post.add(v);
		reversePost.push(v);
		
		
	}
	private boolean isMarked(int v)
	{
		return marked[v];
	}
	
	public Iterable<Integer> getPro()
	{
		return pro;
	}
	public Iterable<Integer> getPost()
	{
		return post;
	}
	public Stack<Integer> getReversePost()
	{
		return reversePost;
	}
	
	
}
