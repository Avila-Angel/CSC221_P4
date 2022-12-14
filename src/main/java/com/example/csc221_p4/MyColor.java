package com.example.csc221_p4;

import javafx.scene.paint.Color;

public enum MyColor {
    // colors in rgba
    CRIMSON(220,20,60,255),
    BURLYWOOD(222,184,135,255),
    PALEGOLDENROD(238,232,170,255),
    GOLD(255,215,0,255),
    GOLDENROD(218,165,32,255),
    DARKGOLDENROD(184,134,11,255),
    TAN(210,180,140,255),
    BLACK(0,0,0,255),
    INDIGO(75,0,130,255),
    HONEYDEW(240,255,240,255),
    WHEAT(245,222,179,255);

    private final int r;
    private final int g;
    private final int b;
    private final int a;
    private final int rgba;

    // Default Constructor
    MyColor(int r, int g, int b, int a){
        this.r = r; // red
        this.g = g; // green
        this.b = b; // blue
        this.a = a; // opacity
        this.rgba = (a << 24) & 0xFF000000 | (r << 16) & 0x00FF0000 | (g << 8) & 0x0000FF00| (b) & 0x000000FF;
    }

    MyColor(MyColor color){
        this.r = color.getR();
        this.g = color.getG();
        this.b = color.getB();
        this.a = color.getA();
        this.rgba = color.getRGBA();
    }

    // getters
    public int getR(){
        return r; // returns r value
    }
    public int getG(){
        return g; // returns g value
    }
    public int getB(){
        return b; // returns b value
    }
    public int getA(){
        return a; // returns opacity value
    }
    public int getRGBA(){
        return rgba; // returns rgba value
    }
    public Color getColor(){
        return Color.rgb(r,g,b,a/255.0); // gets color through javafx
    }
    public String getRGBAStr(){
        return "#" + Integer.toHexString(rgba).toUpperCase(); // returns hex value
    }
}
