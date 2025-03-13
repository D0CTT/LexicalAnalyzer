package unam.fi.compilers.g5.09.Lexer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Lexer class is responsible for tokenizing an input string based on predefined token types.
 * It uses regular expressions to recognize different types of tokens, such as keywords, identifiers,
 * operators, constants, literals, and punctuators. It also ignores whitespace and comments.
 */
public class Lexer {
    private String input; // The input string to be analyzed
    private static final Pattern TOKEN_REGEX; // Regular expression pattern for tokenizing

    // Static block to initialize the regular expression pattern
    static {
        TOKEN_REGEX = Pattern.compile(
            "(?<WHITESPACE>\\s+)|" +  // Matches spaces, tabs, and newlines (ignored)
            "(?<COMMENT>//.*|/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/)|" + // Matches single-line and multi-line comments (ignored)
            "(?<KEYWORD>\\b(case|return|break|void|if|int|float|do|else|while)\\b)|" + // Matches C keywords
            "(?<IDENTIFIER>[a-zA-Z_][a-zA-Z0-9_]*)|" + // Matches variable and function names
            "(?<OPERATOR>(\\+\\+|--|\\+\\=?|-\\=?|\\*\\=?|/\\=?|%\\=?|==?|!\\=?|<=?|>=?|&&|\\|\\||\\^=?|<<=?|>>=?|&=|\\|=|&|\\|))|" + // Matches operators
            "(?<CONSTANT>[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?)|" + // Matches integer and floating-point constants
            "(?<LITERAL>\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\")|" + // Matches string literals
            "(?<PUNCTUATOR>[()\\[\\]{}.,;:#])" // Matches punctuation symbols
        );
    }

    /**
     * Constructs a Lexer object with the given input string.
     * @param input The input string to be tokenized.
     */
    public Lexer(String input) {
        this.input = input;
    }

    /**
     * Analyzes the input string and returns a map of tokens categorized by their type.
     * Each token type contains a LinkedHashMap where tokens are counted based on their occurrences.
     * 
     * @return A map of token types with corresponding token frequency counts.
     */
    public Map<Token.TokenType, LinkedHashMap<String, Integer>> analyze() {
        // Map to store tokens categorized by their type and count occurrences
        Map<Token.TokenType, LinkedHashMap<String, Integer>> tokensFound = new LinkedHashMap<>();

        // Initialize the map with empty LinkedHashMaps for each token type
        for (Token.TokenType type : Token.TokenType.values()) {
            tokensFound.put(type, new LinkedHashMap<>());
        }

        Matcher matcher = TOKEN_REGEX.matcher(input);

        // Process tokens and count occurrences
        while (matcher.find()) {
            Token.TokenType tokenType = null;
            String tokenValue = null;

            // Ignore whitespace and comments
            if (matcher.group("WHITESPACE") != null || matcher.group("COMMENT") != null) {
                continue;
            }

            // Identify token type based on matching group
            if (matcher.group("KEYWORD") != null) {
                tokenType = Token.TokenType.KEYWORD;
                tokenValue = matcher.group();
            } else if (matcher.group("IDENTIFIER") != null) {
                tokenType = Token.TokenType.IDENTIFIER;
                tokenValue = matcher.group();
            } else if (matcher.group("OPERATOR") != null) {
                tokenType = Token.TokenType.OPERATOR;
                tokenValue = matcher.group();
            } else if (matcher.group("CONSTANT") != null) {
                tokenType = Token.TokenType.CONSTANT;
                tokenValue = matcher.group();
            } else if (matcher.group("LITERAL") != null) {
                tokenType = Token.TokenType.LITERAL;
                tokenValue = matcher.group();
            } else if (matcher.group("PUNCTUATOR") != null) {
                tokenType = Token.TokenType.PUNCTUATOR;
                tokenValue = matcher.group();
            }

            // If a valid token is found, update its count
            if (tokenType != null && tokenValue != null) {
                LinkedHashMap<String, Integer> tokenCounts = tokensFound.get(tokenType);
                tokenCounts.put(tokenValue, tokenCounts.getOrDefault(tokenValue, 0) + 1);
            }
        }

        return tokensFound;
    }

    /**
     * Prints the tokens found in the input string along with their occurrence count.
     * Also prints the total number of tokens found.
     * 
     * @param tokenMap A map containing token types and their corresponding tokens with counts.
     */
    public void printTokens(Map<Token.TokenType, LinkedHashMap<String, Integer>> tokenMap) {
        int totalTokens = 0; // Counter for total tokens

        for (Map.Entry<Token.TokenType, LinkedHashMap<String, Integer>> entry : tokenMap.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.print(entry.getKey() + ": ");
                List<String> formattedTokens = new ArrayList<>();

                // Format tokens as "value[count]" and update total count
                for (Map.Entry<String, Integer> tokenEntry : entry.getValue().entrySet()) {
                    formattedTokens.add(tokenEntry.getKey() + "[" + tokenEntry.getValue() + "]");
                    totalTokens += tokenEntry.getValue();
                }

                // Print formatted token list
                System.out.println(String.join(", ", formattedTokens));
            }
        }

        // Print total number of tokens found
        System.out.println("\nTotal tokens found: " + totalTokens);
    }    
}