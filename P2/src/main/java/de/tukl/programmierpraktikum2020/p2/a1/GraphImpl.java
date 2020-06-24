package de.tukl.programmierpraktikum2020.p2.a1;

import java.util.*;

public class GraphImpl<D ,W> implements Graph<D ,W> {
    HashMap<Integer, Node<D>> Nodes;
    HashMap<Set<Integer>, W> Edges;
    int size = 0;

    public GraphImpl() {
        Nodes = new HashMap<>();
        Edges = new HashMap<>();

    }

    @Override
    public int addNode(D data) {
        Node<D> node = new Node<>(data);
        node.id = size;
        Nodes.put(node.id, node);
        size++;
        return node.id;
    }

    @Override
    public D getData(int nodeId) throws InvalidNodeException {
        Node<D> tempNode = Nodes.get(nodeId);
        if (tempNode != null) {
            return tempNode.data;
        } else {
            throw new InvalidNodeException(nodeId);
        }

    }

    @Override
    public void setData(int nodeId, D data) throws InvalidNodeException {
        Node<D> tempNode = Nodes.get(nodeId);
        if (tempNode != null) {
            tempNode.data = data;
            Nodes.replace(nodeId, tempNode);
        } else {
            throw new InvalidNodeException(nodeId);
        }
    }

    @Override
    public void addEdge(int fromId, int toId, W weight) throws InvalidNodeException, DuplicateEdgeException {
        Node<D> tempNodefrom = Nodes.get(fromId);
        Node<D> tempNodeto = Nodes.get(toId);
        Set<Integer> ne = new LinkedHashSet<>();
        ne.add(fromId);
        ne.add(toId);
        if (tempNodefrom != null && tempNodeto != null && !Edges.containsKey(ne)&&tempNodefrom != tempNodeto) {
            Edges.put(ne, weight);
            Nodes.get(fromId).outNods.put(toId, tempNodeto);
            Nodes.get(toId).inNods.put(fromId, tempNodefrom);
        } else {
            if (tempNodefrom == null) {
                throw new InvalidNodeException(fromId);
            } else if (tempNodeto == null) {
                throw new InvalidNodeException(toId);
            } else {
                throw new DuplicateEdgeException(fromId, toId);
            }
        }
    }

    @Override
    public W getWeight(int fromId, int toId) throws InvalidEdgeException {
        Set<Integer> ne = new LinkedHashSet<>();
        ne.add(fromId);
        ne.add(toId);
        if (Nodes.get(fromId) != null && Nodes.get(toId) != null && Edges.containsKey(ne)) {
            return Edges.get(ne);
        } else {
            throw new InvalidEdgeException(fromId, toId);
        }
    }

    @Override
    public void setWeight(int fromId, int toId, W weight) throws InvalidEdgeException {
        Set<Integer> ne = new LinkedHashSet<>();
        ne.add(fromId);
        ne.add(toId);
        if (Nodes.get(fromId) != null && Nodes.get(toId)!= null && Edges.containsKey(ne)) {
            Edges.replace(ne, weight);
        } else {
            throw new InvalidEdgeException(fromId, toId);
        }
    }

    @Override
    public Set<Integer> getNodeIds() {
        Set<Integer> ids = new HashSet<>();
        for (Map.Entry<Integer, Node<D>> pair : Nodes.entrySet()) {
            ids.add(pair.getKey());

        }
        return ids;
    }

    @Override
    public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
        Node<D> tempNode = Nodes.get(nodeId);
        if (tempNode != null) {
            Set<Integer> ids = new HashSet<>();
            for (Map.Entry<Integer, Node<D>> pair : tempNode.inNods.entrySet()) {
                ids.add(pair.getKey());

            }
            return ids;
        }
        else {
            throw new InvalidNodeException(nodeId);
        }
    }

    @Override
    public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
        Node<D> tempNode = Nodes.get(nodeId);
        if (tempNode != null) {
            Set<Integer> ids = new HashSet<>();
            for (Map.Entry<Integer, Node<D>> pair : tempNode.outNods.entrySet()) {
                ids.add(pair.getKey());

            }
            return ids;
        }
        else {
            throw new InvalidNodeException(nodeId);
        }
    }
    public static void main(String[] arg) throws DuplicateEdgeException, InvalidNodeException, InvalidEdgeException {
        GraphImpl<Character ,Integer> graph = new GraphImpl<>();
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
        graph.setWeight(4,3,4);
        for(Integer i : graph.getIncomingNeighbors(1)){
            System.out.println(graph.getData(i) + "---> "+ graph.getData(1) +" " + graph.getWeight(i , 1));
        }
        System.out.println("**********************");
        for(Integer i : graph.getOutgoingNeighbors(1)){
            System.out.println(graph.getData(1) + "---> "+ graph.getData(i) +" " + graph.getWeight(i , 1));
        }
    }
}