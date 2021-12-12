package ioprocessors.input.enums;

import lombok.AllArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
public enum TestInputFiles
{
    SIMPLE("SimplePairingGraph.json"),
    SIMPLE2("SimplePairingGraph2.json"),
    WITHOUT_TOTAL_PAIRING("VanOlyanPontAhonnanNemVezetEl.json"),
    WITH_POSSIBLE_ALTENATIVE_PATH("AlternaloUtLehetseges.json"),
    KONIG_AKADALY_01("KonigAkadaly01.json");

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
}
