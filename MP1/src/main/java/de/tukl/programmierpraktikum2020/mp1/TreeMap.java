package de.tukl.programmierpraktikum2020.mp1;



import java.util.Comparator;


public class TreeMap<K,V>implements Map<K,V> {
	TreeNode<K, V> root;
	Comparator<K> comp;
	int size = 0;

	public TreeMap(Comparator<K> comparator) {
		comparator = new Comparator<K>() {       // Comparator definieren
			@Override
			public int compare(K o1, K o2) {
				if (o1 == o2) return 0;
				else if (o1 == null) return -1;
				else return 1;
			}
		};
		comp = comparator;
	}


	private TreeNode<K, V> MinNod(TreeNode<K, V> Min) // Hilffunktion : hilft um das Node mit kleinstem Element zu finden
	{
		while(Min.left != null){
			Min = Min.left;
		}
		return Min;
	}
	private TreeNode<K, V> removeHelber(TreeNode<K, V> tmp, K key) {
		if(tmp == null) return null;
		// Den Key wurde gefunden und wird gelöscht
		if( comp.compare(key, tmp.key)==0){
			// wenn parent keine Children hat
				if(tmp.left == null && tmp.right == null) {
					tmp = null;
				}
				// wenn parent hat nur right child
				else if(tmp.left == null) {
					TreeNode<K, V> temp = tmp;
					tmp = tmp.right;
				}
				//wenn parent hat nur left child
				else if(tmp.right == null) {
					TreeNode<K, V> temp = tmp;
					tmp = tmp.left;
				}
				else {
					// falls parent hat beide : wir suchen in suptree
					// right nach kleine Elemente und schieben sie villeicht in left subtree
					TreeNode<K, V> temp = MinNod(tmp.right);
					tmp.key = temp.key;
					tmp.right = removeHelber(tmp.right,temp.key);
				}
		}
		// sucht nach dem Node mit dem SChlüssel key in Left Suptree
		else if(comp.compare(key, tmp.key)<0){
			tmp.left = removeHelber(tmp.left,key);
		}
		// sucht nach dem Node mit dem SChlüssel key in right Suptree
		else if (comp.compare(key, tmp.key)>0){
			tmp.right = removeHelber(tmp.right,key);
		}
		return tmp;
	}

	private TreeNode<K, V> addHelper(TreeNode<K, V> temp, K key, V value) {
		//Node wird gefügt und Size erhöht sobalde einer der unteren Fallunterscheidungen
		// erfüllt ist ausßer der zweite , wird nur den Value des Nodes geändert
		if(temp == null) {
			temp = new TreeNode<K, V>(key,value);
			size++;
		}
		else if (comp.compare(key, temp.key) == 0){
			temp.value=value;
			return temp;
		}
		else if(comp.compare(key, temp.key) < 0) temp.left = addHelper(temp.left,key,value);
		else if(comp.compare(key, temp.key) > 0) temp.right = addHelper(temp.right,key,value);
		return temp;
	}


	private K[] inorder(TreeNode<K,V> T ,K[] array, int i) {
		if (T == null) return null;
		array[i] = T.key; i++;
		inorder(T.left, array, i);
		inorder(T.right, array, i);
		return array;
	}

	@Override
	public V get(K key) {
		TreeNode<K, V> tmp = root;
		while (tmp != null ) {
			if (comp.compare(key, tmp.key) == 0) {
				return tmp.value;
			} else if (comp.compare(key, tmp.key) > 0) {
				tmp = tmp.right;
			} else if (comp.compare(key, tmp.key) < 0) {
				tmp = tmp.left;
			}
		}
		return null;
	}

	@Override
	public void put(K key, V value) {
		root = addHelper(root, key, value);
	}


	@Override
	public void remove(K key) {
		if(get(key)!=null){
		root= removeHelber(root, key);
		size--;
		}
	}


	@Override
	public int size() {
		return size;
	}


	@Override
	public void keys(K[] array) {
		TreeNode<K,V> tmp = root;
		if (array == null|| array.length<size()) {
			throw new IllegalArgumentException();
		}
		array = inorder(tmp,array,0);
	}

}

