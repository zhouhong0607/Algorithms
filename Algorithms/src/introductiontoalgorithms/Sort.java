package introductiontoalgorithms;

import java.util.Random;
import java.util.RandomAccess;

public class Sort
{

	public static void main(String[] args)
	{
		int[] a = { 16,4, 10, 14, 7, 9, 3, 2, 8, 1 };
//		quickSort(a, 0, 9);
		System.out.println( randomizedSelect(a, 0, 9, 2));	
//		for (int i = 0; i < a.length; i++)
//		{
//			System.out.println(a[i]);
//		}

	}

	private static void quickSort(int[] array, int p, int r)
	{
		if (p < r)
		{
//			int q = partition(array, p, r);
			int q=randomizedPartition(array, p, r);
			quickSort(array, p, q - 1);
			quickSort(array, q + 1, r);
		}

	}

	private static int randomizedPartition(int[] array,int p,int r)
	{
		int k=p+(int)Math.random()*(r-p+1);//返回p~r的随机数 ，包含r
		int c=array[k];
		array[k]=array[r];
		array[r]=c;
		
		
		return partition(array, p, r);
	}
	
	
	private static int partition(int[] array, int p, int r)// 分割数组，返回q=i+1，使得q左面都小于a[q]，q右面都大于等于a【q】
	{
		int x = array[r];
		int i = p - 1;

		for (int j = p; j <= r - 1; j++)
		{
			if (array[j] < x)
			{
				i++;
				int c = array[i];
				array[i] = array[j];
				array[j] = c;
			}
		}
		int c;
		c = array[i + 1];
		array[i + 1] = array[r];
		array[r] = c;
		return i + 1;

	}
	/**
	 * 期望为线性时间的选择算法，第i个顺序统计量
	 * @param array
	 * @param p
	 * @param r
	 * @param i
	 * @return
	 */
	private static int randomizedSelect(int[] array,int p,int r,int i) 
	{
		
		if(p==r)
		{
			return array[p];
		}
		
		int q=randomizedPartition(array, p, r);
		int k=q-p+1;//算上q左侧一共有k个值
		if(i==k)
		{
			return array[q];
		}else if(i<k)//那么i的顺序统计量在q左侧
		{
			return randomizedSelect(array, p, q-1, i);
		}else   //否则i的顺序统计量在q的右侧
		{
			return randomizedSelect(array, q+1, r, i-k);
		}	
		
		
		
	}
	
}
