package ioprocessors.input.entities;

import lombok.Data;

@Data
public class GraphInputFile {

    private String version;

    private String description;

    private InputGraphStructure graph;

    private String startGroup;
}
