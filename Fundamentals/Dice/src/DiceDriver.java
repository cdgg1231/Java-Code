public class DiceDriver {
    public static void main(String[] args) {
        Dice dice = new Dice();
        System.out.println(dice.getValue());
        dice.roll();
        System.out.println(dice.getValue());
        dice.roll();
        System.out.println(dice.getValue());
    }
}
