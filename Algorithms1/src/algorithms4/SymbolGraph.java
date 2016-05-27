package algorithms4;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class SymbolGraph
{
	private HashMap<String, Integer> st;// 符号名->索引
	private String[] keys;// 索引->符号名
	private Graph graph;// 根据索引建立图

	/**
	 * 通过 扫描文件流 建立 符号图 sp为分隔符
	 * 
	 * @param scanner
	 */
	public SymbolGraph(String fileName, String sp) throws Exception
	{
		// TODO Auto-generated constructor stub

		FileInputStream fileIn = new FileInputStream(fileName);
		Scanner scanner = new Scanner(fileIn);

		st = new HashMap<>();
		/*************** 索引->符号名建立 **************/
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			String[] words = line.split(sp);
			for (String word : words)
			{
				if (!st.containsKey(word))
					st.put(word, st.size());
			}
		}
		/*************** 符号名-> 索引建立 **************/
		keys = new String[st.size()];
		for (String key : st.keySet())
		{
			keys[st.get(key)] = key;
		}
		graph = new Graph(keys.length);
		/************ 第二次扫描 建立图结构 **************/
		fileIn = new FileInputStream(fileName);
		scanner = new Scanner(fileIn);
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			String[] words = line.split(sp);
			String head = words[0];// 边的起始
			for (int i = 1; i < words.length; i++)
			{
				String tail = words[i];// 边的终止
				graph.addEdge(st.get(head), st.get(tail));

			}
		}
	}

	public boolean contains(String s)
	{
		return st.containsKey(s);
	}

	public Integer index(String s)
	{
		return st.get(s);
	}

	public String key(int i)
	{
		return keys[i];
	}

	public Graph getGraph()
	{
		return graph;
	}

	public static void main(String[] args) throws Exception
	{
		String fileName="src/algorithms4/movies.txt";
		String sp="/";
		
		
		SymbolGraph symbolGraph=new SymbolGraph(fileName, sp);
		Scanner scanner=new Scanner(System.in);
		System.out.println("Please input a key:");
		while(scanner.hasNext())
		{
			
			String key=scanner.next();
			Integer index=symbolGraph.index(key);
			
			if(symbolGraph.contains(key))
			{
				for(int i:symbolGraph.getGraph().adjacency(index))
				{
					System.out.println(symbolGraph.key(i)+" ");
				}
				
			}
			System.out.println("Please input a key:");
		}
	}
	
	

}
