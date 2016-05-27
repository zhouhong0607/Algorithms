package introductiontoalgorithms;



/**
 * 红黑树练习
 * 
 * @author Administrator
 *
 */
public class BrBinaryTree
{
	BrTreeNode root;// 根节点
	static BrTreeNode nil;// 哨兵

	public static void main(String[] args)
	{
		int[] array = { 3, 7, 10, 12, 14, 15, 16, 17, 19, 20, 21, 23, 26, 28, 30, 38, 35, 39, 41, 47 };// 关键字结合
		// int[] array={3,7,10,12};//关键字结合
		BrBinaryTree brBinaryTree = new BrBinaryTree();
		for (int i = 0; i < array.length; i++)
		{
			brBinaryTree.insertToBrTree(new BrTreeNode(array[i]));
		}

		System.out.println(brBinaryTree.root.right.right.key);
		System.out.println(brBinaryTree.root.right.right.color);

	}

	public BrBinaryTree()
	{
		nil = new BrTreeNode();// 哨兵节点是黑色
		nil.color = BrTreeNode.BLACK;
		root = nil;

	}

	/**
	 * 红黑节点类
	 * 
	 * @author Administrator
	 *
	 */
	public static class BrTreeNode
	{
		public static final boolean RED = false;
		public static final boolean BLACK = true;
		int key;
		BrTreeNode parent;
		BrTreeNode left;
		BrTreeNode right;
		boolean color;

		public BrTreeNode()
		{

		}

		public BrTreeNode(int k)
		{
			key = k;

		}

	}

	/**
	 * 左旋
	 * 
	 * @param brBinaryTree
	 * @param x
	 */
	private static void leftRotate(BrBinaryTree brBinaryTree, BrTreeNode x)
	{
		if (x != brBinaryTree.nil && x.right != brBinaryTree.nil)// x存在且有右孩子
		{
			BrTreeNode y = x.right;
			x.right = y.left;
			if (y.left != brBinaryTree.nil)// y的左孩子存在
			{
				y.left.parent = x;
			}

			y.parent = x.parent;
			if (x.parent == brBinaryTree.nil)
			{
				brBinaryTree.root = y;
			} else if (x == x.parent.left)
			{
				x.parent.left = y;
			} else
			{
				x.parent.right = y;
			}
			y.left = x;
			x.parent = y;

		}
	}

	/**
	 * 右旋
	 * 
	 * @param brBinaryTree
	 * @param x
	 */
	private static void rightRotate(BrBinaryTree brBinaryTree, BrTreeNode x)
	{
		if (x != brBinaryTree.nil && x.left != brBinaryTree.nil)
		{
			BrTreeNode y = x.left;
			x.left = y.right;
			if (y.right != brBinaryTree.nil)
			{
				y.right.parent = x;
			}
			y.parent = x.parent;
			if (x.parent == brBinaryTree.nil)
			{
				brBinaryTree.root = y;
			} else if (x == x.parent.left)
			{
				x.parent.left = y;
			} else
			{
				x.parent.right = y;
			}
			y.right = x;
			x.parent = y;
		}
	}

	/**
	 * 插入一个节点到红黑树中
	 * 
	 * @param z
	 */
	public void insertToBrTree(BrTreeNode z)
	{
		if (z != null)
		{
			BrTreeNode y = this.nil;
			BrTreeNode x = this.root;
			while (x != this.nil)
			{
				y = x;
				if (z.key < x.key)
				{
					x = x.left;
				} else
				{
					x = x.right;
				}
			}
			z.parent = y;
			if (y == this.nil)// 树为空
			{
				this.root = z;
			} else if (z.key < y.key)// 插入到y的左面
			{
				y.left = z;
			} else // 插入到y的右面
			{
				y.right = z;
			}
			// 对新 插入的节点进行属性设置
			z.color = BrTreeNode.RED;
			z.left = this.nil;
			z.right = this.nil;

			RbInsertFixup(this, z);
		}
	}

	/**
	 * 插入后维护红黑树性质
	 * 
	 * @param brBinaryTree
	 * @param z
	 */
	private static void RbInsertFixup(BrBinaryTree brBinaryTree, BrTreeNode z)// z插入后，维护红黑树
	{

		while (z.parent.color == BrTreeNode.RED)// z的父节点是红色才修复
		{
			if (z.parent == z.parent.parent.left)// 如果z的父节点是祖父的左孩子
			{
				BrTreeNode y = z.parent.parent.right;// y是z的叔节点（可能是nil）
				if (y.color == BrTreeNode.RED)// 叔节点是红色（一定不是nil）
				{
					z.parent.color = BrTreeNode.BLACK;// 父，叔设黑，祖父设红，z指向祖父节点
					y.color = BrTreeNode.BLACK;
					z.parent.parent.color = BrTreeNode.RED;
					z = z.parent.parent;
				} else
				{
					if (z == z.parent.right)// 叔节点是黑色（可能是nil）,z是右孩子
											// ,注意在这部分没有对y（叔节点）赋值

					{
						z = z.parent;
						leftRotate(brBinaryTree, z);// 左旋，成为左孩子
					}
					z.parent.color = BrTreeNode.BLACK;// 父设黑，祖父设红
					z.parent.parent.color = BrTreeNode.RED;
					rightRotate(brBinaryTree, z.parent.parent);// 以爷节点右旋

				}

			} else// z的父节点是祖父的右孩子
			{
				BrTreeNode y = z.parent.parent.left;// 叔节点为左孩子
				if (y.color == BrTreeNode.RED)
				{
					z.parent.color = BrTreeNode.BLACK;
					y.color = BrTreeNode.BLACK;
					z.parent.parent.color = BrTreeNode.RED;
					z = z.parent.parent;
				} else
				{
					if (z == z.parent.right)
					{
						z = z.parent;
						leftRotate(brBinaryTree, z);
					}

					z.parent.color = BrTreeNode.BLACK;
					z.parent.parent.color = BrTreeNode.RED;
					rightRotate(brBinaryTree, z.parent.parent);

				}
			}

		}
		brBinaryTree.root.color = BrTreeNode.BLACK;// 根节点是黑色
	}
	
	/**
	 * 查找二叉树中关键字最小节点
	 * 
	 * @param node
	 * @return
	 */
	private static  BrTreeNode minimumOfTree(BrBinaryTree brBinaryTree,BrTreeNode node)
	{
		if (node !=brBinaryTree.nil )
		{
			while (node.left != brBinaryTree.nil)
			{
				node = node.left;
			}

		}
		return node;
	}
	

	/**
	 * 移植
	 * 
	 * @param brBinaryTree
	 * @param u
	 * @param v
	 */
	private static void RbTransplant(BrBinaryTree brBinaryTree, BrTreeNode u, BrTreeNode v)
	{
		if (u.parent == brBinaryTree.nil)
		{
			brBinaryTree.root = v;
		} else if (u == u.parent.left)
		{
			u.parent.left = v;
		} else
		{
			u.parent.right = v;
		}
		v.parent = u.parent;
	}

	/**
	 * 删除节点z
	 * 
	 * @param z
	 */
	public void RbDelete(BrTreeNode z)
	{
		BrTreeNode y = z;
		BrTreeNode x;
		boolean yOriginalColor = y.color;
		if (z.left == this.nil)
		{
			 x = z.right;
			RbTransplant(this, z, z.right);
		} else if (z.right == this.nil)
		{
			 x=z.left;
			RbTransplant(this , z, z.left);
		}else
		{
			y=minimumOfTree(this, z.right);//y为z的后继
			yOriginalColor=y.color;
			 x=y.right;
			 
			 if(y.parent==z)//y是
			 {
				 x.parent=y;
			 }else
			 {
				 RbTransplant(this, y, y.right);
				 y.right=z.right;
				 y.right.parent=y;
			 }
			 RbTransplant(this, z, y);
			 y.left=z.left;
			 y.left.parent=y;
			 y.color=z.color;
			 
		}
		if(yOriginalColor==BrTreeNode.BLACK)
			RbDeleteFixup(this,x);
		
		

	}
	/**
	 * 维护删除后红黑树性质
	 * @param brBinaryTree
	 * @param x
	 */
	private static void RbDeleteFixup(BrBinaryTree brBinaryTree,BrTreeNode x)
	{
		while(x!=brBinaryTree.root&&x.color==BrTreeNode.BLACK)
		{
			if(x==x.parent.left)
			{
				BrTreeNode w=x.parent.right;
				if(w.color==BrTreeNode.RED)
				{
					w.color=BrTreeNode.BLACK;
					x.parent.color=BrTreeNode.RED;
					leftRotate(brBinaryTree, x.parent);
					w=x.parent.right;
				}
				if(w.left.color==BrTreeNode.BLACK&&w.right.color==BrTreeNode.BLACK)
				{
					w.color=BrTreeNode.RED;
					x=x.parent;
				}else if (w.right.color==BrTreeNode.BLACK)
				{
					w.left.color=BrTreeNode.BLACK;
					w.color=BrTreeNode.RED;
					rightRotate(brBinaryTree, w);
					w=x.parent.right;
				}
				
				w.color=x.parent.color;
				x.parent.color=BrTreeNode.BLACK;
				w.right.color=BrTreeNode.BLACK;
				leftRotate(brBinaryTree, x.parent);
				x=brBinaryTree.root;
			}else
			{
				
			}
			
			
		}
		x.color=BrTreeNode.BLACK;
	}
	
	

}
