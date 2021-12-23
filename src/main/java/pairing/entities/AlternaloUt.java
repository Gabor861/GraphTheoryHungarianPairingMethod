package pairing.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AlternaloUt {
    public Integer startVertex;

    public Integer actualVertex;

    public Stack<IntegerEdge> path;

    public boolean containsVisitedVertex(Integer integer) {
        return getVisitedVertex().contains(integer);
    }

    private Set<Integer> getVisitedVertex() {
        Set<Integer> visitedVertex = new HashSet<>();
        for (IntegerEdge integerEdge : path.toArray(IntegerEdge[]::new)) {
            visitedVertex.addAll(List.of(integerEdge.getSource(), integerEdge.getDestination()));
        }
        return visitedVertex;
    }

    @JsonIgnore
    public static AlternaloUt createByStartVertex(Integer startVertex) {
        AlternaloUt alternaloUt = new AlternaloUt();
        alternaloUt.setActualVertex(startVertex);
        alternaloUt.startVertex = startVertex;
        alternaloUt.path = new Stack<>();
        return alternaloUt;
    }

    @JsonIgnore
    public void addEdgeToPath(IntegerEdge integerEdge) {
        setActualVertex(integerEdge);
        path.push(integerEdge);
    }

    @JsonIgnore
    private void setActualVertex(IntegerEdge integerEdge) {
        if (Objects.isNull(actualVertex)) {
            setActualVertex(integerEdge.getOpponentVertex(startVertex));
        } else {
            setActualVertex(integerEdge.getOpponentVertex(actualVertex));
        }
    }

    @JsonIgnore
    private void setActualVertex(Integer actualVertex) {
        if (Objects.isNull(actualVertex)) {
            throw new IllegalArgumentException("hiba");
        }
        this.actualVertex = actualVertex;
    }

    @JsonIgnore
    public boolean isEmptyPath() {
        return path.isEmpty();
    }

    @JsonIgnore
    public IntegerEdge getLastPathElement() {
        return path.pop();
    }

    public AlternaloUt clone(IntegerEdge integerEdge) {
        AlternaloUt deepCopiedAlternaloUt = new AlternaloUt();
        deepCopiedAlternaloUt.startVertex = this.startVertex;
        deepCopiedAlternaloUt.actualVertex = this.actualVertex;
        deepCopiedAlternaloUt.path = (Stack<IntegerEdge>) this.path.clone();
        deepCopiedAlternaloUt.addEdgeToPath(integerEdge);
        return deepCopiedAlternaloUt;
    }
}
