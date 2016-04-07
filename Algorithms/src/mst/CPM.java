package mst;

import java.io.FileInputStream;
import java.util.Scanner;

public class CPM
{
	public static void main(String[] args) throws Exception
	{
		String filePaht="src/data/jobsPC.txt";
		FileInputStream fileIn=new FileInputStream(filePaht);
		Scanner scanner=new Scanner(fileIn);
		int n=Integer.parseInt(scanner.nextLine());
		EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(2*n+2);
		int s=2*n;//起始点
		int t=2*n+1;//结束点
		//构造任务调度图
		for(int i=0;i<n;i++)
		{
			String sss=scanner.nextLine();
			String[] parts=sss.split("\\s+");
			Double time=Double.parseDouble(parts[0]);
			digraph.addEdge(new DirectedEdge(i, i+n, time));
			digraph.addEdge(new DirectedEdge(s, i, 0.0));
			digraph.addEdge(new DirectedEdge(i+n, t, 0.0));
			
			for(int j=1;j<parts.length;j++)
			{
				int w=Integer.parseInt(parts[j]);
				digraph.addEdge(new DirectedEdge(i+n, w, 0.0));
			}
		}
		//计算  s 为起点的 最长路径树
		AcyclicLP acyclicLP=new AcyclicLP(digraph, s);
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<n;i++)
		{
			sBuilder.append(i+" startAt:"+acyclicLP.distTo(i)+"\n");
		}
		sBuilder.append("the least time:"+acyclicLP.distTo(t));
		System.out.print(sBuilder.toString());
		
	}
}
