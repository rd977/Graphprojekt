package de.tukl.programmierpraktikum2020.p1.a2;
import com.sun.jdi.VoidType;
import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;

import java.util.Iterator;
import java.util.Set;
import java.util.*;

public class WeightedSetCovering<E> {
    Set<E> target;
    Set<WeightedSet<E>> s;
    PriorityQueue<WeightedSet<E>> queue1;
    int l;

    public WeightedSetCovering(Set<E> targetSet, Set<WeightedSet<E>> familyOfSets,
                               PriorityQueue<WeightedSet<E>> queue) {
        target = targetSet;
        s = familyOfSets;
        queue1 = queue;
        Iterator<WeightedSet<E>> itr = s.iterator();
        while(itr.hasNext()){
            queue1.insert(itr.next());
        }
        l=target.size();

    }
    public  Set<WeightedSet<E>> greedyWeightedCover(){
        Set<WeightedSet<E>> result=new HashSet<>();
        while(l>0){
        WeightedSet<E> a= queue1.deleteMax();;
        l-=a.getSet().size();
        result.add(a);
        queue1.map(x->x.subtractWeightedSet(a));
        }
        System.out.println(l);
        return(result);



    }

}


