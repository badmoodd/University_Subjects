package org.example.LogicOperations;

public class LogicOperations {
    public static int conjunction(int a, int b) {
        return a & b;
    }

    public static int disjunction(int a, int b) {
        return a | b;
    }

    public static int negation(int a) {
        return 1 - a;
    }
}
