public class Shoes {

    private int   size;
    private int   type;
    public static final int TENNIS = 0;
    public static final int DRESS  = 1;

    public Shoes(int size, int type) {
        this.size   = size;
        this.type   = type; // assume that type is always 0 or 1
    }

    public double getSize() { return size; }

    public int getType() { return type; }

    @Override
    public String toString() {
        return "Shoes{" + "size=" + size + ", type=" + type + '}';
    }
}

