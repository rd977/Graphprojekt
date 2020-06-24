package de.tukl.programmierpraktikum2020.p2.a1;

import java.util.HashMap;
import java.util.LinkedList;

public class Node<D> {
    D data ;
    int id ;
    HashMap<Integer, Node<D>> inNods;
    HashMap<Integer, Node<D>> outNods;
    Node(D data){
        this.data=data;
        inNods = new HashMap<>();
        outNods = new HashMap<>();
    }
}
