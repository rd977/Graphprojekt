package de.tukl.programmierpraktikum2020.p1.a2;

import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;
import de.tukl.programmierpraktikum2020.p1.a1.*;
import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueueTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WeightedSetCoveringTest {
    public static List<PriorityQueue<Integer>> getPriorityQueueInstances() {
        // Wir nutzen einfach die PriorityQueue Instanzen aus der anderen Testklasse.
        return PriorityQueueTest.getPriorityQueueInstances();
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void weightedSetBeispiel(PriorityQueue<WeightedSet<Integer>> queue) {
        System.out.println("Teste weightedSetBeispiel mit " + queue.getClass().getSimpleName());
        Comparator <WeightedSet <Integer >> cw;
        cw = Comparator . comparingDouble ( WeightedSet :: getWeight );
        queue = new  ListQueue  <>(cw.reversed ());
        Set<Integer> targetset=new HashSet<>();
        targetset.add(1);
        targetset.add(2);
        targetset.add(3);
        targetset.add(4);
        targetset.add(5);
        Set<Integer> b1=new HashSet<>();
        b1.add(1);
        b1.add(2);
        Set<Integer> b2=new HashSet<>();
        b2.add(1);
        b2.add(3);
        Set<Integer> b3=new HashSet<>();
        b3.add(2);
        b3.add(3);
        b3.add(5);
        Set<Integer> b4=new HashSet<>();
        b4.add(4);
        Set<Integer> b5=new HashSet<>();
        b5.add(1);
        b5.add(5);
        Set<Integer> b6=new HashSet<>();
        b6.add(2);
        b6.add(3);
        b6.add(4);
        Bundle bundl1=new Bundle("Bundle1",1,b1);
        Bundle bundl2=new Bundle("Bundle2",2,b2);
        Bundle bundl3=new Bundle("Bundle3",10,b3);
        Bundle bundl4=new Bundle("Bundle4",3,b4);
        Bundle bundl5=new Bundle("Bundle5",4,b5);
        Bundle bundl6=new Bundle("Bundle6",10,b6);
        Set<Bundle> familyOfSets=new HashSet<>();
        familyOfSets.add(bundl1);
        familyOfSets.add(bundl2);
        familyOfSets.add(bundl3);
        familyOfSets.add(bundl4);
        familyOfSets.add(bundl5);
        familyOfSets.add(bundl6);
        WeightedSetCovering ws = new WeightedSetCovering(targetset,familyOfSets,queue);
        Set<Bundle> result1= ws.greedyWeightedCover();
        Set<Bundle> result=new HashSet<>();
        result.add(bundl1);
        result.add(bundl2);
        result.add(bundl4);
        result.add(bundl5);
        // diese ist nur erforderlich in diese fall( mit loschung der features)
        Set<String> names= new HashSet<>();
        Set<String> names1= new HashSet<>();
        Iterator<Bundle> itr = result.iterator();
        while(itr.hasNext()){
            names.add((itr.next().name));
        }
        Iterator<Bundle> itr1 = result1.iterator();
        while(itr.hasNext()){
            names1.add((itr.next().name));
        }
        assert(names.containsAll(names));
        assert(names1.containsAll(names1));





    }
}
