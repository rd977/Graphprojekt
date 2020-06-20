package de.tukl.programmierpraktikum2020.p1.a2;

import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;

import java.util.*;

public class WeightedSetCovering<E> {
    Set<E> targetSet;
    Set<WeightedSet<E>> familyOfSets;
    PriorityQueue<WeightedSet<E>> queue;
    int curentFauters ;
    public WeightedSetCovering(Set<E> targetSet, Set<WeightedSet<E>> familyOfSets,
                               PriorityQueue<WeightedSet<E>> queue) {
        this.targetSet = targetSet;
        this.familyOfSets = familyOfSets;
        this.queue = queue;
        this.curentFauters = targetSet.size();
        for (WeightedSet<E> weightedset : familyOfSets) {
            queue.insert(weightedset);
        }

    }

    public Set<WeightedSet<E>> greedyWeightedCover() {
        Set<WeightedSet<E>> Weighted = new HashSet<>();
        while (curentFauters >0) {
            WeightedSet<E>  temp = queue.deleteMax();
            Weighted.add(temp);
            queue.map(x -> x.subtractWeightedSet(temp));
            curentFauters -= temp.getSet().size();
            }

        return Weighted;
    }

}