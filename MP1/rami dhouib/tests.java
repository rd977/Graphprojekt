package de.tukl.programmierpraktikum2020.mp1;
import java.util.*;

public class tests {
    public static void main (String[] args){
        TreeMap<Integer,Integer> T= new TreeMap(Comparator.<String>naturalOrder());
        T.put(0,0);
        T.put(10,10);
        T.put(20,20);

        T.put(8,8);
        T.put(5,5);
        T.put(3,3);
        T.put(2,2);
        T.put(1,1);
        T.put(4,4);
        T.put(7,7);
        T.put(6,6);
        T.put(9,9);
        T.put(18,18);
        T.put(15,15);
        T.put(13,13);
        T.put(12,12);
        T.put(11,11);
        T.put(14,14);
        T.put(17,17);
        T.put(16,16);
        T.put(19,19);
        System.out.println(T.size);
        T.remove(10);
        System.out.println(T.root.key);
        T.remove(20);
        System.out.println(T.root.key);
        T.remove(14);
        System.out.println(T.root.key);
        T.remove(17);
        System.out.println(T.root.key);
        T.remove(12);
        System.out.println(T.root.key);
        T.remove(15);
        System.out.println(T.root.key);






        for(int i=0;i<100;i++){
            System.out.println(T.get(i));
        }

        System.out.println(T.size());
    }
}
