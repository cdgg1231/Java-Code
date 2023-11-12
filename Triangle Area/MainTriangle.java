public class MainTriangle {
    public static void main(String[] args) {
        TriangleDone triangleA = new TriangleDone(15, 8, 15, 8, 17);
        TriangleDone triangleB = new TriangleDone(3,2.5, 3, 3, 3 );

        double triangleAArea = triangleA.findArea();
        System.out.println(triangleAArea);

        double triangleBArea = triangleB.findArea();
        System.out.println(triangleBArea);

        System.out.println(TriangleDone.numberOfSides);


        //Triangle.findArea() --> Math.pow(2,3); instance variable and static class.
    }
}
