package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.zip.CheckedOutputStream;

public class FibonacciHeap<E> implements PriorityQueue<E>{
    int size=0;
    FibNode<E> maxNode;
    Comparator<E> comp;
    LinkedList<FibNode<E>> rootlist = new LinkedList<>();



    public FibonacciHeap(Comparator<E> comp) {
        this.comp = comp;
    }

    //###############InsertNode########
    private void insertNode(FibNode<E> node) {
        if(rootlist.isEmpty()){
            maxNode = node;
        }
        if(comp.compare(maxNode.key, node.key) < 0){
            maxNode = node;
        }
        rootlist.add(node);
    }

    //############## Binomial ############
    private FibNode<E> Binomial() {

        HashMap<Integer, FibNode<E>> degRoots = new HashMap<Integer, FibNode<E>>();
        FibNode<E> max = rootlist.get(0);

        int i = 0;

        while (rootlist.size() > i) {
                if(comp.compare(max.key,rootlist.get(i).key)<0){
                    max = rootlist.get(i);
                }

            if (!degRoots.containsKey( rootlist.get(i).degree )) {
                degRoots.put(rootlist.get(i).degree , rootlist.get(i));
                i++;
            } else {
                int tempDegree = rootlist.get(i).degree;
                FibNode gleichDeg = degRoots.get(tempDegree);
                FibNode newTree = UnionZweiBäume(rootlist.get(i) , gleichDeg);


                rootlist.set(i, newTree);
                i = rootlist.indexOf(gleichDeg) > i ? i : i - 1;
                rootlist.remove(gleichDeg);
                degRoots.remove(tempDegree);
            }
        }
        return max;
    }

    private FibNode<E> UnionZweiBäume(FibNode<E> FN1, FibNode<E> FN2) {

        if (comp.compare(FN1.key, FN2.key)>0) {
                FN2.next = FN1.child;
                if (FN1.child != null)
                    FN1.child.prev = FN2;
                FN1.child = FN2 ;
                FN2.prev = null;
                FN2.parent = FN1;
                FN1.degree++;
                return FN1;
            } else {
                FN1.next = FN2.child;
                if (FN2.child != null){
                    FN2.child.prev = FN1;
                }
                    FN2.child = FN1 ;
                  FN1.prev = null;
                  FN1.parent = FN2;
                  FN2.degree++;
            return FN2;
    }}
    @Override
    public void insert(E elem) {
        FibNode<E> node = new FibNode<E>(elem);
        if(rootlist.isEmpty()){
            maxNode = node;
        }
        if(comp.compare(maxNode.key, node.key) < 0){
            maxNode = node;
        }
        rootlist.add(node);
        size++;

    }



    @Override
    public void merge(PriorityQueue otherQueue) {

       int s = ((FibonacciHeap<E>) otherQueue).size;
       while (!((FibonacciHeap<E>) otherQueue).rootlist.isEmpty()){
           insertNode(((FibonacciHeap<E>) otherQueue).rootlist.remove());
       }
        size +=s;

    }



    @Override
    public E deleteMax() {
        if(rootlist.isEmpty()){
            return null;
        }
        else if(size ==1) {
            FibNode<E> max = maxNode;
             rootlist.remove();
             maxNode = null;
            return max.key;

        }else {
            FibNode<E> temp = maxNode.child;
            while (temp != null) {
                temp.parent = null;
                temp.marked = false;
                insertNode(temp);
                temp = temp.next;
                if (temp != null && temp.prev != null)
                    temp.prev.next = null;
            }

            FibNode<E> n = maxNode;
            rootlist.remove(maxNode);
            maxNode = Binomial();
            size--;
            return n.key;
        }
        }




    @Override
    public E max() {
        return maxNode.key;
    }
  

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean update(E elem, E updatedElem) {
        return false;
    }



    @Override
    public void map(UnaryOperator f) {

    }
    public static void main(String[] args){
        FibonacciHeap<Integer> f = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());
        FibonacciHeap<Integer> x = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());
        f.insert(2);
        f.insert(3);
        f.insert(0);
        f.insert(5);
       x.insert(1);
        x.insert(7);
        x.insert(5);
        x.insert(3);
        f.merge(x);

        System.out.println(f.deleteMax());
        System.out.println(f.size);
        System.out.println(f.max());
    }

}
