package de.tukl.programmierpraktikum2020.p2.a2;

import de.tukl.programmierpraktikum2020.p2.a1.Graph;
import de.tukl.programmierpraktikum2020.p2.a1.GraphException;
import de.tukl.programmierpraktikum2020.p2.a1.InvalidNodeException;

import java.util.Iterator;

public class GameMoveImpl implements GameMove {


    Graph<Color, Integer> graph;
    public GameMoveImpl(Graph<Color, Integer> graph){
        this.graph = graph;
    }

    public boolean check(int nodeId ) throws GraphException { // ob der Knoten gezwungen gefärbt wurde
        int weightTotal = 0;
        int weightsColor= 0 ;
        for (int i : graph.getIncomingNeighbors(nodeId)){
            weightTotal += i ;
            if(graph.getData(i) == graph.getData(nodeId)){
                weightsColor += i;
            }
        }
        return  weightsColor > weightTotal/2 ;
    }
    public boolean check2(int nodeId ,Color color) throws GraphException {
        int weightTotal2 = 0;
        int weightsColor2= 0 ;
        for (int i : graph.getIncomingNeighbors(nodeId)){
            weightTotal2 += i ;
            if(graph.getData(nodeId)!=Color.WHITE && graph.getData(i) == color){
                weightsColor2 += i;
            }
        }
        return  weightsColor2 > weightTotal2/2 ;
    }
    private void dfs(int nodeId, Color color) throws GraphException {
        for (int nbr :graph.getOutgoingNeighbors(nodeId)) {
            if (check2(nbr, color)&& graph.getData(nbr)!=color ) {
                graph.setData(nbr, color);
                dfs(nbr, color);
            }
        }
    }

    @Override
    public void setColor(int nodeId, Color color) throws GraphException, ForcedColorException {
        if (graph.getData(nodeId)!=color&& check(nodeId) && graph.getData(nodeId)!=Color.WHITE) {
            throw new ForcedColorException(nodeId, color);
        } else {
            graph.setData(nodeId, color);
            dfs(nodeId,color);


        }
    }



    @Override
    public void increaseWeight(int fromId, int toId) throws GraphException {
        graph.setWeight(fromId, toId, graph.getWeight(fromId, toId) + 1);
        if(check2(toId,graph.getData(fromId))){
            graph.setData(toId, graph.getData(fromId));
            dfs(toId,graph.getData(fromId));
        }

    }
    @Override
    public void decreaseWeight(int fromId, int toId) throws GraphException, NegativeWeightException {
            if(graph.getWeight(fromId,toId) ==0){
                throw  new NegativeWeightException(fromId,toId);
            }
            else {
                graph.setWeight(fromId,toId,graph.getWeight(fromId,toId)-1);

            }
    }
}
