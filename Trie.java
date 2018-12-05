package cs600_test1;
import java.util.HashMap;
public class Trie<t> {
	
	private Node<t> root;
	public int size;
	
	public Trie() {
		this.root = new Node<t>();
		this.size = 0;
	}
	/*
	 * there are two parameters passed into the insert function one is the string other is the value (i.e.) page index
	 * this is the implementation of the inverted file or inverted index storing key value pairs (w,L) 
	 * w is the word and L is a collection of references to pages containing the word w 
	 * the elements in the dictionary are occurence list and key(words) in the dictionary are index terms  
	 */
	
	/*
	 * each letter is checked if it is present in the trie if not a new node is created and the character is put into it
	 * when it comes to an end the leaf node will have the page index as a value
	 * each time a new node is added the size of the trie grows and increases 
	 */
	public void insert(String s, t value) {
		HashMap<Character, Node<t>> children = this.root.children;
		Node<t> node = null;
		
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			
			if (children.containsKey(c)) {
				node = children.get(c);
			} else {
				node = new Node<t>(c);
				children.put(c,node);
			}
			
			if (i==s.length()-1) // is the end of the word
				node.value = value;
			
			children = node.children;
		}
		this.size += 1;
	}
	
	/*
	 *when a word is searched and if the characters of the word are present it returns the url which has the word (i.e.) value or page index else returns null 
	 */
	public t search(String s) {
		HashMap<Character, Node<t>> children = this.root.children;
		Node<t> node = null;
		t ans = null;
		
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			
			if (children.containsKey(c)) {
				node = children.get(c);
			} else {
				return null;
			}
			if (i == s.length()-1) {
				ans = node.value;
			}
			children = node.children;
		}
		return ans; // page index gets returned if exists else null
	}
		}
