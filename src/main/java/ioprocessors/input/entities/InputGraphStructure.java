package ioprocessors.input.entities;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class InputGraphStructure
{
    private List<Integer> vertex;

    private List<List<Integer>> edge;

    private Map<String, List<Integer>> groups;

    private List<List<Integer>> pairing;
}
