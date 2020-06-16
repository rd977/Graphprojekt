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
        System.out.println("we entered fixnode");
        System.out.println(element);

        if (f.child!=null) {
            FibNode<E> c = f.child;
            FibNode<E> c1 = f.child;
            boolean b = false;
            while (c != null) {
                if (comp.compare(c.key, element) > 0) {
                    b = true;
                    break;
                }
                c = c.next;
            }
            if (!b) {
                f.key = element;
                if (comp.compare(f.key, maxNode.key) > 0)
                    maxNode = f;


            } else

                while (c1 != null) {
                    c1.parent = null;
                    c1.prev = null;
                    c1.next = null;
                    rootlist.add(c1);
                    c1 = c1.next;
                }
            f.child = null;
            f.key = element;
            f.degree = 0;

        }
        else {
            f.key = element;
            if (comp.compare(f.key, maxNode.key) > 0){
                maxNode = f;}
        }
    }
    public void toroot(FibNode<E> f){
        f.parent.degree-=1;
        if (f.prev!=null){
        f.prev.next=f.next;}
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

    public boolean search(LinkedList<FibNode<E>> rootlist,E element,E element1){
        for (FibNode<E> f:rootlist) {
            if (comp.compare(f.key, element) == 0) {
                if (f.parent!=null){
                    f.parent.degree-=1;}
                if (f.prev!=null){
                f.prev.next=f.next;}
                f.prev=null;
                f.next=null;
                this.rootlist.add(f);
                fixnode(this.rootlist.getLast(),element1);
                if (f.parent!=null){
                    if (f.parent.marked==true)
                        toroot(f.parent);
                    else
                        f.parent.marked=true;
                    f.parent=null;

                }



                return true;

            }
            else if (comp.compare(f.key,element) > 0) {
                if (f.child!=null) {
                    LinkedList<FibNode<E>> L = new LinkedList<>();
                    FibNode<E> c = f.child;
                    while (c != null) {
                        L.add(c);
                        c = c.next;

                    }
                    if (search(L, element,element1) == true)
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
    class comp1 implements Comparator<FibNode<E>>{

        public int compare(FibNode<E> a, FibNode<E> b)
        {
            return comp.compare(a.key,b.key);
        }
    }
    private void merges(){
        System.out.println("we are in merges");
        Collections.sort(rootlist, new comp1());
        boolean a=true;
        while(a){
            a=false;
            for (int i=0 ;i<rootlist.size()-1;i++){
                FibNode c=rootlist.get(i);
                FibNode b=rootlist.get(i + 1);
                if (c.degree  ==b.degree) {

                    rootlist.add(UnionZweiBäume(c, b));
                    rootlist.remove(i);
                    rootlist.remove(i);



                    Collections.sort(rootlist, new comp1());
                    a=true;



                }








            }

        }

    }

    private FibNode<E> UnionZweiBäume(FibNode<E> FN1, FibNode<E> FN2) {
        System.out.println("its between "+FN1.key+" "+FN2.key);
        if (comp.compare(FN1.key, FN2.key)>0) {
                FN2.next = FN1.child;
                if (FN1.child != null)
                    FN1.child.prev = FN2;
                FN1.child = FN2 ;
                FN2.parent = FN1;
                FN1.degree++;
                System.out.println("we return the element"+FN1.key);
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
                  System.out.println("we return the element"+FN2.key);
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
            FibNode<E> temp1;
            while (temp != null) {
                temp.parent = null;
                temp.marked = false;
                insertNode(temp);
                temp1 = temp.next;
                temp.prev= null;
                temp.next= null ;
                temp=temp1;
            }

            FibNode<E> n = maxNode;
            rootlist.remove(maxNode);
            merges();
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
        boolean a=(search(rootlist,elem,updatedElem));
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
        f.insert(
                7
        );
        f.insert(8);
        f.insert(9);
        f.insert(10);
        f.deleteMax();
        for (FibNode<Integer> i:f.rootlist){
            System.out.println(i.key);
            System.out.println(i.degree);
        }
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
