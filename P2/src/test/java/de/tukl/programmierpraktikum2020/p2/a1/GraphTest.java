package de.tukl.programmierpraktikum2020.p2.a1;

import edu.uci.ics.jung.visualization.renderers.GradientVertexRenderer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    GraphImpl<Character ,Integer> graph;

    @BeforeEach
    public  void creatObject() throws DuplicateEdgeException, InvalidNodeException {
        graph = new GraphImpl<>();
        graph.addNode('A');
        graph.addNode('B');
        graph.addNode('C');
        graph.addNode('D');
        graph.addNode('E');
        graph.addNode('F');
        graph.addEdge( 0 ,5 , 15);
        graph.addEdge( 5 ,1 , 30);
        graph.addEdge( 0,1 , 78);
        graph.addEdge( 2,0 , 100);
        graph.addEdge( 4 ,3, 45);
        graph.addEdge( 1 ,4 , 25);
        graph.addEdge( 4,5 , 78);
        graph.addEdge( 1 ,3 , 77);
        graph.addEdge( 1,2 , 32);
        graph.addEdge( 2,3 , 46);
    }
    @Test
    public void getDataTest() throws Exception {

        assertEquals('A' , graph.getData(0));
        assertEquals('F' , graph.getData(5));
        //es gibt keine Knoten mit Id 6.throw Excption
        Assertions.assertThrows(InvalidNodeException.class, () -> graph.getData(6));

    }
    @Test
    public void setDataTest() throws Exception {

        graph.setData(0 , 'K');
        graph.setData(5 , 'Z');
        assertEquals('K' , graph.getData(0));
        assertEquals('Z' , graph.getData(5));
        //es gibt keine Knoten mit Id 6.throw Excption
        Assertions.assertThrows(InvalidNodeException.class, () -> graph.setData(6,'E'));

    }
    @Test
    public void addEdgeTest() throws Exception {
        Assertions.assertThrows(InvalidNodeException.class, () -> graph.addEdge(6 ,0 ,55));
        Assertions.assertThrows(InvalidNodeException.class, () -> graph.addEdge(2,8 ,13));
        Assertions.assertThrows(DuplicateEdgeException.class, () -> graph.addEdge(1,2 ,45));
        Assertions.assertThrows(DuplicateEdgeException.class, () -> graph.addEdge(2,2 ,45));

    }
    @Test
    public void getWeightTest() throws Exception {
        assertEquals(78 , graph.getWeight(0,1));
        Assertions.assertThrows(InvalidEdgeException.class, () -> graph.getWeight(10 ,0));
        Assertions.assertThrows(InvalidEdgeException.class, () -> graph.getWeight(2,9));
        Assertions.assertThrows(InvalidEdgeException.class, () -> graph.getWeight(3,4 ));

    }
    @Test
    public void setWeightTest() throws Exception {
        graph.setWeight(0,1 , 85);
        assertEquals(85 , graph.getWeight(0,1));
        Assertions.assertThrows(InvalidEdgeException.class, () -> graph.setWeight(10 ,0,55));
        Assertions.assertThrows(InvalidEdgeException.class, () -> graph.setWeight(2,9,87));
        Assertions.assertThrows(InvalidEdgeException.class, () -> graph.setWeight(3,4,75 ));

    }
    @Test
    public void getNodeIdsTest() throws Exception {
        Set<Integer> ids = new HashSet<>();
        for(int i = 0 ; i<6 ; i++){
             ids.add(i);
          }
        assertEquals(ids , graph.getNodeIds());
    }
    @Test
    public void getIncomingNeighborsTest() throws Exception {
        Set<Integer> ids = new HashSet<>();

        ids.add(5);
        ids.add(0);
        assertEquals(ids , graph.getIncomingNeighbors(1));
        Assertions.assertThrows(InvalidNodeException.class, () -> graph.getIncomingNeighbors(8));
    }
    @Test
    public void getOutgoingNeighborsTest() throws Exception {
        Set<Integer> ids = new HashSet<>();
        ids.add(4);
        ids.add(3);
        ids.add(2);
        assertEquals(ids , graph.getOutgoingNeighbors(1));
        Assertions.assertThrows(InvalidNodeException.class, () -> graph.getOutgoingNeighbors(9));
    }
}
