package org.example.Minimization.impl;

import org.example.Minimization.Minimisable;
import org.example.Logic.FormulaType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CalculationMethod implements Minimisable {
    @Override
    public List<List<String>> minimisation(List<List<String>> sections, FormulaType formulaType) {
        List<List<String>> merge = merging(sections);
        System.out.println(formulaType.name() + " after gluing:" + merge);
        merge = deleteExternal(merge, formulaType);
        merge = merging(merge);
        return merge;
    }


    public List<List<String>> merging(List<List<String>> sections) {
        Set<List<String>> completionSolving = new HashSet<>(sections);
        Set<List<String>> eject = new HashSet<>();

        IntStream.range(0, sections.size())
                .forEach(i -> IntStream.range(0, sections.size())
                        .filter(j -> i != j)
                        .forEach(j -> {
                            List<String> firstImpl = sections.get(i);
                            List<String> secondImpl = sections.get(j);
                            List<String> integration = tryMerging(firstImpl, secondImpl);
                            if (integration != null) {
                                eject.add(firstImpl);
                                eject.add(secondImpl);
                                completionSolving.add(integration);
                            }
                        }));

        completionSolving.removeAll(eject);
        return new ArrayList<>(completionSolving);
    }

    public boolean isDifferent(String leftPart, String rightPart) {
        return (leftPart.startsWith("!") && leftPart.substring(1).equals(rightPart))
                || (rightPart.startsWith("!") && rightPart.substring(1).equals(leftPart));
    }

    public List<String> tryMerging(List<String> firstImplicant, List<String> secondImplicant) {
        if (isInvalidImplicant(firstImplicant) || isInvalidImplicant(secondImplicant))
            return null;

        int differencesCount = 0;
        int differenceIndex = -1;

        for (int i = 0; i < firstImplicant.size(); i++) {
            if (areTermsDifferent(firstImplicant.get(i), secondImplicant.get(i))) {
                if (isDifferent(firstImplicant.get(i), secondImplicant.get(i))) {
                    differencesCount++;
                    differenceIndex = i;

                    if (differencesCount > 1) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        }

        if (differencesCount == 1) {
            return removeDifference(firstImplicant, differenceIndex);
        } else {
            return null;
        }
    }

    private boolean isInvalidImplicant(List<String> implicant) {
        return implicant.size() == 1;
    }

    private boolean areTermsDifferent(String term1, String term2) {
        return !term1.equals(term2);
    }

    private List<String> removeDifference(List<String> implicant, int differenceIndex) {
        List<String> result = new ArrayList<>(implicant);
        result.remove(differenceIndex);
        return result;
    }

    public List<List<String>> deleteExternal(List<List<String>> constituents, FormulaType type) {
        if (constituents.size() == 1) {
            return constituents;
        }

        Set<List<String>> exclude = new HashSet<>();

        for (List<String> implicant : constituents) {
            Map<String, Integer> valuesMap = createValuesMap(implicant, type);
            List<List<String>> external = constituents.stream()
                    .filter(c -> c != implicant)
                    .collect(Collectors.toList());

            boolean formulaValue = calculateFormulaValue(external, valuesMap, type);

            if (formulaValue) {
                exclude.add(implicant);
            }
        }

        return constituents;
    }


    private boolean calculateFormulaValue(List<List<String>> constituents, Map<String, Integer> valuesMap, FormulaType type) {
        List<Boolean> modified = new ArrayList<>(List.of(false));
        List<Boolean> addList = new ArrayList<>();

        modified.addAll(constituents.stream()
                .map(implicant -> calculateImplicantValue(implicant, valuesMap, type))
                .peek(value -> recaluclateList(modified, value, type))
                .peek(addList::add)
                .skip(1)
                .toList());

        return getResult(modified, type);
    }

    private void recaluclateList(List<Boolean> list, boolean number, FormulaType type) {
        boolean result = (type == FormulaType.PDNF) ? (list.get(0) && number) : (list.get(0) || number);
        list.set(0, result);
    }


    private boolean getResult(List<Boolean> tempList, FormulaType type) {
        return (type == FormulaType.PDNF) == tempList.get(0);
    }

    public Map<String, Integer> createValuesMap(List<String> formulaParts, FormulaType type) {
        return formulaParts.stream()
                .flatMap(term -> Stream.of(term, term.startsWith("!") ? term.substring(1) : "!" + term))
                .collect(Collectors.toMap(
                        term -> term,
                        term -> getGroupValues(term, type)
                ));
    }

    private int getGroupValues(String group, FormulaType type) {
        if (type == FormulaType.PDNF) {
            return group.startsWith("!") ? 0 : 1;
        } else if (type == FormulaType.PCNF) {
            return group.startsWith("!") ? 1 : 0;
        }
        return 0;
    }
    public boolean calculateImplicantValue(List<String> formulaParts, Map<String, Integer> valuesMap, FormulaType type) {
        return switch (type) {
            case PDNF -> calculateDisjunction(formulaParts, valuesMap) == 1;
            case PCNF -> calculateConjunction(formulaParts, valuesMap) == 0;
        };
    }

    public Integer calculateDisjunction(List<String> formulaParts, Map<String, Integer> valuesMap) {
        Integer left = calculateOperand(formulaParts.get(0), valuesMap);
        Integer right = calculateOperand(formulaParts.get(1), valuesMap);

        return left * right;
    }

    public Integer calculateConjunction(List<String> formulaParts, Map<String, Integer> valuesMap) {
        Integer left = calculateOperand(formulaParts.get(0), valuesMap);
        Integer right = calculateOperand(formulaParts.get(1), valuesMap);

        return left + right >= 1 ? 1 : 0;
    }

    private Integer calculateOperand(String unionGroup, Map<String, Integer> valuesMap) {
        Integer value = valuesMap.get(unionGroup);
        if (value == null) {
            value = unionGroup.startsWith("!") ? 1 : 0;
        }
        return value;
    }

}
