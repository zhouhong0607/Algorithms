package chapterfive;

import java.util.Arrays;

import javax.rmi.CORBA.Util;

public class LSD
{
	public static void sort(String[] a, int width)
	{
		int num=a.length;
		int R=256;
		String[] aux=new String[num];
		for(int d=width-1;d>=0;d--)
		{
			int[] count=new int[R+1];
			for(int i=0;i<num;i++)
				count[a[i].charAt(d)+1]++;
			for(int r=0;r<R;r++)
				count[r+1]+=count[r];
			for(int i=0;i<num;i++)
			{
				aux[count[a[i].charAt(d)]++]=a[i];
			}
			for(int i=0;i<num;i++)
			{
				a[i]=aux[i];
			}
		}
	}
	
	public static void main(String[] args)throws Exception
	{
		String[] a={"4DG38","2AYE23","3BI0720","1C50"};
//		LSD.sort(a, 7);
//		MSD.sort(a);
//		Arrays.sort(a);
		MSD.quick3String(a);
 		for(int i=0;i<a.length;i++)
 		{
 			System.out.println(a[i]);
 		}
	}
	
	
}
