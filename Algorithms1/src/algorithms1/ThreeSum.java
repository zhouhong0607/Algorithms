package algorithms1;

import java.util.Arrays;
import java.util.Scanner;
/**
 * 计算数组array中  3个整数和为0的个数
 * @author Administrator
 *
 */
public class ThreeSum
{
	private final static int MAX_VALUE=10000;
	private static int pretime;
	public  static double timeTrial(int N)
	{
		double time;
		
		int[] a=new int[N];
		for(int i=0;i<N;i++)
		{
			a[i]=(int)(-MAX_VALUE)+(int)(Math.random()*(MAX_VALUE*2));
		}
		StopWatch stopWatch=new StopWatch();
		int num=countModify(a);
		time=stopWatch.elapsedTime();
//		System.out.println("Scale:"+N+"      count:"+num+"      time"+time);
	
		return time;
		
	}
	
	
	
	public static int count(int[] a)
	{
		int N=a.length;
		int count=0;
		for(int i=0;i<N;i++)
		{
			for(int j=i+1;j<N;j++)
			{
				for(int k=j+1;k<N;k++)
				{
					if(a[i]+a[j]+a[k]==0)
						count++;
				}
			}
		}
		return count;
		
		
	}
	
	public static int countModify(int[] a)
	{
		Arrays.sort(a);//!!!!! 记得排序  再用二分查找
		int N=a.length;
		int count=0;
		for(int i=0;i<N;i++)
		{
			for(int j=i+1;j<N;j++)
			{
//				if(BinarySearch.rank(a,-(a[i]+a[j]), j+1, N-1)!=-1)
//				{
//						count++;
//				}
				if(BinarySearch.rank(a,-( a[i]+a[j]), 0, N-1)>j)
				{
						count++;
				}
			}
		}
		return count;
	}
	
	
	public static void main(String[] args)
	{
//		int N=0;
//		int[] array;
//		N=5000;
////		N+=500;
////		N=(int)(2000)+(int)(Math.random()*(2000));
//		array=new int[N];
//		for(int i=0;i<N;i++)
//		{
//			array[i]=(int)(-N)+(int)(Math.random()*(N+N));
//		}
//		
//		
//		while(true)
//		{
//
//			
//			
//			StopWatch watch=new StopWatch();
//			int count=ThreeSum.countModify(array);
//			System.out.println("scale:"+N+"    count:"+count+"    time:"+watch.elapsedTime()+"s");
//			
//		}
		
		double rate;
		int N;
		double pretime;
		double time;
		pretime=timeTrial(1000);
		for(N=2000;true;N+=N)
		{
			time=timeTrial(N);
			rate=time/pretime;
			pretime=time;
			
			System.out.println("scale:"+N+"\ttime:"+time+"\t\trate:"+rate);
			
		}
		
		
	}
	
}
