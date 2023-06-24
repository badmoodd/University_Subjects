package org.example.Minimization.impl.util;

import java.util.stream.IntStream;

public class Template {
    public final int[][] table;

    public Template(int[][] table) {
        this.table = table;
    }

    public boolean contains(Template compared) {
        for (int i : range(compared.table.length)) {
            for (int j : range(compared.table[i].length)) {
                if (compared.table[i][j] == 1 && table[i][j] != compared.table[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void reverse() {
        for (int i : range(table.length)) {
            for (int j : range(table[i].length)) {
                table[i][j] = flipValue(table[i][j]);
            }
        }
    }

    private int[] range(int n) {
        return IntStream.range(0, n).toArray();
    }

    private int flipValue(int value) {
        return (value == 1) ? 0 : 1;
    }
}

