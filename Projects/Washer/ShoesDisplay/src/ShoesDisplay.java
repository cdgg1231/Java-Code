public class ShoesDisplay {

    private Node tennisShelf;  // this is two linklist
    private Node dressShelf;  // second linklist

    public ShoesDisplay() {
        tennisShelf = null;
        dressShelf = null;
    }

    // TODO: add the given shoes to the correct shelf (head insert is fine)
    public void add(Shoes shoes) {
        // Node head = shoes.getType() == Shoes.TENNIS ? tennisShelf : dressShelf;
        // advance way to start the linklist

        Node newNode = new Node(shoes);

        if (shoes.getType() == Shoes.TENNIS) {
            newNode.setNext(tennisShelf);
            tennisShelf = newNode;
        } else {
            newNode.setNext(dressShelf);
            dressShelf = newNode;
            //this is intializing two different shoe shelfs. Two different linklist.
        }
    }

    // TODO: return the total number of shoes of the given type
    public int total(int type) {
        int count = 0;
        Node current = tennisShelf; //assuming that type is Tennis
       if (type == Shoes.DRESS) // otherwise change to Dress shoes
           current = dressShelf;
        while (current != null){
            count++;
            current= current.getNext();
        }
        return count;
        //again this is two linklist. You are initializing the count. if(type) than set current to the other
    }

    @Override
    public String toString() {
        String out = "Tennis shoes: ";
        Node current = tennisShelf;
        while (current != null) {
            out += current.toString() + " ";
            current = current.getNext();
        }
        out += "\nDress shoes: ";
        current = dressShelf;
        while (current != null) {
            out += current.toString() + " ";
            current = current.getNext();
        }
        return out;
    }
}