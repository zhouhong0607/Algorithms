package algorithms3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 链表实现顺序查找表（无序）
 * @author Administrator
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key,Value>
{
	private Node first;
	private  List<Key> keys=new ArrayList<>();
	/**
	 * 链表节点
	 * @author Administrator
	 *
	 */
	private  class Node
	{
		Key key;
		Value value;
		Node next;
		public Node(Key k,Value v,Node n)
		{
			// TODO Auto-generated constructor stub
			key=k;
			value=v;
			next=n;
		}
	}
	/**
	 * 通过key查找
	 * @param key
	 * @return
	 */
	public Value get(Key k)
	{
		Node n=first;
		while(n!=null)
		{
			if(n.key.equals(k))//找到
			{
				return n.value;
			}
			n=n.next;
		}
		return null;//未命中
	}
	
	public void put(Key k,Value v)
	{
		Node n=first;
		while(n!=null)
		{
			if(n.key.equals(k))
			{
				n.value=v;
				return;
			}
			n=n.next;
		}
		first=new Node(k, v, first);
		keys.add(k);
	}
	public void delete(Key k)
	{
		Node n=first;
		Node pre=null;
		while(n!=null)
		{
			if(k==n.key)
			{
				if(pre==null)
				{
					first=null;
				}else
				{
				pre.next=n.next;
				}
			}
			pre=n;
			n=n.next;
			
		}
	}
	
	
	
	public List<Key> keySet()
	{
		return keys;
	}
	
}
