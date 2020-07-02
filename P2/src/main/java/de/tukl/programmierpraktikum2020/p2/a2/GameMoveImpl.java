package de.tukl.programmierpraktikum2020.p2.a2;

import de.tukl.programmierpraktikum2020.p2.a1.Graph;
import de.tukl.programmierpraktikum2020.p2.a1.GraphException;

public class GameMoveImpl implements GameMove {


    Graph<Color, Integer> graph;
    public GameMoveImpl(Graph<Color, Integer> graph){
        this.graph = graph;
    }

    public boolean check(int nodeId,Color color ) throws GraphException { // ob der Knoten gezwungen gefärbt wurde
        int weightTotal = 0;
        int weightsColor= 0 ;
        for (int i : graph.getIncomingNeighbors(nodeId)){
            weightTotal += graph.getWeight(i ,nodeId ) ;
            if(graph.getData(i) == color){
                weightsColor += graph.getWeight(i ,nodeId);
            }
        }
        return  weightsColor > weightTotal/2 ;
    }

    private void dfs(int nodeId, Color color) throws GraphException {
        for (int nbr :graph.getOutgoingNeighbors(nodeId)) {
            if (graph.getData(nbr)!=color && check(nbr, color)  ) {
                graph.setData(nbr, color);
                dfs(nbr, color);
            }
        }
    }

    @Override
    public void setColor(int nodeId, Color color) throws GraphException, ForcedColorException {
        if ( graph.getData(nodeId)!=Color.WHITE&&graph.getData(nodeId)!=color&& check(nodeId , graph.getData(nodeId)) ) {
            throw new ForcedColorException(nodeId, color);
        } else {
            graph.setData(nodeId, color);
            dfs(nodeId,color);
        }
    }



    @Override
    public void increaseWeight(int fromId, int toId) throws GraphException {
        graph.setWeight(fromId, toId, graph.getWeight(fromId, toId) + 1);
        if(check(toId,graph.getData(fromId))){
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
