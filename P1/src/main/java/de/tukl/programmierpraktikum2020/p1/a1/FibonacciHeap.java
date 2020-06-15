package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.zip.CheckedOutputStream;

public class FibonacciHeap<E> implements PriorityQueue<E>{
    int size=0;
    FibNode<E> maxNode;
    FibNode<E> current;
    Comparator<E> comp;
    LinkedList<FibNode<E>> rootlist = new LinkedList<>();



    public FibonacciHeap(Comparator<E> comp) {
        this.comp = comp;
    }

    //*****************HilfsFUnktion*******************
    public void insertNode(FibNode<E> node) {
        rootlist.add(node);

    }
    //********************************

    private FibNode<E> Binomial() {

        HashMap<Integer, FibNode<E>> hashroot = new HashMap<Integer, FibNode<E>>();
        FibNode<E> max = rootlist.get(0);

        int i = 0;

        while (rootlist.size() > i) {
                if(comp.compare(max.key,rootlist.get(i).key)<0){
                    max = rootlist.get(i);
                }

            if (!hashroot.containsKey(rootlist.get(i).degree)) {
                hashroot.put(rootlist.get(i).degree, rootlist.get(i));
                i++;
            } else {
                int tempDegree = rootlist.get(i).degree;
                FibNode sameDegreeTree = hashroot.get(tempDegree);
                FibNode newTree = mergeEqualTrees(rootlist.get(i), sameDegreeTree);
                // order of next 2 lines
                rootlist.set(i, newTree);
                i = rootlist.indexOf(sameDegreeTree) > i ? i : i - 1;
                rootlist.remove(sameDegreeTree);
                hashroot.remove(tempDegree);
            }
        }
        return max;
    }

    private FibNode<E> mergeEqualTrees(FibNode<E> f1, FibNode<E> f2) {

        if (comp.compare(f1.key,f2.key)<0) {
            f2.child.add(f1);
            f1.parent = f2;
            f2.degree = f2.child.size();
            return f2;
        } else {
            f1.child.add(f2);
            f2.parent = f1;
            f1.degree = f2.child.size();
            return f1;
        }
    }
    @Override
    public void insert(E elem) {
        FibNode<E> node = new FibNode<E>(elem);
        if(rootlist.isEmpty()){
            maxNode = node;
        }
        if(comp.compare(maxNode.key, node.key) < 0){
            maxNode = node;
        }
        rootlist.addFirst(node);
        size++;

    }



    @Override
    public void merge(PriorityQueue otherQueue) {
        while (!otherQueue.isEmpty()){
            insert((E) otherQueue.deleteMax());
        }
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
            FibNode<E> max = maxNode;
            while (!maxNode.child.isEmpty()){
                insertNode(maxNode.child.remove());
            }
            rootlist.remove(maxNode);
            maxNode = Binomial();
            size--;
            return max.key;
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
        f.insert(2);
        f.insert(3);
        f.insert(0);
        System.out.println(f.deleteMax());
    }

}
