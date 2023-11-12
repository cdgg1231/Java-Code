public class Node {

    private Shoes shoes;
    private Node  next;

    public Node(Shoes shoes) {
        this.shoes = shoes;
        this.next  = null;
    }

    public Node getNext() { return next; }

    public void setNext(Node next) { this.next = next; }

    public Shoes getShoes() { return shoes; }

    @Override
    public String toString() {
        return shoes.toString();
    }
}
