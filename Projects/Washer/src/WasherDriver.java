public class WasherDriver {

    public static void main(String[] args) {

        Washer w1 = new Washer();
        w1.turnOn();
        Washer w2 = new Washer();

        if (w1.equals(w2))  // w1 is the collie obj
            System.out.println("equals");
        else
            System.out.println("not equals");





    }
}

