package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.UnaryOperator;

public class ListQueue<E> implements PriorityQueue<E> {
    Comparator<E> comp;
    private LinkedList<E> list = new LinkedList<>();


    public ListQueue(Comparator<E> comp) {
        this.comp = comp;
    }

    @Override
    public void insert(E elem) {
        if (list.isEmpty()) {
            list.add(elem);
        }
        else {
            int i = 0;
            while (i <= list.size()-1 && comp.compare(list.get(i), elem) > 0) {
                i++;
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
        if (list.isEmpty()) {
            return null;
        } else {
            E max = max();
            list.remove(max);
            return max;

        }

    }

    @Override
    public E max() {
        if (comp.compare(list.getFirst(), list.getLast()) > 0) {
          return list.getFirst();
        } else {
           return list.getLast();
        }

    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean update(E elem, E updatedElem) {
        boolean updated = false;
        for (E e : list) {
            if(comp.compare(e, elem) == 0){
                list.remove(e);
                insert(updatedElem);
                updated = true;
                break;
            }
            }
        return updated;
        }

    @Override
    public void map(UnaryOperator<E> f) {
        LinkedList<E> temp = new LinkedList<>();
        while (!list.isEmpty()) {
            temp.add(f.apply(deleteMax()));
        }
        list = temp;

    }

 /*   public void p() {

        for (E e : list) {
            System.out.println(e);
        }
    }




    public static void main(String[] arg){
        ListQueue<Integer> c = new ListQueue<Integer>(Comparator.<Integer>naturalOrder());
        ListQueue<Integer> g= new ListQueue<Integer>(Comparator.<Integer>naturalOrder());

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


       // System.out.println("****************************************");
      //  System.out.println(c.list.size());
      //  c.map(x ->x*-2);
        //System.out.println(c.list.size());
       // System.out.println("the max " + c.max());
        c.p();
    }*/
}
