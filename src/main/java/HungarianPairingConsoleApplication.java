import ioprocessors.input.InputProcessor;
import ioprocessors.input.enums.TestInputFiles;
import ioprocessors.output.PairGraphJsonFileOutputWriter;
import pairing.HungarianPairingMethod;
import pairing.exceptions.GraphTheoryException;
import pairing.exceptions.KonigAkadalyException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.nio.file.Path;

public class HungarianPairingConsoleApplication
{
    public static void main(String[] args)
    {
        try {
            new PairGraphJsonFileOutputWriter().pullToOutput(
                new HungarianPairingMethod().pairing(
                    InputProcessor.createJsonGraphInputProcessor().getPairGraph(
                        TestInputFiles.getActualProcessedTestGraph()
                    )
                )
            );
        } catch (KonigAkadalyException konigAkadalyException) {
            System.out.println("König akadály detektálva!");
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
