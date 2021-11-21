package pairing;

import pairing.entities.AlternaloUt;
import pairing.entities.IntegerEdge;
import pairing.entities.PairGraph;
import pairing.exceptions.KonigAkadalyException;
import utils.ObjectCloneUtil;

import java.util.List;
import java.util.Optional;

public class AlternatePathSearcher {

    public AlternaloUt searchCorrectionalPath(
            PairGraph graph,
            AlternaloUt alternaloUt
    ) {
        if (searchEndState(graph, alternaloUt).isPresent()) {
            return alternaloUt;
        }

        for (IntegerEdge integerEdge: graph.getPairlessUnvisitedVertexEdgeFromOtherGroup(alternaloUt)) {
            AlternaloUt deepCopiedAlternaloUt = ObjectCloneUtil.getDeepCopy(alternaloUt);
            deepCopiedAlternaloUt.path.push(integerEdge);

            return searchCorrectionalPath(graph, deepCopiedAlternaloUt);
        }

        throw new KonigAkadalyException("Graph-ban königakadály található!");
    }

    private Optional<AlternaloUt> searchEndState(PairGraph pairGraph, AlternaloUt alternaloUt)
    {
        List<IntegerEdge> graphPairlessUnvisitedVertexFromOtherGroup =
                pairGraph.getPairlessUnvisitedVertexEdgeFromOtherGroup(alternaloUt);
        if (!graphPairlessUnvisitedVertexFromOtherGroup.isEmpty()) {
            alternaloUt.path.push(graphPairlessUnvisitedVertexFromOtherGroup.get(0));

            return Optional.of(alternaloUt);
        }

        return Optional.empty();
    }
}
