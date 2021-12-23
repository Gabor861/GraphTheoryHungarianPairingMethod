package ioprocessors.input.enums;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestInputFiles {
    SIMPLE("SimplePairingGraph.json"),
    SIMPLE2("SimplePairingGraph2.json"),
    WITHOUT_TOTAL_PAIRING("VanOlyanPontAhonnanNemVezetEl.json"),
    WITH_POSSIBLE_ALTENATIVE_PATH("AlternaloUtLehetseges.json"),
    KONIG_AKADALY_01("KonigAkadaly01.json"),
    HAZIFELADAT_4_1("HaziFeladat4Feladat1.json");

    String filename;

    public Path getFilePath() {
        return Paths.get(
                System.getProperty("user.dir"), "src", "main", "resources", "data", filename);
    }

    public static Path getBaseInputPath() {
        return Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data");
    }

    public static Stream<Path> getFilesFromBaseInputDirectory() throws IOException {
        return Files.walk(TestInputFiles.getBaseInputPath(), 1)
                .filter(Files::isRegularFile)
                .filter(file -> file.toString().endsWith(".json"));
    }
}
