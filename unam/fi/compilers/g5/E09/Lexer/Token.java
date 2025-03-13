package unam.fi.compilers.g5.E09.Lexer;
/**
 * The Token class represents a lexical token, storing its type and value.
 */
public class Token {

    /**
     * Enum representing different types of tokens that can be identified.
     */
    public enum TokenType {
        KEYWORD,       // Reserved words like "int", "if", "return", etc.
        IDENTIFIER,    // Variable names, function names, etc.
        OPERATOR,      // Arithmetic, logical, and assignment operators (+, -, =, etc.)
        CONSTANT,      // Numeric constants (e.g., 42, 3.14)
        LITERAL,       // String literals (e.g., "Hello World")
        PUNCTUATOR,    // Symbols like parentheses, commas, semicolons, etc.
        WHITESPACE,    // Spaces, tabs, and newlines (ignored in token processing)
        COMMENT        // Single-line (//) or multi-line (/* ... */) comments
    }

    private TokenType type;  // The type of the token
    private String value;    // The actual string representation of the token

    /**
     * Constructor for the Token class.
     *
     * @param type  The type of the token (from the TokenType enum).
     * @param value The value of the token as a string.
     */
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Retrieves the token type.
     *
     * @return The TokenType of this token.
     */
    public TokenType getType() {
        return this.type;
    }

    /**
     * Retrieves the token value.
     *
     * @return The string representation of the token.
     */
    public String getValue() {
        return this.value;
    }
}
