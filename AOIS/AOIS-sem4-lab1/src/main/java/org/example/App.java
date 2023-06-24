package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        int a = 18;
        int b = 10;

        System.out.println("Direct");
        System.out.println(a + "  ----  " + BinaryConvertor.direct(a));
        System.out.println(-a + "  ----  " + BinaryConvertor.direct(-a));
        System.out.println(b + "  ----  " + BinaryConvertor.direct(b));
        System.out.println(-b + "  ----  " + BinaryConvertor.direct(-b));
        System.out.println(87 + "  ----  " + BinaryConvertor.direct(87));

        System.out.println("************************");

        System.out.println("Reverse");
        System.out.println(a + "  ----  " + BinaryConvertor.reverse(a));
        System.out.println(-a + "  ----  " + BinaryConvertor.reverse(-a));
        System.out.println(b + "  ----  " + BinaryConvertor.reverse(b));
        System.out.println(-b + "  ----  " + BinaryConvertor.reverse(-b));
        System.out.println(-35 + "  ----  " + BinaryConvertor.direct(-35));

        System.out.println("************************");

        System.out.println("Two-Complement");
        System.out.println(a + "  ----  " + BinaryConvertor.twoComplement(a));
        System.out.println(-a + "  ----  " + BinaryConvertor.twoComplement(-a));
        System.out.println(b + "  ----  " + BinaryConvertor.twoComplement(b));
        System.out.println(-b + "  ----  " + BinaryConvertor.twoComplement(-b));
        System.out.println(-110 + "  ----  " + BinaryConvertor.direct(-110));

        System.out.println("************************");

        System.out.println("Direct addition");
        System.out.println(a + " + " + b + " = ----" + BinaryConvertor.addDirect(BinaryConvertor.direct(a), BinaryConvertor.direct(b)));
        System.out.println(-a + " + " + b + " = ----" + BinaryConvertor.addDirect(BinaryConvertor.direct(-a), BinaryConvertor.direct(b)));
        System.out.println(a + " + " + -b + " = ----" + BinaryConvertor.addDirect(BinaryConvertor.direct(a), BinaryConvertor.direct(-b)));
        System.out.println(-a + " + " + -b + " = ----" + BinaryConvertor.addDirect(BinaryConvertor.direct(-a), BinaryConvertor.direct(-b)));

        System.out.println("************************");

        System.out.println("Reverse addition");
        System.out.println(a + " + " + b + " = ----" + BinaryConvertor.addReverse(BinaryConvertor.reverse(a), BinaryConvertor.reverse(b)));
        System.out.println(-a + " + " + b + " = ----" + BinaryConvertor.addReverse(BinaryConvertor.reverse(-a), BinaryConvertor.reverse(b)));
        System.out.println(a + " + " + -b + " = ----" + BinaryConvertor.addReverse(BinaryConvertor.reverse(a), BinaryConvertor.reverse(-b)));
        System.out.println(-a + " + " + -b + " = ----" + BinaryConvertor.addReverse(BinaryConvertor.reverse(-a), BinaryConvertor.reverse(-b)));

        System.out.println("************************");

        System.out.println("Two-complement addition");
        System.out.println(a + " + " + b + " = ----" + BinaryConvertor.addTwoComplement(BinaryConvertor.twoComplement(a), BinaryConvertor.twoComplement(b)));
        System.out.println(-a + " + " + b + " = ----" + BinaryConvertor.addTwoComplement(BinaryConvertor.twoComplement(-a), BinaryConvertor.twoComplement(b)));
        System.out.println(a + " + " + -b + " = ----" + BinaryConvertor.addTwoComplement(BinaryConvertor.twoComplement(a), BinaryConvertor.twoComplement(-b)));
        System.out.println(-a + " + " + -b + " = ----" + BinaryConvertor.addTwoComplement(BinaryConvertor.twoComplement(-a), BinaryConvertor.twoComplement(-b)));

        System.out.println("************************");

        System.out.println("Multiplication");
        System.out.println(a + " * " + b + " = ----" + BinaryConvertor.multiplication(BinaryConvertor.direct(a), BinaryConvertor.direct(b)));
        System.out.println(-a + " * " + b + " = ----" + BinaryConvertor.multiplication(BinaryConvertor.direct(-a), BinaryConvertor.direct(b)));
        System.out.println(a + " * " + -b + " = ----" + BinaryConvertor.multiplication(BinaryConvertor.direct(a), BinaryConvertor.direct(-b)));
        System.out.println(-a + " * " + -b + " = ----" + BinaryConvertor.multiplication(BinaryConvertor.direct(-a), BinaryConvertor.direct(-b)));

        System.out.println("************************");
        System.out.println("Division");
        System.out.println(a + " / " + b + " = ----" + BinaryConvertor.division(BinaryConvertor.direct(a), BinaryConvertor.direct(b)));
        System.out.println(-a + " / " + b + " = ----" + BinaryConvertor.division(BinaryConvertor.direct(-a), BinaryConvertor.direct(b)));
        System.out.println(a + " / " + -b + " = ----" + BinaryConvertor.division(BinaryConvertor.direct(a), BinaryConvertor.direct(-b)));
        System.out.println(-a + " / " + -b + " = ----" + BinaryConvertor.division(BinaryConvertor.direct(-a), BinaryConvertor.direct(-b)));
        System.out.println("************************");

        System.out.println("Floating Point addition");
        System.out.println(1.125f + " + " + 1.125f);
        System.out.println(BinaryConvertor.additionFloatingPoint(1.125f, 1.125f));
        System.out.println();

        System.out.println(18.0f + " + " + 10.0f);
        System.out.println(BinaryConvertor.additionFloatingPoint(18.0f,10.0f));
        System.out.println();

        System.out.println(15.14f + " + " + 9.28f);
        System.out.println(BinaryConvertor.additionFloatingPoint(15.14f,9.28f));
        System.out.println();

        System.out.println(29.13f + " + " + 10.28f);
        System.out.println(BinaryConvertor.additionFloatingPoint(29.13f,10.28f));
    }
}
