package chapterfive;

public class MSD
{
	private static int R=256;
	private static final int M=15;
	private static String[] aux;
	
	private static int charAt(String s,int d)
	{
		if(d<s.length())
		{
			return s.charAt(d);
		}else
		{
			return -1;
		}
	}
	public static void sort(String[] a)
	{
		int len=a.length;
		aux=new String[R];
		sort(a, 0, len-1, 0);
		
	}
	public static void sort(String[] a,int low,int high,int d)
	{
		if(high<=low+M)
		{
			insertionSort(a,low,high,d);
			return;
		}
		
		int[] count=new int[R+2];
		for(int i=low;i<=high;i++)
			count[charAt(a[i], d)+2]++;
		for(int i=0;i<R+1;i++)
			count[i+1]+=count[i];
		for(int i=low;i<=high;i++)
		{
			aux[count[charAt(a[i], d)+1]++]=a[i];
		}
		for(int i=0;i<R;i++)
		{
			a[i+low]=aux[i];
		}
		
		
		for(int i=low;i<high;i++)
		{
			sort(a, low+count[i], low+count[i+1]-1, d+1);
		}
		
		
	}
	private static boolean less(String v,String w,int d)
	{
		return v.substring(d).compareTo(w.substring(d))<0;
	}
	
	public static void insertionSort(String[] a,int low,int high,int d)
	{
		for(int i=low;i<=high;i++)
		{
			for(int j=i;j>low&&less(a[j], a[j-1], d);j--)
			{
				exchange(a, j, j-1);
			}
		}
	}
	
	private static void exchange(String[] a,int i,int j)
	{
		String s=a[i];
		a[i]=a[j];
		a[j]=s;
	}
}
