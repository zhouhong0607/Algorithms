package threadtest;

public class ThreadSafeTest implements Runnable
{
	int num=10;
	public void run()
	{
		while(true)
		{
			doit();
			
		}
	}
	synchronized void doit()
	{
		if(num>0)
		{
			try{
				Thread.sleep(100);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.println("tickets"+num--);
		}
	}
	
	
	public static void main(String[] args)
	{
		ThreadSafeTest t=new ThreadSafeTest();
		
		Thread threadA=new Thread(t);
		Thread threadB=new Thread(t);
		Thread threadC=new Thread(t);
		Thread threadD=new Thread(t);
		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
	}
}
