package CarShop.models.enums;

public enum Role {

    User(0),
    Admin(1);
    private final int index;
    Role(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }

}
