package pairing.entities;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class PairGraph {

    private Map<String, List<Integer>> groups;

    private String startGroup;

    private List<IntegerEdge> edges;

    private List<IntegerEdge> pairEdges;

    public void deleteFromPairedEdge(IntegerEdge integerEdge) {
        pairEdges.remove(integerEdge);
    }

    public boolean isPaired(IntegerEdge integerEdge) {
        return pairEdges.contains(integerEdge);
    }

    public List<Integer> getPairlessVertexFromStartGroup() {
        return listOfStartGroupVertex().stream()
                .filter(vertex -> !hasPair(vertex))
                .collect(Collectors.toList());
    }

    private List<Integer> listOfStartGroupVertex() {
        return groups.get(startGroup);
    }

    /** Végállapot */
    public List<IntegerEdge> getPairlessUnvisitedVertexEdgeFromOtherGroup(AlternaloUt alternaloUt) {
        if (!isVertexInStartGroup(alternaloUt.actualVertex)) {
            return List.of();
        }

        List<IntegerEdge> kapcsolodoElek =
                edges.stream()
                        .filter(integerEdge -> integerEdge.pointIn(alternaloUt.getActualVertex()))
                        .collect(Collectors.toList());
        List<IntegerEdge> nemParositott =
                kapcsolodoElek.stream()
                        .filter(
                                integerEdge ->
                                        !hasPair(
                                                integerEdge.getOpponentVertex(
                                                        alternaloUt.getActualVertex())))
                        .collect(Collectors.toList());
        return nemParositott.stream()
                .filter(
                        integerEdge ->
                                !alternaloUt.containsVisitedVertex(
                                        integerEdge.getOpponentVertex(
                                                alternaloUt.getActualVertex())))
                .collect(Collectors.toList());
    }

    /** Végállapot */
    public List<IntegerEdge> getInPairedUnvisitedVertexEdgeFromOtherGroup(AlternaloUt alternaloUt) {

        if (isVertexInStartGroup(alternaloUt.getActualVertex())) {
            return edges.stream()
                    .filter(
                            integerEdge ->
                                    integerEdge.pointIn(alternaloUt.getActualVertex())
                                            && hasPair(
                                                    integerEdge.getOpponentVertex(
                                                            alternaloUt.getActualVertex()))
                                            && !alternaloUt.containsVisitedVertex(
                                                    integerEdge.getOpponentVertex(
                                                            alternaloUt.getActualVertex())))
                    .collect(Collectors.toList());
        }
        return pairEdges.stream()
                .filter(
                        integerEdge ->
                                integerEdge.pointIn(alternaloUt.getActualVertex())
                                        /* && hasPair(alternaloUt.getActualVertex())*/
                                        && !alternaloUt.containsVisitedVertex(
                                                integerEdge.getOpponentVertex(
                                                        alternaloUt.getActualVertex())))
                .collect(Collectors.toList());
    }

    /**
     * Keresés folytatódik
     *
     * @param visitedVertex
     * @return
     */
    public List<Integer> getInPairUnvisitedVertexFromOtherGroup(Set<Integer> visitedVertex) {
        return groups.get(getOtherGroup()).stream()
                .filter(vertex -> hasPair(vertex) && !visitedVertex.contains(vertex))
                .collect(Collectors.toList());
    }

    private String getOtherGroup() {
        Set<String> set = groups.keySet();
        set.remove(startGroup);
        return (String) set.toArray()[0];
    }

    public boolean hasPair(Integer vertex) {
        return pairEdges.stream().filter(pairEdge -> pairEdge.pointIn(vertex)).count() != 0;
    }

    public boolean equalsOfPointOfGroups() {
        if (groups.size() == 2) {
            String[] strings = groups.keySet().toArray(String[]::new);
            return groups.get(strings[0]).size() == groups.get(strings[1]).size();
        }
        return false;
    }

    public List<IntegerEdge> getVertexConnectedEdge(Integer vertex) {
        return edges.stream().filter(asd -> asd.pointIn(vertex)).collect(Collectors.toList());
    }

    private boolean isVertexInStartGroup(Integer vertex) {
        return listOfStartGroupVertex().contains(vertex);
    }

    public void addToPairing(IntegerEdge integerEdge) {
        pairEdges.add(integerEdge);
    }

    public void deleteFromPairing(IntegerEdge integerEdge) {
        pairEdges.add(integerEdge);
    }
}
