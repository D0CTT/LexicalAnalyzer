import java.util.List;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        String codigo = "int x = 10; if (x > 5) { return x; }";
        Lexer lexer = new Lexer(codigo);
        Map<Token.TokenType, List<Token>> tokenMap = lexer.analyze();

        lexer.printTokens(tokenMap);
    }
}
