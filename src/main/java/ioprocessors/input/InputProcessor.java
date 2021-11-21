package ioprocessors.input;

import ioprocessors.input.reader.JsonGraphInputFileReader;
import lombok.extern.slf4j.Slf4j;
import pairing.entities.PairGraph;

@Slf4j
public class InputProcessor
{
    public JsonGraphInputFileReader graphInputFileReader;

    public GraphCreator graphCreator;

    private InputProcessor(JsonGraphInputFileReader graphInputFileReader, GraphCreator graphCreator)
    {
        this.graphInputFileReader = graphInputFileReader;
        this.graphCreator = graphCreator;
    }

    public static InputProcessor createJsonGraphInputProcessor()
    {
        return new InputProcessor(
            new JsonGraphInputFileReader(),
            new GraphCreator()
        );
    }

    public PairGraph getPairGraph(String s)
    {
        return graphCreator.mapToPairGraph(
            graphInputFileReader.readFile(s)
        );
    }
}
