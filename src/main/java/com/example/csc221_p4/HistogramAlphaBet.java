package com.example.csc221_p4;

import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class HistogramAlphaBet {
    private Map<Character, Integer> frequency = new HashMap<Character, Integer>(); // frequencies of the alphabet characters
    private Map<Character, Double> probability = new HashMap<Character,Double>(); // probability of the alphabet characters

    //Constructor
    HistogramAlphaBet(String temp){
        // Get frequency of each Alphabet character
        for(int i =0; i<temp.length();i++) {
            frequency(frequency,temp.charAt(i));
        }

        // Get Probability
        double total = frequency.values().stream().mapToInt(Integer::intValue).sum();
        for(Map.Entry<Character,Integer> set : frequency.entrySet()) {
            probability.put(set.getKey(), set.getValue()/total);
        }
    }

    public void frequency(Map<Character,Integer> temp, char key){
        temp.putIfAbsent(key, 0);
        temp.put(key, temp.get(key) + 1);
    }

    class MyPieChart{
        int sliceNum; // Number of Slices
        int radius; // Radius of Circle
        MyPoint center; // Center of the Pie
        Map<Character, Slice> pies = new HashMap<Character,Slice>();
        double other;

        MyPieChart(int sliceNum, int radius, MyPoint c){
            this.sliceNum = sliceNum;
            this.radius = radius;
            this.center = c;
            Random r = new Random();
            Double totalAngle = 0.0 ; // Total Angle

            List<Character> keys = probability
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<Character, Double>comparingByValue()
                            .reversed())
                    .limit(sliceNum).map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            for(int i = 0; i<keys.size();i++) {
                pies.put(keys.get(i), new Slice(c, radius, probability.get(keys.get(i))*360, totalAngle,
                        MyColor.values()[r.nextInt(MyColor.values().length - 1)]));
                totalAngle += probability.get(keys.get(i))*360;
                if(i == keys.size()-1) {
                    pies.put(' ', new Slice(c, radius, 360 - totalAngle, totalAngle, MyColor.values()[r.nextInt(MyColor.values().length - 1)]));
                    other = 1 - totalAngle/360;
                }
            }
        }
        public void draw(GraphicsContext gc) {
            // Iterate through the Colors for MyColor
            for(Map.Entry<Character,Slice> set: pies.entrySet()) {
                set.getValue().draw(gc);

                Slice slice = set.getValue();
                int r = slice.getR();
                int x = slice.getCenter().getX();
                int y = slice.getCenter().getY();
                double angle = Math.toRadians(slice.getSAngle()+(slice.getAngle()/2));

                String data = set.getKey() + " : " + String.format("%.4f", probability.get(set.getKey()));

                if(probability.get(set.getKey()) == null) {
                    data = "All other letters" + " : " + String.format("%.4f", other);
                }
                gc.fillText(data , x+1.3*r*Math.cos(angle), y+1.3*r*Math.sin(angle));
            }
        }
    }
}

