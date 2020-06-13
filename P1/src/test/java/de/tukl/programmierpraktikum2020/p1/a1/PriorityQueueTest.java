package de.tukl.programmierpraktikum2020.p1.a1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriorityQueueTest {
    /**
     * Diese Methode wird verwendet, um Instanzen von PriorityQueue Implementierungen an Testmethoden zu übergeben.
     */
    public static List<PriorityQueue<Integer>> getPriorityQueueInstances() {
        List<PriorityQueue<Integer>> implementations = new LinkedList<>();
        // Um Compilefehler zu verhindern, sind die Instanziierungen der PriorityQueue Implementierungen auskommentiert.
        // Kommentieren Sie die Zeilen ein, sobald Sie die entsprechenden Klassen implementiert haben.
        implementations.add(new ListQueue<>(Comparator.<Integer>naturalOrder()));
        //implementations.add(new SkewHeap<>(Comparator.<Integer>naturalOrder()));
        //implementations.add(new FibonacciHeap<>(Comparator.<Integer>naturalOrder()));
        return implementations;
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel(PriorityQueue<Integer> queue) {
        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());

        // Test: eine frisch initialisierte Queue ist leer
        assertTrue(queue.isEmpty());
    }


    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel2(PriorityQueue<Integer> queue) {
        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());

        // Test: max
        queue.insert(5);
        queue.insert(2);
        queue.insert(3);
        queue.insert(1);
        queue.insert(4);
        queue.insert(8);
        queue.insert(9);
        queue.insert(12);
        assertEquals(12, queue.max());



    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel3(PriorityQueue<Integer> queue) {
        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());

        //Test : max after Update
        queue.insert(5);
        queue.insert(2);
        queue.insert(12);
        queue.insert(3);
        queue.insert(1);
        queue.insert(4);
        queue.insert(8);
        queue.insert(9);
        queue.update(12,13);
        assertEquals(13, queue.max());
    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel4(PriorityQueue<Integer> queue) {
        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());

        //Test : max after map
        queue.insert(0);
        queue.insert(1);
        queue.insert(12);
        queue.insert(3);
        queue.insert(1);
        queue.insert(4);
        queue.insert(2);
        queue.insert(9);
        queue.map(x->x*2);
        assertEquals(24,queue.max() );

    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel5(PriorityQueue<Integer> queue) {
        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());

        //Test : deletmax
        queue.insert(0);
        queue.insert(1);
        queue.insert(12);
        queue.insert(3);
        queue.insert(1);
        queue.insert(4);
        queue.insert(2);
        queue.insert(9);
        queue.deleteMax();
        assertEquals(9,queue.max() );

    }
   /* @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel6(PriorityQueue<Integer> queue, PriorityQueue<Integer> queue2) {
        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());
        //Test : max after merge


        queue.insert(0);
        queue.insert(1);
        queue.insert(12);
        queue.insert(3);
        queue.insert(1);
        queue.insert(4);
        queue.insert(2);
        queue.insert(9);
        queue2.insert(7);
        queue2.insert(14);
        queue2.insert(8);
        queue2.insert(20);
        assertEquals(20,queue.max() );

    }
*/




}
