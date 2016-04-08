package mst;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

public class Arbitrage
{
	public static void main(String[] args) throws Exception
	{
		String filePath="src/data/rates.txt";
		FileInputStream fileIn=new FileInputStream(filePath);
		Scanner scanner=new Scanner(fileIn);
		int n=Integer.parseInt(scanner.nextLine());
		EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(n);
		String[] moneys=new String[n];
		for(int i=0;i<n;i++)
		{
			String[] parts=scanner.nextLine().split("\\s+");
			moneys[i]=parts[0];
			for(int j=1;j<n+1;j++)
			{
				if(i+1!=j)
				{
					digraph.addEdge(new DirectedEdge(i, j-1, -Math.log(Double.parseDouble(parts[j]))));
				}
			}
		}
		BellmanFordSP bellmanFordSP=new BellmanFordSP(digraph, 0);
		if(bellmanFordSP.hasNegativeCycle())
		{
			Double stake=1000.0;
			Stack<DirectedEdge> cycle=bellmanFordSP.negativeCycle();
			while(!cycle.isEmpty())
			{
				DirectedEdge edge=cycle.pop();
				System.out.print(String.format("%.3f", stake)+moneys[edge.from()]+"");
				stake*=Math.exp(-edge.weight());
				System.out.print("="+String.format("%.3f", stake)+moneys[edge.to()]+"\n");
			}
			
			
		}
		
		
		
	}
	
	
}
