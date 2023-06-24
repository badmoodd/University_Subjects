package org.example.Logic;

import org.example.Exeptions.LogicalFormulaException;

import java.util.*;
import java.util.stream.Collectors;

public class Parser {
    private final static List<String> binaryBundle = Arrays.asList(Symbols.IMPLICATION, Symbols.CONJUNCTION, Symbols.DISJUNCTION, Symbols.EQUIVALENCE);
    private final Map<Integer, String> subFormulaMap = new HashMap<>();
    private final Map<String, List<Integer>> tableMap = new LinkedHashMap<>();

    private int counter = 0;

    public Parser(StringBuilder formula) throws LogicalFormulaException {
        if (isFormula(formula)) {
            subFormulaMap.put(++counter, formula.toString());

            List<String> subFormulaList = getDistinctSubFormulaList();

            List<String> subListOfAtomicAndConstant = getSubListOfAtomicAndConstant(subFormulaList);
            List<String> subListOfSecondPart = getSubListOfSecondPart(subFormulaList);

            int countOfAtomicAndConstant = subListOfAtomicAndConstant.size();
            int countOfConstants = getCountOfConstants(subListOfAtomicAndConstant);

            subListOfAtomicAndConstant.addAll(subListOfSecondPart);

            subListOfAtomicAndConstant.forEach(subFormula -> tableMap.put(subFormula, new ArrayList<>()));

            TruthTable truthTable = new TruthTable();
            truthTable.generateTruthTable(countOfAtomicAndConstant, countOfConstants, tableMap);

            FormulaConstructor sknfConstructor = new FormulaConstructor();
            sknfConstructor.generateFormulas(countOfAtomicAndConstant, tableMap);

        } else {
            throw new LogicalFormulaException("This string doesn't' satisfies logic language ");
        }
    }

    private List<String> getDistinctSubFormulaList() {
        List<String> subFormulaList = new ArrayList<>(subFormulaMap.values());
        return subFormulaList.stream().distinct().collect(Collectors.toList());
    }

    private List<String> getSubListOfAtomicAndConstant(List<String> subFormulaList) {
        return subFormulaList.stream()
                .filter(subFormula -> !subFormula.startsWith(Symbols.LEFT_BRACKET))
                .collect(Collectors.toList());
    }

    private List<String> getSubListOfSecondPart(List<String> subFormulaList) {
        return subFormulaList.stream()
                .filter(subFormula -> subFormula.startsWith(Symbols.LEFT_BRACKET))
                .collect(Collectors.toList());
    }

    private int getCountOfConstants(List<String> subListOfAtomicAndConstant) {
        return (int) subListOfAtomicAndConstant.stream()
                .filter(subFormula -> subFormula.equals(Symbols.LOGIC_FALSE) || subFormula.equals(Symbols.LOGIC_TRUE))
                .count();
    }

    private boolean isFormula(StringBuilder formula) {
        if (formula.isEmpty() || formula.toString().equals("()")) {
            return false;
        }

        if ((formula.toString().equals(Symbols.LOGIC_TRUE) || formula.toString().equals(Symbols.LOGIC_FALSE))
                && formula.length() == 1) {
            subFormulaMap.put(++counter, formula.toString());
            return true;
        }

        if (formula.substring(0, 1).equals(Symbols.LEFT_BRACKET)
                && formula.substring(formula.length() - 1, formula.length()).equals(Symbols.RIGHT_BRACKET)
                && isAtomicFormula(new StringBuilder(formula.substring(1, formula.length() - 1)))) {
            return false;
        }

        if (isAtomicFormula(formula)) {
            return true;
        }

        if (isUnaryComplexFormula(formula)) {
            return true;
        }

        return isBinaryFormula(formula);
    }

    private boolean isAtomicFormula(StringBuilder formula) {
        if (Symbols.alphabet.contains(formula.charAt(0)) && formula.length() == 1) {
            subFormulaMap.put(++counter, formula.toString());
            return true;
        }
        formula.charAt(0);
        return false;
    }

    private boolean isUnaryComplexFormula(StringBuilder formula) {
        StringBuilder substringFormula;

        if (!formula.substring(0, 1).equals(Symbols.LEFT_BRACKET)) return false;
        if (!formula.substring(formula.length() - 1, formula.length()).equals(Symbols.RIGHT_BRACKET)) return false;

        if (formula.substring(1, 2).equals(Symbols.NEGATION)) {
            substringFormula = new StringBuilder(formula.substring(2, formula.length() - 1));
        } else substringFormula = new StringBuilder(formula.substring(1, formula.length() - 1));

        if (isFormula(substringFormula)) {
            subFormulaMap.put(++counter, substringFormula.toString());
            return true;
        }
        return false;
    }

    private boolean isBinaryFormula(StringBuilder formula) {
        if (!formula.substring(0, 1).equals(Symbols.LEFT_BRACKET) ||
                !formula.substring(formula.length() - 1, formula.length()).equals(Symbols.RIGHT_BRACKET)) {
            return false;
        }

        List<Integer> bundleIndexes = new ArrayList<>();
        binaryBundle.forEach(bundle -> {
            int index = formula.indexOf(bundle);
            while (index != -1) {
                bundleIndexes.add(index);
                index = formula.indexOf(bundle, index + 1);
            }
        });
        bundleIndexes.sort(Integer::compareTo);

        for (int index : bundleIndexes) {
            int countOfBrackets = 0;
            for (int j = 1; j < formula.length() - 1; j++) {
                if (j == index) {
                    if (countOfBrackets == 0) {
                        StringBuilder firstSubstring = new StringBuilder(formula.substring(1, j));
                        StringBuilder secondSubstring;
                        if (formula.substring(index, index + 2).equals(Symbols.IMPLICATION) ||
                                formula.substring(index, index + 2).equals(Symbols.CONJUNCTION) ||
                                formula.substring(index, index + 2).equals(Symbols.DISJUNCTION)) {
                            secondSubstring = new StringBuilder(formula.substring(index + 2, formula.length() - 1));
                        } else {
                            secondSubstring = new StringBuilder(formula.substring(index + 1, formula.length() - 1));
                        }
                        if (isFormula(firstSubstring) && isFormula(secondSubstring)) {
                            subFormulaMap.put(++counter, firstSubstring.toString());
                            subFormulaMap.put(++counter, secondSubstring.toString());
                            return true;
                        }
                    }
                } else {
                    if (formula.substring(j, j + 1).equals(Symbols.LEFT_BRACKET)) {
                        countOfBrackets++;
                    } else if (formula.substring(j, j + 1).equals(Symbols.RIGHT_BRACKET)) {
                        countOfBrackets--;
                    }
                }
            }
        }
        return false;
    }

    public static String getIMPLICATION() {
        return Symbols.IMPLICATION;
    }

    public static String getCONJUNCTION() {
        return Symbols.CONJUNCTION;
    }

    public static String getDISJUNCTION() {
        return Symbols.DISJUNCTION;
    }

    public static String getNEGATION() {
        return Symbols.NEGATION;
    }

    public static String getEQUIVALENCE() {
        return Symbols.EQUIVALENCE;
    }

    public static String getLogicTrue() {
        return Symbols.LOGIC_TRUE;
    }

    public static String getLogicFalse() {
        return Symbols.LOGIC_FALSE;
    }
}


