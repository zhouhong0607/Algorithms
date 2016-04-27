package chapterfive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;
import java.util.zip.ZipOutputStream;

import data.StopWatch;

public class ViolenceSearch
{
	public static int search(String pat, String txt)
	{
		int M = pat.length();
		int N = txt.length();
		for (int i = 0; i <= N - M; i++)
		{
			int j;
			for (j = 0; j < M; j++)
				if (txt.charAt(i + j) != pat.charAt(j))
					break;
			if (j == M)
				return i;
		}
		return N;
	}

	public static int backSearch(String pat, String txt)
	{
		int j, M = pat.length();
		int i, N = txt.length();
		for (i = 0, j = 0; i < N && j < M; i++)
		{
			if (txt.charAt(i) == pat.charAt(j))
				j++;
			else
			{
				i -= j;
				j = 0;
			}
		}
		if (j == M)
			return i - M;// 找到匹配
		else
			return N;// 未找到匹配
	}

	public static void main(String[] args) throws Exception
	{
		String filePath = "src/data/words3.txt";
		FileReader fileIn = new FileReader(filePath);
		BufferedReader bReader=new BufferedReader(fileIn);
	
		Scanner scanner = new Scanner(bReader);
		String txt = new String();
		while (scanner.hasNextLine())
		{
			txt += scanner.nextLine();
		}
		System.out.println(txt);
		
		
//		FileOutputStream fileOut=new FileOutputStream("src/data/words3.txt");
//		fileOut.write(txt.getBytes());
//		fileOut.flush();
		
		
		String pat = "qqq";
		KMP kmp=new KMP(pat);
		BoyerMoore boyerMoore=new BoyerMoore(pat);
		RabinKarp rabinKarp=new RabinKarp(pat);
		
		StopWatch stopWatch = new StopWatch();
//		 System.out.println(ViolenceSearch.backSearch(pat, txt));
//		System.out.println(ViolenceSearch.search(pat, txt));
//		System.out.println(kmp.search(txt));
//		System.out.println(boyerMoore.search(txt));
		System.out.println(rabinKarp.search(txt));
		System.out.println(stopWatch.elapsedTime());      
		scanner.close();
	}
}
