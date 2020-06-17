package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.UnaryOperator;

public class FibonacciHeap<E> implements PriorityQueue<E>{
    FibNode<E> maxNode;
    FibNode<E> foundNode;
    FibNode<E> vater;
     int size =0;
    Comparator<E> comp;
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
        FibNode<E> node = new FibNode<>(elem);
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
        HashMap<Integer, FibNode<E>> degRoots = new HashMap<>();
        if(rootlist.isEmpty()) {
            return null;
        }

        FibNode<E> max = rootlist.getFirst();
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
                FibNode<E> sameDegree = degRoots.get(tempDegree);
                FibNode<E> newTree = mergTowtrees(rootlist.get(i) , sameDegree);

                rootlist.set(i, newTree);
                i = rootlist.indexOf(sameDegree) > i ? i : i - 1;
                rootlist.remove(sameDegree);
                degRoots.remove(tempDegree);
            }
        }
        return max;
    }

    //------------------ mergTowtrees------------------------------
    // Hilfsfunktion bei Binomial Funktion
    private FibNode<E> mergTowtrees(FibNode<E> FiboNode1, FibNode<E> FiboNode2) {
        if (comp.compare(FiboNode1.key, FiboNode2.key)>0) {
            FiboNode1.child.add(FiboNode2);
            FiboNode2.parent = FiboNode1;
            FiboNode1.degree++;
            return FiboNode1;
        } else {
            FiboNode2.child.add(FiboNode1);
            FiboNode1.parent = FiboNode2;
            FiboNode2.degree++;
            return FiboNode2;
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
    private void updateMax(){
        for(FibNode<E> i : rootlist){
            if(comp.compare(i.key,maxNode.key)>0){
                maxNode = i;
            }
        }
    }
    private FibNode<E> find (E elem){
        foundNode = null;
        vater=null;
        for (FibNode<E> eFibNode : rootlist) {
            if (foundNode != null) {
                break;
            }
            findeNode(eFibNode, elem);
        }
        return foundNode;
    }

    private void updateElem(FibNode<E> node, E updateElem) {
        E value = node.key;
        node.key = updateElem;
        if(vater==null){
            if(comp.compare(node.key , value) >= 0){
                return;
            }
            else {
                if(node.child.isEmpty()){
                    updateMax();
                }
                else {
                    while (!node.child.isEmpty()) {
                        cut(node.child.remove(), node);
                    }

                }
            }
        }
        if (vater != null && comp.compare(node.key, vater.key) > 0) {
            int i = vater.child.indexOf(node);
            cut(vater.child.remove(i), vater);
            fix_after_cut(vater);
        }

    }

    private void cut(FibNode<E> child, FibNode<E> Vater) {
        Vater.degree--;
        child.parent=null;
        child.marked = false;
        insertNode(child);


    }
    private void fix_after_cut(FibNode<E> y) {
        FibNode<E> z = y.parent;
        if (z != null) {
            if (!y.marked) {
                y.marked = true;
            }
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
        update(node.key , f.apply(node.key));
        for (int i = 0; i < node.degree; i++) {
            mapNode(node.child.get(i), f);
        }
    }
    public void map(UnaryOperator<E> f) {
        for (FibNode<E> eFibNode : rootlist) {
            mapNode(eFibNode, f);
        }

    }
    private void print(FibNode<E> node ) {
        System.out.println(node.key);
        for (int i = 0; i < node.degree; i++) {
            print(node.child.get(i));
        }
    }
    public void printRoot() {
        for (FibNode<E> eFibNode : rootlist) {
            System.out.println("--");
            print(eFibNode);
        }

    }

    public static void main(String[] args){
        FibonacciHeap<Integer> g = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());
        FibonacciHeap<Integer> x = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());
      //  f.insert(2);
     //   f.insert(3);
       // UnaryOperator<Integer> x2 = a -> a * 2;
       // f.map(x2);
       // System.out.println("f Max = " + f.deleteMax());

        g.insert(5);
        g.insert(2);
        g.insert(3);
        g.insert(20);
        g.insert(4);
        g.insert(777);
        g.insert(9);
        g.insert(12);
        x.insert(100);
        x.insert(50);
        x.insert(88);
        x.insert(63);
        x.insert(77);
        x.insert(78);
        x.insert(9);
        x.insert(1);
        x.update(9, 11);
        x.update(12, 6);
        x.update(77, 0);
        x.merge(g);
        x.map(c->c*2);
        x.update(8, 800);

    System.out.println(x.deleteMax() + " is the max has been deleted");
        x.printRoot();
      //System.out.println(x.rootlist.size());
      // System.out.println(x.max());
     //  System.out.println(x.size);
      //  FibNode<Integer> a=new FibNode(5);
       //System.out.println(x.find(1554)==null);
    }


}
