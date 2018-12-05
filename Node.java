package cs600_test1;
import java.util.HashMap;
/*
 * each node is stored in a hashmap 
 */
public class Node<t> {
	char key;
	t value;
	HashMap<Character, Node<t>> children;
	
	public Node() {
		this.children = new HashMap<Character, Node<t>>();
		
	}
	
	public Node(char key) {
		this.key = key;
		this.children = new HashMap<Character, Node<t>>();
	}
	/*	
	public Node(char key, t value) {
		this.key = key;
		this.value = value;
		this.children = new HashMap<Character, Node<t>>();
	}
	*/
}
