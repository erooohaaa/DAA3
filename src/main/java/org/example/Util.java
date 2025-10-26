package org.example;

public class Util {

    public static double msDuration(long startNanoTime, long endNanoTime) {
        double milliseconds = (endNanoTime - startNanoTime) / 1_000_000.0;
        return Math.round(milliseconds * 100.0) / 100.0;
    }
}