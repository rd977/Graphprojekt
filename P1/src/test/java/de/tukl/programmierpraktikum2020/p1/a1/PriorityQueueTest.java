package de.tukl.programmierpraktikum2020.p1.a1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        queue.insert(5);
        queue.insert(2);
        queue.insert(3);
        queue.insert(1);
        queue.insert(4);
        queue.insert(8);
        queue.insert(9);
        queue.insert(12);
        assertEquals(12, queue.max());
        queue.update(9, 11);
        queue.update(12, 6);
        assertEquals(11, queue.max());
        assertEquals(11, queue.deleteMax());
        assertEquals(8, queue.max());
        queue.map(x -> x * 2);
        assertEquals(16, queue.max());
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel2(PriorityQueue<Integer> queue,PriorityQueue<Integer> queue2) {
        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());

        // Test: eine frisch initialisierte Queue ist leer
        assertTrue(queue.isEmpty());
        queue.insert(5);
        queue.insert(2);
        queue.insert(3);
        queue.insert(1);
        queue.insert(4);
        queue.insert(8);
        queue.insert(9);
        queue.insert(12);
        assertEquals(12, queue.max());
        queue.update(9, 11);
        queue.update(12, 6);
        assertEquals(11, queue.max());
        assertEquals(11, queue.deleteMax());
        assertEquals(9, queue.max());
        queue2.insert(6);
        queue2.insert(5);
        queue2.insert(4);
        queue2.insert(3);
        queue2.insert(2);
        queue2.insert(1);
        queue2.merge(queue);
        assertEquals(9, queue2.max());

    }






}
