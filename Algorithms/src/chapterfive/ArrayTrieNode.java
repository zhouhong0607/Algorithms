package chapterfive;

public class ArrayTrieNode
{
	ArrayTrieNode[] children;
	boolean isEnd;
	public ArrayTrieNode()
	{
		// TODO Auto-generated constructor stub
		this.children=new ArrayTrieNode[26];
		this.isEnd=false;
	}
	public boolean isEnd()
	{
		return this.isEnd;
	}
	public void setEnd()
	{
		this.isEnd=true;
	}
	
	public boolean hasChild(char c)
	{
		return children[c-'a']!=null;
	}
	public ArrayTrieNode getChild(char c)
	{
		return children[c-'a'];
	}
	public ArrayTrieNode addChild(char c)
	{
		if(this.hasChild(c)) return this.getChild(c);
		
		ArrayTrieNode child=new ArrayTrieNode();
		children[c-'a']=child;
		return child;
		
	}
	
}
