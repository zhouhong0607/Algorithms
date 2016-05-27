package threadtest;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class PriorityTest extends JFrame
{
	final JProgressBar progressBar = new JProgressBar();
	final JProgressBar progressBar2 = new JProgressBar();
	final JProgressBar progressBar3 = new JProgressBar();
	final JProgressBar progressBar4 = new JProgressBar();
	Thread threadA;
	Thread threadB;
	Thread threadC;
	Thread threadD;
	
	private final class MyThread implements Runnable
	{
		private final JProgressBar bar;
		int count = 0;

		private MyThread(JProgressBar bar)// 
		{
			this.bar = bar;
		}

		public void run()
		{
			while (true)
			{
				bar.setValue(count+=10);
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}

		}

	}

	public static void setPriority(String threadName, int priority, Thread t)
	{
		t.setPriority(priority);
		t.setName(threadName);
		t.start();
	}

	public PriorityTest()
	{
		super();
		getContentPane().add(progressBar, BorderLayout.NORTH);
		getContentPane().add(progressBar2, BorderLayout.WEST);
		getContentPane().add(progressBar3, BorderLayout.CENTER);
		getContentPane().add(progressBar4, BorderLayout.SOUTH);
		progressBar.setStringPainted(true);
		progressBar2.setStringPainted(true);
		progressBar3.setStringPainted(true);
		progressBar4.setStringPainted(true);
		
		 threadA = new Thread(new MyThread(progressBar));
		 threadB = new Thread(new MyThread(progressBar2));
		 threadC = new Thread(new MyThread(progressBar3));
		 threadD = new Thread(new MyThread(progressBar4));
		setPriority("A", 10, threadA);
		setPriority("B", 7, threadB);
		setPriority("C", 5, threadC);
		setPriority("D", 2, threadD);
	}
	
	public static void init(JFrame frame,int width,int height)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		init(new PriorityTest(), 500, 500);
	}
}
