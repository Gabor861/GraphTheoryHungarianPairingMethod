package pairing.entities;

import java.util.List;
import lombok.Data;

@Data
abstract class Edge<T> {

    private String label;

    private T source;

    private T destination;

    private double weight;

    public boolean pointIn(T point) {
        return List.of(source, destination).contains(point);
    }

    public String getLabel() {
        return label;
    }

    public boolean isSourceVertex(T vertex) {
        return source.equals(vertex);
    }

    public T getOpponentVertex(T vertex) {
        return source == vertex ? destination : source;
    }
}
