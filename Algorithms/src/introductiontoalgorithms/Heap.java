package introductiontoalgorithms;

public class Heap
{
	private static int heapSize;//优先队列  使用

	public static void main(String[] args)
	{
		int[] a = { 16, 4, 10, 14, 7, 9, 3, 2, 8, 1 };

		// buildMaxHeap(a,a.length);
		// buildMinHeap(a,a.length);
		heapSize = a.length;
		heapSort(a);

		for (int i = 0; i < a.length; i++)
		{
			System.out.println(a[i]);
		}

	}

	private static int parent(int i) //返回父节点下标
	{
		return (i-1)/2;
	}
	
	private static int left(int i)//返回左孩子下标
	{
		return 2*i+1;
	}
	
	private static int right(int i)//返回右孩子下标
	{
		return 2*i+2;
	}
	
	/**
	 * *******在i处维护最大堆************
	 * 
	 * @param array
	 * @param i
	 * @param heapSize
	 */
	private static void maxHeapify(int[] array, int i, int heapSize)
	{
		if (heapSize > 0)
		{

			int left = left(i);// 左子树索引
			int right = right(i);// 右子树索引
			int largest = i;// 最大值的索引
			if ((left < heapSize) && (array[left] > array[largest]))
			{
				largest = left;
			}

			if ((right < heapSize) && (array[right] > array[largest]))
			{
				largest = right;
			}
			if (largest != i)
			{
				int c;
				c = array[i];
				array[i] = array[largest];
				array[largest] = c;
				maxHeapify(array, largest, heapSize);

			}
		}
	}

	/**
	 * 对一个数组前heapSize个元素进行最大堆处理
	 * 
	 * @param array
	 * @param heapSize
	 */
	private static void buildMaxHeap(int[] array, int heapSize) // 注意
																// heapSize最大为数组长
																// array.length
	{

		for (int i = heapSize / 2 - 1; i >= 0; i--) // 对非叶子节点进行最大堆处理
		{
			maxHeapify(array, i, heapSize);
		}

	}

	/**
	 * 在i处维护最小堆
	 * 
	 * @param array
	 * @param i
	 * @param heapSize
	 */
	private static void minHeapify(int[] array, int i, int heapSize)// 最小堆处理
	{
		if (heapSize > 0)
		{

			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int minimum = i;
			if (left < heapSize && array[left] < array[minimum])
			{
				minimum = left;
			}
			if (right < heapSize && array[right] < array[minimum])
			{
				minimum = right;
			}

			if (minimum != i)
			{
				int c;
				c = array[minimum];
				array[minimum] = array[i];
				array[i] = c;
				minHeapify(array, minimum, heapSize);
			}
		}
	}

	/**
	 * 对一个数组前heapSize个元素进行最小堆处理，
	 * 
	 * @param array
	 * @param heapSize
	 */
	private static void buildMinHeap(int[] array, int heapSize)
	{
		for (int i = heapSize - 1; i >= 0; i--)
		{
			minHeapify(array, i, heapSize);
		}
	}

	/**
	 * 堆排序
	 * 
	 * @param array
	 */
	private static void heapSort(int[] array)
	{
		for (int i = 0; i < array.length - 1; i++)// 如 数组长度3 只需要进行2次处理
		{
			// buildMaxHeap(array, array.length-i);//每次堆的长度减少i
			buildMinHeap(array, array.length - i);
			int c;
			c = array[array.length - 1 - i]; // 交换array[0]与array[array.length-i]//将最大堆处理后根节点（堆中最大值）放到后面
			array[array.length - 1 - i] = array[0];
			array[0] = c;

		}

	}

	/*************************** 优先队列 **************************/

	/**
	 * 返回堆中最大键值元素
	 * 
	 * @param array
	 * @return
	 */
	private static int heapMaximum(int[] array)// 返回具有最大键字的元素
	{
		if (heapSize > 0)
			return array[0];
		else
		{
			System.out.println("堆中无 剩余元素,返回-10000");// 假定-10000为负无穷
			return -10000;
		}

	}

	/**
	 * 返回堆中最大键值元素，并且删除这个节点
	 * 
	 * @param array
	 * @param heapSize
	 * @return
	 */
	private static int heapExtractMax(int[] array)
	{
		if (heapSize > 0)
		{
			int max = array[0];
			array[0] = array[heapSize - 1];
			heapSize -= 1;
			maxHeapify(array, 0, heapSize);
			return max;

		} else
		{

			System.out.println("堆中无 剩余元素,返回-10000,并且不执行删除操作");// 假定-10000为负无穷
			return -10000;
		}
	}
	/**
	 * 提升i位置上的值为新Key
	 * @param array
	 * @param i
	 * @param key
	 */
	private static void heapIncreaseKey(int[]array,int i,int key)
	{
		if(key>array[i])//key大再进行操作
		{
			array[i]=key;
			while(parent(i)>=0&&array[parent(i)]<array[i])//如果父节点小 则进行交换
			{
				int c;
				c=array[i];
				array[i]=array[parent(i)];
				array[parent(i)]=c;
			}
		}
		
	}
	/**
	 * 插入一个新的键值
	 * @param array
	 * @param key
	 */
	private static void maxHeapInsert(int[] array,int key)
	{
		
		array[heapSize]=-10000;//数组下标等于heapSize-1
		heapIncreaseKey(array, heapSize, key);
		heapSize++;
		
	}
	
	
	

}
