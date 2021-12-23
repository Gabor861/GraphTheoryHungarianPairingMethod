import ioprocessors.input.PairGraphInputProcessor;
import ioprocessors.input.enums.TestInputFiles;
import ioprocessors.output.JsonFileOutputWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import lombok.extern.slf4j.Slf4j;
import pairing.HungarianPairingMethodStrategy;
import pairing.exceptions.GraphTheoryException;
import pairing.exceptions.KonigAkadalyException;

@Slf4j
public class HungarianPairingConsoleApplication {
    static JsonFileOutputWriter jsonFileOutputWriter;

    private static InputMod aktualisInput = InputMod.FROM_INPUT_DIRECTORY;

    enum InputMod {
        FROM_INPUT_DIRECTORY,
        FROM_INPUT_DIRECTORY_JUST_REGIST,
        FROM_SELECT
    }

    public static void main(String[] args) throws IOException {
        jsonFileOutputWriter = new JsonFileOutputWriter();

        if (aktualisInput.equals(InputMod.FROM_SELECT)) {
            futtatas(getFilePath());
        } else if (aktualisInput.equals(InputMod.FROM_INPUT_DIRECTORY)) {
            TestInputFiles.getFilesFromBaseInputDirectory()
                    .forEach(HungarianPairingConsoleApplication::futtatas);
        } else {
            Arrays.stream(TestInputFiles.values())
                    .forEach(HungarianPairingConsoleApplication::futtatas);
        }
    }

    private static void futtatas(TestInputFiles inputFile) {
        futtatas(inputFile.getFilePath());
    }

    private static void futtatas(Path inputFile) {

        try {
            jsonFileOutputWriter.pullToOutput(
                    HungarianPairingMethodStrategy.create()
                            .pairing(
                                    PairGraphInputProcessor.createJsonGraphInputProcessor()
                                            .getPairGraph(inputFile.toString())),
                    inputFile);
        } catch (KonigAkadalyException konigAkadalyException) {
            log.error("König akadály detektálva!");
            jsonFileOutputWriter.pullToOutput(
                    konigAkadalyException.getStringObjectMap(), inputFile);
        }
    }

    private static Path getFilePath() {
        JFileChooser j =
                new JFileChooser(
                        System.getProperty("user.dir"), FileSystemView.getFileSystemView());

        j.addChoosableFileFilter(new FileNameExtensionFilter("Only .json files", "json"));
        j.addChoosableFileFilter(new FileNameExtensionFilter("Only .graph files", "graph"));

        if (j.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return Path.of(j.getSelectedFile().getAbsolutePath()).toFile().toPath();
        }

        throw new GraphTheoryException("Bemeneti fájl nincs megadva!");
    }
}
