package chapterfive;

import java.math.BigInteger;
import java.util.Random;

/**
 * 指纹识别法（蒙特卡洛法，拉斯维加斯检验）
 * 
 * @author Administrator
 *
 */
public class RabinKarp
{
	private String pat;
	private long patHash;// 模版字符串的hash值
	private final int R = 256;// 字母表的大小
	private long Q;// 一个很0大的素数
	private int M;// 模版字符串的长度
	private long RM;// R^(M-1)%Q

	public RabinKarp(String pattern)
	{
		// TODO Auto-generated constructor stub
		this.pat = pattern;
		this.M = pattern.length();
		this.Q = longRandomPrime();
		this.RM = R ^ (M - 1) % Q;
		this.patHash = hash(pattern, M);

	}

	/**
	 * 计算key[0..M-1]的散列值 Horner 方法
	 * 
	 * @param key
	 * @param M
	 * @return
	 */
	private long hash(String key, int M)
	{

		long h = 0;
		for (int i = 0; i < M; i++)
		{
			h = (h * R + key.charAt(i)) % Q;
		}
		return h;
	}

	/**
	 * 随机31位 素数
	 * @return
	 */
	private  long longRandomPrime()
	{
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}

	/**
	 * 蒙特卡洛检验
	 * 
	 * @param i
	 * @return
	 */
	public boolean check(int i)
	{
		return true;
	}

	/**
	 * 拉斯维加斯检验
	 * 
	 * @param txt
	 * @param i
	 * @return
	 */
	private boolean check(String txt, int i)
	{
		for (int j = 0; j < M; j++)
			if (pat.charAt(j) != txt.charAt(i + j))
				return false;
		return true;
	}

	public int search(String txt)
	{
		int N = txt.length();
		long txtHash = hash(txt, M);
		if (txtHash == patHash && check(0))
			return 0;
		for (int i = M; i < N; i++)
		{
			txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;
			if (txtHash == patHash && check(i - M + 1))
				return i - M + 1;

		}
		return N;
	}

}
