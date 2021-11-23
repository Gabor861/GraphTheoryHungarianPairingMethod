package pairing;

import pairing.entities.AlternaloUt;
import pairing.entities.IntegerEdge;
import pairing.entities.PairGraph;

public class PathCorrector
{
    public void correctPathBy(PairGraph pairGraph, AlternaloUt alternaloUt)
    {
        while (!alternaloUt.isEmptyPath()) {
            alterPairing(
                pairGraph,
                alternaloUt.getLastPathElement()
            );
        }
    }

    private void alterPairing(PairGraph pairGraph, IntegerEdge integerEdge)
    {
        if (pairGraph.isPaired(integerEdge)) {
            pairGraph.deleteFromPairedEdge(integerEdge);
        } else {
            pairGraph.addToPairing(integerEdge);
        }
    }
}
