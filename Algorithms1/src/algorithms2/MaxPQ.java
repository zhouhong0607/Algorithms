package algorithms2;

import java.util.Comparator;

/**
 * 最大（优先队列）
 * @author Administrator
 *
 */
public class MaxPQ<T extends Comparable<? super T>> 
{
	private T[] pq;//优先队列 数组(二叉堆)
	private int num;//队列大小
	
	public MaxPQ()
	{
		pq=(T[])new Comparable[2];

		num=0;
	
	}
	public MaxPQ(int size)//固定长度大小
	{
		pq=(T[])new Comparable[size+1];
		num=0;
	
		
	}
	public MaxPQ(T[] a) //用a中元素 构建优先队列
	{
		pq=(T[])new Comparable[a.length+1];
		for(int i=0;i<a.length;i++)
		{
			insert(a[i]);//将a[i]插入到队列中
		}
		num=a.length;
		
	}
	/**
	 * 调整数组大小(调整为size大小)
	 * @param a
	 */
	private  void resize(int size)
	{
		T[] assi=(T[])new Comparable[size];
		for(int i=1;i<=num;i++)
		{
			assi[i]=pq[i];
		}
		pq=assi;
		
	}
	/**
	 * 将k位置元素上浮
	 * @param k
	 */
	private void swim(int k)
	{
		while(k>1&&less(pq[k/2], pq[k]))
		{
			exchange(k/2, k);
			k=k/2;
		}
	}
	/**
	 * 将k位置元素下沉
	 * @param k
	 */
	private void sink(int k)
	{
		while(2*k<=num)
		{
			int i=k;
			i=less(pq[k], pq[2*k])?2*k:k;
			if(2*k<num)//存在右孩子再判断
			i=less(pq[i], pq[2*k+1])?2*k+1:i;
			if(k==i)
				break;
			exchange(k, i);
			k=i;
			
		}
		
	}
	
	
	
	/**
	 * 比较a，b
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean less(T a,T b)
	{
		return a.compareTo(b)<0;
	}
	/**
	 * 交换i，j
	 * @param a
	 * @param b
	 */
	private   void exchange(int i,int j)
	{
		T c=pq[i];
		pq[i]=pq[j];
		pq[j]=c;
	}
	/**
	 * 返回最大元素
	 * @return
	 */
	public T getMax()
	{
			
			return pq[1];
		
	}
	/**
	 * 返回队列大小
	 * @return
	 */
	public int size()
	{
		return num;
	}
	/**
	 * 插入一个元素(考虑到数组变化)
	 * @param a
	 */
	public void insert(T a) 
	{
		
//			if(num+1<pq.length)
//			{
				pq[++num]=a;
				swim(num);
				if(num+1==pq.length)
					resize(2*pq.length);
				
				
//			}else
//			{
//				System.out.println("队列已满"); 
//			}

	}
	/**
	 * 删除最大元素,并返回
	 * @return
	 */
	public T deleteMax()
	{
	
		T a=null;
		if(num>0)
		{
			a=pq[1];
			exchange(1, num);//交换最大元素和最后一个元素的位置,num--表示删除
			pq[num--]=null;//防治对象游离
			sink(1);
			if(num>0&&num+1<=pq.length/4)
				resize(pq.length/2);
					
		}else
		{
			System.out.println("队列为空");
		}
		return a;
	}
	
	
	
	
	/**
	 * 打印队列
	 */
	public void show()
	{
		System.out.print("Num:"+size());
		System.out.print("\tArrayLength:"+pq.length);
		System.out.print("\tData:");
		int i=0;
		while(i!=num)
		{
			i++;
			System.out.print(pq[i]+" ");
			
			
		}
		System.out.println();
	}
	
	public static void heapSort(Comparable[]  a)
	{
		MaxPQ<Comparable<Comparable>> maxPQ=new MaxPQ<>(a);
		for(int i=a.length-1;i>=0;i--)
		{
			a[i]=maxPQ.deleteMax();
			
		}
		
	}
	
	public static void main(String[] args)
	{
		Integer[] a=new Integer[100];
		Sort.setArrayValue(a);
		Sort.show(a);
		heapSort(a);
		Sort.show(a);
		System.out.println(Sort.select(a, 3));
	
	}
	
}
