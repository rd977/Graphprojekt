package de.tukl.programmierpraktikum2020.p1.a1;


import java.util.LinkedList;

public class FibNode<E>{

    E key;
    FibNode<E> parent , prev , next , child;
    int degree;
    boolean marked ;
    FibNode(E key){
        this.key=key;
    }

}

