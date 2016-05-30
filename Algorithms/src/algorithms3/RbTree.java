package algorithms3;

import java.util.Stack;

public class RbTree<Key extends Comparable<Key>, Value>
{
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;
	public class Node
	{
		private	Key key;// 键
		private	Value value;// 值
		private	boolean color;// 父节点指向它链接的颜色
		private	Node left, right;// 左右孩子节点
		private	int num;// 节点计数器

		public Node(Key k, Value v, int n, boolean c)
		{
			// TODO Auto-generated constructor stub-
			key = k;
			value = v;
			num = n;
			color = c;
		}
	}

	/**
	 * 返回x的节点计数
	 * 
	 * @param x
	 * @return
	 */
	public int size()
	{
		return size(root);
	}
	private int size(Node x)
	{
//		if (x == null)
//			return 0;
//		return size(x.left) + size(x.right) + 1;
		if (x == null)
			return 0;
		return x.num;
	}

	private boolean isRed(Node x)
	{

		
		if (x == null)
			return false;
		return x.color == RED;
	}

	/**
	 * 左旋
	 * 
	 * @param h
	 * @return
	 */
	private Node leftRotate(Node h)
	{

		
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.num = h.num;
		h.num = size(h.left) + size(h.right) + 1;
		return x;
	}

	/**
	 * 右旋
	 * 
	 * @param h
	 * @return
	 */
	private Node rightRotate(Node h)
	{

		
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = x.right.color;
		x.right.color = RED;
		x.num = h.num;
		h.num = size(h.left) + size(h.right) + 1;
		return x;
	}
	private void flipColors(Node h)
	{
		h.color=RED;
		h.left.color=BLACK;
		h.right.color=BLACK;
		return;
	}
	/**
	 * 插入
	 * @param k
	 * @param v
	 */
	public void put(Key k,Value v)
	{
		root=put(root, k, v);
		root.color=BLACK;
	}
	
	private Node put(Node h,Key k,Value v)
	{

		
		if (h == null)
			return new Node(k, v,  1,RED);

		int cmp = k.compareTo(h.key);
		if (cmp < 0)
			h.left = put(h.left, k, v);
		else if (cmp > 0)
			h.right = put(h.right, k, v);
		else
			h.value = v;

		// fix-up any right-leaning links
		if (isRed(h.right) && !isRed(h.left))
			h = leftRotate(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rightRotate(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);
		h.num = size(h.left) + size(h.right) + 1;

		return h;
	}
	
	
	
	
	
	
	
	
	public Key min()
	{
		return min(root).key;
	}
	
	private Node min(Node x)
	{
		if(x==null) return null;
		
		if(x.left!=null) return min(x.left);
		else return x;
	}
	
	public Key max()
	{
		return max(root).key;
	}
	
	private Node max(Node x)
	{
		if(x==null) return null;
		
		if(x.right!=null) return max(x.right);
		else return x;
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
	
	
	/**
	 * 查找k的value
	 */
	public Value get(Key k)
	{
		return get(root, k);
	}
	
	private Value get(Node x,Key k)
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
	
	
	
}
