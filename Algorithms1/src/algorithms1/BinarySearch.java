package algorithms1;

public class BinarySearch
{
	/**
	 * 二分法查找 !!!记得排序
	 * @param a
	 * @return
	 */
	public static int rank(int[] a,int key,int low,int high)
	{
		if(low>high)
			return -1;
		int mid=(low+high)/2;
		
		if(key==a[mid])
		{
			return mid;
		}else if (key<a[mid])
		{
			return rank(a, key, low, mid-1);
		}else
		{
			return rank(a, key, mid+1, high);
		}
		
	}
	
	public static void main(String[] args)
	{
		int[] a={0,4,5,7,9};
		int i=BinarySearch.rank(a, 2, 0, 4);
		System.out.println(i);
	}
}
