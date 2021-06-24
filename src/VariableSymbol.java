public class VariableSymbol extends Symbol {

    private int symbolSizeBytes;

    public VariableSymbol (String symbolName, SymbolType symbolType) {
        super(symbolName, symbolType);
        calculateSymbolSizeInBytes(symbolType);
    }

    private void calculateSymbolSizeInBytes(SymbolType symbolType) {
        if (symbolType==SymbolType.INT || symbolType==SymbolType.DOUBLE || symbolType==SymbolType.BOOL) {
            this.symbolSizeBytes = 4;
        }
    }

    public int getSymbolSizeBytes() {
        return symbolSizeBytes;
    }
}
