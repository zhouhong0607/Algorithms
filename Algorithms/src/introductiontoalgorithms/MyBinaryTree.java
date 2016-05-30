package introductiontoalgorithms;
//package algorithms;
//
//import java.util.LinkedList;
//import java.util.NoSuchElementException;
//import java.util.Queue;
//import java.util.Stack;
//
///**
// * 网上代码
// * 
// * @author Administrator
// *
// * @param <T>
// */
//
//public class MyBinaryTree<T extends Comparable<? super T>>
//{
//	private TreeNode<T> root;
//	private int size;
//	// 队列用于层序遍历时压入和弹出节点
//	private Queue q = new LinkedList<TreeNode<T>>();
//
//	public MyBinaryTree()
//	{
//		root = null;
//	}
//
//	public MyBinaryTree(TreeNode<T> root)
//	{
//		this.root = root;
//		size = 1;
//	}
//
//	// 插入一个节点，如果该节点的值在树中已经存在，那么就返回false,否则返回true
//	public boolean insert(T e)
//	{
//		if (root == null)
//		{
//			root = new TreeNode<T>(null, e, null);
//			size = 1;
//			return true;
//		}
//		if (e == null)
//			throw new NullPointerException();
//		Comparable<? super T> nodeValue = (Comparable<? super T>) e;
//		TreeNode<T> t = root;
//		TreeNode<T> parent = null;
//		int compareResult = 0;
//		while (t != null)
//		{
//			parent = t;
//			compareResult = e.compareTo(t.getValue());
//			if (compareResult > 0)
//				t = t.getRight();
//			else if (compareResult < 0)
//				t = t.getLeft();
//			else
//				return false;
//		}
//		/* 或者也能如下写法 */
//		/*
//		 * do { parent = t; compareResult = e.compareTo(t.getValue());
//		 * if(compareResult>0) t = t.getRight(); else if(compareResult<0) t =
//		 * t.getLeft(); else return false; } while(t!=null);
//		 */
//		TreeNode<T> node = new TreeNode<T>(null, e, null);
//		if (compareResult > 0)
//			parent.setRight(node);
//		else
//			parent.setLeft(node);
//		size++;
//		return true;
//	}
//
//	private boolean isEmpty()
//	{
//		return size == 0;
//	}
//
//	public boolean delete(T value)
//	{
//		if (root == null)
//			throw new RuntimeException("Tree is empty");
//		TreeNode<T> node = getNodeByValue(value);
//		if (node == null)
//			throw new NoSuchElementException();
//		size--;
//		TreeNode<T> parent = getParent(value);
//		boolean isLeft = false;
//		if (parent != null)
//		{
//			if (parent.getLeft() == node)
//			{
//				isLeft = true;
//			}
//		}
//		if (node.getLeft() == null && node.getRight() == null)
//		{
//			if (node == root)
//				root = null;
//			else
//			{
//				node = null;
//				if (isLeft)
//					parent.setLeft(null);
//				else
//					parent.setRight(null);
//			}
//			return true;
//		}
//		if (node.getLeft() != null && node.getRight() == null)
//		{
//			if (node == root)
//				root = root.getLeft();
//			else
//			{
//				parent.setLeft(node.getLeft());
//				node = null;
//			}
//			return true;
//		}
//		if (node.getLeft() == null && node.getRight() != null)
//		{
//			if (node == root)
//				root = root.getRight();
//			parent.setRight(node.getRight());
//			node = null;
//			return true;
//		}
//		if (node.getLeft() != null && node.getRight() != null)
//		{
//			TreeNode<T> successor = getSuccessor(node);
//			// connect parent of current to successor insteed
//			if (node == root)
//				root = successor;
//			else if (isLeft)
//				parent.setLeft(successor);
//			else
//				parent.setRight(successor);
//			// connect successor to current's left child
//			successor.setLeft(node.getLeft());
//			return true;
//		}
//		return false;
//	}
//
//	private TreeNode<T> getSuccessor(TreeNode<T> delNode)
//	{
//		TreeNode<T> successorParent = delNode;
//		TreeNode<T> successor = delNode;
//		TreeNode<T> current = delNode.getRight(); // go to right child
//		while (current != null) // until no more left children
//		{
//			successorParent = successor;
//			successor = current;
//			current = current.getLeft(); // go to left child
//		}
//		if (successor != delNode.getRight()) // if successor not right child ,
//												// make connections
//		{
//			successorParent.setLeft(successor.getRight());
//			successor.setRight(delNode.getRight());
//		}
//		return successor;
//	}
//
//	// 获取节点值最小的节点
//	public TreeNode<T> getMin()
//	{
//		if (root == null)
//			throw new RuntimeException("Tree is empty");
//		TreeNode<T> t = root;
//		while ((t.getLeft()) != null)
//			t = t.getLeft();
//		return t;
//	}
//
//	// 获取节点值最大的节点
//	public TreeNode<T> getMax()
//	{
//		if (root == null)
//			throw new RuntimeException("Tree is empty");
//		TreeNode<T> t = root;
//		while (t.getRight() != null)
//			t = t.getRight();
//		return t;
//	}
//
//	// 找到节点值所对应的节点
//	public TreeNode<T> getNodeByValue(T value)
//	{
//		if (isEmpty())
//			throw new RuntimeException("the tree is empty");
//		if (value == null)
//		{
//			System.out.println("value is null");
//			throw new NullPointerException();
//		}
//		TreeNode<T> t = root;
//		int comp = 0;
//		Comparable<? super T> e = (Comparable<? super T>) value;
//		while (t != null)
//		{
//			comp = e.compareTo(t.getValue());
//			if (comp > 0)
//				t = t.getRight();
//			if (comp < 0)
//				t = t.getLeft();
//			else
//				return t;
//		}
//		return null;
//	}
//
//	// 根据值找到该值所对应的父亲节点
//	public TreeNode<T> getParent(T value)
//	{
//		if (isEmpty())
//			throw new RuntimeException("Tree is empty");
//		if (value == null)
//			throw new NullPointerException();
//		// 从根节点开始，根节点的父亲节点是null
//		TreeNode<T> parent = null;
//		TreeNode<T> t = root;
//		Comparable<? super T> e = (Comparable<? super T>) value;
//		int comp = 0;
//		while (t != null && (comp = e.compareTo(t.getValue())) != 0)
//		{
//			parent = t;
//			if (comp > 0)
//				t = t.getRight();
//			else
//				t = t.getLeft();
//			if (t == null)
//			{
//				parent = null;
//			}
//		}
//		return parent;
//	}
//
//	public void preOrder(TreeNode<T> root)
//	{
//		if (root == null)
//			throw new RuntimeException("empty tree");
//		System.out.println(root.getValue());
//		if (root.getLeft() != null)
//			preOrder(root.getLeft());
//		if (root.getRight() != null)
//			preOrder(root.getRight());
//	}
//
//	// 前序遍历非递归实现
//	public void preOrderNoRecursive(TreeNode<T> root)
//	{
//		Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
//		while (root != null || !stack.isEmpty())
//		{
//			while (root != null)
//			{
//				System.out.println(root.getValue());
//				stack.push(root);
//				root = root.getLeft();
//			}
//			if (!stack.isEmpty())
//			{
//				root = stack.pop();
//				root = root.getRight();
//			}
//		}
//	}
//
//	public void inOrder(TreeNode<T> root)
//	{
//		if (root.getLeft() != null)
//			inOrder(root.getLeft());
//		System.out.println(root.getValue());
//		if (root.getRight() != null)
//			inOrder(root.getRight());
//	}
//
//	// 中序遍历非递归实现
//	public void inOrderNoRecursive(TreeNode<T> root)
//	{
//		Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
//		while (root != null || !stack.isEmpty())
//		{
//			while (root != null)
//			{
//				stack.push(root);
//				root = root.getLeft();
//			}
//			if (!stack.isEmpty())
//			{
//				root = stack.pop();
//				System.out.println(root.getValue());
//				root = root.getRight();
//			}
//		}
//	}
//
//	public void PostOrder(TreeNode<T> root)
//	{
//		if (root.getLeft() != null)
//			inOrder(root.getLeft());
//		if (root.getRight() != null)
//			inOrder(root.getRight());
//		System.out.println(root.getValue());
//	}
//
//	public void levelOrder(TreeNode<T> root)
//	{
//		if (root == null)
//			throw new RuntimeException("empty tree");
//		// 先把根节点存入队列
//		q.add(root);
//		// 只要队列不空，就出队，访问，然后将该出队的节点的左右非空孩子节点放入队列中
//		while (!q.isEmpty())
//		{
//			TreeNode<T> node = (TreeNode<T>) q.poll();
//			System.out.println(node.getValue());
//			if (node.getLeft() != null)
//				q.add(node.getLeft());
//			if (node.getRight() != null)
//				q.add(node.getRight());
//		}
//	}
//
//	public TreeNode<T> getRoot()
//	{
//		return root;
//	}
//
//	public static void main(String[] args)
//	{
//		MyBinaryTree<String> tree = new MyBinaryTree<String>();
//		tree.insert("haha");
//		tree.insert("hehe");
//		tree.insert("abab");
//		/*
//		 * tree.insert("lala"); tree.insert("zzad");
//		 */
//		TreeNode<String> root = tree.getRoot();
//		tree.inOrder(tree.getRoot());
//		tree.inOrderNoRecursive(tree.getRoot());
//		// tree.PostOrder(root);
//		// tree.inOrder(root);
//	}
//}