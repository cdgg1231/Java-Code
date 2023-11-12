public class Washer {

    private boolean on;
    private String cycle;

    public Washer() {
        on = false;
        cycle = "idle";
    }

    public boolean isOn() {
        return on;
    }

    public void turnOn() {
        on = true;
        cycle = "idle";
    }

    public void turnOff() {
        on = false;
        cycle = "idle";
    }

    public String getCycle() {
        return cycle;
    }

    private void wash() {
        if (isOn() && getCycle().equals("idle"))
            cycle = "wash";
    }

    private void rinse() {
        if (isOn() && getCycle().equals("wash"))
            cycle = "rinse";
    }

    private void spin() {
        if (isOn() && getCycle().equals("rinse"))
            cycle = "spin";
    }

    // run the current cycle for the given amount of minutes
    private void runFor(int minutes) {
        try {
            Thread.sleep(minutes * 60 * 1000);
        }
        catch (InterruptedException ex) {

        }
    }

    public void run(){
        turnOn();
        wash();
        runFor(30);
        rinse();
        runFor(30);
        spin();
        runFor(30);
        cycle = "idle";
    }

    @Override

    public boolean equals(final Object obj){

   Washer other = (Washer) obj; //type cast to look at the washer object

        return on == other.on && cycle.equals(other.cycle);  //don't have to put this.on cuz its an instance variable. If
        // you have a local variable in the method than you do. Also have to use .equals on cycle. Cuz it's a string
        // a reference variable. On is a primite type boolean.
    }

@Override
public Object clone(){
        Washer clone = new Washer();
        clone.on    = on;
        clone.cycle = cycle;
        return clone;

}

    @Override
    public String toString() {
        return "Washer{" +
                "on=" + on +
                ", cycle='" + cycle + '\'' +
                '}';
    }
}
