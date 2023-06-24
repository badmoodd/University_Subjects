package org.example.Logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormulaConstructor {
    private int countOfAtomicAndConstant;
    private Map<String, List<Integer>> tableMap;
    private List<String> listOfKeys;
    private final StringBuilder formulaSKNF = new StringBuilder();
    private final StringBuilder formulaSDNF = new StringBuilder();
    private final StringBuilder additionsBrackets = new StringBuilder();

    public void generateFormulas(int countOfAtomicAndConstant, Map<String, List<Integer>> tableMap) {
        this.countOfAtomicAndConstant = countOfAtomicAndConstant;
        this.tableMap = tableMap;
        this.listOfKeys = new ArrayList<>(tableMap.keySet());
        constructFormulaSDNF();
        constructFormulaSKNF();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/org/example/Resource/perfectFormulas.txt"));
            writer.write("SDNF : " + formulaSDNF);
            writer.write("\nSKNF : " + formulaSKNF);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void constructFormulaSKNF() {
        StringBuilder binFormula = new StringBuilder();
        int counter = 0;
        for (int indexOfRow = 0; indexOfRow < tableMap.get(listOfKeys.get(0)).size(); indexOfRow++) {
            if (tableMap.get(listOfKeys.get(listOfKeys.size() - 1)).get(indexOfRow) == 1) continue;
            for (int i = 0; i < countOfAtomicAndConstant; i++) {
                if (i == 0) {
                    formulaSKNF.append("(".repeat(countOfAtomicAndConstant - 1));
                }
                if (tableMap.get(listOfKeys.get(i)).get(indexOfRow) == 0) {
                    formulaSKNF.append(listOfKeys.get(i));
                    binFormula.append("0");
                } else {
                    formulaSKNF.append("(!").append(listOfKeys.get(i)).append(")");
                    binFormula.append("1");
                }
                if (i > 0) formulaSKNF.append(")|");
                else formulaSKNF.append("|");
                if (i == countOfAtomicAndConstant - 1) {
                    formulaSKNF.delete(formulaSKNF.length() - 2, formulaSKNF.length());
                }
            }
            if (counter > 0) formulaSKNF.append(")&");
            else formulaSKNF.append("&");
            binFormula.append("*");
            counter++;
        }
        additionsBrackets.append("(".repeat(Math.max(0, counter - 1)));
        if (formulaSKNF.length() == 0) System.out.println("\nДля данной формулы не существует СКНФ");
        else {
            formulaSKNF.delete(formulaSKNF.length() - 2, formulaSKNF.length());
            System.out.println("\nФормула СКНФ: " + formulaSKNF);
            System.out.println(binFormula.deleteCharAt(binFormula.length() - 1));
            System.out.println(decimalFormulaRepresentation(binFormula, "SKNF"));
        }
    }

    private void constructFormulaSDNF() {
        StringBuilder binFormula = new StringBuilder();
        int counter = 0;
        for (int indexOfRow = 0; indexOfRow < tableMap.get(listOfKeys.get(0)).size(); indexOfRow++) {
            if (tableMap.get(listOfKeys.get(listOfKeys.size() - 1)).get(indexOfRow) == 0) continue;
            for (int i = 0; i < countOfAtomicAndConstant; i++) {
                if (i == 0) {
                    formulaSDNF.append("(".repeat(countOfAtomicAndConstant - 1));
                }
                if (tableMap.get(listOfKeys.get(i)).get(indexOfRow) == 0) {
                    formulaSDNF.append("(!").append(listOfKeys.get(i)).append(")");
                    binFormula.append("0");
                } else {
                    formulaSDNF.append(listOfKeys.get(i));
                    binFormula.append("1");
                }
                if (i > 0) formulaSDNF.append(")&");
                else formulaSDNF.append("&");
                if (i == countOfAtomicAndConstant - 1) {
                    formulaSDNF.delete(formulaSDNF.length() - 2, formulaSDNF.length());
                }
            }
            if (counter > 0) formulaSDNF.append(")|");
            else formulaSDNF.append("|");
            binFormula.append("+");
            counter++;
        }
        additionsBrackets.append("(".repeat(Math.max(0, counter - 1)));
        if (formulaSDNF.length() == 0) System.out.println("\nДля данной формулы не существует СДНФ");
        else {
            formulaSDNF.delete(formulaSDNF.length() - 2, formulaSDNF.length());
            System.out.println("\nФормула СДНФ: " + formulaSDNF);
            System.out.println(binFormula.deleteCharAt(binFormula.length() - 1));
            System.out.println(decimalFormulaRepresentation(binFormula, "SDNF"));

        }
    }

    private ArrayList<Integer> decimalFormulaRepresentation(StringBuilder binaryFormulaRepresentation, String nameOfFormula) {
        ArrayList<Integer> decFormula = new ArrayList<>();
        String appendingSymbol = "";

        if (nameOfFormula.equals("SDNF")) {
            appendingSymbol = "\\+";
        }
        if (nameOfFormula.equals("SKNF")) {
            appendingSymbol = "\\*";
        }
        String strBinFormula = binaryFormulaRepresentation.toString();
        String[] splitBinArray = strBinFormula.split(appendingSymbol);

        for (String s : splitBinArray) {
            decFormula.add(Integer.parseInt(s, 2));
        }

        return decFormula;
    }

    private int solveIndex(List<Integer> list, int countOfAtomicAndConstant) {
        List<Integer> sortList = new ArrayList<>();
        if (countOfAtomicAndConstant == 3) {
            sortList.add(list.get(0));
            sortList.add(list.get(4));
            sortList.add(list.get(2));
            sortList.add(list.get(6));
            sortList.add(list.get(1));
            sortList.add(list.get(5));
            sortList.add(list.get(3));
            sortList.add(list.get(7));
        }
        StringBuilder binStrList = new StringBuilder();
        for (int i : sortList) {
            binStrList.append(i);
        }
        System.out.println("index - " + sortList);
        return Integer.parseInt(binStrList.toString(), 2);
    }


}
