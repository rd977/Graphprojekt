package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.UnaryOperator;

public class ListQueue<E> implements PriorityQueue<E> {
    Comparator<E> comp;
    private LinkedList<E> list=new LinkedList<E>();



    public ListQueue(Comparator<E> comp) {
        this.comp = comp;
    }

    @Override
    public void insert(E elem){
        if (list.isEmpty()) {
            list.add(elem);
        }
        else {
            int i = 0;
            Iterator<E> iterator = list.iterator();
            while (iterator.hasNext() ) {
                if (comp.compare(iterator.next() , elem )> 0 ){
                    i++;
                }
                else{
                    break;
                }
            }
            list.add(i, elem);

            }
    }


    @Override
    public void merge(PriorityQueue<E> otherQueue) {
    while (!otherQueue.isEmpty()) {
        insert(otherQueue.deleteMax());
    }
    }

    @Override
    public E deleteMax() {
        if(list.isEmpty()){
            return null;
        }
        else{
            E max = list.getFirst();
            list.remove(max);
            return max;

            }

    }

    @Override
    public E max() {
        return list.getLast();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean update(E elem, E updatedElem) {
        boolean updated = false;
        LinkedList<E> temp = list;
         for(int i = temp.size()-1; i >=0 ; i--) {
            if( comp.compare(temp.get(i) , elem )==0 ){
                list.remove(i);
                insert(updatedElem);
                updated = true;
        }
         }

        return updated;

    }

    @Override
    public void map(UnaryOperator<E> f) {
        LinkedList<E> temp = new LinkedList<E>();
        while (!list.isEmpty()){
        temp.add(f.apply(deleteMax()));
        }
        list = temp;

    }


    public void p() {

        for(int i = 0 ; i < list.size() ; i++){
           System.out.println(list.get(i));
        }
    }
   /* public static void main(String[] arg){
        ListQueue<Integer> c = new ListQueue<Integer>(Comparator.<Integer>naturalOrder());
        ListQueue<Integer> g= new ListQueue<Integer>(Comparator.<Integer>naturalOrder());
        g.insert(1);
        g.insert(1);
        c.insert(1);
        c.insert(2);
        c.insert(3);
        c.insert(4);
        c.insert(1);
        c.insert(9);
        c.insert(0);
        c.insert(5);
        c.insert(1);
        c.insert(9);
        c.insert(9);
        c.insert(9);



        c.merge(g);
        //c.deleteMax();
        c.update(3,2);
        c.update(2,3);
        c.update(9,3);
        c.p();
        System.out.println("****************************************");

        c.map(x ->x*2);
        c.p();
    }*/
}
