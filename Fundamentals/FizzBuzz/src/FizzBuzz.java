/*
 * CS2050 - Computer Science II - Summer 2021
 * Instructor: Thyago Mota
 * Description: Homework 03 - FizzBuzz
 */

public class FizzBuzz {

    private int current;
    private static final int DEFAULT_START = 1;

    // TODO: implement a parameter-less constructor that sets the current value to the default start (ie, 1)
    public FizzBuzz(){
        this.current = DEFAULT_START;         // do not need this. because no parameter pulling data
    }


    // TODO: implement a parameterized constructor that sets the current value based on the user provided value;
    //  if start is less than 1, set current to the default start (ie, 1)

    public FizzBuzz(int current) {
        if(current < 1)
            this.current = DEFAULT_START;
        else
        this.current = current;
    }


    // TODO: implement the getter method that should return the value of current

    public int getCurrent() {
        return current;
    }


    // TODO: implement next which should increment current by 1 unit
    public void next(){
        current++;
    }

    // TODO: override toString which should return the current number as a string, or the words "Fizz", "Buzz", or
    //  "FizzBuzz" depending whether the current number is a multiple of 3, 5, or both, respectively


    @Override
    public String toString() {
        if(current % 3 == 0 && current % 5== 0)
            return "FizzBuzz";

            else if(current % 3 == 0)
                return "Fizz";
           else if (current % 5 ==0)
                return "Buzz";
            else
                return String.valueOf(current);
            // to pass the test convert to string
        // But to look nice in code. return "Try again"

    }
}