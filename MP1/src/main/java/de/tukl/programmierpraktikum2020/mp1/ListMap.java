package de.tukl.programmierpraktikum2020.mp1;


public class ListMap<K ,V>  implements Map<K ,V> {
    Node<K, V> head;
    Node<K, V> tail;
    int size = 0;

    //-----------getNode----------------------------//
    //Gibt das Node des eingegebnen Schlüssels zurück
    //die Funktion vereinfacht die Änderung des Value
    public Node getNode(K key) {
        Node<K, V> node = head;
        while (node != null) {
            if (node.key .equals(key) ) {
                return node;
            }
            node = node.next;
        }
        return  null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node  = head;
        while (node != null) {
            if (node.key .equals(key) ) {
               return node.value;
            }
            node = node.next;
        }
        return  null;
    }


    @Override
    public void put(K key, V value) {
       Node<K,V> node =getNode(key);
       //falls der Schlüssel bereit in der Liste ist , ändern wir nur seinen Value
       if(node!=null){
           node.value=value;
       }
        // falls nicht!..
        else {
           Node<K, V> node_x = new Node<K, V>(key,value);
           if (size == 0) {
                //falls die Liste Leer ist
               head = node_x;
               tail= node_x;
           } else {
               tail.next = node_x;

           }
           tail = node_x;
           size++;
       }
    }




    @Override
    public void remove(K key) {
        if (get(key) != null) {     // remove wird duchgefüht nur wenn key in der Liste ist
            Node<K, V> tmp = head;
            if (tmp.key .equals(key)) {  // falls key in dem head
                head = tmp.next;
            }
            else {
                while (tmp.next.key != key) {
                    tmp = tmp.next;
                }
                // falls der key am Ende ist
                if (tmp.next == tail) {
                    tail = tmp;
                    tail.next = null;
                }
                // falls der key  zwischen head und tail ist
                else {
                    tmp.next = tmp.next.next;
                }
            }
            size--;
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
            Node<K, V> node = head;
            int i =0;
            while (node != null && i < size() ){
                array[i] = node.key;
                i++;
                node = node.next;
            }
        }
    }

}
