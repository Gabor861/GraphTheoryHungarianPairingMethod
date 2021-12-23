package ioprocessors.input;

import ioprocessors.input.entities.GraphInputFileV1;
import java.util.ArrayList;
import java.util.List;
import pairing.entities.IntegerEdge;
import pairing.entities.PairGraph;

public class GraphCreator {

    public PairGraph mapToPairGraph(GraphInputFileV1 graphInput) {
        PairGraph pairGraph = new PairGraph();

        pairGraph.setStartGroup(graphInput.getStartGroup());
        pairGraph.setGroups(graphInput.getGraph().getGroups());
        pairGraph.setPairEdges(mapBy(graphInput.getGraph().getPairing()));
        pairGraph.setEdges(mapBy(graphInput.getGraph().getEdge()));

        return pairGraph;
    }

    private List<IntegerEdge> mapBy(List<List<Integer>> lists) {
        List<IntegerEdge> integerEdgeList = new ArrayList<>();

        lists.stream()
                .forEach(
                        integerList ->
                                integerEdgeList.add(
                                        new IntegerEdge(integerList.get(0), integerList.get(1))));

        return integerEdgeList;
    }
}
