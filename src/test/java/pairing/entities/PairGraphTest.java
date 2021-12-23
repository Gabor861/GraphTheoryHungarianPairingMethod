package pairing.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PairGraphTest {

    @Test
    void isPaired() {

        IntegerEdge integerEdgeasd = new IntegerEdge(1, 2);
        PairGraph pairGraph = new PairGraph();
        pairGraph.setPairEdges(List.of(new IntegerEdge(1, 2)));
        Assertions.assertTrue(pairGraph.isPaired(integerEdgeasd));
    }
}
