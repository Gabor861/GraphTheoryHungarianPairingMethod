package pairing;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pairing.entities.AlternaloUt;
import pairing.entities.PairGraph;

@AllArgsConstructor
@Slf4j
public class HungarianPairingMethodStrategy {
    private PathCorrector pathCorrector;

    private AlternatePathSearcher alternatePathSearcher;

    public static HungarianPairingMethodStrategy create() {
        return new HungarianPairingMethodStrategy(new PathCorrector(), new AlternatePathSearcher());
    }

    public PairGraph pairing(PairGraph graph) {
        List<Integer> pairlessVertex = graph.getPairlessVertexFromStartGroup();
        if (pairlessVertex.isEmpty()) {
            return graph;
        }

        searchCorrectionPath(graph, pairlessVertex.get(0));
        return pairing(graph);
    }

    private void searchCorrectionPath(PairGraph graph, Integer pairlessVertex) {
        this.pathCorrector.correctPathBy(
                graph,
                alternatePathSearcher.alternaloUtKereses(
                        graph, AlternaloUt.createByStartVertex(pairlessVertex)));
    }
}
