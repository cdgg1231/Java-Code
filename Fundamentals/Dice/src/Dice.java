import java.lang.invoke.VarHandle;
import java.util.Random;

public class Dice{

    private static int DEFAULT_SIDES = 6;
    private static int INITIAL_VALUE = 1;
    private static int MIN_SIDES = 2;
    private int side;
    private int value;

    public Dice(int side) {
       if(side <= MIN_SIDES)
           this.side = MIN_SIDES;
       else
           this.side = side;
       value= INITIAL_VALUE;

    }

    public Dice(){
        this.side = DEFAULT_SIDES;
        value = INITIAL_VALUE;
    }

    public int getValue() {
        return value;
    }

    public int roll(){
        value = new Random().nextInt(side+ 1);
        return value;
    }
}