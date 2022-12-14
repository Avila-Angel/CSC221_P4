package com.example.csc221_p4;

import javafx.scene.canvas.GraphicsContext;

class MyPoint {
    private int x; // x-coordinate of point (0,0)
    private int y; // y-coordinate of point (0,0)
    private MyColor color; // Color of point

    // constructors
    MyPoint() {
        setPoint(0,0);
        this.color = MyColor.GOLD; // default color
    }
    public MyPoint(int x, int y) {
        setPoint(x, y);
        this.color = MyColor.DARKGOLDENROD; // default color
    }
    public MyPoint (int x, int y, MyColor color) {
        setPoint(x, y);
        this.color = color;
    }
    // copy constructor - makes a copy of myPoint object
    public MyPoint(MyPoint temp) {
        setPoint(temp.getX(), temp.getY());
        this.color = temp.getColor();
    }
    // Setters
    // sets x and y
    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // sets color of point
    public void setColor(MyColor temp) {
        this.color = temp;
    }

    // Getters

    // returns x value
    public int getX() {
        return x;
    }
    // returns y value
    public int getY() {
        return y;
    }

    // returns color object of the point
    public MyColor getColor() {
        return color;
    }

    // Compute the distance between this point and some other point
    public double length(MyPoint temp) {
        int dx = x - temp.getX();
        int dy = y - temp.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    // Get angle - takes a temp point as a parameter and returns the angle from the initial point to temp point
    public double angleX(MyPoint temp){
        double dx = (double) (temp.getX() - x);
        double dy = (double) (temp.getY() - y);
        return Math.toDegrees(Math.atan2(dy, dx));
    }

    // Draw a point on canvas with a size of 1 by 1 and with a color
    public void draw(GraphicsContext gc) {
        gc.setFill(color.getColor());
        gc.fillOval(x, y, 1, 1);

    }

    // return a string representation of the MyPoint Object
    @Override
    public String toString() {
        return "Point{" +
                + x +
                ", " + y +
                '}';
    }
}

