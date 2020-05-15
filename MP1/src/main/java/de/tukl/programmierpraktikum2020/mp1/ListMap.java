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
           Node<K, V> newnode = new Node<K, V>(key,value);
           if (size == 0) {
                //falls die Liste Leer ist
               head = newnode;
           } else {
               tail.next = newnode;

           }
           tail = newnode;
           size++;
       }
    }




    @Override
    public void remove(K key) {
        // remove wird duchgefüht nur wenn key in der Liste ist
        if (get(key) != null) {     
            Node<K, V> node = head;
            // falls key in dem head Node
            if (node.key .equals(key)) {  
                head = node.next;
            }
            else {
                while (node.next.key != key) {
                    node = node.next;
                }
                // falls der key am Ende ist
                if (node.next == tail) {
                    tail = node;
                    tail.next = null;
                }
                // falls der key  zwischen head und tail ist
                else {
                    node.next = node.next.next;
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
