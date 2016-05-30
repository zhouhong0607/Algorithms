package introductiontoalgorithms;

/**
 * 
 * @author Administrator 2016/3/6 最大子数组算法测试 65535 2^16 2147483648 (2^32)/2
 */

public class SubMaximumArray
{
	public static void main(String[] args)
	{
		int[] a = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };
		int[] maxSubarray = new int[3];
//		maxSubarray = findMaximunSubarray(a, 0, a.length - 1);
		
		maxSubarray=findMaxSubarray(a);
		
		String sss = "";
		for (int i = maxSubarray[0]; i <= maxSubarray[1]; i++)
		{

			sss += a[i] + ",";
		}
		System.out.println(sss);
		System.out.println("左侧游标" + maxSubarray[0]);
		System.out.println("右侧游标" + maxSubarray[1]);
		System.out.println("最大子数组的和为" + maxSubarray[2]);

	}

	/******************* 分治策略算法， nlogn ********************/

	private static int[] findMaximunSubarray(int[] array, int low, int high)
	{
		int mid;

		int[] a = new int[3];

		int[] left = new int[3];
		int[] right = new int[3];
		int[] cross = new int[3];
		if (high == low)
		{
			a[0] = low;// 左侧下标
			a[1] = high;// 右侧下标
			a[2] = array[high];// 最大值
			return a;
		} else
		{
			mid = (low + high) / 2;
		}
		left = findMaximunSubarray(array, low, mid);
		right = findMaximunSubarray(array, mid + 1, high);
		cross = findMaxCrossSubarray(array, low, mid, high);

		if (left[2] >= right[2] && left[2] >= cross[2])
		{
			return left;
		} else if (right[2] >= left[2] && right[2] >= cross[2])
		{
			return right;
		}

		return cross;

	}

	/**
	 * 寻找跨越中点的最大子数组，先找左面最大子数组，再找右面最大子数组。
	 * 
	 * 
	 * @param array
	 * @param low
	 * @param mid
	 * @param high
	 * @return
	 */
	private static int[] findMaxCrossSubarray(int[] array, int low, int mid, int high)
	{
		int sum = 0;// 循环中数组和
		int leftSum = -1000;// 左侧最大和
		int rightSum = -1000;// 右侧做大和
		int leftIndex = mid;// 最大跨越子数组左侧下标
		int rightIndex = mid + 1;// 最大跨越子数组右侧下标
		int index;// 数组游标

		for (index = mid; index >= low; index--)// 寻找左侧最大子数组
		{
			sum += array[index];
			if (sum > leftSum)
			{
				leftSum = sum;
				leftIndex = index;
			}
		}

		sum = 0;
		for (index = mid + 1; index <= high; index++)// 寻找右侧最大子数组
		{
			sum += array[index];
			if (sum > rightSum)
			{
				rightSum = sum;
				rightIndex = index;
			}
		}

		int[] a = { leftIndex, rightIndex, leftSum + rightSum };

		return a;

	}
	/******************* 分治策略算法， nlogn ********************/

	
	
	/******************* 非递归算法， n ********
	 * 
	 * 已知a[1~j]的最大子数组,则a[1~(j+1)]的最大子数组为a[1~j]的最大子数组或者 a[i~（j+1）]其中一个数组   1《i《j+1
	 * 
	 * ************/
	
	private static int[] findMaxSubarray(int[] array)
	{
		int[] a=new int[3];

		int rightArrayLeftIndex;//右侧数组（a[i~j+1]）的最大和，左右下标
		int rightArrayRightIndex;
		int rightArrayMaxSum;
		
		a[0]=0;//a[0]为第一个左侧最大子数组 a[1~j]
		a[1]=0;
		a[2]=array[0];
		
		rightArrayLeftIndex=0;//a[0]也为右侧第一个最大子数组
		rightArrayRightIndex=0;
		rightArrayMaxSum=array[0];
		
		
		
		for(int i=1;i<array.length;i++)
		{
			//先计算右面
			if((rightArrayMaxSum+array[i])>=array[i])  //如果新数组值array[i]比前面的总和+array[i]小的话,则右侧新的最大子数组只需要向右移一位
			{
				rightArrayRightIndex=i;
				rightArrayMaxSum+=array[i];
			}else 									//否则，新的array[i]为最大子数组
			{
				rightArrayMaxSum=array[i];
				rightArrayLeftIndex=i;
				rightArrayRightIndex=i;
			}
			//判断左右两边 子数组大小
			if(rightArrayMaxSum>=a[2])   //如果右侧的子数组大  则换值成为新的左侧做大子数组a[1~j]
			{
				a[0]=rightArrayLeftIndex;
				a[1]=rightArrayRightIndex;
				a[2]=rightArrayMaxSum;
			}
			
			
			
		}
		
		
	
		
		return a;
		
		
		
	}
	

}
