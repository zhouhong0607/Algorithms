package algorithms3;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import algorithms1.StopWatch;

public class FileIndex
{
	private FileIndex()
	{
		// TODO Auto-generated constructor stub

	}

	public static void main(String[] args) throws Exception
	{
		File[] files = new File[9];
		files[0] = new File("src/algorithms3/mobydick.txt");
		files[2] = new File("src/algorithms3/aesop.txt");
		files[3] = new File("src/algorithms3/amino.csv");
		files[4] = new File("src/algorithms3/DJIA.csv");
		files[5] = new File("src/algorithms3/ip-by-country.csv");
		files[6] = new File("src/algorithms3/leipzig.txt");
		files[7] = new File("src/algorithms3/movie.txt");
		files[8] = new File("src/algorithms3/tale.txt");
		

		HashMap<String, HashSet<String>> hashMap = new HashMap<>();

		StopWatch stopWatch = new StopWatch();
		for (int i = 0; i < files.length; i++)
		{
			if (files[i] != null)
			{
				Scanner scanner = new Scanner(files[i]);
				while (scanner.hasNext())
				{
					String key = scanner.next();
					if (!hashMap.containsKey(key))
						hashMap.put(key, new HashSet<>());
					hashMap.get(key).add(files[i].getName());
				}
			}
		}

		System.out.println("Time:" + stopWatch.elapsedTime());

		Scanner input = new Scanner(System.in);
		while (input.hasNext())
		{
			String key = input.next();

			if (hashMap.containsKey(key))
			{
				System.out.println(hashMap.get(key).toString());
			} else
			{
				System.out.println("Nothing");
			}
		}

	}

}
