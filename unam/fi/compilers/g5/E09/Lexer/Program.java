package unam.fi.compilers.g5.E09.Lexer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The Program class serves as the entry point for running the lexer.
 * It initializes a source code file, processes it using the Lexer,
 * and then prints the detected tokens along with their counts.
 */
public class Program {
    public static void main(String[] args) {
        String filePath = selectFile();
        if (filePath == null) {
            System.out.println("No file selected. Exiting...");
            return;
        }
        
        String code = "";
        try {
            code = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Create a Lexer instance with the input code
        Lexer lexer = new Lexer(code);

        /**
         * Analyze the input code and generate a map containing
         * token types along with their occurrences.
         */
        Map<Token.TokenType, LinkedHashMap<String, Integer>> tokenMap = lexer.analyze();

        /**
         * Print the categorized tokens and their counts.
         * Also prints the total number of tokens found.
         */
        lexer.printTokens(tokenMap);
    }

    /**
     * Opens a file chooser dialog for the user to select a file.
     * @return the absolute path of the selected file, or null if no file was selected.
     */
    private static String selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a source code file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt", "c", "java", "cpp"));
        int userSelection = fileChooser.showOpenDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file.getAbsolutePath();
        }
        return null;
    }
}