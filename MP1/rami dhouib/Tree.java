package mp1;

public class Tree {
	
	public class Node<V> {
		V wert;
		Node right;
		Node left;
		Node(Node<V> right,Node<V> left,V wert){
			this.wert=wert;
			this.right=right;
			this.left=left;
		}
		Node(V wert){
			this.wert=wert;
			this.right=null;
			this.left=null;
		}
	public void change(V wert) {
		this.wert=wert;
	}
			
			
		
		}
	
		
	}

