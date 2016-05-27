package algorithms3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javax.security.auth.kerberos.KerberosTicket;

/**
 * 二叉搜索树
 * 
 * @author Administrator
 *
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value>
{
	Node root;

	/**
	 * 二叉树节点
	 * 
	 * @author Administrator
	 */
	private class Node
	{
		Key key;
		Value value;
		int num;// 节点计数器
		Node left;
		Node right;

		public Node(Key k, Value v, int n)
		{
			// TODO Auto-generated constructor stub
			key = k;
			value = v;
			num = n;
		}
	}

	/**
	 * 返回二叉树大小
	 * 
	 * @return
	 */
	public int size()
	{
		return size(root);
	}

	/**
	 * 返回节点x的大小
	 * 
	 * @param x
	 * @return
	 */
	private int size(Node x)
	{
		if (x == null)
		{
			return 0;
		} else
		{
			return x.num;
		}
	}

	/**
	 * 查找k的value
	 * 
	 * @param k
	 * @return
	 */
	public Value get(Key k)
	{
		return get(root, k);
	}

	private Value get(Node x, Key k)
	{
		if (x == null)
			return null;

		int cmp = k.compareTo(x.key);
		if (cmp < 0)
		{
			return get(x.left, k);
		} else if (cmp > 0)
		{
			return get(x.right, k);

		} else
		{
			return x.value;
		}
	}
/**
 * 插入或者修改一个节点
 * @param k
 * @param v
 */
	public void put(Key k, Value v)
	{
		root = put(root, k, v);
	}

	private Node put(Node x, Key k, Value v)
	{
		if (x == null)
			return new Node(k, v, 1);
		int cmp = k.compareTo(x.key);
		if (cmp < 0)
		{
			x.left = put(x.left, k, v);
		} else if (cmp > 0)
		{
			x.right = put(x.right, k, v);
		} else
		{
			x.value = v;
		}

		x.num = size(x.left) + size(x.right) + 1;
		return x;

	}
	/**
	 * 返回最小节点的关键值
	 * @return
	 */
	public Key min()
	{
		Node n= min(root);
		if(n==null) return null;
		return n.key;
	}
	private Node min(Node x)
	{
		if(x==null) return null;
		
		if(x.left==null)
			return x;
		else
			return min(x.left);
		
	}
	/**
	 * 返回最大key
	 * @return
	 */
	public Key max()
	{
		Node n= max(root);
		if(n==null) return null;
		return n.key;
	
	}
	private Node max(Node x)
	{
		if(x==null) return null;
		
		if(x.right==null)
			return x;
		else
			return max(x.right);
	}
	/**
	 * 返回k的前驱
	 * @param k
	 * @return
	 */
	public Key floor(Key k)
	{
		Node x=floor(root, k);
		if(x==null) return null;
		return x.key;
	}
	private Node floor(Node x,Key k)
	{
		if(x==null) return null;
		int cmp=k.compareTo(x.key);
		if(cmp==0) return x;
		if(cmp<0) return floor(x.left, k);
		Node n=floor(x.right, k);
		if(n!=null) return n;
		else return x;
	}
	/**
	 * 返回k的后继
	 * @param k
	 * @return
	 */
	public Key ceiling(Key k)
	{
		Node n=ceiling(root, k);
		if(n==null) return null;
		return n.key;
	}
	private Node ceiling(Node x,Key k)
	{
		if(x==null) return null;
		
		int cmp=k.compareTo(x.key);
		
		if(cmp==0) return x;
		if(cmp>0) return ceiling(x.right, k);
		
		Node n=ceiling(x.left, k);
		if(n==null) return x;
		return n;
	}
	/**
	 * 找到排名第i的键(有i个比它小的键)
	 * @param i
	 * @return
	 */
	public Key select(int i)
	{
		return select(root, i).key;
	}
	
	private Node select(Node x,int i)
	{
		if(x==null) return null;
		
		int t=size(x.left);//左子树个数
	
		if(t>i) return select(x.left, i);
		if(t<i) return select(x.right, i-t-1);
		return x;
	}
	/**
	 * 键值k的排名(比k小的个数)
	 * @param k
	 * @return
	 */
	public int rank(Key k)
	{
		return rank(root, k);
		
	}
	
	private int rank(Node x,Key k)
	{
		if(x==null) return 0;
		int cmp=k.compareTo(x.key);
		if(cmp<0) return rank(x.left, k);
		if(cmp>0) return size(x.left)+1+rank(x.right,k);
		return size(x.left);
	}
	/**
	 * 删除最小节点
	 */
	public void deleteMin()
	{
	root=	deleteMin(root);
	}
	private Node deleteMin(Node x)
	{
		if(x.left==null) return x.right;
		x.left=deleteMin(x.left);
		x.num=size(x.left)+size(x.right)+1;
		return x;
	}
	
//	public void deleteMax()
//	{
//		root=deleteMax(root);
//		
//	}
//	private Node deleteMax(Node x)
//	{
//		if(x.right==null) return x.left;
//		
//		x.right=deleteMax(x.right);
//		x.num=size(x.left)+size(x.right)+1;
//		return x;
//	}
	
	public void delete(Key k)
	{
		root=delete(root, k);
	}
	private Node delete(Node x,Key k)
	{
		if(x==null) return null;
		int cmp=k.compareTo(x.key);
		
		if(cmp<0) x.left=delete(x.left, k);
		else if(cmp>0) x.right=delete(x.right, k);
		else
		{
			if(x.left==null) return x.right;
			if(x.right==null) return x.left;
			
			Node n=x;
			x=min(x.right);//x的后继
			x.right=deleteMin(n.right);
			x.left=n.left;
		}
		x.num=size(x.left)+size(x.right)+1;
		return x;
	}
	
	public Iterable<Key> keySet()
	{
		return keySet(min(),max());
	}
	public Iterable<Key> keySet(Key low,Key high)
	{
		Stack<Key> queue=new Stack<>();
		keySet(root, queue, low, high);
		return queue;
		
//		Queue<Key> queue=new LinkedList<>();
//		keySet(root, queue, low, high);
//		return queue;
	}
	
	private void keySet(Node x,Stack<Key> queue,Key low,Key high)
	{
		if(x==null) return ;
		int cmpl=low.compareTo(x.key);
		int cmph=high.compareTo(x.key);
		
		if(cmpl<0) keySet(x.left,queue,low,high);
		if(cmpl<=0&&cmph>=0) queue.add(x.key);
		if(cmph>0) keySet(x.right,queue,low,high);
	}
	
}
