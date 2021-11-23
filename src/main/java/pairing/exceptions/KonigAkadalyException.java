package pairing.exceptions;

import lombok.Getter;
import pairing.entities.AlternaloUt;
import pairing.entities.PairGraph;

import java.util.HashMap;
import java.util.Map;

@Getter
public class KonigAkadalyException extends GraphTheoryException
{
    Map<String, Object> stringObjectMap = new HashMap<>();

    public KonigAkadalyException(String message, PairGraph pairGraph, AlternaloUt alternaloUt) {
        super(message);
        stringObjectMap.put("pairGraph", pairGraph);
        stringObjectMap.put("alternaloUt", alternaloUt);
    }
}
