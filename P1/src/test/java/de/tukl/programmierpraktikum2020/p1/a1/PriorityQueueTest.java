package de.tukl.programmierpraktikum2020.p1.a1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
        implementations.add(new FibonacciHeap<>(Comparator.<Integer>naturalOrder()));
        return implementations;
    }
    public static List<Arguments> getTwoPriorityQueueInstances() {
        List<Arguments> implementations = new LinkedList<>();
        // Um Compilefehler zu verhindern, sind die Instanziierungen der PriorityQueue Implementierungen auskommentiert.
        // Kommentieren Sie die Zeilen ein, sobald Sie die entsprechenden Klassen implementiert haben.
        implementations.add(Arguments.of(new ListQueue<>(Comparator.<Integer>naturalOrder()), new ListQueue<>(Comparator.<Integer>naturalOrder())));
        //implementations.add(Arguments.of(new SkewHeap<>(Comparator.<Integer>naturalOrder()), new SkewHeap<>(Comparator.<Integer>naturalOrder())));
        implementations.add(Arguments.of(new FibonacciHeap<>(Comparator.<Integer>naturalOrder()), new FibonacciHeap<>(Comparator.<Integer>naturalOrder())));
        return implementations;
    }
//
//
//
//
//           91 ---------------88 -----------86 -------26
//         /  / \             /  \           |
//        82  90  2          60   33         68
//      / \   |             |
//     53  62 88           59
//     |
//     9
//




    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void isEmptyTest(PriorityQueue<Integer> queue) {
        System.out.println("Teste isEmpty mit " + queue.getClass().getSimpleName());

        // Test: eine frisch initialisierte Queue ist leer


    //    -----------------------------------------------


        assertTrue(queue.isEmpty());
    }


    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void insertTest(PriorityQueue<Integer> queue) {
        System.out.println("Teste insert mit " + queue.getClass().getSimpleName());
        
        // Test: insert
        assertTrue(queue.isEmpty());


//         --5--


        queue.insert(5);
        assertTrue(!queue.isEmpty());

    }
    
    
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void maxTest(PriorityQueue<Integer> queue) {
        System.out.println("Teste max mit " + queue.getClass().getSimpleName());
        assertTrue(queue.isEmpty());
        queue.insert( 2 );
        queue.insert( 93 );
        queue.insert( 91 );
        queue.insert( 90 );
        queue.insert( 88 );
        queue.insert( 53 );
        queue.insert( 9 );
        queue.insert( 62 );
        queue.insert( 82 );
        queue.insert( 88 );
        queue.insert( 33 );
        queue.insert( 59 );
        queue.insert( 60 );
        queue.insert( 86 );
        queue.insert( 68 );
        queue.insert( 26 );
        assertEquals(93,queue.max());

    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void deletMax(PriorityQueue<Integer> queue) {
        System.out.println("Teste max mit " + queue.getClass().getSimpleName());
        // deletMax mit einer leeren rootliste
        assertEquals(null , queue.deleteMax());

    }
    
    
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void updateTest(PriorityQueue<Integer> queue) {
        System.out.println("Teste Update mit " + queue.getClass().getSimpleName());
        queue.insert( 2 );
        queue.insert( 93 );
        queue.insert( 91 );
        queue.insert( 90 );
        queue.insert( 88 );
        queue.insert( 53 );
        queue.insert( 9 );
        queue.insert( 62 );
        queue.insert( 82 );
        queue.insert( 88 );
        queue.insert( 33 );
        queue.insert( 59 );
        queue.insert( 60 );
        queue.insert( 86 );
        queue.insert( 68 );
        queue.insert( 26 );

        //Tests für Update von ELemente die in der Queue nicht vorhand
        assertEquals(93,queue.deleteMax());
        assertTrue(!queue.update(44, 15));
        assertTrue(!queue.update(-6, 47));
        assertTrue(!queue.update(0, 47));


    }


    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void updateTest0(PriorityQueue<Integer> queue) {
        System.out.println("Teste Update mit " + queue.getClass().getSimpleName());

        // Update Roots wenn sie keine Kinder haben
        queue.insert(2);
        queue.insert(93);
        queue.insert(91);
        queue.insert(90);
        assertTrue(queue.update(2, 3));
        assertTrue(queue.update(91, 2));
        assertTrue(queue.update(90, 7));
        assertTrue(queue.update(3, 0));
        assertTrue(!queue.update(-6, 47));
    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void updateTest4(PriorityQueue<Integer> queue) {
        System.out.println("Teste Update mit " + queue.getClass().getSimpleName());
        // Update Roots mit einem größen Schlüssel
        queue.insert( 2 );
        queue.insert( 93 );
        queue.insert( 91 );
        queue.insert( 90 );
        queue.insert( 89 );
        queue.insert( 53 );
        queue.insert( 9 );
        queue.insert( 62 );
        queue.insert( 82 );
        queue.insert( 88 );
        queue.insert( 33 );
        queue.insert( 59 );
        queue.insert( 60 );
        queue.insert( 86 );
        queue.insert( 68 );
        queue.insert( 26 );
        assertEquals(93 , queue.deleteMax());


//           91 ---------------88 -----------86 -------26
//         /  / \             /  \           |
//        82  90  2          60   33         68
//      / \   |             |
//     53  62 88           59
//     |
//     9
        assertTrue(queue.update(91, 101));
        assertTrue(queue.update(88, 106));
        assertTrue(queue.update(86, 200));
        assertTrue(queue.update(26, 27));

//           101 ---------------106 -----------200 -------27
//         /  / \             /  \           |
//        82  90  2          60   33         68
//      / \   |             |
//     53  62 88           59
//     |
//     9
//


    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void updateTest6(PriorityQueue<Integer> queue) {
        System.out.println("Teste Update mit " + queue.getClass().getSimpleName());
        //Update Root mit kleineren Werten
        queue.insert( 2 );
        queue.insert( 93 );
        queue.insert( 91 );
        queue.insert( 90 );
        queue.insert( 88 );
        queue.insert( 53 );
        queue.insert( 9 );
        queue.insert( 62 );
        queue.insert( 82 );
        queue.insert( 88 );
        queue.insert( 33 );
        queue.insert( 59 );
        queue.insert( 60 );
        queue.insert( 86 );
        queue.insert( 68 );
        queue.insert( 26 );
        assertEquals(93 , queue.deleteMax());

//           91 ---------------88 -----------86 -------26
//         /  / \             /  \           |
//        82  90  2          60   33         68
//      / \   |             |
//     53  62 88           59
//     |
//     9
//
        assertTrue(queue.update(91, 2));
        assertTrue(queue.update(88, 3));
        assertTrue(queue.update(86, 5));
        assertTrue(queue.update(26, -6));


//         --2---3---5------ -6----2-----90---------82 ------3-----60-------68
//                                        |         /  \            |
//                                        89       53   62          59
//                                                 |
//                                                 9


    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void updateTest7(PriorityQueue<Integer> queue) {
        System.out.println("Teste Update mit " + queue.getClass().getSimpleName());
        //Update Kinder mit kleineren Werten
        queue.insert( 2 );
        queue.insert( 93 );
        queue.insert( 91 );
        queue.insert( 90 );
        queue.insert( 89 );
        queue.insert( 53 );
        queue.insert( 9 );
        queue.insert( 62 );
        queue.insert( 82 );
        queue.insert( 88 );
        queue.insert( 33 );
        queue.insert( 59 );
        queue.insert( 60 );
        queue.insert( 86 );
        queue.insert( 68 );
        queue.insert( 26 );
        assertEquals(93 , queue.deleteMax());

//           91 ---------------88 -----------86 -------26
//         /  / \             /  \           |
//        82  90  2          60   33         68
//      / \   |             |
//     53  62 88           59
//     |
//     9

        assertTrue(queue.update(9, 4));
        assertTrue(queue.update(89, 3));
        assertTrue(queue.update(68, 5));
        assertTrue(queue.update(2, -6));
//
//           91 ---------------88 -----------86 -------26
//         /  / \             /  \           |
//       82  90  -6        60   33           5
//      / \   |             |
//     53  62 3            59
//     |
//     4
//



    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void updateTest9(PriorityQueue<Integer> queue) {
        System.out.println("Teste Update mit " + queue.getClass().getSimpleName());
        //Update  Kinder  mit kleineren Werten als die Wetre von ihren kinder
        queue.insert(2);
        queue.insert(93);
        queue.insert(91);
        queue.insert(90);
        queue.insert(89);
        queue.insert(53);
        queue.insert(9);
        queue.insert(62);
        queue.insert(82);
        queue.insert(88);
        queue.insert(33);
        queue.insert(59);
        queue.insert(60);
        queue.insert(86);
        queue.insert(68);
        queue.insert(26);
        assertEquals(93, queue.deleteMax());


//           91 ---------------88 -----------86 -------26
//         /  / \             /  \           |
//        82  90  2          60   33         68
//      / \   |             |
//     53  62 88           59
//     |
//     9
//
        assertTrue(queue.update(53, 8));
        assertTrue(queue.update(90, 5));
        assertTrue(queue.update(60, 13));

//
//            91 ---------88 ------86----26----9---89---59---
//          /   \        /  \       |
//         82   2       13  33      68
//        / \
//       8  62
//
//
//


    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void updateTest88(PriorityQueue<Integer> queue) {
        System.out.println("Teste Update mit " + queue.getClass().getSimpleName());
        //Update lezte Kinder(leafs) mit größeren Werten als die Wetre von ihren perent
        queue.insert(2);
        queue.insert(93);
        queue.insert(91);
        queue.insert(90);
        queue.insert(89);
        queue.insert(53);
        queue.insert(9);
        queue.insert(62);
        queue.insert(82);
        queue.insert(88);
        queue.insert(33);
        queue.insert(59);
        queue.insert(60);
        queue.insert(86);
        queue.insert(68);
        queue.insert(26);
        assertEquals(93, queue.deleteMax());

//           91 ---------------88 -----------86 -------26
//         /  / \             /  \           |
//        82  90  2          60   33         68
//      / \   |             |
//     53  62 88           59
//     |
//     9
        assertTrue(queue.update(9, 54));
        assertTrue(queue.update(89, 91));
        assertTrue(queue.update(68, 87));
        assertTrue(queue.update(2, 100));
//
//            91 ---------88 ------86 --26----54---91---87---100
//         /  /          /  \
//        82  90        60   33
//       / \            |
//      53   62         59
//
//
//


    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void updateTest10(PriorityQueue<Integer> queue) {
        System.out.println("Teste Update mit " + queue.getClass().getSimpleName());
        //Update lezte Kinder(leafs) mit größeren Werten als die Wetre von ihren perent
        queue.insert(2);
        queue.insert(93);
        queue.insert(91);
        queue.insert(90);
        queue.insert(89);
        queue.insert(53);
        queue.insert(9);
        queue.insert(62);
        queue.insert(82);
        queue.insert(88);
        queue.insert(33);
        queue.insert(59);
        queue.insert(60);
        queue.insert(86);
        queue.insert(68);
        queue.insert(26);
        assertEquals(93, queue.deleteMax());

//           91 ---------------88 -----------86 -------26
//         /  / \             /  \           |
//        82  90  2          60   33         68
//      / \   |             |
//     53  62 88           59
//     |
//     9
        assertTrue(queue.update(9, 54));
        assertTrue(queue.update(89, 91));
        assertTrue(queue.update(68, 87));
        assertTrue(queue.update(2, 100));
//
//            91 ---------88 ------86 --26----54---91---87---100
//         /  /          /  \
//        82  90        60   33
//       / \            |
//      53   62         59
//
        assertEquals(100, queue.deleteMax());
//


    }

        @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void mapTest(PriorityQueue<Integer> queue) {
            System.out.println("Teste map mit " + queue.getClass().getSimpleName());


            queue.insert(2);
            queue.insert(93);
            queue.insert(91);
            queue.insert(90);
            queue.insert(89);
            queue.insert(53);
            queue.insert(9);
            queue.insert(62);
            queue.insert(82);
            queue.insert(88);
            queue.insert(33);
            queue.insert(59);
            queue.insert(60);
            queue.insert(86);
            queue.insert(68);
            queue.insert(26);
            assertEquals(93, queue.deleteMax());

//           91 ---------------88 -----------86 -------26
//         /  / \             /  \           |
//        82  90  2          60   33         68
//      / \   |             |
//     53  62 88           59
//     |
//     9
            queue.map(x -> x * 3);


            //      273---270---297--264--258---246----204---186----180--177----159---99----78---27----6

            assertEquals(273, queue.deleteMax());


        }
    
    
    @ParameterizedTest
    @MethodSource("getTwoPriorityQueueInstances")
    public void mergeTest(PriorityQueue<Integer> queue, PriorityQueue<Integer> queue2) {
        System.out.println("Teste merge mit " + queue.getClass().getSimpleName() + " und " + queue2.getClass().getSimpleName());

        for(int i = 0 ; i <5 ; i++){
            queue.insert(i);
        }
        for(int i = 20 ; i <100 ; i++){
            queue2.insert(i);
        }
        queue.merge(queue2);
        assertEquals(99, queue.deleteMax());
        assertTrue(queue2.isEmpty());
        queue2.merge(queue);
        assertEquals(98, queue2.deleteMax());
        assertTrue(queue.isEmpty());


        
    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void IterdeletmaxLEER(PriorityQueue<Integer> queue) {
        System.out.println("Teste deleteMax mit " + queue.getClass().getSimpleName());
        assertTrue(queue.isEmpty());
        assertEquals (null,queue.deleteMax());
        queue.insert(1);
        assertTrue(!queue.isEmpty());
    }
    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void Iterdeletmax(PriorityQueue<Integer> queue) {
        System.out.println("Teste deleteMax mit " + queue.getClass().getSimpleName());
        queue.insert(5);
        queue.insert(2);
        queue.insert(7);
        queue.insert(1);
        queue.insert(-5);
        queue.insert(8);
        queue.insert(9);
        queue.insert(0);
        while (!queue.isEmpty()){
            queue.deleteMax();
        }
        assertTrue(queue.isEmpty());

    }


    @ParameterizedTest
    @MethodSource("getTwoPriorityQueueInstances")
    public void Mix(PriorityQueue<Integer> queue, PriorityQueue<Integer> queue2) {
        System.out.println("Teste merge mit " + queue.getClass().getSimpleName() + " und " + queue2.getClass().getSimpleName());

        queue.insert(2);
        queue.insert(3);
        queue.insert(5);
        queue.insert(8);
        queue.insert(7);
        queue.insert(9);
        queue.insert(11);
        queue.insert(29);
        queue.insert(10);
        queue.insert(13);
        queue.insert(14);
        queue.insert(15);
        queue.insert(12);
        queue.insert(28);
        queue.insert(25);
        queue.insert(30);
        queue.insert(0);
        queue.insert(26);
        queue.insert(39);
        queue.insert(47);
        queue.insert(27);
        queue.insert(41);
        queue.insert(42);
        queue.insert(50);
        queue.insert(17);
        queue.insert(23);
        queue.insert(33);
        queue.insert(40);
        queue.insert(19);
        queue.insert(20);
        queue.insert(1);
        queue.insert(78);
        queue.insert(100);

        queue2.insert(2);
        queue2.insert(93);
        queue2.insert(91);
        queue2.insert(90);
        queue2.insert(89);
        queue2.insert(53);
        queue2.insert(9);
        queue2.insert(62);
        queue2.insert(82);
        queue2.insert(88);
        queue2.insert(33);
        queue2.insert(59);
        queue2.insert(60);
        queue2.insert(86);
        queue2.insert(68);
        queue2.insert(26);
        assertEquals(100 ,queue.deleteMax());
        assertTrue(queue.update(25,31));
        assertTrue(queue.update(11,30));
        assertTrue(queue.update(3,9));
        queue.update(5,10);
        assertEquals(78, queue.deleteMax());
        queue.merge(queue2);
        assertEquals(93, queue.deleteMax());



    }



}
