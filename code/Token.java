public class Token {
    public enum TokenType {
        KEYWORD, IDENTIFIER, OPERATOR, CONSTANT, LITERAL, PUNCTUATOR;
    }

    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

}
