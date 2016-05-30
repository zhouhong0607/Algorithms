package algorithms3;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import algorithms1.StopWatch;

public class LookupIndex
{
	private LookupIndex()
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String[] args) throws Exception
	{
		FileInputStream fileIn = new FileInputStream("src/algorithms3/amino.csv");
		Scanner scanner = new Scanner(fileIn);
		HashMap<String, ArrayList<String>> st = new HashMap<>();
		HashMap<String, ArrayList<String>> ts = new HashMap<>();
		StopWatch stopWatch=new StopWatch();
		
		while (scanner.hasNext())
		{
			String line[] = scanner.nextLine().split(",");
			String key = line[0];

			for (int i = 1; i < line.length; i++)
			{
				String val = line[i];
				if (!st.containsKey(key))
					st.put(key, new ArrayList<>());
				if (!ts.containsKey(val))
					ts.put(val, new ArrayList<>());
				st.get(key).add(val);
				ts.get(val).add(key);
			}
		}

		System.out.println("Time:"+stopWatch.elapsedTime());
		
		
		scanner.close();
		fileIn.close();
		scanner = new Scanner(System.in);
		while (scanner.hasNext())
		{
			String query = scanner.nextLine();

			if (st.containsKey(query))
//				System.out.println(st.get(s).toString());
				for(String s:st.get(query))
					System.out.print(s+" ");
				
			if (ts.containsKey(query))
				for(String s:ts.get(query))
					System.out.print(s+" ");
//				System.out.println(ts.get(s).toString());
			
			
			System.out.println("\nSearchTime:"+stopWatch.elapsedTime());
			
		}
		
		
	}

}
