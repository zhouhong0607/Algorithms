package algorithms2;

/**
 * 二叉最小堆
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class MinPQ<T extends Comparable<? super T>>
{
	T[] pq;
	int num;

	public MinPQ()
	{
		pq = (T[]) new Comparable[2];
		num = 0;
	}

	public MinPQ(int max)
	{
		// TODO Auto-generated constructor stub
		pq = (T[]) new Comparable[max];
		num = 0;
	}

	public MinPQ(T[] a)
	{
		pq=(T[]) new Comparable[a.length+1];
		
		
		for (int i = 0; i < a.length; i++)
			insert(a[i]);
		num = a.length;
	}

	public int size()
	{
		return num;
	}

	public boolean isEmpty()
	{
		return num == 0;
	}

	private boolean less(int i, int j)
	{
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exchange(int i, int j)
	{
		T c = pq[i];
		pq[i] = pq[j];
		pq[j] = c;
	}

	public T min()
	{
		return pq[1];
	}

	/**
	 * 上浮
	 * 
	 * @param k
	 */
	private void swim(int k)
	{
		while (k > 1 && less(k, k / 2))
		{
			exchange(k, k / 2);
			k=k/2;
		}
	}

	/**
	 * 下沉
	 * 
	 * @param k
	 */
	private void sink(int k)
	{
		while (k <= num / 2)
		{
			int j;
			j = less(2 * k, k) ? 2 * k : k;
			if (2 * k < num)// 存在右子树
				j = less(2 * k + 1, j) ? 2 * k + 1 : j;
			if (j == k)
				break;
			exchange(k, j);
			k = j;

		}
	}

	/**
	 * 插入到队列
	 * 
	 * @param a
	 */
	public void insert(T a)
	{
		pq[++num] = a;
		swim(num);
		if(num+1==pq.length)
			resize(pq.length*2);
	}

	/**
	 * 删除最小并返回
	 * 
	 * @return
	 */
	public T deleteMin()
	{
		T value = pq[1];
		exchange(1, num);
		pq[num--] = null;
		sink(1);
		if(num>0&&num+1<=pq.length/4)
			resize(pq.length/2);
		return value;
	}

	/**
	 * 重新设置数组长度
	 * 
	 * @param k
	 */
	private void resize(int k)
	{
		T[] assi = (T[]) new Comparable[k];
		for (int i = 1; i <= num; i++)
			assi[i] = pq[i];
		pq = assi;
	}

	public void show()
	{
		System.out.print("Size:" + num);
		System.out.print("\tArraySize:" + pq.length + "\t");
		for (int i = 1; i <= num; i++)
		{
			System.out.print(pq[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		Integer[] a=new Integer[10000];
		Sort.setArrayValue(a);
		MinPQ<Integer> minPQ=new MinPQ<>(a);
		minPQ.show();
		for(int i=0;i<3;i++)
			System.out.print(minPQ.deleteMin()+" ");
		System.out.println();
		minPQ.show();
	}

}
