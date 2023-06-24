package org.example.Logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TruthTable {
    private int countOfAtomicAndConstant, rows = 0, countOfConstants;
    private Map<String, List<Integer>> tableMap;
    private List<String> listOfKeys;

    public void generateTruthTable(int countOfAtomicAndConstant, int countOfConstants, Map<String, List<Integer>> tableMap) {
        this.countOfAtomicAndConstant = countOfAtomicAndConstant;
        this.countOfConstants = countOfConstants;   //Для корректного создания строк относительно констант
        this.tableMap = tableMap;
        this.listOfKeys = new ArrayList<>(tableMap.keySet());

        startGenerateTruthTable();
        for (int indexOfCurrentEl = countOfAtomicAndConstant; indexOfCurrentEl < listOfKeys.size(); indexOfCurrentEl++) {
            for (int rowNumber = 0; rowNumber < rows; rowNumber++) {
                generateValueToCurrentPosition(indexOfCurrentEl, rowNumber);
            }
        }
        printTruthTable();
    }

    private void startGenerateTruthTable() {
        rows = (int) Math.pow(2, countOfAtomicAndConstant - countOfConstants);
        for (int i = 0; i < rows; i++) {
            for (int j = countOfAtomicAndConstant - 1; j >= 0; j--) {
                if (listOfKeys.get(j).equals(Parser.getLogicTrue())) {
                    tableMap.get(listOfKeys.get(j)).add(1);
                } else if (listOfKeys.get(j).equals(Parser.getLogicFalse())) {
                    tableMap.get(listOfKeys.get(j)).add(0);
                } else tableMap.get(listOfKeys.get(j)).add((i / (int) Math.pow(2, j)) % 2);
            }
        }
    }

    private void generateValueToCurrentPosition(int indexOfCurrentEl, int numberOfRow) {
        String substring = listOfKeys.get(indexOfCurrentEl).substring(1, listOfKeys.get(indexOfCurrentEl).length() - 1);

        if (substring.startsWith(Parser.getNEGATION())) {
            handleNegation(substring, indexOfCurrentEl, numberOfRow);
        } else {
            handleBinaryOperation(substring, indexOfCurrentEl, numberOfRow);
        }
    }

    private void handleNegation(String substring, int indexOfCurrentEl, int numberOfRow) {
        for (int i = indexOfCurrentEl - 1; i >= 0; i--) {
            if (substring.indexOf(listOfKeys.get(i)) == 1) {
                negationMethod(i, indexOfCurrentEl, numberOfRow);
                return;
            }
        }
    }

    private void handleBinaryOperation(String substring, int indexOfCurrentEl, int numberOfRow) {
        int firstSubstringIndex = -1, secondSubstringIndex = -1, lastPositionOfFirstSb = 0;
        String method = "";

        for (int i = indexOfCurrentEl - 1; i >= 0; i--) {
            int digit = substring.indexOf(listOfKeys.get(i), lastPositionOfFirstSb);
            if (digit == 0) {
                firstSubstringIndex = i;
                lastPositionOfFirstSb = listOfKeys.get(i).length() - 1;
            } else if (digit != -1 && digit > lastPositionOfFirstSb) {
                secondSubstringIndex = i;
                method = substring.substring(digit - 2, digit);
            }
            if (firstSubstringIndex != -1 && secondSubstringIndex != -1) break;
        }

        if (firstSubstringIndex != -1 && secondSubstringIndex == -1) {
            secondSubstringIndex = firstSubstringIndex;
            method = substring.substring(lastPositionOfFirstSb + 1, substring.indexOf(listOfKeys.get(firstSubstringIndex), lastPositionOfFirstSb + 1));
        }

        if (method.equals(Parser.getCONJUNCTION())) {
            conjunctionMethod(firstSubstringIndex, secondSubstringIndex, indexOfCurrentEl, numberOfRow);
        } else if (method.equals(Parser.getDISJUNCTION())) {
            disjunctionMethod(firstSubstringIndex, secondSubstringIndex, indexOfCurrentEl, numberOfRow);
        } else if (method.equals(Parser.getIMPLICATION())) {
            implicationMethod(firstSubstringIndex, secondSubstringIndex, indexOfCurrentEl, numberOfRow);
        } else {
            equivalenceMethod(firstSubstringIndex, secondSubstringIndex, indexOfCurrentEl, numberOfRow);
        }
    }

    private void negationMethod(int positionOfSubFormula, int positionOfFormula, int numberOfRow) {
        int result;
        int valueOfSubFormula = tableMap.get(listOfKeys.get(positionOfSubFormula)).get(numberOfRow);
        if (valueOfSubFormula == 1) result = 0;
        else result = 1;
        tableMap.get(listOfKeys.get(positionOfFormula)).add(result);
    }

    private void conjunctionMethod(int firstSubstringIndex, int secondSubstringIndex, int positionOfFormula, int numberOfRow) {
        int result;
        int valueOfFirstSubstring = tableMap.get(listOfKeys.get(firstSubstringIndex)).get(numberOfRow);
        int valueOfSecondSubstring = tableMap.get(listOfKeys.get(secondSubstringIndex)).get(numberOfRow);
        if (valueOfFirstSubstring == 0 || valueOfSecondSubstring == 0) result = 0;
        else result = 1;
        tableMap.get(listOfKeys.get(positionOfFormula)).add(result);
    }

    private void disjunctionMethod(int firstSubstringIndex, int secondSubstringIndex, int positionOfFormula, int numberOfRow) {
        int result;
        int valueOfFirstSubstring = tableMap.get(listOfKeys.get(firstSubstringIndex)).get(numberOfRow);
        int valueOfSecondSubstring = tableMap.get(listOfKeys.get(secondSubstringIndex)).get(numberOfRow);
        if (valueOfFirstSubstring == 0 && valueOfSecondSubstring == 0) result = 0;
        else result = 1;
        tableMap.get(listOfKeys.get(positionOfFormula)).add(result);
    }

    private void implicationMethod(int firstSubstringIndex, int secondSubstringIndex, int positionOfFormula, int numberOfRow) {
        int result;
        int valueOfFirstSubstring = tableMap.get(listOfKeys.get(firstSubstringIndex)).get(numberOfRow);
        int valueOfSecondSubstring = tableMap.get(listOfKeys.get(secondSubstringIndex)).get(numberOfRow);
        if ((valueOfFirstSubstring == 1 && valueOfSecondSubstring == 1)
                || (valueOfFirstSubstring == 0 && valueOfSecondSubstring == 1)
                || (valueOfFirstSubstring == 0 && valueOfSecondSubstring == 0)) result = 1;
        else result = 0;
        tableMap.get(listOfKeys.get(positionOfFormula)).add(result);
    }

    private void equivalenceMethod(int firstSubstringIndex, int secondSubstringIndex, int positionOfFormula, int numberOfRow) {
        int result;
        int valueOfFirstSubstring = tableMap.get(listOfKeys.get(firstSubstringIndex)).get(numberOfRow);
        int valueOfSecondSubstring = tableMap.get(listOfKeys.get(secondSubstringIndex)).get(numberOfRow);
        if ((valueOfFirstSubstring == 1 && valueOfSecondSubstring == 1)
                || (valueOfFirstSubstring == 0 && valueOfSecondSubstring == 0)) result = 1;
        else result = 0;
        tableMap.get(listOfKeys.get(positionOfFormula)).add(result);
    }

    private void printTruthTable() {
        Map<List<Integer>, Integer> map = new HashMap<>();
        ArrayList<String> symbols = new ArrayList<>();
        System.out.println("Таблица истинности:");
        for (String subFormula :
                listOfKeys) {
            if (subFormula.length() == 1) {
                symbols.add(subFormula);
            }
            System.out.print(subFormula + "  |  ");
        }
        for (int numberOfRow = 0; numberOfRow < tableMap.get(listOfKeys.get(0)).size(); numberOfRow++) {
            System.out.println();
            List<Integer> keys = new ArrayList<>();
            int value = -1;
            for (String listOfKey : listOfKeys) {
                if (listOfKey.length() == 1) {
                    keys.add(tableMap.get(listOfKey).get(numberOfRow));
                }
                if (listOfKey.equals(listOfKeys.get(listOfKeys.size() - 1))) {
                    value = tableMap.get(listOfKey).get(numberOfRow);
                }
                System.out.print(tableMap.get(listOfKey).get(numberOfRow));

                for (int lengthOfKey = 0; lengthOfKey < listOfKey.length() - 1; lengthOfKey++) System.out.print(" ");
                System.out.print("  |  ");

            }
            map.put(keys, value);
        }
        loadToFile(map, symbols);
    }

    private void loadToFile(Map<List<Integer>, Integer> map, ArrayList<String> symbols) {
        try {
            ObjectOutputStream mapStream = new ObjectOutputStream(new FileOutputStream("src/main/java/org/example/Resource/table.bin"));
            mapStream.writeObject(map);
            mapStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectOutputStream symbolStream = new ObjectOutputStream(new FileOutputStream("src/main/java/org/example/Resource/constituents.bin"));
            symbolStream.writeObject(symbols);
            symbolStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
