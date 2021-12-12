package pairing.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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

    @JsonIgnore
    public static AlternaloUt buildByStartVertex(Integer startVertex)
    {
        AlternaloUt alternaloUt = new AlternaloUt();
        alternaloUt.actualVertex = startVertex;
        alternaloUt.startVertex = startVertex;
        alternaloUt.path = new Stack<>();
        return alternaloUt;
    }

    @JsonIgnore
    public void addEdgeToPath(IntegerEdge integerEdge)
    {
        setActualVertex(integerEdge);
        path.push(integerEdge);
    }

    @JsonIgnore
    private void setActualVertex(IntegerEdge integerEdge)
    {
        if (isEmptyPath() || path.lastElement().isSourceVertex(actualVertex)) {
            actualVertex = integerEdge.getDestination();
        } else {
            actualVertex = integerEdge.getSource();
        }
    }

    @JsonIgnore
    public boolean isEmptyPath()
    {
        return path.isEmpty();
    }

    @JsonIgnore
    public IntegerEdge getLastPathElement()
    {
        return path.pop();
    }
}
