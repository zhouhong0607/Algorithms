package chapterfive;

import java.util.HashMap;

public class HashMapTrieNode
{
	private HashMap<Character, HashMapTrieNode> children;
	private boolean isEnd;
	public HashMapTrieNode()
	{
		// TODO Auto-generated constructor stub
		children=new HashMap<>();
		isEnd=false;
	}
	public boolean isEnd()
	{
		return isEnd;
	}
	public void setEnd()
	{
		this.isEnd=true;
	}
	public boolean hasChild(Character c)
	{
		return children.containsKey(c);
	}
	
	public HashMapTrieNode getChild(Character c)
	{
		return children.get(c);
	}
	
	public HashMapTrieNode addChild(Character c)
	{
		if(hasChild(c)) return getChild(c);
		
		HashMapTrieNode node=new HashMapTrieNode();
		children.put(c, node);
		return node;
	}
	
	
	
	
}
