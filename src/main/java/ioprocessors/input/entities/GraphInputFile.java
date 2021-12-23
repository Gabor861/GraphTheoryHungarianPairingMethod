package ioprocessors.input.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
abstract class GraphInputFile<VersionOfInputGraphStructure extends InputGraphStructure> {
    @NotBlank private String version;

    @NotBlank private String description;

    @NotNull private VersionOfInputGraphStructure graph;

    @NotBlank private String startGroup;
}
