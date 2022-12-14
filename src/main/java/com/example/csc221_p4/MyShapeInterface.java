package com.example.csc221_p4;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Vector;

interface MyShapeInterface {
    //Return the bounding rectangle of a shape
    MyRectangle getMyBoundingRectangle();

    //Return True or False if a point is found in a shape
    boolean pointInMyShape(MyPoint x);

    //static Method
    public static Vector<MyPoint> intersectMyShapes(MyShape x, MyShape y) {
        Vector<MyPoint> temp = new Vector<MyPoint>();

        MyRectangle rectangleX = x.getMyBoundingRectangle();
        //MyRectangle rectangleY = y.getMyBoundingRectangle();

        for (int i = 0; i < rectangleX.getWidth(); ++i) {
            for (int j = 0; j < rectangleX.getHeight(); ++j) {
                MyPoint test = new MyPoint(rectangleX.getP().getX() + i, rectangleX.getP().getY() + j);
                if (y.pointInMyShape(test) && x.pointInMyShape(test)) {
                    temp.add(test);
                }
            }
        }
        return temp;
    }

    //Draw Method
    public default Canvas drawIntersectMyShapes(MyShape x, MyShape y) {
        Canvas localCanvas = new Canvas();
        GraphicsContext gc = localCanvas.getGraphicsContext2D();


        Vector<MyPoint> shape;
        shape = intersectMyShapes(x, y);

        x.draw(gc);
        y.draw(gc);

        for (MyPoint i : shape) {
            i.draw(gc);
        }
        return gc.getCanvas();
    }

    public static void drawIntersectMyShapes(MyShape x, MyShape y, GraphicsContext gc) {
        Vector<MyPoint> shape = new Vector<MyPoint>();
        shape = intersectMyShapes(x, y);

        x.draw(gc);
        y.draw(gc);
        for (MyPoint i : shape) {
            i.draw(gc);
        }
    }
}