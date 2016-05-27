package algorithms3;
import java.awt.RenderingHints.Key;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class SeparateChainingHashST<Key,Value>
{

	private int N;
	private int M;
	private SequentialSearchST<Key, Value>[] st;
	
	public SeparateChainingHashST()
	{
		// TODO Auto-generated constructor stub
		this(997);
	}
	
	public SeparateChainingHashST(int m)
	{
		// TODO Auto-generated constructor stub
		M=m;
		st=(SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
		for(int i=0;i<M;i++)
			st[i]=new SequentialSearchST<>();
	}
	/**
	 * 键值的哈希函数
	 * @param k
	 * @return
	 */
	private int hash(Key k)
	{
		return (k.hashCode()&0x7fffffff)%M;
	}
	public Value get(Key k)
	{
		return  (Value)(st[hash(k)].get(k));
	}
	public void put(Key k,Value v)
	{
		st[hash(k)].put(k, v);
		N++;
	}
	public void delete(Key k)
	{
		SequentialSearchST<Key, Value> a= st[hash(k)];
		if(a!=null)
			a.delete(k);
		
		
	}
	
	
	public Iterable<Key> keySet()
	{
		Stack<Key> keys=new Stack<>();
		for(int i=0;i<M;i++)
		{
			if(st[i]!=null)
			{
				Iterator<Key> iterator= st[i].keySet().iterator();
				while(iterator.hasNext())
				{
					keys.push(iterator.next());
				}
			}
			
		}
		
		return keys;
		
	}
	
	
	public int size()
	{
		return N;
	}
	
	
	public void main(String[] args)
	{
		
		
		
	}
}
