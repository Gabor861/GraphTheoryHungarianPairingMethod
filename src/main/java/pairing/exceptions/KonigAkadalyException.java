package pairing.exceptions;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import pairing.entities.AlternaloUt;
import pairing.entities.PairGraph;

@Getter
public class KonigAkadalyException extends GraphTheoryException {
    Map<String, Object> stringObjectMap = new HashMap<>();

    public KonigAkadalyException(PairGraph pairGraph, AlternaloUt alternaloUt) {
        super("Graph-ban königakadály található!");
        stringObjectMap.put("pairGraph", pairGraph);
        stringObjectMap.put("alternaloUt", alternaloUt);
    }
}
