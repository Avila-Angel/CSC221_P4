package com.example.csc221_p4;

import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape{
    private int w, h;
    private MyPoint pCenter; // origin of MyOval
    private MyColor color; // color of MyOval
    private int abscissa; // the distance of a point from y axis scaled with the x axis (x coordinate)


    //Default Constructor

    MyOval(){
        this.w = 0;
        this.h = 0;
        this.abscissa = 0;
        this.pCenter = new MyPoint(0,0);
        this.color = MyColor.GOLD; // default color
    }

    MyOval(int height, int width, MyPoint point, MyColor color){
        this.w = width;
        this.h = height;
        this.pCenter = point;
        this.color = color;
        this.abscissa = width;
    }
    // Copy Constructor
    MyOval(MyOval temp){
        this.w = temp.w;
        this.h = temp.h;
        this.pCenter = temp.getCenter();
        this.color = temp.getColor();
        this.abscissa = temp.abscissa;
    }

    // getters
    public MyPoint getCenter(){
        return pCenter; // returns center point (origin)
    }
    public int getA(){
        return Math.max(w, h); // returns horizontal axis
    }
    public int getB(){
        return Math.min(w, h); // returns vertical axis
    }
    public int getWidth() { return w;} // returns width
    public int getHeight() { return h;} // returns height

    public MyColor getColor(){
        return color; // returns color
    }

    //Methods for Area and perimeter
    @Override
    public double area() {
        return (Math.PI * (w / 2) * (h / 2)); // overrides super class and returns area of oval
    }
    @Override
    public double perimeter(){
        return ((2 * Math.PI) * Math.sqrt((w * w + h * h) / 2));
        // overrides super class and returns perimeter of oval
    }

    // toString - returns string representation of MyOval
    @Override
    public String toString(){
        return 	"Abscissa: " + abscissa + "\n" +
                "Area: " + "\n" +
                "Perimeter: " + "\n";
    }

    // draw - draws MyOval object
    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(getColor().getColor()); // gets color
        gc.fillOval(pCenter.getX()-(w / 2), pCenter.getY()-(h / 2), w, h);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyRectangle rectangle = new MyRectangle(this.h, this.w,
                new MyPoint(pCenter.getX()-(w/2),pCenter.getY()-(h/2)), MyColor.BLACK);
        return rectangle;
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        int w2 = w/2;
        int h2 = h/2;
        return ((Math.pow(p.getX()-pCenter.getX(),2)/(w2*w2)+Math.pow(p.getY()-pCenter.getY(),2)/(h2*h2))<=1);
    }
}