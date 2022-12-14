package com.example.csc221_p4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape{
    private MyPoint pStart, pEnd, pCenter; //starting, ending, and center point
    private MyColor color; // ArcFill color
    private double angle; // arc angle
    private double angleStart; // starting Angle
    private int w, h;
    private int width, height; // Width and Height
    MyOval oval;

    // Constructor
    MyArc(MyOval o, MyPoint farPoint, MyPoint smallPoint, MyColor color){
        this.oval = o;
        this.color = color;
        //Getting info from Oval
        this.pCenter = o.getCenter();
        this.angleStart = pCenter.angleX(smallPoint);
        this.angle = pCenter.angleX(farPoint) - angleStart;
        this.width = o.getWidth();
        this.height = o.getHeight();
        this.w = width /2;
        this.h = height /2;
        this.pStart = farPoint;
        this.pEnd = smallPoint;
    }

    MyArc(MyOval o, double angle, double angleStart, MyColor color) {
        this.oval = o;
        this.angle = angle;
        this.angleStart = angleStart;
        this.color = color;
        //Getting info from Oval
        this.pCenter = o.getCenter();
        this.width = o.getWidth();
        this.height = o.getHeight();
        this.w = width / 2;
        this.h = height / 2;
        this.pStart = new MyPoint(w * (int) Math.cos(Math.toRadians(angle + angleStart)),
                h * (int) Math.sin(Math.toRadians(angle + angleStart)));
        this.pEnd = new MyPoint(w * (int) Math.cos(Math.toRadians(angleStart)),
                h * (int) Math.sin(Math.toRadians(angleStart)));
    }
    //get Methods
    public MyPoint getpStart() {return pStart;}
    public MyPoint getpEnd() {return pEnd;}
    public MyPoint getpCenter() {return pCenter;}
    public int getWidth() { return w;}
    public int getHeight() { return h;}
    public MyColor getC() {return color;}

    public double length(){
        double t = Math.atan(w / h*Math.tan(angle));
        return Math.sqrt((w * w)*(Math.sin(t)*Math.sin(t))+(h * h) * (Math.cos(t) * Math.cos(t)));
    }

    @Override
    public double area() {
        System.out.println((Math.toRadians(angle + angleStart) * w * h / 2)
                - (Math.toRadians(angleStart) * w * h / 2));
        return ((Math.toRadians(angle + angleStart) * w * h / 2)
                - (Math.toRadians(angleStart) * w * h / 2));
    }

    @Override
    public double perimeter() {
        double t = Math.atan(w / h*Math.tan(angle));
        return Math.sqrt((w * w)*(Math.sin(t) * Math.sin(t))+(h * h) * (Math.cos(t) * Math.cos(t)));
    }

    @Override
    public String toString() {
        return "Arc Angle: " + this.angle +
                "\nArc Length: " + this.length() +
                "\nArc Sector Area: " + this.area();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color.getColor());
        gc.fillArc((int) pCenter.getX() - w,(int) pCenter.getY() - h,
                width, height, angleStart, angle, ArcType.ROUND);
    }

    //@Override
    public MyRectangle getMyBoundingRectangle(){ //Change
        MyPoint pBegin = new MyPoint(pCenter.getX() - w, pCenter.getY() - h);
        MyRectangle temp = new MyRectangle(width, height,pBegin,MyColor.BLACK);
        return temp;
    }

    //@Override
    public boolean pointInMyShape(MyPoint p) {
        if((Math.pow(p.getX()- pCenter.getX(),2) / (w * w) + Math.pow(p.getY()- pCenter.getY(),2) /
                (h * h)) <= 1 && (Math.abs(pCenter.angleX(p)-360)%360 <= (angle+ angleStart) &&
                Math.abs(pCenter.angleX(p)-360)%360>= angleStart)){
            return true;
        }
        return false;
    }
}
