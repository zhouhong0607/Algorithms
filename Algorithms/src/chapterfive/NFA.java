package chapterfive;

import java.util.HashSet;
import java.util.Stack;

import mst.Digraph;
import mst.DirectedDFS;

public class NFA
{
	private char[] re;//匹配转换
	private Digraph G;//epsilon转换
	private int M;//状态数量
	
	public NFA(String regexp)
	{
		// TODO Auto-generated constructor stub
		Stack<Integer> ops=new Stack<>();
		re=regexp.toCharArray();
		M=re.length;
		G=new Digraph(M+1);
		
		for(int i=0;i<M;i++)
		{
			int lp=i;
			if(re[i]=='('||re[i]=='|')
				ops.push(i);
			else if (re[i]==')')
			{
				int or=ops.pop();
				if(re[or]=='|')
				{
					lp=ops.pop();
					G.addEdge(lp, or+1);
					G.addEdge(or, i);
				}else
					lp=or;
			}
			if(i<M-1&&re[i+1]=='*')
			{
				G.addEdge(lp, i+1);
				G.addEdge(i+1, lp);
			}
			if(re[i]=='('||re[i]=='*'||re[i]==')')
				G.addEdge(i, i+1);
		}
	}
	public boolean recognizes(String txt)
	{
		HashSet<Integer> pc=new HashSet<>();
		DirectedDFS dfs=new DirectedDFS(G, 0);
		for(int v=0;v<G.vNum();v++)
			if(dfs.isMarked(v))
				pc.add(v);
		for(int i=0;i<txt.length();i++)
		{
			//计算txt[i+1]可能到达的所有状态
			HashSet<Integer> match=new HashSet<>();
			for(int v:pc)
			{
				if(v<M)
					if(re[v]==txt.charAt(i)||re[v]=='.')
						match.add(v+1);
			}
			pc=new HashSet<>();
			dfs=new DirectedDFS(G, match);
			for(int v=0;v<G.vNum();v++)
				if(dfs.isMarked(v))
					pc.add(v);
		}
		for(int v:pc) if(v==M) return true;
		return false;
	}
	
}
