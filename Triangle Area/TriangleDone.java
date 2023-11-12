public class TriangleDone {
    double base;
    double height;
    double sideLenOne;
    double sideLenTwo;
    double sideLenThree;

    //static variable
    static int numberOfSides = 3;

    public TriangleDone(double base, double height, double sideLenOne, double sideLenTwo, double sideLenThree) {
        this.base = base;
        this.height = height;
        this.sideLenOne = sideLenOne;
        this.sideLenTwo = sideLenTwo;
        this.sideLenThree = sideLenThree;
    }
    public double findArea(){
        return (this.base * this.height) / 2;

    }
}
