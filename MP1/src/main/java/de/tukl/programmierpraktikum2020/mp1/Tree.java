package de.tukl.programmierpraktikum2020.mp1;
public class Tree<K, V> {
	public V value=null;
	public K key=null;
	public Tree<K, V> right=new Tree();
	public Tree<K, V> left=new Tree();
		
	Tree(V value,K key){
		this.key=key;
		this.value=value;
		}
	Tree();
	
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
	


