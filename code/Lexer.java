import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private String input;
    private static final Pattern TOKEN_REGEX;

    static {
        TOKEN_REGEX = Pattern.compile(
            "(?<KEYWORD>\\b(case|return|break|void|if|int|float|do|else|while)\\b)|" +
            "(?<IDENTIFIER>[a-zA-Z_][a-zA-Z0-9_]*)|" +
            "(?<OPERATOR>(\\+\\+|--|\\+\\=?|-\\=?|\\*\\=?|/\\=?|%\\=?|==?|!\\=?|<=?|>=?|&&|\\|\\||\\^=?|<<=?|>>=?|&=|\\|=|&|\\|))|" +
            "(?<CONSTANT>[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?)|" +
            "(?<LITERAL>\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\")|" +
            "(?<PUNCTUATOR>[()\\[\\]{}.,;:#])"
        );
    }

    public Lexer(String input) {
        this.input = input;
    }

    public Map<Token.TokenType, List<Token>> analyze() {
        Map<Token.TokenType, List<Token>> tokensFound = new LinkedHashMap<>();

        for (Token.TokenType type : Token.TokenType.values()) {
            tokensFound.put(type, new ArrayList<>());
        }

        Matcher matcher = TOKEN_REGEX.matcher(input);

        while (matcher.find()) {
            if (matcher.group("KEYWORD") != null) {
                tokensFound.get(Token.TokenType.KEYWORD).add(new Token(Token.TokenType.KEYWORD, matcher.group()));
            } else if (matcher.group("IDENTIFIER") != null) {
                tokensFound.get(Token.TokenType.IDENTIFIER).add(new Token(Token.TokenType.IDENTIFIER, matcher.group()));
            } else if (matcher.group("OPERATOR") != null) {
                tokensFound.get(Token.TokenType.OPERATOR).add(new Token(Token.TokenType.OPERATOR, matcher.group()));
            } else if (matcher.group("CONSTANT") != null) {
                tokensFound.get(Token.TokenType.CONSTANT).add(new Token(Token.TokenType.CONSTANT, matcher.group()));
            } else if (matcher.group("LITERAL") != null) {
                tokensFound.get(Token.TokenType.LITERAL).add(new Token(Token.TokenType.LITERAL, matcher.group()));
            } else if (matcher.group("PUNCTUATOR") != null) {
                tokensFound.get(Token.TokenType.PUNCTUATOR).add(new Token(Token.TokenType.PUNCTUATOR, matcher.group()));
            }
        }

        return tokensFound;
    }

    public void printTokens(Map<Token.TokenType, List<Token>> tokenMap) {
        for (Map.Entry<Token.TokenType, List<Token>> entry : tokenMap.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.print(entry.getKey() + ": ");
                for (Token token : entry.getValue()) {
                    System.out.print(token.getValue() + "  ");
                }
                System.out.println("\n");
            }
        }
    }
}
