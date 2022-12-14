package com.example.csc221_p4;

import javafx.scene.canvas.GraphicsContext;

public class MyCircle extends MyOval{
    private int radius;
    private MyPoint center;
    private MyColor color;

    MyCircle(int r, MyPoint center, MyColor color){
//		super(r*2,r*2,center,color);
        this.radius = r;
        this.center = center;
        this.color = color;
    }
    //Get Methods
    public int getR() {return radius;}
    public MyPoint getCenter() {return center;}
    public MyColor getC() {return color;}

    //Perimeter and Area
    public double Area() {return radius*radius*Math.PI;}
    public double Perimeter() {return 2*radius*Math.PI;}

    //String
    @Override
    public String toString() {
        return "Radius: " + radius +
                "\nCenter: " + center.toString() +
                "\nArea: " + Area() +
                "\nCircumference: " + Perimeter(); 
    }
    //draw
    public void draw(GraphicsContext gc) {
        gc.setFill(color.getColor());
        gc.fillOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
    }

    @Override
    public MyRectangle getMyBoundingRectangle(){
        MyPoint pBegin = new MyPoint(center.getX()-radius,center.getY()-radius);
        MyRectangle temp = new MyRectangle(radius*2, radius*2,pBegin,MyColor.BLACK);
        return temp;
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        int x = p.getX();
        int y = p.getY();
        return (Math.pow(center.getX()-x,2)+Math.pow(center.getY()-y,2)<=radius*radius);
    }

}
