package algorithms3;

import java.util.HashMap;
import java.util.HashSet;

public class SparseVector
{
	private HashMap<Integer, Double> vec;
	
	public SparseVector()
	{
		// TODO Auto-generated constructor stub
		vec=new HashMap<>();
	
	}
	
	public void put(Integer i,Double d)
	{
		vec.put(i, d);
		
	}
	
	public Double get(Integer i)
	{
		Double d=vec.get(i);
		 return d!=null?d:0.0;
	}
	public double dot(double[] that)
	{
		double sum=0.0;
		for(Integer i:vec.keySet())
		{
			sum+=vec.get(i)*that[i];
		}
		return sum;
	}
	
	public static void main(String[] args)
	{
		
		
		
		
		SparseVector[] a=new SparseVector[5];
		double x[]={.05,.04,.36,.37,.19};
		double b[]=new double[5];
		for(int i=0;i<5;i++)
		{
			a[i]=new SparseVector();
		}
		a[0].put(1, .90);
		a[1].put(2, .36);
		a[1].put(3, .36);
		a[1].put(4, .18);
		a[2].put(3, .90);
		a[3].put(0, .90);
		a[4].put(0, .47);
		a[4].put(2, .47);
		
		
		for(int i=0;i<5;i++)
		{
			b[i]=a[i].dot(x);
			System.out.println(String.format("%.3f", b[i])+" ");
		}
		
		
		
	}
	
	
}
