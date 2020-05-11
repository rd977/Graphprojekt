package de.tukl.programmierpraktikum2020.mp1;
import java.util.*;

public class TreeMap<K, V> implements Map<K, V> {
	Comparator k;
	Tree root;
	int size=0;

	TreeMap(Comparator k) {
		this.k=k;
	}
	
	@Override
	public V get(K key) {
		Tree<K,V> root1=root;
		V result=null;
		while (true) {
		if (root1.value==null)
			break;
	    int a= k.compare(root1.key,key );
	    if (a==0)
	    	break;
	        result= root1.value;
	    if (a>0)
	    	root1= root1.left;
	    if (a<0)
	    	root1=root1.right;
	    }
	    return result;
	}

 	@Override
 	public void put(K key, V value) {  //benutzt binary search tree  eigenschaft
		Tree<K,V> root1=root;
		while (true) {

		if (root1.value==null)
			root1.value=value;
			root1.key=key;
			size++;
		int a= k.compare(root1.key,key );
		if (a==0) {
			root1.value = value;
			break;
		}
		if (a>0)
			root1= root1.left;
		if (a<0)
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


