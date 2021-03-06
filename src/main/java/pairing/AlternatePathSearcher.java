package pairing;

import java.util.List;
import java.util.Optional;
import pairing.entities.AlternaloUt;
import pairing.entities.IntegerEdge;
import pairing.entities.PairGraph;
import pairing.exceptions.KonigAkadalyException;

class AlternatePathSearcher {
    public AlternaloUt alternaloUtKereses(PairGraph graph, AlternaloUt alternaloUt) {
        if (alternaloUtLezarasanakKeresese(graph, alternaloUt).isPresent()) {
            return alternaloUt;
        }
        return getBovitettAlternaloUt(graph, alternaloUt);
    }

    private Optional<AlternaloUt> alternaloUtLezarasanakKeresese(
            PairGraph pairGraph, AlternaloUt alternaloUt) {
        List<IntegerEdge> graphPairlessUnvisitedVertexFromOtherGroup =
                pairGraph.getPairlessUnvisitedVertexEdgeFromOtherGroup(alternaloUt);
        if (!graphPairlessUnvisitedVertexFromOtherGroup.isEmpty()) {
            alternaloUt.addEdgeToPath(graphPairlessUnvisitedVertexFromOtherGroup.get(0));

            return Optional.of(alternaloUt);
        }

        return Optional.empty();
    }

    private AlternaloUt getBovitettAlternaloUt(PairGraph graph, AlternaloUt alternaloUt) {
        for (IntegerEdge integerEdge :
                graph.getInPairedUnvisitedVertexEdgeFromOtherGroup(alternaloUt)) {
            return alternaloUtKereses(graph, alternaloUt.clone(integerEdge));
        }

        throw new KonigAkadalyException(graph, alternaloUt);
    }
}
