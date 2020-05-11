package de.tukl.programmierpraktikum2020.mp1;
public class TreeNode<K, V> {
	public V value=null;
	public K key=null;
	public TreeNode<K, V> right;
	public TreeNode<K, V> left;
		
	TreeNode(K key,V value){
		this.key=key;
		this.value=value;
		left=null;
		right=null;
		}
	
	public int count () {
		    int a=0;
		    int b=0;
			if (value!=null){
				if (left!=null){
					a=left.count();
				}
				if (right!=null){
					b=right.count();
				}

				return 1+a+b;}
			else {
				return 0;}
		}
	public void add(int i,K[] array) {
		array[i]=this.key;
		if (left!=null){
			left.add(i+1,array);
		    if (right !=null){
		        right.add(this.left.count() +1,array);}}
		if (right !=null){
		     right.add(i+1,array)   ;}

		
	}
			
			
		
		}
	


