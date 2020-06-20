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
        cw = Comparator.comparingDouble ( WeightedSet :: getWeight );
        queue = new FibonacciHeap<>(cw.reversed ());


        Set<Integer> targetset=new HashSet<>();
        targetset.add(1);
        targetset.add(2);
        targetset.add(3);
        targetset.add(4);
        targetset.add(5);
        Set<Integer> subset1=new HashSet<>();
        subset1.add(1);
        subset1.add(2);
        Set<Integer> subset2=new HashSet<>();
        subset2.add(1);
        subset2.add(3);
        Set<Integer> subset3=new HashSet<>();
        subset3.add(2);
        subset3.add(3);
        subset3.add(5);
        Set<Integer> subset4=new HashSet<>();
        subset4.add(4);
        Set<Integer> subset5=new HashSet<>();
        subset5.add(1);
        subset5.add(5);
        Set<Integer> subset6=new HashSet<>();
        subset6.add(1);
        subset6.add(2);
        subset6.add(3);
        subset6.add(4);
        subset6.add(5);
        Bundle bundl1=new Bundle("Bundle1",1,subset1);
        Bundle bundl2=new Bundle("Bundle2",2,subset2);
        Bundle bundl3=new Bundle("Bundle3",10,subset3);
        Bundle bundl4=new Bundle("Bundle4",3,subset4);
        Bundle bundl5=new Bundle("Bundle5",4,subset5);
        Bundle bundl6=new Bundle("Bundle6",10,subset6);
        Set<Bundle> familyOfSets=new HashSet<>();
        familyOfSets.add(bundl1);
        familyOfSets.add(bundl2);
        familyOfSets.add(bundl3);
        familyOfSets.add(bundl4);
        familyOfSets.add(bundl5);
        familyOfSets.add(bundl6);
//-----------------------------------------------------------------------------------------
        WeightedSetCovering cover = new WeightedSetCovering(targetset , familyOfSets , queue);
        Set<WeightedSet<Integer>> coverset = cover.greedyWeightedCover();

        Set<Integer> set =new HashSet<>();
        double cost = 0;
        for( WeightedSet<Integer> i : coverset){
            set.addAll(i.getSet());
            cost+=i.getWeight()* i.getSet().size();
        }
        assertEquals( set , targetset);
        assertEquals( 10 , cost);
        //                                Bundels
//                   1      2      3        4       5      6
//               ----------------------------------------------
//          1    |  *   |   *   |       |      |   *   |      |
//               ----------------------------------------------
//          2    |  *   |       |   *   |      |       |   *  |
// Fauters        ----------------------------------------------
//          3    |      |   *   |   *   |      |       |   *  |
//               ----------------------------------------------
//          4    |      |       |       |   *  |       |   *  |
//               ----------------------------------------------
//          5    |      |       |   *   |      |   *   |      |
//               ----------------------------------------------
//                  1       2      10       3      4      10
//                                   Cots$

        // DIE optimal Bundels sind 1 ,2 4 und 5
        // Cost ist 10$ von 30$





    }





    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void weightedSetBeispiel3(PriorityQueue<WeightedSet<Integer>> queue) {
        System.out.println("Teste weightedSetBeispiel mit " + queue.getClass().getSimpleName());
        Comparator <WeightedSet <Integer >> cw;
        cw = Comparator.comparingDouble ( WeightedSet :: getWeight );
        queue = new FibonacciHeap<>(cw.reversed ());

        Set<Integer> targetset=new HashSet<>();
        targetset.add(1);
        targetset.add(2);
        targetset.add(3);
        targetset.add(4);
        targetset.add(5);
        Set<Integer> subset1=new HashSet<>();
        subset1.add(1);
        subset1.add(2);
        subset1.add(3);
        subset1.add(4);
        subset1.add(5);
        Set<Integer> subset2=new HashSet<>();
        subset2.add(1);
        subset2.add(3);
        Set<Integer> subset3=new HashSet<>();
        subset3.add(2);
        subset3.add(3);
        subset3.add(5);
        Set<Integer> subset4=new HashSet<>();
        subset4.add(4);
        Set<Integer> subset5=new HashSet<>();
        subset5.add(1);
        subset5.add(5);
        Set<Integer> subset6=new HashSet<>();
        subset6.add(1);
        subset6.add(2);
        subset6.add(3);
        subset6.add(4);
        subset6.add(5);
        Bundle bundl1=new Bundle("Bundle1",1,subset1);
        Bundle bundl2=new Bundle("Bundle2",2,subset2);
        Bundle bundl3=new Bundle("Bundle3",10,subset3);
        Bundle bundl4=new Bundle("Bundle4",3,subset4);
        Bundle bundl5=new Bundle("Bundle5",4,subset5);
        Bundle bundl6=new Bundle("Bundle6",10,subset6);
        Set<Bundle> familyOfSets=new HashSet<>();
        familyOfSets.add(bundl1);
        familyOfSets.add(bundl2);
        familyOfSets.add(bundl3);
        familyOfSets.add(bundl4);
        familyOfSets.add(bundl5);
        familyOfSets.add(bundl6);
//-----------------------------------------------------------------------------------------
        WeightedSetCovering cover = new WeightedSetCovering(targetset , familyOfSets , queue);
        Set<WeightedSet<Integer>> coverset = cover.greedyWeightedCover();

        Set<Integer> set =new HashSet<>();
        double cost = 0 ;
        for( WeightedSet<Integer> i : coverset){
            set.addAll(i.getSet());
            cost+=i.getWeight()* i.getSet().size();
        }

        assertEquals( 1 , cost);
        assertEquals( set , targetset);
    }



//                                Bundels
//                   1      2      3        4       5      6
//               ----------------------------------------------
//          1    |   *   |   *   |       |      |   *   |      |
//               ----------------------------------------------
//          2    |   *   |       |   *   |      |       |   *  |
// Fauters        ----------------------------------------------
//          3    |   *   |   *   |   *   |      |       |   *  |
//               ----------------------------------------------
//          4    |   *   |       |       |   *  |       |   *  |
//               ----------------------------------------------
//          5    |   *   |       |   *   |      |   *   |      |
//               ----------------------------------------------
//                  1       2      10       3      4      10
//                                   Cots

        // DIE optimal Bundels sind nur 1
        // Cost ist 1$ von 30$



    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void weightedSetBeispiel2(PriorityQueue<WeightedSet<Integer>> queue) {
        System.out.println("Teste weightedSetBeispiel mit " + queue.getClass().getSimpleName());
        Comparator<WeightedSet<Integer>> cw;
        cw = Comparator.comparingDouble(WeightedSet::getWeight);
        queue = new FibonacciHeap<>(cw.reversed());


        Set<Integer> targetset = new HashSet<>();
        targetset.add(1);
        targetset.add(2);
        targetset.add(3);
        targetset.add(4);
        targetset.add(5);
        Set<Integer> subset1 = new HashSet<>();
        subset1.add(1);
        Set<Integer> subset2 = new HashSet<>();
        Set<Integer> subset3 = new HashSet<>();
        subset3.add(2);
        Set<Integer> subset4 = new HashSet<>();
        subset4.add(4);
        Set<Integer> subset5 = new HashSet<>();
        subset5.add(5);
        Set<Integer> subset6 = new HashSet<>();
        subset6.add(3);

        Bundle bundl1 = new Bundle("Bundle1", 1, subset1);
        Bundle bundl2 = new Bundle("Bundle2", 2, subset2);
        Bundle bundl3 = new Bundle("Bundle3", 10, subset3);
        Bundle bundl4 = new Bundle("Bundle4", 3, subset4);
        Bundle bundl5 = new Bundle("Bundle5", 4, subset5);
        Bundle bundl6 = new Bundle("Bundle6", 10, subset6);
        Set<Bundle> familyOfSets = new HashSet<>();
        familyOfSets.add(bundl1);
        familyOfSets.add(bundl2);
        familyOfSets.add(bundl3);
        familyOfSets.add(bundl4);
        familyOfSets.add(bundl5);
        familyOfSets.add(bundl6);
//-----------------------------------------------------------------------------------------
        WeightedSetCovering cover = new WeightedSetCovering(targetset, familyOfSets, queue);
        Set<WeightedSet<Integer>> coverset = cover.greedyWeightedCover();

        Set<Integer> set =new HashSet<>();
        double cost = 0 ;
        for( WeightedSet<Integer> i : coverset){
            set.addAll(i.getSet());
            cost+=i.getWeight()* i.getSet().size();
        }

        assertEquals( 28 , cost);
        assertEquals( set , targetset);


//                                Bundels
//                   1      2      3        4       5      6
//               ----------------------------------------------
//          1    |   *  |      |       |      |       |       |
//               ----------------------------------------------
//          2    |      |      |   *   |      |       |       |
// Fauters        ----------------------------------------------
//          3    |     |       |       |      |       |   *   |
//               ----------------------------------------------
//          4    |     |        |      |   *  |       |       |
//               -----------------------------------------------
//          5    |      |       |      |      |     *  |       |
//               ----------------------------------------------
//                  1       2      10       3      4      10
//                                   Cots$


        // DIE optimal Bundels sind nur 1 ,3,4,5,6
        // cost 28$ von 30$


    }





}


