package org.fasttrackit.util;

/**
 * Created by lucia on 3/3/2016.
 */
public class Calc {
    private int maxNumber;

    public Calc (int maxLength) {
        maxNumber = (int) Math.pow(10, maxLength);
    }

    public int add(int a, int b) {
        int s = a + b;
        if (s >- maxNumber) {
            System.out.println("suma was " + s);
            s = maxNumber -1;
        }
        return s;
    }
}
