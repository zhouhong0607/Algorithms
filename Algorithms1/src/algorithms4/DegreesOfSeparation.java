package algorithms4;

import java.util.Scanner;

/**
 * 两点之间间隔的度数（BFS 单点最短路径）
 * @author Administrator
 *
 */
public class DegreesOfSeparation
{
	public static void main(String[] args) throws Exception
	{
		String fileName="src/algorithms4/routes.txt";
		String sp=" ";
		SymbolGraph symbolGraph=new SymbolGraph(fileName, sp);
		Graph graph=symbolGraph.getGraph();
		
		String head="JFK";
		if(!symbolGraph.contains(head)) return;
		
		int i=symbolGraph.index(head);
		BreadthFirstSearch bSearch=new BreadthFirstSearch(graph, i);
		Scanner scanner=new Scanner(System.in);
		System.out.println("Please input a key:");
		while(scanner.hasNext())
		{
			String tail=scanner.next();
			if(symbolGraph.contains(tail))
			{
				int j=symbolGraph.index(tail);
				if(bSearch.hasPathTo(j))
				{
					for(Integer k:bSearch.pathTo(j))
					{
						System.out.println("  "+symbolGraph.key(k));
					}
				}
			}
			
		}
		
		
		
		
		
	}
	
}
