package algorithms3;

import java.awt.RenderingHints.Key;
import java.util.Stack;

public class LinearProbingHashST<Key, Value>
{
	private int N;
	private int M;
	private Key[] keys;
	private Value[] values;

	public LinearProbingHashST()
	{
		// TODO Auto-generated constructor stub
		this(16);
	}

	public LinearProbingHashST(int cap)
	{
		// TODO Auto-generated constructor stub
		M = cap;
		keys = (Key[]) new Object[M];
		values = (Value[]) new Object[M];

	}

	private int hash(Key k)
	{
		return (k.hashCode() & 0x7fffffff) % M;
	}

	private void resize(int cap)
	{
		LinearProbingHashST<Key, Value> t;
		t = new LinearProbingHashST<>(cap);
		for (int i = 0; i < M; i++)
		{
			if (keys[i] != null)
				t.put(keys[i], values[i]);
		}
		keys = t.keys;
		values = t.values;
		M = t.M;
	}

	public void put(Key k, Value v)
	{
		if (N >= M / 2)
			resize(2 * M);
		int i;
		for (i = hash(k); keys[i] != null; i = (i + 1) % M)
		{
			if (keys[i].equals(k))
			{
				values[i] = v;
				return;
			}
		}
		keys[i] = k;
		values[i] = v;
		N++;
	}

	public Value get(Key k)
	{
		int i;
		for (i = hash(k); keys[i] != null; i = (i + 1) % M)
		{
			if (keys[i].equals(k))
				return values[i];
		}
		return null;

	}

	public void delete(Key k)
	{
		if(get(k)==null) return;
		
		int i=hash(k);
		while(!keys[i].equals(k))
			i=(i+1)%M;
		
		keys[i]=null;
		values[i]=null;
	
		
		while(keys[i=(i+1)%M]!=null)
		{
			Key k1=keys[i];
			Value v1=values[i];
			keys[i]=null;
			values[i]=null;
			N--;
			put(k1, v1);
		}
		
		N--;
		if(N>0&&N<=M/8) resize(M/2);
		
		
	}
	
	public Iterable<Key> keySet()
	{
		Stack<Key> keySet = new Stack<>();
		for (int i = 0; i < M; i++)
		{
			if (keys[i] != null)
				keySet.push(keys[i]);
		}
		return keySet;
	}

	public int size()
	{
		return N;
	}
}
