package data;

import java.util.Scanner;

/**
 * 计时器
 * @author Administrator
 *
 */
public class StopWatch
{
	private final long startime;
	public StopWatch()
	{
		startime=System.currentTimeMillis();
	}
	/**
	 * 返回对象创建以来经过的时间
	 * @return
	 */
	public double elapsedTime()
	{
		long now=System.currentTimeMillis();
		return (now-startime)/1000.0;
	}
	
	
	
	
	
}
