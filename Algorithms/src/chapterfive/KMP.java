package chapterfive;

public class KMP
{
	private String pat;
	private int[][] dfa;//有限状态自动机
	//构造函数   构造 状态机
	public KMP(String pattern)
	{
		// TODO Auto-generated constructor stub
		this.pat=pattern;
		int M=pat.length();
		int R=256;
		dfa=new int[R][M];
		dfa[pat.charAt(0)][0]=1;
		for(int X=0,j=1;j<M;j++)
		{//计算dfa[][]
			for(int c=0;c<R;c++)
			{
				dfa[c][j]=dfa[c][X];//更新匹配失败情况下的值
			}
			dfa[pat.charAt(j)][j]=j+1;//设置匹配成功情况下的值j+1
			X=dfa[pat.charAt(j)][X];//更新重启状态
		}
		
		
	}
	public int search(String txt)
	{
		int i,j,N=txt.length(),M=pat.length();
		for( i=0,j=0;i<N&&j<M;i++)
		{
			if(txt.charAt(i)==pat.charAt(j)) j++;
			else
			{
				j=dfa[txt.charAt(i)][j];
			}
		}
		if(j==M) return i-M;
		else return N;
	}
}
