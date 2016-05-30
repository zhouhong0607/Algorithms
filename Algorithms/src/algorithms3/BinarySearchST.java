package algorithms3;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
/**
 * 二分查找有序符号表
 * @author Administrator
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable< Key>,Value>
{
	Key[] keys;
	Value[] values;
	int N;
	public BinarySearchST(int capacity)
	{
		// TODO Auto-generated constructor stub
		keys=(Key[]) new Comparable[capacity];
		values=(Value[]) new Object[capacity];
	}
//	private boolean less(int i,int j)
//	{
//		return keys[i].compareTo(keys[j])<0;
//	}
	private void resize(int capacity)
	{
		Key[] assiKeys=(Key[]) new Comparable[capacity];
		Value[] assiValues=(Value[]) new Object[capacity];
		
		for(int i=0;i<N;i++)
		{
			assiKeys[i]=keys[i];
			assiValues[i]=values[i];
		}
		keys=assiKeys;
		values=assiValues;
	}
	/**迭代实现
	 * k的rank值（k的数组下标，或者比k小的个数）
	 *
	 * @param k
	 * @return
	 */
	private int rank(Key k)
	{
		int low=0;
		int high=N-1;
		
		while(low<=high)
		{
			int mid=(low+high)/2;
			
			int com=k.compareTo(keys[mid]);//k与mid比较的结果
			
			if(com<0)//k less than mid
			{
				high=mid-1;
			}else if (com>0)
			{
				low=mid+1;
			}else
			{
				return mid;
			}
		}
		return low;
	}
	/**
	 * 递归实现rank
	 * @param k
	 * @param low
	 * @param high
	 * @return
	 */
	private int rankRecursion(Key k,int low,int high)
	{
		if(high<low)
			return low;
		
		int mid=(low+high)/2;
		int comp=k.compareTo(keys[mid]);
		if(comp<0)
		{
			return rankRecursion(k, low, mid-1);
		}else if (comp>0) {
			return rankRecursion(k, mid+1, high);
		}else
		{
			return mid;
		}
	}
	/**
	 * 返回k对应的value值
	 * @param k
	 * @return
	 */
	public Value get(Key k)
	{
		int i=rank(k);
		if(i<N&&k.compareTo(keys[i])==0)
		{
			return values[i];
		}
		return null;
	}
	/**
	 * 存在则修改value，不存在添加
	 * @param k
	 * @param v
	 */
	public void put(Key k,Value v)
	{
		int i=rank(k);
		if(i<N&&keys[i].compareTo(k)==0)//找到k，修改即可
		{
			values[i]=v;
		}else //没找到添加到新的
		{
			N++;
			//动态改变数组长度
			if(N==keys.length)
				resize(2*N);
			//把i及后面的值 后移一位
			for(int j=N-1;j>i;j--)
			{
				keys[j]=keys[j-1];
				values[j]=values[j-1];
				
			}
			//把新值插入i的位置
			keys[i]=k;
			values[i]=v;
			
		}
	}
	/**
	 * 返回所有元素的一个迭代（队列实现）
	 * @return
	 */
	public Iterable<Key> keySet()
	{
		List<Key> keylist=new ArrayList<>();
		for(Key k:keys)
		{
			keylist.add(k);
		}
		
		return keylist;
	}
	
}
