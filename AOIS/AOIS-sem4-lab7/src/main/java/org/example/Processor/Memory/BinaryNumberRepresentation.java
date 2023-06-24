package org.example.Processor.Memory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BinaryNumberRepresentation implements Comparable<BinaryNumberRepresentation> {
    final static int BITS_COUNT = 1 << 4;
    private final int decimalRepresentation;

    private final List<Integer> bits;

    public BinaryNumberRepresentation(int decimal) {
        this.decimalRepresentation = decimal;
        this.bits = toBinary(decimal);
    }

    public BinaryNumberRepresentation(List<Integer> bits) {
        this.decimalRepresentation = convertBinaryToDecimal(bits);
        this.bits = bits;
    }

    @NotNull
    private List<Integer> toBinary(int decimal) {
        List<Integer> directView = new ArrayList<>();

        int currentNumber = decimal;
        while (currentNumber != 0) {
            directView.add(currentNumber % 2);
            currentNumber /= 2;
        }

        while (directView.size() < BITS_COUNT) {
            directView.add(0);
        }

        Collections.reverse(directView);
        return directView;
    }

    public int getDecimalRepresentation() {
        return decimalRepresentation;
    }

    public static int convertBinaryToDecimal(List<Integer> binaryList) {
        int decimal = 0;

        int size = binaryList.size();
        for (int i = 0; i < size; i++) {
            int power = size - i - 1;
            int digit = binaryList.get(i);
            decimal += digit * Math.pow(2, power);
        }

        return decimal;
    }

    public List<Integer> getBits() {
        return bits;
    }

    @Override
    public String toString() {
        return bits +
                ":[" +
                decimalRepresentation +
                ']';
    }

    @Override
    public int compareTo(@NotNull BinaryNumberRepresentation o) {
        for (int i = 0; i < BITS_COUNT; i++) {
            if (this.bits.get(i) == 1 && o.bits.get(i) == 0)
                return 1;
            else if (this.bits.get(i) == 0 && o.bits.get(i) == 1)
                return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryNumberRepresentation binary = (BinaryNumberRepresentation) o;
        return decimalRepresentation == binary.decimalRepresentation && Objects.equals(bits, binary.bits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decimalRepresentation, bits);
    }
}
