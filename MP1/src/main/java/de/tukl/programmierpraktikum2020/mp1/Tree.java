package de.tukl.programmierpraktikum2020.mp1;
public class Tree {
	
	public class Node<K, V> {
		public V value=null;
		public K key=null;
		public Node<K, V> right;
		public Node<K, V> left;
		
		Node(V value,K key){
			this.key=key;
			this.value=value;
			this.right=null;
			this.left=null;
		}
	
	public int count () {  // for keys method to function
			if (value!=null)
				return 1+left.count()+right.count();
			else 
				return 0;
		}
	public void add(int i,K[] array) {
		array[i]=this.key;
		left.add(i+1,array);
		right.add(this.left.count() +1,array);
		
	}
			
			
		
		}
	
		
	}

