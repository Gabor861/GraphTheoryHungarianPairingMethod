package ioprocessors.input;

import ioprocessors.input.entities.GraphInputFileV1;
import ioprocessors.input.reader.JsonFileReader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pairing.entities.PairGraph;

@Slf4j
@AllArgsConstructor
public class PairGraphInputProcessor {
    private JsonFileReader<GraphInputFileV1> graphInputFileV1JsonFileReader;

    private GraphCreator graphCreator;

    public static PairGraphInputProcessor createJsonGraphInputProcessor() {
        return new PairGraphInputProcessor(new JsonFileReader<>(), new GraphCreator());
    }

    public PairGraph getPairGraph(String s) {
        return graphCreator.mapToPairGraph(
                graphInputFileV1JsonFileReader.readFile(s, GraphInputFileV1.class));
    }
}
