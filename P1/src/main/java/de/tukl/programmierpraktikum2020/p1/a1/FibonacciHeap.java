package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.zip.CheckedOutputStream;

public class FibonacciHeap<E> implements PriorityQueue<E>{
    FibNode<E> maxNode;
    FibNode<E> foundNode;
    FibNode<E> vater;
    int size =0;
    Comparator<E> comp;
    boolean found = false;
    LinkedList<FibNode<E>> rootlist = new LinkedList<>();



    public FibonacciHeap(Comparator<E> comp) {
        this.comp = comp;
    }





//---------------insert------------------------------------------
// --------------insertNord-------------
// Hilft beim Einfügen von childern in rootlist
    private void insertNode(FibNode<E> node) {
         if(rootlist.isEmpty()){
        maxNode = node;
           }
    if(comp.compare(maxNode.key, node.key) < 0){
        maxNode = node;
         }
    rootlist.add(node);
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
        rootlist.add(node);
        size++;

    }


//------------------------------merge--------------------------------------
    @Override
    public void merge(PriorityQueue otherQueue) {
       while (!((FibonacciHeap<E>) otherQueue).rootlist.isEmpty()){
           insertNode(((FibonacciHeap<E>) otherQueue).rootlist.remove());
       }
    }


//------------------------deletMax----------------------------------------------------------
    //------------------------DELETMAX------------------------------------------------------

    //----------------- Binomoial ----------------
    // alle Bäume die gleichen Degree haben , werden zusammengesetzt
    //gibt auch den neuen Max zurück
    private FibNode<E> Binomial() {
        HashMap<Integer, FibNode<E>> degRoots = new HashMap<Integer, FibNode<E>>();
        if(rootlist.isEmpty()) {
            return null;
        }

        FibNode<E> max = rootlist.get( 0 );
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
                FibNode newTree = mergTowtrees(rootlist.get(i) , gleichDeg);

                rootlist.set(i, newTree);
                i = rootlist.indexOf(gleichDeg) > i ? i : i - 1;
                rootlist.remove(gleichDeg);
                degRoots.remove(tempDegree);
            }
        }
        return max;
    }

    //------------------ mergTowtrees------------------------------
    // Hilfsfunktion bei Binomial Funktion
    private FibNode<E> mergTowtrees(FibNode<E> FN1, FibNode<E> FN2) {
        if (comp.compare(FN1.key, FN2.key)>0) {
            FN1.child.add(FN2);
            FN2.parent = FN1;
            FN1.degree++;
            return FN1;
        } else {
            FN2.child.add(FN1);
            FN1.parent = FN2;
            FN2.degree++;
            return FN2;
        }}
    @Override
    public E deleteMax() {
        if(rootlist.isEmpty()){
            return null;
        }else {

            while(!maxNode.child.isEmpty()) {
                insertNode(maxNode.child.remove());
            }
            FibNode<E> max = maxNode;
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
        return rootlist.isEmpty();
    }

    //------------------------------------update-----------------------------------------------------
    //------------------------------------UPDATE-----------------------------------------------------

    //-----------HilfsFunktionBeimSuchen
    private void findeNode(FibNode<E> node, E elem) {
        if(comp.compare(node.key , elem) == 0 ){
            foundNode = node ;
            vater = node.parent;
        }
        else if(comp.compare(node.key , elem) > 0){
            for(int i = 0 ; i<node.degree ; i++){
                findeNode(node.child.get(i) , elem);
            }
        }
    }

    //-------------- SearchNachElement----------------------
    private FibNode<E> find (E elem){
        foundNode = null;
        vater=null;
        for (int i = 0 ; i <rootlist.size() ; i++){
            if(foundNode!=null){
                break;
            }
            findeNode(rootlist.get(i) , elem);
        }
        return foundNode;
    }

    private void updateElem(FibNode<E> node, E updateElem) {
        node.key = updateElem;
        FibNode<E> Vater = node.parent;
        if (Vater != null && comp.compare(node.key, Vater.key) < 0) {
            cut(node, Vater);
            fix_after_cut(Vater);
        }
        if (comp.compare(node.key, maxNode.key) > 0)
            maxNode = node;
    }

    private void cut(FibNode<E> child, FibNode<E> Vater) {
        Vater.degree--;
        child.parent=null;
        child.marked = false;
        int i = Vater.child.indexOf(child);
        insertNode(Vater.child.remove(i));
    }
    private void fix_after_cut(FibNode<E> y) {
        FibNode<E> z = y.parent;
        if (z != null) {
            if (y.marked == false)
                y.marked=true;
            else {
                cut(y, z);
                fix_after_cut(z);
            }
        }
    }
    @Override
    public boolean update(E elem, E updatedElem) {
        FibNode<E> x = find(elem);
        if (x != null) {
            updateElem(x, updatedElem);
            return true;
        }

         return false;


    }


//---------------------map------------------------------------------
    private void mapNode(FibNode<E> node ,UnaryOperator<E> f ) {
        update(node.key ,(E)f.apply(node.key) );
        for (int i = 0; i < node.degree; i++) {
            mapNode(node.child.get(i), f);
        }
    }
    public void map(UnaryOperator<E> f) {
        for(int i = 0 ; i < rootlist.size();i++){
            mapNode(rootlist.get(i) , f);
        }

    }


   /* public static void main(String[] args){
        FibonacciHeap<Integer> g = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());
        FibonacciHeap<Integer> x = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());
      //  f.insert(2);
     //   f.insert(3);
       // UnaryOperator<Integer> x2 = a -> a * 2;
       // f.map(x2);
       // System.out.println("f Max = " + f.deleteMax());
        g.insert(8);
        g.insert(6);
       x.insert(1);
        x.insert(3);
        x.insert(2);
        x.insert(5);
        x.insert(6);
        x.merge(g);
       // x.map(c->c*2);
       // System.out.println(x.deleteMax());

      //System.out.println(x.rootlist.size());
       // System.out.println(x.max());
      //  FibNode<Integer> a=new FibNode(5);
      //  System.out.println(a.next!=null);
    }*/

}
