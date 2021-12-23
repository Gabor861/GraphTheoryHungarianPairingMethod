package pairing.entities;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IntegerEdge extends Edge<Integer> {
    public IntegerEdge(int source, int destination) {
        setSource(source);
        setDestination(destination);
    }

    @Override
    public boolean pointIn(Integer point) {
        return List.of(getSource(), getDestination()).contains(point);
    }

    @Override
    public String getLabel() {
        return getSource() + String.valueOf(getDestination());
    }
}
