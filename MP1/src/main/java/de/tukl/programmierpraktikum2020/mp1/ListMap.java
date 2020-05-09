package de.tukl.programmierpraktikum2020.mp1;

import java.security.Key;
import java.util.TooManyListenersException;

public class ListMap<K ,V>  implements Map<K ,V> {
    Node<K, V> head;
    Node<K, V> tail;
    int size = 0;



    @Override
    public V get(K key) {
        Node<K, V> tmp = head;
        while (tmp != null) {
            if (tmp.key == key) {
               return tmp.value;
            }
            tmp = tmp.next;
        }
        return  null;
    }



    @Override
    public void put(K key, V value) {
        Node<K, V> tmp = head;
        boolean v = true;
        while (tmp != null) {
            if (tmp.key == key) {
                tmp.value = value;
                v = false;
                break;
            }
            tmp = tmp.next; }

        if(v){
            if (size == 0) {
                Node<K, V> tmp1= new Node<K, V>();
                tmp1.key = key;
                tmp1.value = value;
                head = tmp1;
                tail = tmp1;
                size++;
            } else {
                Node<K, V> tmp1 = new Node<K, V>();
                tmp1.key = key;
                tmp1.value = value;
                tail.next = tmp1;
                tail = tmp1;
                size++;

            }
        }
    }




    @Override
    public void remove(K key) {
        if (get(key) != null) {
            Node<K, V> tmp = head;
            if (tmp.key == key) {
                head = tmp.next;
                size--;
            } else {
                while (tmp.next.key != key) {
                    tmp = tmp.next;
                }
                if (tmp.next == tail) {
                    tail = tmp;
                    tail.next = null;
                    size--;
                } else {
                    tmp.next = tmp.next.next;
                    size--;
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
        if (array == null|| array.length< size()) {
            throw new IllegalArgumentException();
        } else {
            Node<K, V> tmp = head;
            int i = 0;
            while (tmp != null && i < size() ){
                array[i] = tmp.key;
                i++;
                tmp = tmp.next;
            }
        }
    }
}
