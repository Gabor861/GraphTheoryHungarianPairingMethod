package pairing.entities;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class IntegerEdge extends Edge<Integer> {

    @Override
    public boolean pointIn(Integer point) {
        return List.of(getSource(), getDestination()).contains(point);
    }

    @Override
    public String getLabel() {
        return getSource() + String.valueOf(getDestination());
    }
}
