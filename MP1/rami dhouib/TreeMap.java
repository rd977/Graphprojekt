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
		if (temp.right == null)
			return temp;
		else
			return getMaxHelper(temp.right);
	}

	private void removeHelper1(TreeNode<K, V> root, K key,TreeNode<K, V> parent,boolean b) {
		if (root.right!=null)
			removeHelper1(root.right,key,root,false);
		else
			if (b){
				parent.left=null;
				size--;
		        System.out.println("size was reduced");}
			else{
				parent.right=null;
				size--;
		        System.out.println("size was reduced");}


	}
		private void removeHelper(TreeNode<K, V> root, K key,TreeNode<K, V> parent,boolean b) {
		if (root == null) {
			return;
		} else if (comp.compare(key, root.key) < 0) {
			removeHelper(root.left, key,root,true);
		} else if (comp.compare(key, root.key) > 0) {
			removeHelper(root.right, key,root,false);
		} else {
			if (root.left == null) {
				if (b){
				parent.left= root.right;
				}
				else{
					parent.right= root.right;
				}
				size--;

				System.out.println("size was reduced");;}
			else if (root.right == null) {
			    if (b){
				    parent.left= root.left;
				}
			    else{
				    parent.right= root.left;
				}
				size--;


				System.out.println("size was reduced");
			} else {
				TreeNode<K, V> maxValue = this.getMaxHelper(root);
				root.key = maxValue.key;
				root.value=maxValue.value;
				removeHelper1(root.left,key,root,true);
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
				if (root.left == null) {
					root = root.right;
					size--;
					System.out.println("size was reduced");
					;
				} else if (root.right == null) {
					root = root.left;
					size--;
					System.out.println("size was reduced");
				} else {
					TreeNode<K, V> maxValue = this.getMaxHelper(root);
					root.key = maxValue.key;
					root.value = maxValue.value;
					removeHelper1(root.left, maxValue.key, root, true);
				}
			} else {
				if (comp.compare(key, root.key) < 0) {
					removeHelper(root.left, key, root, true);
				} else {
					removeHelper(root.right, key, root, false);
				}
			}
		}
	}



	@Override
	public int size() {
		return size;
	}


	private void inorder(TreeNode<K,V> T ,K[] array, int i) {
		if (T == null)
			return ;
		array[i] = T.key;
		i++;
		inorder(T.left, array, i);
		inorder(T.right, array, i);
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

