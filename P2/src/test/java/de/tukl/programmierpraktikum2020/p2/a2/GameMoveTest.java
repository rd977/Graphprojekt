package de.tukl.programmierpraktikum2020.p2.a2;

import de.tukl.programmierpraktikum2020.p2.a1.Graph;
import de.tukl.programmierpraktikum2020.p2.a1.GraphImpl;
import de.tukl.programmierpraktikum2020.p2.a1.InvalidNodeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameMoveTest {
    @Test
    public void example() throws Exception {
        Graph<Color , Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        GameMove gm = new GameMoveImpl(graph);
        gm.setColor( a , Color.GREEN);
        assertEquals(Color.GREEN ,graph.getData(a) );
    }
    @Test
    public void example2() throws Exception {
        Graph<Color , Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        int b = graph.addNode(Color.WHITE);
        graph.addEdge(a ,b , 2);
        GameMove gm = new GameMoveImpl(graph);
        gm.setColor( a , Color.GREEN);
        assertEquals(Color.GREEN ,graph.getData(b) );
    }
    @Test
    public void example3() throws Exception {
        //Beispiel 1 (2 Spieler, setColor und increaseWeight)
        Graph<Color , Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        int b = graph.addNode(Color.WHITE);
        int c = graph.addNode(Color.WHITE);
        graph.addEdge(a ,b , 2);
        graph.addEdge(b ,a , 3);
        graph.addEdge(c ,a , 1);
        graph.addEdge(c ,b , 2);
        GameMove gm = new GameMoveImpl(graph);
        gm.setColor( c , Color.RED);
        assertEquals(Color.RED ,graph.getData(c) );
        gm.increaseWeight(c ,b);
        assertEquals(Color.RED ,graph.getData(a) );
        assertEquals(Color.RED ,graph.getData(b) );
        gm.setColor( c , Color.RED);
        assertEquals(Color.RED ,graph.getData(c) );
        gm.setColor( c , Color.GREEN);
        assertEquals(Color.GREEN ,graph.getData(c) );
        assertEquals(Color.GREEN ,graph.getData(a) );
        assertEquals(Color.GREEN ,graph.getData(b) );
    }
    @Test
    public void example4() throws Exception {
        //Beispiel 2 (2 Spieler, setColor und decreaseWeight)
        Graph<Color , Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        int b = graph.addNode(Color.WHITE);
        int c = graph.addNode(Color.WHITE);
        int d = graph.addNode(Color.WHITE);
        graph.addEdge(a ,c , 1);
        graph.addEdge(a ,b , 1);
        graph.addEdge(c ,b , 3);
        graph.addEdge(d ,b , 1);
        graph.addEdge(d ,c , 1);
        graph.addEdge(c ,c , 3);
        GameMove gm = new GameMoveImpl(graph);
        gm.setColor( a , Color.RED);
        assertEquals(Color.RED ,graph.getData(a) );

        gm.setColor( c , Color.GREEN);
        assertEquals(Color.GREEN ,graph.getData(c) );
        assertEquals(Color.GREEN ,graph.getData(b) );

        gm.setColor(d, Color.RED);
        assertEquals(Color.RED ,graph.getData(d) );

        gm.decreaseWeight(c ,b);
        assertEquals(Color.RED ,graph.getData(a) );
        assertEquals(Color.GREEN ,graph.getData(c) );
        assertEquals(Color.GREEN ,graph.getData(b) );
        assertEquals(Color.RED ,graph.getData(d) );

        gm.decreaseWeight(c ,b);
        assertEquals(Color.RED ,graph.getData(a) );
        assertEquals(Color.GREEN ,graph.getData(c) );
        assertEquals(Color.RED ,graph.getData(b) );
        assertEquals(Color.RED ,graph.getData(d) );

    }
    @Test
    public void example5() throws Exception {
        //Beispiel 3 (4 Spieler, setColor und increaseWeight)
        Graph<Color , Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        int b = graph.addNode(Color.WHITE);
        int c = graph.addNode(Color.WHITE);
        int d = graph.addNode(Color.WHITE);
        graph.addEdge(a ,c , 1);
        graph.addEdge(a ,b , 1);
        graph.addEdge(c ,b , 2);
        graph.addEdge(d ,b , 2);
        graph.addEdge(d ,c , 1);
        graph.addEdge(c ,c , 2);
        GameMove gm = new GameMoveImpl(graph);
        gm.setColor( d , Color.RED);
        gm.setColor( c , Color.GREEN);

        gm.setColor(b, Color.BLUE);
        assertEquals(Color.BLUE ,graph.getData(b) );
        gm.setColor(b, Color.YELLOW);
        assertEquals(Color.YELLOW,graph.getData(b) );


        gm.setColor( a , Color.RED);
        assertEquals(Color.RED ,graph.getData(a) );
        assertEquals(Color.RED ,graph.getData(b) );
        assertEquals(Color.RED ,graph.getData(d) );


        gm.increaseWeight(c , c);
        assertEquals(Color.GREEN ,graph.getData(c) );
        assertEquals(Color.RED ,graph.getData(a) );
        assertEquals(Color.RED ,graph.getData(b) );
        assertEquals(Color.RED ,graph.getData(d) );

        gm.setColor( d , Color.BLUE);
        assertEquals(Color.BLUE ,graph.getData(d) );

        gm.setColor( a , Color.YELLOW);
        assertEquals(Color.YELLOW ,graph.getData(a) );

        gm.setColor( d , Color.RED);
        assertEquals(Color.RED ,graph.getData(d) );

        gm.increaseWeight(c , b);
        assertEquals(Color.GREEN ,graph.getData(c) );
        assertEquals(Color.YELLOW ,graph.getData(a) );
        assertEquals(Color.RED ,graph.getData(b) );
        assertEquals(Color.RED ,graph.getData(d) );

        gm.setColor( b , Color.BLUE);
        assertEquals(Color.BLUE,graph.getData(b) );

        gm.setColor( d , Color.YELLOW);
        assertEquals(Color.YELLOW ,graph.getData(d) );

        gm.setColor( d , Color.RED);
        assertEquals(Color.RED ,graph.getData(d) );

        gm.increaseWeight(c , b);
        assertEquals(Color.GREEN ,graph.getData(c) );
        assertEquals(Color.YELLOW ,graph.getData(a) );
        assertEquals(Color.GREEN ,graph.getData(b) );
        assertEquals(Color.RED ,graph.getData(d) );

    }
    @Test
    public void example6() throws Exception {
        //Beispiel 4 (2 Spieler, setColor und ForcedColorException)
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        int b = graph.addNode(Color.WHITE);
        graph.addEdge(a, b, 1);
        GameMove gm = new GameMoveImpl(graph);
        gm.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        Assertions.assertThrows(ForcedColorException.class, () ->  gm.setColor(b, Color.GREEN));
    }
    @Test
    public void example7() throws Exception {
        //Beispiel 5 (2 Spieler, setColor und ForcedColorException)
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        graph.addEdge(a, a, 1);
        GameMove gm = new GameMoveImpl(graph);
        gm.setColor(a, Color.RED);
        Assertions.assertThrows(ForcedColorException.class, () ->  gm.setColor(a, Color.GREEN));
    }
    @Test
    public void example8() throws Exception {
        //Beispiel 6 (2 Spieler, setColor und decreaseWeight)
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        graph.addEdge(a, a, 1);
        GameMove gm = new GameMoveImpl(graph);
        gm.setColor(a, Color.RED);
        gm.decreaseWeight(a, a);
        gm.setColor(a, Color.GREEN);
        assertEquals(Color.GREEN, graph.getData(a));
    }
    @Test
    public void example9() throws Exception {
        //Beispiel 7 (2 Spieler, decreaseWeight und NegativeWeightException)
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        graph.addEdge(a, a, 1);
        GameMove gm = new GameMoveImpl(graph);
        gm.decreaseWeight(a, a);

        Assertions.assertThrows(NegativeWeightException.class, () ->  gm.decreaseWeight(a, a));

    }
    }
