package chapterfive;

public class TrieTree
{
	private ArrayTrieNode root;

	public TrieTree()
	{
		// TODO Auto-generated constructor stub
		root = new ArrayTrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word)
	{
		char[] s = word.toCharArray();
		ArrayTrieNode node = root;
		for (int i = 0; i < s.length; i++)
		{
			node = node.addChild(s[i]);
		}
		node.setEnd();
	}

	// Returns if the word is in the trie.
	public boolean search(String word)
	{
		char[] s = word.toCharArray();
		int i;
		ArrayTrieNode node = root;
		for (i = 0; i < s.length; i++)
		{
			if (node.hasChild(s[i]))
			{
				node = node.getChild(s[i]);
			} else
			{
				break;
			}
		}
		return i == s.length;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix)
	{
		return search(prefix);
	}
}
