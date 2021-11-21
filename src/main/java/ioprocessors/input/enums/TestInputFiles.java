package ioprocessors.input.enums;

import lombok.AllArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
public enum TestInputFiles {

    Simple("SimplePairingGraph.json"),
    Simple2("SimplePairingGraph2.json"),
    WithoutTotalPairing("VanOlyanPontAhonnanNemVezetEl.json"),
    WithPossibleAltenativePath("AlternaloUtLehetseges.json");

    String filename;

    public Path getFilePath()
    {
        return Paths.get(
                System.getProperty("user.dir"),
                "src",
                "main",
                "java",
                "data",
                filename
        );
    }

    public static String getActualProcessedTestGraph() {
        return TestInputFiles.WithPossibleAltenativePath.getFilePath().toString();
    }
}
