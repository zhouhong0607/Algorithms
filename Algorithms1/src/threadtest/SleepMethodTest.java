package threadtest;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


import javax.swing.JFrame;

public class SleepMethodTest extends JFrame
{
	private Thread t;
	private  static Color[] color={Color.BLACK,Color.BLUE,Color.CYAN,Color.GREEN,Color.ORANGE,Color.YELLOW,Color.RED,Color.PINK,Color.LIGHT_GRAY};
	private static final Random rand=new Random();//创建随即对象
	private static Color getC()
	{
		return color[rand.nextInt(color.length)];
	}
	public SleepMethodTest()
	{
		t=new Thread(new Runnable()
		{
			
			int x=30;
			int y=50;
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				while(true)
				{
					try
					{
						Thread.sleep(100);
					}catch(InterruptedException e)
					{
						e.printStackTrace();
					}
					Graphics graphics=getGraphics();
					graphics.setColor(getC());
					graphics.draw3DRect(x, y, 180, y++,false);
					if(y>=180)
					{
						y=50;
					}
				}
			}
		});
		t.start();
	}
	
	public static void main(String[] args)
	{
		init(new SleepMethodTest(), 200, 200);
	}
	
	public static void init(JFrame frame,int width,int height)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}
