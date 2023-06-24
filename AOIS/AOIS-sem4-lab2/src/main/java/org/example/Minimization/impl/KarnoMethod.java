package org.example.Minimization.impl;

import org.example.Minimization.Minimisable;
import org.example.Minimization.impl.util.Template;
import org.example.Logic.FormulaType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KarnoMethod implements Minimisable {

    @Override
    public List<List<String>> minimisation(List<List<String>> constituents, FormulaType type) {
        createIndexes();
        ArrayList<String> symbols = loadSymbolsFromFile();
        Map<List<Integer>, Integer> map = loadMapFromFile();
        int[][] Karno = createKarnoTable(map);
        Template table = createDataTable(Karno, type);
        List<Template> figures = findFigures(table);
        List<List<String>> output = processFigures(figures, symbols, type);
        print(table);
        return output;
    }

    private ArrayList<String> loadSymbolsFromFile() {
        ArrayList<String> symbols = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/org/example/Resource/constituents.bin"));
            symbols = (ArrayList<String>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return symbols;
    }

    private Map<List<Integer>, Integer> loadMapFromFile() {
        Map<List<Integer>, Integer> map = new HashMap<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/org/example/Resource/table.bin"));
            map = (Map<List<Integer>, Integer>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    private int[][] createKarnoTable(Map<List<Integer>, Integer> map) {
        int[][] karnoTable = new int[2][4];
        for (int i = 0; i < karnoTable.length; i++) {
            for (int j = 0; j < karnoTable[i].length; j++) {
                karnoTable[i][j] = map.get(supportingIndex.get(Arrays.asList(i, j)));
            }
        }
        return karnoTable;
    }

    private Template createDataTable(int[][] Karno, FormulaType type) {
        Template table = new Template(Karno);
        if (type == FormulaType.PCNF) {
            table.reverse();
        }
        return table;
    }

    private List<Template> findFigures(Template table) {
        List<Template> figures = new ArrayList<>();
        for (Template template : tables) {
            if (table.contains(template) && !isFigureContained(figures, template)) {
                figures.add(template);
            }
        }
        return figures;
    }

    private boolean isFigureContained(List<Template> figures, Template template) {
        for (Template figure : figures) {
            if (figure.contains(template)) {
                return true;
            }
        }
        return false;
    }

    private List<List<String>> processFigures(List<Template> figures, ArrayList<String> symbols, FormulaType type) {
        return figures.stream()
                .map(this::findIndexes)
                .map(this::findCommonIndexes)
                .map(commonIndexes -> convertToStringIndex(commonIndexes, symbols, type))
                .collect(Collectors.toList());
    }


    private List<List<Integer>> findIndexes(Template template) {
        return IntStream.range(0, template.table.length)
                .boxed()
                .flatMap(i -> IntStream.range(0, template.table[i].length)
                        .filter(j -> template.table[i][j] == 1)
                        .mapToObj(j -> supportingIndex.get(Arrays.asList(i, j))))
                .collect(Collectors.toList());
    }


    private Map<Integer, Integer> findCommonIndexes(List<List<Integer>> indexes) {
        Map<Integer, Integer> commonIndexes = new HashMap<>();
        int i = 0;
        int size = indexes.get(0).size();

        while (i < size) {
            final int currentIndex = i;
            boolean isSame = indexes.stream()
                    .allMatch(list -> list.get(currentIndex).equals(indexes.get(0).get(currentIndex)));
            if (isSame) {
                commonIndexes.put(i, indexes.get(0).get(i));
            }
            i++;
        }
        return commonIndexes;
    }

    private List<String> convertToStringIndex(Map<Integer, Integer> commonIndexes, ArrayList<String> symbols, FormulaType type) {
        return commonIndexes.entrySet()
                .stream()
                .map(entry -> {
                    String symbol = symbols.get(entry.getKey());
                    boolean isNegated = (entry.getValue() == (type == FormulaType.PDNF ? 0 : 1));
                    return isNegated ? "!" + symbol : symbol;
                })
                .collect(Collectors.toList());
    }
    public void print(Template table) {
        List<String> leftSymbols = List.of("!A", "A");
        List<List<String>> terms = List.of(
                List.of("!B", "!C"),
                List.of("!B", "C"),
                List.of("B", "C"),
                List.of("B", "!C")
        );

        printHeader(terms);
        printTable(table, leftSymbols);
    }

    private void printHeader(List<List<String>> terms) {
        System.out.print("\t");
        terms.forEach(term -> System.out.print(term + "\t\t"));
        System.out.println();
    }

    private void printTable(Template table, List<String> leftSymbols) {
        leftSymbols.forEach(System.out::print);

        for (int[] row : table.table) {
            for (int cell : row) {
                System.out.print("\t\t" + cell);
            }
            System.out.println();
        }
    }



    private static void createIndexes() {
        supportingIndex.put(Arrays.asList(0, 0), Arrays.asList(0, 0, 0));
        supportingIndex.put(Arrays.asList(0, 1), Arrays.asList(0, 0, 1));
        supportingIndex.put(Arrays.asList(0, 2), Arrays.asList(0, 1, 1));
        supportingIndex.put(Arrays.asList(0, 3), Arrays.asList(0, 1, 0));
        supportingIndex.put(Arrays.asList(1, 0), Arrays.asList(1, 0, 0));
        supportingIndex.put(Arrays.asList(1, 1), Arrays.asList(1, 0, 1));
        supportingIndex.put(Arrays.asList(1, 2), Arrays.asList(1, 1, 1));
        supportingIndex.put(Arrays.asList(1, 3), Arrays.asList(1, 1, 0));
    }

    private static final Map<List<Integer>, List<Integer>> supportingIndex = new HashMap<>();
    private static final List<Template> tables = List.of(
            new Template(new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}}),
            new Template(new int[][]{{1, 1, 1, 1}, {0, 0, 0, 0}}),
            new Template(new int[][]{{0, 0, 0, 0}, {1, 1, 1, 1}}),
            new Template(new int[][]{{1, 1, 0, 0}, {1, 1, 0, 0}}),
            new Template(new int[][]{{0, 1, 1, 0}, {0, 1, 1, 0}}),
            new Template(new int[][]{{0, 0, 1, 1}, {0, 0, 1, 1}}),
            new Template(new int[][]{{1, 0, 0, 1}, {1, 0, 0, 1}}),
            new Template(new int[][]{{1, 1, 0, 0}, {0, 0, 0, 0}}),
            new Template(new int[][]{{0, 1, 1, 0}, {0, 0, 0, 0}}),
            new Template(new int[][]{{0, 0, 1, 1}, {0, 0, 0, 0}}),
            new Template(new int[][]{{1, 0, 0, 1}, {0, 0, 0, 0}}),
            new Template(new int[][]{{0, 0, 0, 0}, {1, 1, 0, 0}}),
            new Template(new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}}),
            new Template(new int[][]{{0, 0, 0, 0}, {0, 0, 1, 1}}),
            new Template(new int[][]{{0, 0, 0, 0}, {1, 0, 0, 1}}),
            new Template(new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}}),
            new Template(new int[][]{{0, 1, 0, 0}, {0, 1, 0, 0}}),
            new Template(new int[][]{{0, 0, 1, 0}, {0, 0, 1, 0}}),
            new Template(new int[][]{{0, 0, 0, 1}, {0, 0, 0, 1}}),
            new Template(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}}),
            new Template(new int[][]{{0, 1, 0, 0}, {0, 0, 0, 0}}),
            new Template(new int[][]{{0, 0, 1, 0}, {0, 0, 0, 0}}),
            new Template(new int[][]{{0, 0, 0, 1}, {0, 0, 0, 0}}),
            new Template(new int[][]{{0, 0, 0, 0}, {1, 0, 0, 0}}),
            new Template(new int[][]{{0, 0, 0, 0}, {0, 1, 0, 0}}),
            new Template(new int[][]{{0, 0, 0, 0}, {0, 0, 1, 0}}),
            new Template(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 1}}));
}
