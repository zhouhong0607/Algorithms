package introductiontoalgorithms;

/**
 * 裴波那契堆
 * 
 * @author Administrator
 *
 */
public class FibHeap
{
	FibNode min;// 最小节点
	int num;// 节点数目
	FibNode nil;// 哨兵

	public static void main(String[] args) throws Exception
	{
		
	}

	static class FibNode
	{
		int key;
		int degree;
		boolean mark;
		FibNode parent;
		FibNode child;
		FibNode left;
		FibNode right;

		public FibNode()// 无参构造函数~哨兵
		{
			key = 10000;// +无穷
			left = this;
			right = this;
		}

		public FibNode(int k)
		{
			// TODO Auto-generated constructor stub
			key = k;
			left = this;
			right = this;
		}

	}

	public FibHeap()
	{
		// TODO Auto-generated constructor stub
		nil = new FibNode();
		min = nil;
		num = 0;
	}

	public void fibHeapInsert(FibNode x)
	{
		x.degree = 0;
		x.mark = false;
		x.parent = null;
		x.child = null;
		// 插入节点x
		x.right = this.min;
		x.left = this.min.left;
		x.right.left = x;
		x.left.right = x;
		this.min = x;
		if (x.key < this.min.key)
			this.min = x;

		this.num++;
	}

	public FibNode minimum()// 返回最小节点
	{
		return this.min;
	}

	/**
	 * 将两个裴波那契堆合并
	 * 
	 * @return
	 */
	public static FibHeap fibHeapUnion(FibHeap heap1, FibHeap heap2)
	{
		FibHeap heap = new FibHeap();
		
			
			FibNode heap1Nil, heap2Nil;// 两个堆的哨兵
			heap1Nil = heap1.min;
			heap2Nil = heap2.min;
			// 找到两个堆的哨兵，然后将两个链表合并，哨兵1成为新堆的哨兵
			while (heap1Nil != heap1.nil)
				heap1Nil = heap1Nil.left;
			while (heap2Nil != heap2.nil)
				heap2Nil = heap2Nil.left;
			// 链接 heap1的哨兵成为heap的新哨兵
			heap1Nil.right.left = heap2Nil.left;
			heap2Nil.left.right = heap1Nil.right;
			heap1Nil.right = heap2Nil.right;
			heap2Nil.right.left = heap1Nil;
			// 初始化heap参数
			heap.nil = heap1Nil;
			heap.min = heap2.min.key < heap1.min.key ? heap2.min : heap1.min;// heap1,2顺序不可以换
			heap.num = heap1.num + heap2.num; // 如果heap1,2同时为空则哨兵.key相等此时min应该指向heap1的哨兵
			
		
		return heap;
	}

}
