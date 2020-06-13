package de.tukl.programmierpraktikum2020.p1.a1;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.UnaryOperator;
import java.util.Comparator;



public class ListQueue<E> implements PriorityQueue<E> {
    public LinkedList<E> list=new LinkedList<E>();
    Comparator<E> comp;



    public ListQueue(Comparator<E> comparator) {
        comp = comparator;
    }

    @Override
    public void insert(E elem){
        if (list.isEmpty()) {
            list.add(elem);
        }
        else {
            int i = 0;
            Iterator<E> iterator = list.iterator();
            while (iterator.hasNext()  ) {
                if (comp.compare(iterator.next() , elem )< 0)
                   i++;
                else
                    break;
            }
            list.add(i, elem);}
    }
    @Override
    public void merge(PriorityQueue<E> otherQueue){
        while (otherQueue.isEmpty()==false) {
            list.add(otherQueue.deleteMax());
        }
    }
     public E deleteMax () {
         if (list.isEmpty()) {
             return (null);
         }

         return (list.removeLast());

     }
    @Override
    public E max () {
        if (list.isEmpty()) {
            return (null);
        }
        Iterator<E> iterator = list.iterator();
        E a=iterator.next();
        while (iterator.hasNext()) {
             a = (iterator.next());
        }
        return (a);


    }
    @Override
    public boolean isEmpty() {
        if (list.isEmpty()) {
            return (true);
        }
        else {
            return (false);
        }
    }

    @Override
    public boolean update(E elem, E updatedElem){
        if (elem==updatedElem){
            return false;
        }
        int i = 0;
        if (list.isEmpty()) {
            return (false);
        }

        else if (comp.compare(list.getLast() , elem )<0) {

        }
        else {
            while (comp.compare(list.get(i), elem) < 0) {
                i++;
            }
        }
        if (list.get(i)==elem){
            list.remove(i);
        }
        this.insert(updatedElem);
        return(true);




    }
    @Override
    public void map(UnaryOperator <E> f){
        LinkedList<E> list2=new LinkedList<E>();
        if (list.isEmpty()) {
            return ;
        }
        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(f.apply(iterator.next()));
        }
        list=list2;
        return;

    }







}