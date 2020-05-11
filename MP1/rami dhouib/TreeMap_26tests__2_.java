package de.tukl.programmierpraktikum2020.mp1;


import javax.swing.*;
import java.util.Comparator;


public class TreeMap<K,V>implements Map<K,V> {
	TreeNode<K, V> root;
	Comparator comp;
	int size = 0;

	public TreeMap(Comparator comp) {
		this.comp = comp;
	}


	@Override
	public V get(K key) {
		TreeNode<K, V> tmp = root;
		while (tmp != null) {
			if (comp.compare(key, tmp.key) == 0) {
				System.out.println("ze got it");
				return tmp.value;

			} else if (comp.compare(key, tmp.key) > 0) {
				tmp = tmp.right;
			} else if (comp.compare(key, tmp.key) < 0) {
				tmp = tmp.left;
			}
		}
		return null;
	}


	private TreeNode<K,V> getMaxHelper(TreeNode<K, V> temp) {
		if (temp.right == null){
			System.out.println(temp.key);
			return temp;
		    }
		else{
			return getMaxHelper(temp.right);}
	}

	private void removeHelper1(TreeNode<K, V> root1,TreeNode<K, V> parent,boolean b) {
		if (root1.right!=null)
			removeHelper1(root1.right,root1,false);
		else
			if (b){
				parent.left=root1.left;
				System.out.println(root1.key);
		        System.out.println("size was reduced");}
			else{
				parent.right=root1.left;
				System.out.println(root1.key);
		        System.out.println("size was reduced");}


	}
		private void removeHelper(TreeNode<K, V> root1, K key,TreeNode<K, V> parent,boolean b) {
		if (root1 == null) {
			return;
		} else if (root1.left!=null &comp.compare(key, root1.key) < 0) {
			removeHelper(root1.left, key,root1,true);
		} else if (root1.right!=null & comp.compare(key, root1.key) > 0) {
			removeHelper(root1.right, key,root1,false);
		} else if(comp.compare(key, root1.key) == 0) {
			size--;
			if (root1.left == null &  root1.right == null) {
				if (b){
					parent.left= null;
				}
				else{
					parent.right= null;
				}
			      }
			else if (root1.left == null ) {
				if (b){
				parent.left= root1.right;
				}
				else{
					parent.right= root1.right;
				}

				System.out.println("size was reduced");;}
		    else if (root1.right == null) {
			    if (b){
				    parent.left= root1.left;
				}
			    else{
				    parent.right= root1.left;
				}


				System.out.println("size was reduced");
			}
		    else {
				TreeNode<K, V> maxValue = this.getMaxHelper(root1.left);
				root1.key=maxValue.key;
				root1.value=maxValue.value;
				removeHelper1(root1.left,root1,true);
			}
		}
		return;
	}


	@Override
	public void put(K key, V value) {

		if (root == null) {
			root = new TreeNode<>(key, value);
			size++;
			System.out.println("siz was added added")	;
		} else {
			TreeNode<K, V> tmp = root;
			while (true) {
				if (comp.compare(key, tmp.key) == 0) {
					tmp.value=value;
					System.out.println("alue changed")	;
					return;


				} else if (comp.compare(key, tmp.key) <0) {
					if (tmp.left==null){
						tmp.left=new TreeNode(key,value);
					    size++;
						System.out.println("size was added");
					    return;}
					else{
					    tmp = tmp.left;}
				} else if (comp.compare(key, tmp.key) > 0) {
					if (tmp.right==null){
						tmp.right=new TreeNode(key,value);
					    size++;
						System.out.println("size was added");
					    return;}
					else{
						tmp = tmp.right;}
				}

			}


		}
	}


	@Override
	public void remove(K key) {
		if (root != null) {
			if (root.key == key) {
				size--;
				if (root.left == null) {
					root = root.right;
					System.out.println("size was reduced");
					;
				}
				else if (root.right == null) {
					root = root.left;
					System.out.println("size was reduced");
				}
				else {
					TreeNode<K, V> maxValue = this.getMaxHelper(root.left);
					root.key = maxValue.key;
					root.value = maxValue.value;
					removeHelper1(root.left, root, true);
				}
			} else {
				if (comp.compare(key, root.key) < 0) {
					removeHelper(root.left, key, root, true);
				}
				else {
					removeHelper(root.right, key, root, false);
				}
			}
		}
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
		if (root!=null) {
			root.add(0, array);
		}
	}

}

