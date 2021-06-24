public class ArraySymbol extends Symbol {

    private int arraySize;
    private SymbolType elementsType;

    public ArraySymbol (String symbolName, SymbolType symbolType, int arraySize, SymbolType elementsType) {
        super(symbolName, symbolType);
        this.arraySize = arraySize;
        this.elementsType = elementsType;
    }

    public int getArraySize() {
        return arraySize;
    }

    public SymbolType getElementsType() {
        return elementsType;
    }
}
