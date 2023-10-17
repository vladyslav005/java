package hw8.tests;

import hw8.*;

public class ProcessShapeTest {

    public static void main(String[] args) {
        ProcessShape.printFigureName(new Circle());
        ProcessShape.printFigureName(new Triangle());
        ProcessShape.printFigureName(new Trapezoid());
        ProcessShape.printFigureName(new Square());
        ProcessShape.printFigureName(new Rectangle());
        ProcessShape.printFigureName(new Hexagon());
    }
}
