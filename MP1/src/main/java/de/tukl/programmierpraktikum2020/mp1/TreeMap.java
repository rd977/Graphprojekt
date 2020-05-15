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
        // Der Key wurde gefunden und wird gelöscht
        if (comp.compare(key, node.key) == 0) {                                
            // wenn Parent keine Kinder hat
            if (node.left == null && node.right == null) {                            
                node = null;
            }
            // wenn Parent hat nur ein rechtes Kind
            else if (node.left == null) {
                node = node.right;
            }
            //wenn parent hat NUR ein linkes Kind
            else if (node.right == null) {
                node = node.left;
            }
            else {
                // falls Parent beide Kinder hat : wir suchen in dem rechten Unterbaum nach dem Knoten,der den kleinsten key hat
                //wenn wir den linken Unterbaum wählen möchten dann müssen wir  nach dem Knoten suchen,der den  größten key hat
                //HIER haben wir uns für den ersten Wahl etnschieden.
                // Wenn wir den minimum gefunden haben dann tauschen wir den 
                //Key und Value mit dem Key und dem Value des Root des Unterbaum aus und umgekehrt.  
                //und Löchen wir den Knoten (minnode )

                TreeNode<K, V> minnode = minimum(node.right);
                TreeNode<K, V> t = node;
                node.key = minnode.key;
                node.value = minnode.value;
                minnode.key = t.key;
                minnode.value = t.value;
                node.right = remove_helper(node.right, node.key);
            }
        }
        // sucht nach dem Knoten mit dem  key in linken Unterbaum
        else if (comp.compare(key, node.key) < 0) {
            node.left = remove_helper(node.left, key);
        }
        // sucht nach dem Knoten mit dem  key in rechten Unterbaum
        else  {
            node.right = remove_helper(node.right, key);
        }
        //Liefert den ganzen Baum ohne den gelöschten Knoten zurück
        return node;
    }
    //------------------minimum Funktion ----------------------------------------------//
    private TreeNode<K, V> minimum(TreeNode<K, V> Min) {
        // Hilffunktion : hilft um den Knoten mit kleinstem Element zu finden
        while (Min.left != null) {
            Min = Min.left;
        }
        return Min;
    }

    //------------------inorder Funktion ----------------------------------------------//

    private void inorder(TreeNode<K, V> T, K[] array) {
        //inorder traversal , also die Keys sind aufsteigend sortiert
       // führt erstmal den linkes Unterbaum dann den Root dann den rechten Unterbaum durch.
        if (T == null) return;

        inorder(T.left, array);
        if (index < size()) {
            array[index] = T.key;
            index++; }
        inorder(T.right, array);
    }


    //------------------addHelper Funktion ----------------------------------------------//
    private TreeNode<K, V> addHelper(TreeNode<K, V> node, K key, V value) {
        //falls keine Elemente in dem Baum sind
        if(node == null) {
            node = new TreeNode<K, V>(key,value);
            size++;
        }
        //falls der gefügte Key schon bereit in dem Baum ist , dann ändern wir nur den Value
        else if (comp.compare(key, node.key) == 0){
            node.value=value;
            return node;
        } 
        // zwei rekursive prüfen die rechten und linken Unterbäume
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
        inorder(node, array);
    }

}

