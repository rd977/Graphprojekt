package de.tukl.programmierpraktikum2020.mp1;

public class TreeNode<K,V> {
    K key;
    V value;
    TreeNode<K,V> left , right;
    TreeNode(K key , V value){
        this.key=key;
        this.value=value;
        left=null;
        right=null;

    }
}


