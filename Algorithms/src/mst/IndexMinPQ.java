package mst;

public class IndexMinPQ<Key extends Comparable<Key>>
{
	private Key[] keys;
	private Integer[] pq;// pq[i]=k ,k-> key
	private Integer[] qp;// qp[k]=i
	private int num;

	public IndexMinPQ(int capacity)
	{
		// TODO Auto-generated constructor stub
		keys = (Key[]) new Comparable[capacity + 1];
		pq = new Integer[capacity + 1];
		qp = new Integer[capacity + 1];
		for (int i = 0; i < capacity + 1; i++)
		{
			pq[i] = -1;
			qp[i] = -1;
		}
		num = 0;
	}

	private void swim(int n)
	{
		if (n == 1)
			return;
		if (less(n, n / 2))
		{
			exchange(n, n / 2);
			swim(n / 2);
		} else
		{
			return;
		}
	}

	private void sink(int n)
	{
		if (n > num / 2)
			return;

		int k = n;
		if (less(2 * n, k))
			k = 2 * n;
		if ((num > 2 * n) && less(2 * n + 1, k))
			k = 2 * n + 1;
		if (k == n)
			return;
		else
		{
			exchange(k, n);
			sink(k);
		}
	}

	private void exchange(int i, int j)
	{
		int c = qp[pq[i]];
		qp[pq[i]] = qp[pq[j]];
		qp[pq[j]] = c;

		c = pq[i];
		pq[i] = pq[j];
		pq[j] = c;
	}

	private boolean less(int i, int j)
	{
		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
	}

	/**
	 * 进入队列
	 * 
	 * @param key
	 */
	public void enqueue(Integer index, Key key)
	{
		num++;
		pq[num] = index;
		qp[index] = num;
		keys[index] = key;
		swim(num);
	}

	/**
	 * 出队列 返回最小值的Index
	 */
	public Integer dequeue()
	{
		Integer index = pq[1];
		exchange(1, num);
		keys[pq[num]] = null;
		qp[pq[num]] = -1;
		pq[num] = -1;
		num--;
		sink(1);
		return index;
	}

	public boolean contains(int index)
	{
		return keys[index] != null;
	}

	public boolean isEmpty()
	{
		return num == 0;
	}

	public void change(int index, Key key)
	{
		keys[index] = key;
		swim(qp[index]);
		sink(qp[index]);
	}

	/**
	 * 根据index返回相应的Key
	 * 
	 * @param index
	 * @return
	 */

}
