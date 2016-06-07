package introductiontoalgorithms;

public class LCS
{
	int m, n;
	int[][] c;

	String s;

	public LCS(String s1, String s2)
	{
		s = s1;
		m = s1.length();
		n = s2.length();
		c = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++)
		{

			for (int j = 1; j <= n; j++)
			{
				if (s1.charAt(i - 1) == s2.charAt(j - 1))
				{
					c[i][j] = c[i - 1][j - 1] + 1;

				} else if (c[i - 1][j] >= c[i][j - 1])
				{
					c[i][j] = c[i - 1][j];

				} else
				{
					c[i][j] = c[i][j - 1];

				}

			}
		}
	}

	public int getLcsLength()
	{
		return this.c[m][n];
	}

	public String getLcs()
	{
		return getLcs(m, n);
	}

	private String getLcs(int i, int j)
	{
		if (i == 0 || j == 0)
			return "";
		int max = c[i - 1][j - 1];
		max = Math.max(c[i - 1][j], max);
		max = Math.max(max, c[i][j - 1]);
		if (max == c[i - 1][j - 1])
		{
			return getLcs(i - 1, j - 1) + s.charAt(i - 1);
		} else if (max == c[i - 1][j])
		{
			return getLcs(i - 1, j);
		} else
		{
			return getLcs(i, j - 1);
		}

	}

	public static void main(String[] args) throws Exception
	{
		String s1 = "ABCBDAB";
		String s2 = "BDCABA";
		LCS lcs = new LCS(s1, s2);
		System.out.println(lcs.getLcsLength());
		System.out.println(lcs.getLcs());

	}
}
