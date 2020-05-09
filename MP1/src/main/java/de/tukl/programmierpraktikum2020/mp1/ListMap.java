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
    public Node getNode(K key) { // Hilfsfunktion _> vereinfacht den Wert einer Node zu ändern
        Node<K, V> tmp = head;
        while (tmp != null) {
            if (tmp.key == key) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return  null;
    }




    @Override
    public void put(K key, V value) {
       Node node =getNode(key);
       if(node!=null){                //key ist schon geschbeichert
           node.value=value;
       }

        else {
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
        if (get(key) != null) {     // remove wird duchgefüht nur wenn key in der Liste ist
            Node<K, V> tmp = head;
            if (tmp.key == key) {  // falls key in dem head
                head = tmp.next;
                size--;
            } else {
                while (tmp.next.key != key) {
                    tmp = tmp.next;
                }
                if (tmp.next == tail) { // falls key am Ende
                    tail = tmp;
                    tail.next = null;
                    size--;
                } else {   // falls key in dazwichen
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
