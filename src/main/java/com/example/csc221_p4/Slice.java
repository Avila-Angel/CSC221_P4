package com.example.csc221_p4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

class Slice{
    private MyPoint center; //First point, Last point, Center point
    private MyColor color; //Color of the ArcFill
    private double angle; //Angle of the arc
    private double startAngle; //Starting Angle
    private int radius; // radius

    //Constructor for Slice
    Slice(MyPoint c, int r, double angle, double sAngle, MyColor color){
        this.center = c;
        this.radius = r;
        this.angle = angle;
        this.startAngle = sAngle;
        this.color = color;
    }

    //get Methods
    public int getR() {return radius;}
    public double getSAngle() {return startAngle;}
    public double getAngle() {return angle;}
    public MyPoint getCenter() {return center;}
    public MyColor getC() {return color;}

    //Arc length of the slice
    public double arclength(){return radius*Math.toRadians(angle);}
    //Arc Area
    public double area() {	return radius*radius*Math.toRadians(angle)/2;}

    public double perimeter() {return arclength()+2*radius;}

    public String toString() {
        return "Slice angle : " + angle +
                "\n Radius: " +
                "\nArc Length: " + arclength() +
                "\nArc Sector Area: " + area() +
                "\nColor: " + color;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color.getColor());
        gc.fillArc(center.getX()-radius,center.getY()-radius, radius*2, radius*2, -startAngle, -angle, ArcType.ROUND);
    }
}
