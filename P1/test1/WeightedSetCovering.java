package de.tukl.programmierpraktikum2020.p1.a2;
import de.tukl.programmierpraktikum2020.p1.a1.FibNode;
import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;

import java.util.*;
import java.util.function.UnaryOperator;

public class WeightedSetCovering<E> {
    private Set<E> targetSet;
    private Set<WeightedSet<E>> familyOfSets;
    private PriorityQueue<WeightedSet<E>> queue;
    int l;

    public WeightedSetCovering(Set<E> targetSet, Set<WeightedSet<E>> familyOfSets,
                               PriorityQueue<WeightedSet<E>> queue) {
        this.targetSet = targetSet;
        this.familyOfSets = familyOfSets;
        this.queue = queue;
        Iterator<WeightedSet<E>> itr = familyOfSets.iterator();
        while(itr.hasNext()){
            this.queue.insert(itr.next());
        }
        int l = this.targetSet.size();
    }
    
    
    public Set<WeightedSet<E>> greedyWeightedCover(){
        familyOfSets.clear();
        WeightedSet<E> temp;
        Set<WeightedSet<E>> temp2 = familyOfSets;
    
        while( !queue.isEmpty() ){
            temp = queue.deleteMax();
            familyOfSets.add( temp );
            while( !queue.isEmpty() ){
                temp2.add( queue.deleteMax().subtractWeightedSet( temp ) );
            }
            for( WeightedSet<E> s: temp2 ){
                queue.insert( s );
                temp2.remove( s );
            }
        }
        
        return familyOfSets;
    }
    //l=5;
}


