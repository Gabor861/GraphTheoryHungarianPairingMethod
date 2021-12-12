package ioprocessors.input;

import ioprocessors.input.entities.GraphInputFileV1;
import pairing.entities.IntegerEdge;
import pairing.entities.PairGraph;

import java.util.ArrayList;
import java.util.List;

public class GraphCreator
{
    public PairGraph mapToPairGraph(GraphInputFileV1 graphInput)
    {
        PairGraph pairGraph = new PairGraph();
        pairGraph.setStartGroup(graphInput.getStartGroup());
        pairGraph.setGroups(graphInput.getGraph().getGroups());

        pairGraph.setPairEdges(
            mapBy(
                graphInput.getGraph().getPairing()
            )
        );

        pairGraph.setEdges(
            mapBy(
                graphInput.getGraph().getEdge()
            )
        );

        return pairGraph;
    }

    private List<IntegerEdge> mapBy(List<List<Integer>> lists)
    {
        List<IntegerEdge> integerEdgeList = new ArrayList<>();

        lists.stream().forEach(
            integerList -> {
                IntegerEdge integerEdge = new IntegerEdge();
                integerEdge.setSource(integerList.get(0));
                integerEdge.setDestination(integerList.get(1));

                integerEdgeList.add(integerEdge);
            }
        );

        return integerEdgeList;
    }
}
