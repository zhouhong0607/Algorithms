package chapterfive;

public class BoyerMoore
{
	private int[] right;
	private String pat;
	public BoyerMoore(String pattern)
	{
		// TODO Auto-generated constructor stub
		this.pat=pattern;
		int R=256;
		int M=pat.length();
		right=new int[R];
		for(int i=0;i<R;i++)
		right[i]=-1;
		for(int i=0;i<M;i++)
			right[pat.charAt(i)]=i;
	}
	
	public int search(String txt)
	{
		int skip;
		for(int i=0;i<=txt.length()-pat.length();i+=skip)
		{
			skip=0;
			for(int j=pat.length()-1;j>=0;j--)
			{
				if(pat.charAt(j)!=txt.charAt(i+j))
				{
					skip=j-right[txt.charAt(i+j)];
					if(skip<1) skip=1;
					break;
				}
			}
			if(skip==0) return i;
		}
		return txt.length();
	}
	
	
}
