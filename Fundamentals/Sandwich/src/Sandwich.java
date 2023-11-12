public class Sandwich {

    private String name;
    private int calories;
    private double price;

    final int DEFAULT_CALORIES = 0;
    final int DEFAULT_PRICE = 0;
    private static final int HEALTHY_CALORIES = 250;

    public Sandwich(String name, int calories, double price) {
        this.name = name;
        if (this.calories <0)
            this.calories = DEFAULT_CALORIES;
        else this.calories = calories;

        if(this.price <0)
        this.price =DEFAULT_PRICE;
        else this.price = price;

    }

    public boolean  isHealthy(){
        if (calories< HEALTHY_CALORIES)
            return true;
        else return false;
        }

    @Override
    public String toString() {
        return name + " has " +  calories + " Calories and cost price$=" + price;
    }
}
