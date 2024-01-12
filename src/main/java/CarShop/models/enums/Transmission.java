package CarShop.models.enums;

public enum Transmission {

    Manual(0), Automatic(1);

    private final int index;

    Transmission(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
