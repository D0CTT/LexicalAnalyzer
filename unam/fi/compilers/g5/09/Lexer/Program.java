package unam.fi.compilers.g5.09.Lexer;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Program class serves as the entry point for running the lexer.
 * It initializes a source code string, processes it using the Lexer,
 * and then prints the detected tokens along with their counts.
 */
public class Program {
    public static void main(String[] args) {
        /**
         * Sample C-like code to be analyzed.
         */
        String code = "int main() {\r\n" +
                        "    int x, a = 2, b = 3, c = 5;\r\n" +
                        "    x = a + b * c;\r\n" +
                        "    printf(\"The value of x is %d\", x);\r\n" +
                        "    return 0;\r\n" +
                        "}";

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
}
