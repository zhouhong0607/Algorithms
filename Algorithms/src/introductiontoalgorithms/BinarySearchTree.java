package introductiontoalgorithms;

import java.awt.RenderingHints.Key;

// 注   -1 为Null
public class BinarySearchTree
{
	private TreeNode root;

	public BinarySearchTree()
	{
		root = null;
	}
//       6
//	   5    7
//  2	 5	   8
	public static void main(String[] args)
	{
		int[] array={2,5,7,6,5,8};//关键字结合
		BinarySearchTree binarySearchTree=new BinarySearchTree();//构建一颗搜索二叉树
		for(int i=0;i<array.length;i++)//通过关键字构建树节点插入到搜索二叉树中
		{
			TreeNode newNode=new TreeNode(array[i]);
			binarySearchTree.InsertToTree(newNode);
		}
//		binarySearchTree.deleteFromTree(binarySearchTree.root.left.right);
//		binarySearchTree.preorderTreeWalk(binarySearchTree.root);       
		System.out.println(binarySearchTree.root.left.key); 
//		System.out.println(binarySearchTree.maximumOfTree(binarySearchTree.root).key);
	}																														
	private static class  TreeNode
	{
		int key;// 关键字

		TreeNode parent;// 父节点
		TreeNode left;// 左孩子
		TreeNode right;// 右孩子

		public  TreeNode(int k)
		{
			key = k;
			parent = null;
			left = null;
			right = null;
		}
	}

	/**
	 * 递归方式搜索关键字为value的节点
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	public static TreeNode treeSearch(TreeNode node, int value)
	{
		if (node == null || node.key == value)
		{
			return node;
		}

		if (value < node.key)
		{
			return treeSearch(node.left, value);
		} else
		{
			return treeSearch(node.right, value);
		}

	}

	/**
	 * 迭代方式搜索关键字value
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	public static TreeNode iterativeTreeSearch(TreeNode node, int value)
	{

		while (node != null && node.key != value)
		{
			if (value < node.key)
			{
				node = node.left;
			} else
			{
				node = node.right;
			}

		}
		return node;
	}

	/**
	 * 查找二叉树中关键字最小节点
	 * 
	 * @param node
	 * @return
	 */
	public static TreeNode minimumOfTree(TreeNode node)
	{
		if (node != null)
		{
			while (node.left != null)
			{
				node = node.left;
			}

		}
		return node;
	}

	/**
	 * 返回最大关键字的节点
	 * 
	 * @param node
	 * @return
	 */
	public static TreeNode maximumOfTree(TreeNode node)
	{
		if (node != null)
		{
			while (node.right != null)
			{
				node = node.right;
			}
		}
		return node;
	}

	/**
	 * 前序遍历的递归实现
	 * 
	 * @param node
	 */
	public static void preorderTreeWalk(TreeNode node)
	{
		if (node != null)
		{
			System.out.println(node.key);
			preorderTreeWalk(node.left);
			preorderTreeWalk(node.right);
		}

	}

	/**
	 * 中序遍历的递归实现
	 * 
	 * @param node
	 */
	public static void inorderTreeWalk(TreeNode node)
	{
		if (node != null)
		{
			inorderTreeWalk(node.left);
			System.out.println(node.key);
			inorderTreeWalk(node.right);
		}
	}

	/**
	 * 后序遍历的递归实现
	 * 
	 * @param node
	 */
	public static void postorderTreeWalk(TreeNode node)
	{
		if (node != null)
		{
			postorderTreeWalk(node.left);
			postorderTreeWalk(node.right);
			System.out.println(node.key);
		}
	}

	/**
	 * 返回node节点的前驱(<=key的第一个)
	 * 
	 * @param node
	 * @return
	 */                                      
	public static TreeNode predecessorOfTree(TreeNode node)
	{
		if (node != null)
		{
			if (node.left != null)// 孩子方向找前驱
			{
				return maximumOfTree(node.left);
			}
			TreeNode assiNode = node.parent;
			while (assiNode != null && assiNode.left == node)// 找一个左上方向的父节点,直到根节点都没有左上方向的节点则返回根节点的parent=null,否则返回第一个左上节点
			{
				node = assiNode;
				assiNode = node.parent;
			}
			return assiNode;
		}
		return node;
	}

	/**
	 * 节点node的后继(>=key的第一个)
	 * 
	 * @param node
	 * @return
	 */
	public static TreeNode successorOfTree(TreeNode node)
	{
		if (node != null)
		{
			if (node.right != null)
			{
				return minimumOfTree(node.right);
			}
			TreeNode assiNode = node.parent;
			while (assiNode != null && assiNode.right == node)
			{
				node = assiNode;
				assiNode = node.parent;
			}
			return assiNode;

		}
		return node;
	}

	/**
	 * 插入一个节点到树中
	 * 
	 * @param node
	 */
	public void InsertToTree(TreeNode node)
	{
		if (root == null)// 空树
		{
			root = node;
			node.parent = null;
			return;
		}

		TreeNode assiNode = root;
		TreeNode keepNode=null;//需要一个保留非空的
		while (assiNode != null)
		{
			keepNode=assiNode;
			if (node.key < assiNode.key)
			{
				assiNode = assiNode.left;
			} else
			{
				assiNode = assiNode.right;
			}
		}
		node.parent = keepNode;
		if (node.key < keepNode.key)// 插入左面
		{
			keepNode.left = node;

		} else // 插入右面
		{
			keepNode.right = node;

		}
	}

	/**
	 * 对二叉树binaryTree的 newNode的子树替换oldNode成为oldNode双亲的新孩子
	 * 
	 * @param oldNode
	 * @param newNode
	 */
	public static void transplant(BinarySearchTree binaryTree, TreeNode oldNode, TreeNode newNode)
	{
		// 建立和父亲的关系
		if (oldNode.parent == null)// 根节点的情况
		{
			binaryTree.root = newNode;
		}else if (oldNode == oldNode.parent.left)
		{
			oldNode.parent.left = newNode;
		}else
		{
			oldNode.parent.right = newNode;
		}
		
		if (newNode != null)// 新节点可以为空，新节点为空情况可视为清除以oldNode为节点的二叉树
		{
			newNode.parent = oldNode.parent;
		}
	}

	/**
	 * 删除节点 分为三中情况 1.node是叶子节点 2.node只有一个孩子节点 3.node有两个孩子节点
	 * 
	 * @param node
	 */
	public void deleteFromTree(TreeNode node)
	{
		if (node.left == null)// 如果node的左子树为空，则用右子树替换(包含node为叶子节点的情况)
		{
			transplant(this, node, node.right);
		} else if (node.right == null)// 只有左子树情况
		{
			transplant(this, node, node.left);
		} else // 左右都不为空的情况
		{
			TreeNode successor = successorOfTree(node);// 找到node 的后继
			if (successor == node.right)// 后继恰好是node 的右孩子
			{
				transplant(this, node, successor);// 后继替换node
			} else // 后继不是node的 右孩子 注意到 successor一定没有左子树，
			{
				transplant(this, successor, successor.right);
				successor.right = node.right;
				node.parent = successor;
				transplant(this, node, successor);
			}

			// 建立后继和node的左子树的联系
			successor.left = node.left;
			node.left.parent = successor;

		}

	}

}
