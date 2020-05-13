package de.tukl.programmierpraktikum2020.mp1;


import java.util.Comparator;


public class TreeMap<K, V> implements Map<K, V> {
    TreeNode<K, V> root;
    Comparator<K> comp;
    int size = 0;
    int index = 0;  // Hilfsindex um Keys in Array beim  Traversal zu speichern

    public TreeMap(Comparator<K> comparator) {
        comp = comparator;
    }

    //------------------removeHelber Funktion ----------------------------------------------//
    private TreeNode<K, V> remove_helper(TreeNode<K, V> node, K key) {
        if (node == null) return null;
        // Den Key wurde gefunden und wird gelöscht
        if (comp.compare(key, node.key) == 0) {
            // wenn Parent keine Kinder hat
            if (node.left == null && node.right == null) {
                node = null;
            }
            // wenn Parent hat nur ein rechtes Kind
            else if (node.left == null) {
                node = node.right;
            }
            //wenn parent hat  ein linkes Kind
            else if (node.right == null) {
                node = node.left;
            }
            else {
                // falls Parent hat beide Kinder : wir suchen in der rechten Suptree nach dem Node das keinste Elmment hat
                //wenn wir die Linke Suptree wählen dann müssen wir nach dem Node suchen , das größte Element hat
                //wenn wir den minimum gefunden haben dann tauchen wir den Key und Value mit dem Key und dem Value des Parent und LÖchen wir das Node (minnode )

                TreeNode<K, V> minnode = minimum(node.right);
                TreeNode<K, V> t = node;
                node.key = minnode.key;
                node.value = minnode.value;
                minnode.key = t.key;
                minnode.value = t.value;

                node.right = remove_helper(node.right, node.key);
            }
        }
        // sucht nach dem Node mit dem SChlüssel key in Left Suptree
        else if (comp.compare(key, node.key) < 0) {
            node.left = remove_helper(node.left, key);
        }
        // sucht nach dem Node mit dem SChlüssel key in right Suptree
        else if (comp.compare(key, node.key) > 0) {
            node.right = remove_helper(node.right, key);
        }
        return node;
    }
    //------------------minimum Funktion ----------------------------------------------//
    private TreeNode<K, V> minimum(TreeNode<K, V> Min) {
        // Hilffunktion : hilft um das Node mit kleinstem Element zu finden

        while (Min.left != null) {
            Min = Min.left;
        }
        return Min;
    }

    //------------------keys_array Funktion ----------------------------------------------//

    private void keys_array(TreeNode<K, V> T, K[] array) {
        //inorder traversal , also die Kesy sind aufsteigend sortiert
        if (T == null) return;

        keys_array(T.left, array);
        if (index < size()) {
            array[index] = T.key;
            index++; }
        keys_array(T.right, array);
    }

    //------------------addHelper Funktion ----------------------------------------------//
    private TreeNode<K, V> addHelper(TreeNode<K, V> node, K key, V value) {
        //Node wird gefügt und Size erhöht sobalde einer der unteren Fallunterscheidungen
        // erfüllt ist ausßer der zweite , wird nur den Value des Nodes geändert
        if(node == null) {
            node = new TreeNode<K, V>(key,value);
            size++;
        }
        else if (comp.compare(key, node.key) == 0){
            node.value=value;
            return node;
        }
        else if(comp.compare(key, node.key) < 0) node.left = addHelper(node.left,key,value);
        else if(comp.compare(key, node.key) > 0) node.right = addHelper(node.right,key,value);
        return node;
    }


    @Override
    //------------------get Funktion ----------------------------------------------//
    public V get(K key) {
        TreeNode<K, V> node = root;
        while (node != null) {
            if (comp.compare(key, node.key) == 0) {
                return node.value;
            } else if (comp.compare(key, node.key) > 0) {
                node = node.right;
            } else if (comp.compare(key, node.key) < 0) {
                node = node.left;
            }
        }
        return null;
    }


    @Override
    public void put(K key, V value) {
        root = addHelper(root,key,value);
    }


    @Override
    public void remove(K key) {
        if (get(key) != null) {
            root = remove_helper( root,key );
            size--;
        }
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public void keys(K[] array) {
        TreeNode<K, V> node = root;
        if (array == null || array.length < size()) {
            throw new IllegalArgumentException();
        }
        keys_array(node, array);
    }

}

