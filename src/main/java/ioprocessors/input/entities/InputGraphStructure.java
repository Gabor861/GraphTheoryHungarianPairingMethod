package ioprocessors.input.entities;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class InputGraphStructure {
    private List<Integer> vertex;

    private List<List<Integer>> edge;

    private Map<String, List<Integer>> groups;

    private List<List<Integer>> pairing;
}
