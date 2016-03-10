package org.fasttrackit.util; //grupul de unde face parte clasa

/**
 * Created by lucia on 3/3/2016.
 */
public class Example {
    public static void main(String[] args) {
        Calc myCalc = new Calc(3);

        int sum = myCalc.add(567, 876);
        System.out.println(sum);

        sum = myCalc.add(3, 5);
        System.out.println(sum);
    }
}


