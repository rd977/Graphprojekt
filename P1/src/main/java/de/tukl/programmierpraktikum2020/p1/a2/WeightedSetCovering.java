package de.tukl.programmierpraktikum2020.p1.a2;
import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;

import java.util.Iterator;
import java.util.Set;

public class WeightedSetCovering<E> {
    Set<E> target;
    Set<WeightedSet<E>> s;
    PriorityQueue<WeightedSet<E>> queue;
    int l;

    public WeightedSetCovering(Set<E> targetSet, Set<WeightedSet<E>> familyOfSets,
                               PriorityQueue<WeightedSet<E>> queue) {
        target = targetSet;
        s = familyOfSets;
        this.queue = queue;
        Iterator<WeightedSet<E>> itr = s.iterator();
        while(itr.hasNext()){
            this.queue.insert(itr.next());
        }
        int l=target.size();

    }
    l=5;


}


