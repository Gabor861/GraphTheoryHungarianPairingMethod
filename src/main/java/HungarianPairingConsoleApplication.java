import ioprocessors.input.PairGraphInputProcessor;
import ioprocessors.input.enums.TestInputFiles;
import ioprocessors.output.JsonFileOutputWriter;
import pairing.HungarianPairingMethodStrategy;
import pairing.exceptions.GraphTheoryException;
import pairing.exceptions.KonigAkadalyException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.nio.file.Path;
import java.util.Arrays;

public class HungarianPairingConsoleApplication
{
    static JsonFileOutputWriter jsonFileOutputWriter;

    public static void main(String[] args)
    {
        jsonFileOutputWriter = new JsonFileOutputWriter();
        Arrays.stream(TestInputFiles.values()).forEach(HungarianPairingConsoleApplication::futtatas);
    }

    private static void futtatas(TestInputFiles testInputFiles) {

        try {
            jsonFileOutputWriter.pullToOutput(
                    HungarianPairingMethodStrategy.create().pairing(
                            PairGraphInputProcessor.createJsonGraphInputProcessor().getPairGraph(
                                    testInputFiles.getFilePath().toString()
                            )
                    ),
                    testInputFiles
            );
        } catch (KonigAkadalyException konigAkadalyException) {
            System.out.println("König akadály detektálva!");
            jsonFileOutputWriter.pullToOutput(
                    konigAkadalyException.getStringObjectMap(),
                    testInputFiles
            );
        }
    }

    private static String getFilePath()
    {
        JFileChooser j = new JFileChooser(
            System.getProperty("user.dir"),
            FileSystemView.getFileSystemView()
        );

        j.addChoosableFileFilter(new FileNameExtensionFilter("Only .json files", "json"));
        j.addChoosableFileFilter(new FileNameExtensionFilter("Only .graph files", "graph"));

        if (j.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return Path.of(j.getSelectedFile().getAbsolutePath()).toFile().getAbsolutePath();
        }

        throw new GraphTheoryException("Bemeneti fájl nincs megadva!");
    }
}
