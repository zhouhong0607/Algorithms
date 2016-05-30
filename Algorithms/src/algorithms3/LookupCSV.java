package algorithms3;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 字典类用例
 * @author Administrator
 *
 */
public class LookupCSV
{
	public static void main(String[] args) throws Exception 
	{
		FileInputStream fileIn=new FileInputStream("src/algorithms3/amino.csv");
		int keyField=0;
		int valField=1;
		HashMap<String, String> hashMap=new HashMap<>();
		Scanner scanner=new Scanner(fileIn);
		
		while(scanner.hasNext())
		{
			String[] line=scanner.nextLine().split(",");
			if(!hashMap.containsKey(line[0]))
				hashMap.put(line[0], line[1]);
		}
		
		scanner.close();
		fileIn.close();
		scanner=new Scanner(System.in);
		while(scanner.hasNext())
		{
			String s=scanner.next();
			if(hashMap.containsKey(s))
				System.out.print(hashMap.get(s));
			else
				System.out.print("contains null");
		}
		
		
	}
	
	
}
