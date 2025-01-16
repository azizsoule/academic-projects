package soule.caculator;

public enum Operation {

    ADDITION("+"),
    SUBTRACTION("-"),
    DIVISION("/"),
    MULTIPLICATION("*"),
    MODULO("%");

    private final String symbol;

    Operation(String symbol) {
        this.symbol = "\\"+symbol;
    }

    public String getSymbol() {
        return Character.valueOf(symbol.charAt(1)).toString();
    }

    public String getRegexSymbol() {
        return symbol;
    }

    public Character getChar() {
        return getSymbol().charAt(0);
    }

}
