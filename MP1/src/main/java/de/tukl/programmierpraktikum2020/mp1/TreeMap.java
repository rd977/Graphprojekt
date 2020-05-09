package de.tukl.programmierpraktikum2020.mp1;
import java.util.Comparator;
import java.security.Key;
import java.util.TooManyListenersException;

public class TreeMap<K, V> implements Map<K, V> {
	Comparator k;
	Tree.Node root= null;
	int size=0;
	
	TreeMap(Comparator k) {
		this.k=k;
		
	}
	
	@Override
	public V get(K key) {
		boolean boole=false;
		Tree.Node<K,V> root1=root;
		V result=null;
		while (boole =! true) {
			
		if (root==null) 
			boole=true;
	    int a= k.compare(root1.key,key );
	    if (a==0)
	    	boole=true;
	        result= root1.value;
	    if (a<0)
	    	root1= root1.left;
	    if (a>0)
	    	root1=root1.right;
	    
		}
	    return result;
	
			
	}

	 @Override
	 public void put(K key, V value) {
		    boolean boole=false; //benutzt binary search tree  eigenschaft
			Tree.Node<K,V> root1=root;
			while (boole =! true) {
				
			if (root1==null) 
				root1.key= key;
			    root1.value=value;
			    size++;
		    int a= k.compare(root1.key,key );
		    if (a==0)
		    	boole=true;
		        root1.value=value;
		    if (a<0)
		    	root1= root1.left;
		    if (a>0)
		    	root1=root1.right;
			}
	}	
	@Override
	public void remove(K key) {  //nicht implementiert(optinal)
		throw new UnsupportedOperationException();
	}
	@Override
    public int size() {
        return size;
    }
	@Override
	public void keys(K[] array) {
		
		
		                  
		if (array == null|| array.length< this.size()) 
            throw new IllegalArgumentException();
		else 
			root.add(0, array);
		    	
	}
	
		
        	
		
        		
         
            

		
	}


}
