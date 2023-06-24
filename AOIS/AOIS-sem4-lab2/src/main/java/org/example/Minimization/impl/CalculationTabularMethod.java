package org.example.Minimization.impl;

import org.example.Minimization.Minimisable;
import org.example.Logic.FormulaType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculationTabularMethod implements Minimisable {

    CalculationMethod merge;

    public CalculationTabularMethod() {
        this.merge = new CalculationMethod();
    }

    @Override
    public List<List<String>> minimisation(List<List<String>> constituents, FormulaType type) {
        List<List<String>> merged = merge.merging(merge.merging(constituents));
        List<List<String>> result = new ArrayList<>();
        ArrayList<ArrayList<Integer>> table = createTable(merged, constituents);
        print(table, merged, constituents);

        for (List<String> gluedList : merged) {
            boolean unique = false;
            for (int i = 0; i < constituents.size(); i++) {
                List<Integer> coincidences = findCoincidences(merged, table, i, merged.indexOf(gluedList));

                if (coincidences.size() == 1) {
                    unique = true;
                }
            }
            if (unique) {
                result.add(gluedList);
            } else {
                for (int i = 0; i < constituents.size(); i++) {
                    table.get(merged.indexOf(gluedList)).set(i, 0);
                }
            }
        }
        return result;
    }

    public void print(ArrayList<ArrayList<Integer>> table, List<List<String>> merged, List<List<String>> parts) {
        printHeader(parts);
        printTable(table, merged);
    }

    private void printHeader(List<List<String>> constituents) {
        System.out.print("\t\t");
        for (List<String> implicant : constituents) {
            System.out.print(implicant + "\t");
        }
        System.out.println();
    }

    private void printTable(ArrayList<ArrayList<Integer>> table, List<List<String>> glued) {
        Iterator<List<String>> iterator = glued.iterator();
        int rowIndex = 0;
        while (rowIndex < table.size()) {
            System.out.print(iterator.next() + "\t");
            printRow(table.get(rowIndex));
            System.out.println();
            rowIndex++;
        }
    }

    private void printRow(ArrayList<Integer> row) {
        int columnIndex = 0;
        while (columnIndex < row.size()) {
            System.out.print("\t\t" + row.get(columnIndex));
            columnIndex++;
        }
    }

    private ArrayList<ArrayList<Integer>> createTable(List<List<String>> minimizeList, List<List<String>> constituents) {
        ArrayList<ArrayList<Integer>> table = createEmptyTable(minimizeList, constituents);
        fillTable(table, minimizeList, constituents);
        return table;
    }

    private ArrayList<ArrayList<Integer>> createEmptyTable(List<List<String>> minimizeList, List<List<String>> constituents) {
        ArrayList<ArrayList<Integer>> table = new ArrayList<>(minimizeList.size());
        for (List<String> ignored : minimizeList) {
            ArrayList<Integer> row = new ArrayList<>(Collections.nCopies(constituents.size(), 0));
            table.add(row);
        }
        return table;
    }

    private void fillTable(ArrayList<ArrayList<Integer>> table, List<List<String>> minimizeList, List<List<String>> constituents) {
        IntStream.range(0, minimizeList.size()).forEach(i ->
                IntStream.range(0, constituents.size()).forEach(j -> {
                    if (isImplicantContained(minimizeList.get(i), constituents.get(j))) {
                        table.get(i).set(j, 1);
                    }
                })
        );
    }

    private boolean isImplicantContained(List<String> formulaPart, List<String> constituent) {
        return new HashSet<>(constituent).containsAll(formulaPart);
    }

    public List<Integer> findCoincidences(List<List<String>> minimizeList, ArrayList<ArrayList<Integer>> matrix, int columnIndex, int iterator) {
        return IntStream.range(0, minimizeList.size())
                .filter(j -> matrix.get(j).get(columnIndex) == 1 && matrix.get(iterator).get(columnIndex) == 1)
                .mapToObj(j -> 1)
                .collect(Collectors.toList());
    }
}
