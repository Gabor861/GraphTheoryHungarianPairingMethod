package pairing.entities;

import lombok.Builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@Builder
public class AlternaloUt
{
    public Integer startVertex;

    public Integer actualVertex;

    public Stack<IntegerEdge> path;

    public boolean containsVisitedVertex(Integer integer)
    {
        return getVisitedVertex().contains(integer);
    }

    private Set<Integer> getVisitedVertex()
    {
        Set<Integer> visitedVertex = new HashSet<>();
        for (IntegerEdge integerEdge : path.toArray(IntegerEdge[]::new)) {
            visitedVertex.addAll(List.of(integerEdge.getSource(), integerEdge.getDestination()));
        }
        return visitedVertex;
    }

    public static AlternaloUt buildByStartVertex(Integer startVertex)
    {
        return AlternaloUt
            .builder()
            .actualVertex(startVertex)
            .startVertex(startVertex)
            .path(new Stack<IntegerEdge>())
            .build();
    }
}
