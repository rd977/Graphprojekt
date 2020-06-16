package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.zip.CheckedOutputStream;

public class FibonacciHeap<E> implements PriorityQueue<E>{
    FibNode<E> maxNode;
    Comparator<E> comp;
    LinkedList<FibNode<E>> rootlist = new LinkedList<>();



    public FibonacciHeap(Comparator<E> comp) {
        this.comp = comp;
    }
    public  void fixnode(FibNode<E> f, E element){
        // noch nicht klar wie diese implementiert sein
        FibNode<E> c=f.child;
        FibNode<E> c1=f.child;
        boolean b =false;
        while(c!=null){
            if (comp.compare(c.key,element) > 0){
                b=true;
                break;}
                c=c.next;}
        if ( !b){
            f.key=element;
            if (comp.compare(f.key,maxNode.key) > 0)
                maxNode=f;


        }
        else

            while(c1!=null){
                c1.parent=null;
                c1.prev=null;
                c1.next=null;
                rootlist.add(c1);
                c1=c1.next;
            }
            f.child=null;
            f.key=element;
            f.degree=0;


    }
    public void toroot(FibNode<E> f){
        f.parent.degree-=1;
        f.prev.next=f.next;
        f.marked=false;
        f.prev=null;
        f.next=null;
        rootlist.add(f);


        if (f.parent.marked==true){
            f.parent.marked=false;
            toroot(f.parent);}
        else{
            f.parent.marked=true;}
        }

    public boolean search(LinkedList<FibNode<E>> rootlist,E element){
        for (FibNode<E> f:rootlist) {
            if (comp.compare(f.key, element) == 0) {
                if (f.parent!=null){
                    f.parent.degree-=1;}
                f.prev.next=f.next;
                f.prev=null;
                f.next=null;
                this.rootlist.add(f);
                fixnode(rootlist.getLast(),element);
                if (f.parent!=null){
                    if (f.parent.marked==true)
                        toroot(f.parent);
                    else
                        f.parent.marked=true;
                    f.parent=null;
                    return true;}

            }
            else if (comp.compare(f.key,element) > 0) {
                if (f.child!=null) {
                    LinkedList<FibNode<E>> L = new LinkedList<>();
                    FibNode<E> c = f.child;
                    while (c != null) {
                        L.add(c);
                        c = c.next;

                    }
                    if (search(L, element) == true)
                        return (true);
                }
            }
        }






        return false;
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

    }



    @Override
    public void merge(PriorityQueue otherQueue) {
       while (!((FibonacciHeap<E>) otherQueue).rootlist.isEmpty()){
           insertNode(((FibonacciHeap<E>) otherQueue).rootlist.remove());
       }
        System.out.println(otherQueue.deleteMax());

    }



    @Override
    public E deleteMax() {
        if(rootlist.isEmpty()){
            return null;

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
            return n.key;
        }
        }




    @Override
    public E max() {
        return maxNode.key;
    }
  

    @Override
    public boolean isEmpty() {
        return rootlist.isEmpty();
    }

    @Override
    public boolean update(E elem, E updatedElem) {
        boolean a=(search(rootlist,elem));
         return(a);




    }



    @Override
    public void map(UnaryOperator f) {
        return;

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
        f.update(3,4);
        f.merge(x);

        System.out.println(f.deleteMax());
        System.out.println(f.max());
        System.out.println(x.isEmpty());
        FibNode<Integer> a=new FibNode(5);
        System.out.println(a.next!=null);
    }

}
