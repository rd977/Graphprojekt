package de.tukl.programmierpraktikum2020.mp1;


public class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
    }
}
