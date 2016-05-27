package algorithms3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import algorithms1.StopWatch;

public class ST<Key,Value>
{
	public static void main(String[] args) throws Exception
	{
		FileInputStream io=new FileInputStream("src/algorithms3/mobydick.txt");
//		SequentialSearchST<String, Integer> count=new SequentialSearchST<>();
//		BinarySearchST<String, Integer> count=new BinarySearchST<>(10);
//		TreeMap<String, Integer> count=new TreeMap<>();
//		HashMap<String, Integer> count=new HashMap<>();
//		Hashtable<String, Integer> count=new Hashtable<>();
		
//		BinarySearchTree<String, Integer> count=new BinarySearchTree<>();
//		RbTree<String, Integer> count=new RbTree<>();
//		RedBlackBST<String, Integer> count=new RedBlackBST<>();
//		RedBlackLiteBST<String, Integer> count=new RedBlackLiteBST<>();
//		SeparateChainingHashST<String, Integer> count=new SeparateChainingHashST<>();
		LinearProbingHashST<String, Integer> count= new LinearProbingHashST<>();
		
		
		
	 
		
		
		StopWatch stopWatch=new StopWatch();
		
//		count.put("S", 1);
//		count.put("E", 1);
//		count.put("A", 1);
//		count.put("R", 1);
//		count.put("C", 1);
//		count.put("H", 1);
//		count.put("X", 1);
//		count.put("M", 1);
//		count.put("P", 1);
//		count.put("L", 1);
		
		
		
		Scanner scanner=new Scanner(io);
		while(scanner.hasNext())
		{
			String word=scanner.next();
//			if(word.length()<8)
//				continue;
			if(count.get(word)!=null)
			{
				count.put(word, count.get(word)+1);
			}else
			{
				count.put(word, 1);
			}
		}
		
		System.out.println("Size:"+count.size()+"\tTime:"+stopWatch.elapsedTime());
		
		String max=" ";
		int maxValue=0;
		for(String s:count.keySet())
		{
			if(s!=null)
			if(count.get(s)>maxValue)
			{
				max=s;
				maxValue=count.get(s);
			}
		}
		System.out.println("MaxWord: "+max+"\tCount: "+count.get(max)+"\tTime: "+stopWatch.elapsedTime());
		
		for(String s:count.keySet())
		{
			count.delete(s);
			
		}
		System.out.println("after delete:"+count.size());
		
		scanner.close();
		io.close();
		
	}
}
