package de.tukl.programmierpraktikum2020.p2.a2;

import de.tukl.programmierpraktikum2020.p2.a1.Graph;
import de.tukl.programmierpraktikum2020.p2.a1.GraphException;
import de.tukl.programmierpraktikum2020.p2.a1.InvalidNodeException;

public class GameMoveImpl implements GameMove {


    Graph<Color, Integer> graph;
    public GameMoveImpl(Graph<Color, Integer> graph){
        this.graph = graph;
    }

    public boolean CheckIN(int nodeId , Color color) throws GraphException {
        int weightTotalIN = 0;
        int weightsColorIN = 0 ;
        for (int i : graph.getIncomingNeighbors(nodeId)){
            weightTotalIN += i ;
            if(graph.getData(i) == color){
                weightsColorIN += i;
            }
        }
        return  weightsColorIN > weightTotalIN/2 ;
    }
    public boolean CheckOUt(int nodeId , Color color) throws GraphException {
        int weightTotal = 0;
        int weightsColor = 0 ;
        for (int i : graph.getOutgoingNeighbors(nodeId)){
            weightTotal += i ;
            if(graph.getData(i) == color){
                weightsColor += i;
            }
        }
        return  weightsColor > weightTotal/2 ;
    }
    @Override
    public void setColor(int nodeId, Color color) throws GraphException, ForcedColorException {


            int weightTotal = 0;
            int weightColor = 0;
            for(int i :graph.getIncomingNeighbors(nodeId)){
                weightTotal += i;
                if(graph.getData(i)!= Color.WHITE&&graph.getData(i) == graph.getData(nodeId)){
                    weightColor+=i;
                }
            }
            if(weightColor> weightTotal/2 ){
                throw new ForcedColorException(nodeId,color);
            }
            else {
                graph.setData(nodeId,color);
                for (int i : graph.getOutgoingNeighbors(nodeId)){
                    if(CheckIN(i , color)){
                        graph.setData(i,color);

                    }
                }
                }


        }


    @Override
    public void increaseWeight(int fromId, int toId) throws GraphException {
        graph.setWeight(fromId, toId, graph.getWeight(fromId, toId) + 1);
            if (CheckIN(toId, graph.getData(fromId))) {
                graph.setData(toId, graph.getData(fromId));

            }
    }
    @Override
    public void decreaseWeight(int fromId, int toId) throws GraphException, NegativeWeightException {
            if(graph.getWeight(fromId,toId) ==0){
                throw  new NegativeWeightException(fromId,toId);
            }
            else {
                graph.setWeight(fromId,toId,graph.getWeight(fromId,toId)-1);
                if (CheckIN(toId, graph.getData(fromId))) {
                    graph.setData(toId, graph.getData(fromId));

                }
            }
    }
}
