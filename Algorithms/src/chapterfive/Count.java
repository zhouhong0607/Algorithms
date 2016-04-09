package chapterfive;

import java.io.FileInputStream;
import java.lang.Character.UnicodeScript;
import java.util.Scanner;

public class Count
{
	public static void main(String[] args)throws Exception
	{
		String filePath="src/data/abra.txt";
		FileInputStream fileIn=new FileInputStream(filePath);
		Scanner scanner=new Scanner(fileIn);
		Alphabet alphabet=new Alphabet("ABCDR");
		int R=alphabet.R();
		int[] count=new int[R];
		String s=scanner.nextLine();
		int N=s.length();
		for(int i=0;i<N;i++)
		{
			if(alphabet.contains(s.charAt(i)))
			count[alphabet.toIndex(s.charAt(i))]++;
			
		}
		
		for(int c=0;c<R;c++)
		{
			System.out.println(alphabet.toChar(c)+" "+count[c]);
		}
		
		scanner.close();
	}
}
