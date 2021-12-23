package pairing;

import lombok.extern.slf4j.Slf4j;
import pairing.entities.AlternaloUt;
import pairing.entities.IntegerEdge;
import pairing.entities.PairGraph;

@Slf4j
class PathCorrector {
    public void correctPathBy(PairGraph pairGraph, AlternaloUt alternaloUt) {
        if (alternaloUt.path.size() % 2 == 0) {
            log.error(String.valueOf(alternaloUt.path));
            throw new IllegalArgumentException("Az alternáló út csak páros lehet");
        }

        log.info("Javítóút találat: " + alternaloUt.toString());

        while (!alternaloUt.isEmptyPath()) {
            alterPairing(pairGraph, alternaloUt.getLastPathElement());
        }
    }

    private void alterPairing(PairGraph pairGraph, IntegerEdge integerEdge) {
        if (pairGraph.isPaired(integerEdge)) {
            pairGraph.deleteFromPairedEdge(integerEdge);
        } else {
            pairGraph.addToPairing(integerEdge);
        }
    }
}
