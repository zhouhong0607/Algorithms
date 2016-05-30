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
// * @author Administrator
// *
// * @param <T>
// */
//
//
////在树中，节点对应的值具备可比较性或者在构造树时树本身具有比较性，这里假设插入书中的值具有比较性  
//class TreeNode<T extends Comparable<? super T>>
//{
//	private TreeNode<T> left;
//	private T value;
//	private TreeNode<T> right;
//
//	public TreeNode(T value)
//	{
//		this.value = value;
//		this.right = null;
//		this.left = null;
//	}
//
//	public TreeNode()
//	{
//	}
//
//	public TreeNode(TreeNode<T> left, T value, TreeNode<T> right)
//	{
//		this.left = left;
//		this.value = value;
//		this.right = right;
//	}
//
//	public TreeNode<T> getLeft()
//	{
//		return left;
//	}
//
//	public void setLeft(TreeNode<T> left)
//	{
//		this.left = left;
//	}
//
//	public T getValue()
//	{
//		return value;
//	}
//
//	public void setValue(T value)
//	{
//		this.value = value;
//	}
//
//	public TreeNode<T> getRight()
//	{
//		return right;
//	}
//
//	public void setRight(TreeNode<T> right)
//	{
//		this.right = right;
//	}
//
//}
//
