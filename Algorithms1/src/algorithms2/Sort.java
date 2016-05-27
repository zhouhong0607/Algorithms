package algorithms2;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import algorithms1.StopWatch;

public class Sort
{

	private final static int MAX = 10000;

	/**
	 * 返回每种排序算法的使用时间
	 * 
	 * @param type
	 * @param a
	 * @return
	 */
	public static double useTime(String type, int N)
	{

		Integer[] a = new Integer[N];
		Sort.setArrayValue(a);

		StopWatch watch = new StopWatch();
		if (type.equals("Insert"))
			insertSort(a);
		else if (type.equals("Select"))
			selectSort(a);
		else if (type.equals("Shell"))
			shellSort(a);
		else if (type.equals("Merge"))
			mergeSort(a, 0, a.length - 1);
		else if (type.equals("Quick"))
			quickSort(a, 0, a.length - 1);
		else if (type.equals("System"))
			systemSort(a);
		else if (type.equals("3Quick"))
			threeCutQuickSort(a, 0, a.length - 1);
		else
		{

		}

		return watch.elapsedTime();

	}

	/**
	 * 为数组a随机赋值
	 * 
	 * @param a
	 */
	public static void setArrayValue(Comparable[] a)
	{
		int N = a.length;
		for (int i = 0; i < N; i++)
			a[i] = (int) (-MAX) + (int) (Math.random() * (2 * MAX));
	}

	/**
	 * 切分数组
	 * 
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	private static int partition(Comparable[] a, int low, int high)
	{
		int select = low + (int) Math.random() * (high - low + 1);
		Comparable value = a[select];// 根据value切分

		int k = low;

		for (int i = low; i <= high; i++)
		{
			if (less(a[i], value))
			{
				exchange(a, i, k);
				k++;
			}
		}
		return k;
	}
/**
 * 找到无序数组 k顺序统计量
 * @param a
 * @param k
 * @return
 */
	public static Comparable select(Comparable[] a,int k)
	{
		int low=0;
		int high=a.length-1;
		
		
		while(high>low)
		{
			
			int	j=partition(a, low, high);
				if(k==j) return a[k];
				else if (k<j)
				{
					high=j-1;
				}else
				{
					low=j+1;
				}
		}
		
		return a[k];
		
	}
	
	
	public static void systemSort(Comparable[] a)
	{

		Arrays.sort(a);

	}

	public static void threeCutQuickSort(Comparable[] a,int low,int high)
	{
		if(high<=low)
			return;
		//切割部分
		int p=low+(int)(Math.random()*(high-low+1));
		Comparable value=a[p];//随机取一个元素
		int i=low;//i左侧比value小
		int j=high;//j右侧比value大
		
		for(int k=low;k<=j;k++)
		{
			
			if(a[k].compareTo(value)<0)
			{
				exchange(a, i++, k); //如果一直都是小于，则i，k保持同步，直到碰到第一个相等（一定会碰到 至少为value的位置）k++而i不变此时i指向的数等于value
				
			}else if (a[k].compareTo(value)>0)
			{
				exchange(a, k--, j--);//如果碰到大于，则交换后k（--之后会执行++）的值保持不变，因为不知道交换过来k位置上的值的大小
				
			}else//相等
			{
				;
			}
		}
		
		threeCutQuickSort(a, low, i-1);
		threeCutQuickSort(a, j+1, high);
		
		
	}
	
	
	/**
	 * 快速排序
	 * 
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void quickSort(Comparable[] a, int low, int high)
	{
		if (low >= high)
			return;
		int p = partition(a, low, high);
		quickSort(a, low, p - 1);
		quickSort(a, p + 1, high);

	}

	/**
	 * 归并排序
	 * 
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void mergeSort(Comparable[] a, int low, int high)
	{
		if (low == high)
			return;

		int mid = (low + high) / 2;
		mergeSort(a, low, mid);
		mergeSort(a, mid + 1, high);
		merge(a, low, mid, high);
	}

	/**
	 * 合并两个有序数组
	 * 
	 * @param a
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void merge(Comparable[] a, int low, int mid, int high)
	{

		Comparable[] assi = new Comparable[a.length];
		for (int s = low; s <= high; s++)
		{
			assi[s] = a[s];
		}
		int i = low;
		int j = mid + 1;

		for (int k = low; k < high; k++)
		{
			if (i > mid)
			{
				a[k] = assi[j++];
			} else if (j > high)
			{
				a[k] = assi[i++];
			} else if (less(assi[i], assi[j]))
			{
				a[k] = assi[i++];
			} else
			{
				a[k] = assi[j++];
			}

		}

	}

	public static void shellSort(Comparable[] a)
	{
		int N = a.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;
		while (h >= 1)
		{
			for (int i = h; i < N; i++)
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
					exchange(a, j, j - h);

			h /= 3;
		}

	}

	/**
	 * 选择排序
	 * 
	 * @param a
	 */
	public static void selectSort(Comparable[] a)
	{
		int N = a.length;
		for (int i = 0; i < N - 1; i++)
		{
			int min = i;
			for (int j = i + 1; j < N; j++)
			{
				if (less(a[j], a[min]))
					min = j;
			}
			if (i != min)
				exchange(a, i, min);
		}

	}

	/**
	 * 插入排序(类似冒泡)
	 * 
	 * @param a
	 */
	public static void insertSort(Comparable[] a)
	{
		int N = a.length;
		for (int i = 1; i < N; i++)
		{

			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
			{
				exchange(a, j, j - 1);
			}

		}
	}

	/**
	 * 比较a是否小于b
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private static boolean less(Comparable a, Comparable b)
	{
		return a.compareTo(b) < 0;
	}

	/**
	 * 交换a[i] a[j]的值
	 * 
	 * @param a
	 * @param i
	 * @param j
	 */
	private static void exchange(Comparable[] a, int i, int j)
	{
		Comparable c;
		c = a[i];
		a[i] = a[j];
		a[j] = c;
	}

	/**
	 * 判断数组a是否已经排序好
	 * 
	 * @param a
	 * @return
	 */
	private static boolean isSorted(Comparable[] a)
	{
		for (int i = 1; i < a.length; i++)
		{
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	/**
	 * 打印数组a
	 * 
	 * @param a
	 */
	public static void show(Comparable[] a)
	{
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void main(String[] args)
	{
//		String[] a = { "A", "C", "F", "D", "G" };
//		Integer[] a={5,1,4,3,3};
		int size=1000;
		Integer[] a=new Integer[size];
		Sort.setArrayValue(a);
		 Sort.threeCutQuickSort(a, 0, size-1);
		 if(Sort.isSorted(a))
		 Sort.show(a);
		 
		 int N = 100;
		 double preInsertTime = 1.0;
			double preSelectTime = 1.0;
			double preShellTime = 1.0;
			double preMergeTime = 1.0;
			double preQucikTime = 1.0;
			double preSystemTime = 1.0;
			double preQuickTime3 = 1.0;
			
			
			
//		while (true)
//		{
//		
//
//			double insertTime = Sort.useTime("Insert", N);
//			double selectTime = Sort.useTime("Select", N);
//			double shellTime = Sort.useTime("Shell", N);
//			double mergeTime = Sort.useTime("Merge", N);
//			double quickTime = Sort.useTime("Quick", N);
//			double systemTime = Sort.useTime("System", N);
//			double quickTime3=Sort.useTime("3Quick", N);
//			System.out.println("Scale:" + N +
//					// "\tInsert:" + insertTime + "\tRate:"
//					// + String.format("%.3f", insertTime / preInsertTime) +
//					// "\tSelect:" + selectTime + "\tRate:"
//					// + String.format("%.3f", selectTime / preSelectTime) +
//			"\tShell:" + shellTime + "\tRate:" + String.format("%.3f", shellTime / preShellTime) + "\tMerge:"
//					+ mergeTime + "\tRate:" + String.format("%.3f", mergeTime / preMergeTime) + "\tQuick:" + quickTime
//					+ "\tRate:" + String.format("%.3f", quickTime / preQucikTime) + "\tSystem:" + systemTime + "\tRate:"
//					+ String.format("%.3f", systemTime / preSystemTime)+ "\tThreeQucik:" + quickTime3 + "\tRate:"
//							+ String.format("%.3f", quickTime3 / preQuickTime3));
//			preInsertTime = insertTime;
//			preSelectTime = selectTime;
//			preShellTime = shellTime;
//			preMergeTime = mergeTime;
//			preQucikTime = quickTime;
//			preSystemTime = systemTime;
//			preQuickTime3=quickTime3;
//			N *= 2;
//		}

	}

}
