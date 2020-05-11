package de.tukl.programmierpraktikum2020.mp1;


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
                return tmp.value;

            } else if (comp.compare(key, tmp.key) > 0) {
                tmp = tmp.right;
            } else if (comp.compare(key, tmp.key) < 0) {
                tmp = tmp.left;
            }
        }
        return null;
    }


    private K getMaxHelper(TreeNode<K, V> temp) {
        if (temp.right == null)
            return temp.key;
        else
            return getMaxHelper(temp.right);
    }


    private TreeNode<K, V> removeHelper(TreeNode<K, V> root, K key) {
        if (root == null) {
            return root;
        } else if (comp.compare(key, root.key) < 0) {
            root.left = removeHelper(root.left, key);
        } else if (comp.compare(key, root.key) > 0) {
            root.right = removeHelper(root.right, key);
        } else {
            if (root.left == null) {
                TreeNode<K, V> temp = root.right;
                size--;
                return temp;
            } else if (root.right == null) {
                TreeNode<K, V> temp = root.left;
                size--;
                return temp;
            } else {
                K maxValue = this.getMaxHelper(root.left);
                root.key = maxValue;
                root.left = removeHelper(root.left, maxValue);
            }
        }
        return root;
    }


    private void addHelper(TreeNode<K, V> temp, K key, V value) {
        if (comp.compare(key, temp.key) == 0) {
            temp.value = value;

        } else if (comp.compare(key, temp.key) < 0) {
            if (temp.left == null) {
                temp.left = new TreeNode<>(key, value);
                size++;
            } else {
                addHelper(temp.left, key, value);
            }
        } else if (comp.compare(key, temp.key) > 0){
            if (temp.right == null) {
                temp.right = new TreeNode<>(key, value);
                size++;
            } else {
                addHelper(temp.right, key, value);
            }
        }
    }


    @Override
    public void put(K key, V value) {

        if (root == null) {
            root = new TreeNode<>(key, value);
            size++;
        } else {
            addHelper(root, key, value);
        }
    }


    @Override
    public void remove(K key) {
        root = removeHelper(root, key);
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
        if (array == null|| array.length<size) {
            throw new IllegalArgumentException();
        }
    inorder(root,array,0);
    }

    }

