package pairing;

import pairing.entities.AlternaloUt;
import pairing.entities.PairGraph;

import java.util.List;

public class HungarianPairingMethod {

    PathCorrector pathCorrector;

    AlternatePathSearcher alternatePathSearcher;

    public HungarianPairingMethod() {
        pathCorrector = new PathCorrector();
        alternatePathSearcher = new AlternatePathSearcher();
    }

    public PairGraph pairing(PairGraph graph)
    {
        List<Integer> pairlessVertex = graph.getPairlessVertexFromStartGroup();

        if (pairlessVertex.isEmpty()) {
            return graph;
        }

        searchCorrectionPath(graph, pairlessVertex.get(0));
        return pairing(graph);
    }

    private void searchCorrectionPath(PairGraph graph, Integer pairlessVertex)
    {
        this.pathCorrector.correctPathBy(
            graph,
            alternatePathSearcher.searchCorrectionalPath(
                graph,
                AlternaloUt.buildByStartVertex(pairlessVertex)
            )
        );
    }
}
