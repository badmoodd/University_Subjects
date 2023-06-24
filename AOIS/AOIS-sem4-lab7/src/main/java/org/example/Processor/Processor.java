package org.example.Processor;

import org.example.LogicOperations.LogicOperations;
import org.example.MaskSearch;
import org.example.Processor.Memory.Exceptions.MemoryException;
import org.example.Processor.Memory.Memory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Processor implements WordsBoolOperations{
    public static final int BIT_COUNT = 4;

    private static final int Aj_START_Index = 3;
    private static final int Bj_START_Index = 7;

    private static final int ZERO_TO_APPEND = 0;
    private final Memory memory;

    public Processor(Memory memory) {
        this.memory = memory;
    }

    /**
     * Reads a cut section of a number from the memory starting at the specified index.
     * The cut section includes the bits at the specified index from each word in the memory.
     *
     * @param index number at  reading the cut section starts.
     * @return An ArrayList of integers representing the cut section of the number.
     * Each element in the ArrayList represents a bit in the cut section, starting from the least significant bit.
     */
    public List<Integer> readCutSectionNumber(int index) {
        List<Integer> slice = new ArrayList<>();

        for (int i = index; i < memory.getWords().size(); i++) {
            slice.add(memory.getWords().get(i).getBits().get(index));
        }
        for (int i = 0; i < index; i++) {
            slice.add(memory.getWords().get(i).getBits().get(index));
        }
        return slice;
    }

    /**
     * Writes the provided bits to a cut section of the memory starting at the specified index.
     * The cut section includes the bits at the specified index in each word of the memory.
     *
     * @param index       number at  writing the cut section starts.
     * @param bitsToWrite An ArrayList of integers representing the bits to be written.
     *                    Each element in the ArrayList represents a bit in the cut section, starting from the least significant bit.
     */
    private void writeCutSectionTo(int index, List<Integer> bitsToWrite) {
        for (int i = index; i < memory.getWords().size(); i++) {
            memory.getWords().get(i).getBits().set(index, bitsToWrite.get(i - index));
        }

        for (int i = 0; i < index; i++) {
            memory.getWords().get(i).getBits().set(index, bitsToWrite.get(memory.getSize() + i - index));
        }
    }

    /**
     * Prints all the cut sections of memory.
     * Each cut section represents a sequence of bits from each word in the memory.
     *
     * @return A String representation of all the cut sections of memory.
     */
    public String printAllMemoryCutSections() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < memory.getSize(); i++) {
            result.append(readCutSectionNumber(i));
            if (i < memory.getSize() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Prohibition of the second argument. Just looks like c = !a * b.
     *
     * @param a number of CutSection in memory
     * @param b number of CutSection in memory
     * @return result of the operation
     */
    @Override
    public List<Integer> function4(int a, int b) {
        List<Integer> firstCutSection = readCutSectionNumber(a);
        List<Integer> secondCutSection = readCutSectionNumber(b);

        return IntStream.range(0, memory.getWords().size())
                .mapToObj(i -> LogicOperations.conjunction(
                        LogicOperations.negation(firstCutSection.get(i)),
                        secondCutSection.get(i)))
                .collect(Collectors.toList());
    }

    /**
     * Not equivalence. Just looks like c = (!a * b) + (a * !b).
     *
     * @param a number of CutSection in memory
     * @param b number of CutSection in memory
     * @return result of the operation
     */
    @Override
    public List<Integer> function6(int a, int b) {
        List<Integer> positiveFirstCutSection = readCutSectionNumber(a);
        List<Integer> positiveSecondCutSection = readCutSectionNumber(b);

        return IntStream.range(0, memory.getWords().size())
                .mapToObj(i -> LogicOperations.disjunction(
                        LogicOperations.conjunction(
                                LogicOperations.negation(positiveFirstCutSection.get(i)),
                                positiveSecondCutSection.get(i)),
                        LogicOperations.conjunction(
                                positiveFirstCutSection.get(i),
                                LogicOperations.negation(positiveSecondCutSection.get(i)))))
                .collect(Collectors.toList());
    }

    /**
     * Equivalence. Just looks like c = (a * b) + (!a * !b).
     *
     * @param a number of CutSection in memory
     * @param b number of CutSection in memory
     * @return result of the operation
     */
    @Override
    public List<Integer> function9(int a, int b) {
        List<Integer> positiveFirstCutSection = readCutSectionNumber(a);
        List<Integer> positiveSecondCutSection = readCutSectionNumber(b);

        return IntStream.range(0, memory.getWords().size())
                .mapToObj(i -> LogicOperations.disjunction(
                        LogicOperations.conjunction(
                                positiveFirstCutSection.get(i),
                                positiveSecondCutSection.get(i)),
                        LogicOperations.conjunction(
                                LogicOperations.negation(positiveFirstCutSection.get(i)),
                                LogicOperations.negation(positiveSecondCutSection.get(i)))))
                .collect(Collectors.toList());
    }

    /**
     * Equivalence. Just looks like c = a + !b.
     *
     * @param a number of CutSection in memory
     * @param b number of CutSection in memory
     * @return result of the operation
     */
    @Override
    public List<Integer> function11(int a, int b) {
        List<Integer> positiveFirstCutSection = readCutSectionNumber(a);
        List<Integer> positiveSecondCutSection = readCutSectionNumber(b);

        return IntStream.range(0, memory.getWords().size())
                .mapToObj(i -> LogicOperations.disjunction(
                        positiveFirstCutSection.get(i),
                        LogicOperations.negation(positiveSecondCutSection.get(i))))
                .collect(Collectors.toList());
    }

    public void sumOfFields(String inputMask) throws MemoryException {

        for (int i = 0; i < memory.getSize(); i++) {
            List<Integer> binaryRepresentation = readCutSectionNumber(i);
            if (MaskSearch.searchByMask(inputMask, binaryRepresentation)) {
                List<Integer> Aj = binaryRepresentation.subList(Aj_START_Index, Bj_START_Index);
                List<Integer> Bj = binaryRepresentation.subList(Bj_START_Index, Bj_START_Index + 4);
                List<Integer> sum = binarySum(Aj, Bj);
                List<Integer> resultToWrite = new ArrayList<>(binaryRepresentation);
                resultToWrite.subList(Bj_START_Index + 4, binaryRepresentation.size()).clear();

                int startIndexToWrite = Bj_START_Index + 4;
                if (sum.size() == 4) {
                    resultToWrite.add(startIndexToWrite, ZERO_TO_APPEND);
                    startIndexToWrite++;
                }
                resultToWrite.addAll(startIndexToWrite, sum);
                writeCutSectionTo(i, resultToWrite);
            }
        }

    }


    /**
     * Calculates the sum of two binary numbers represented as ArrayLists of integers.
     * Each element in the ArrayList represents a bit in the binary number, starting from the least significant bit.
     *
     * @param first  The first binary number represented as an ArrayList of integers.
     * @param second The second binary number represented as an ArrayList of integers.
     * @return The sum of the two binary numbers as an ArrayList of integers.
     * Each element in the ArrayList represents a bit in the binary sum, starting from the least significant bit.
     */
    private static @NotNull List<Integer> binarySum(List<Integer> first, List<Integer> second) {
        List<Integer> sumResult = new ArrayList<>(Collections.nCopies(BIT_COUNT, 0));
        int carry = 0;

        for (int i = BIT_COUNT - 1; i >= 0; i--) {
            int sum = first.get(i) + second.get(i) + carry;
            sumResult.set(i, sum % 2); // Store the least significant bit of the sum

            if (sum >= 2) {
                carry = 1; // Set the carry
            } else {
                carry = 0; // Reset the carry
            }
        }

        // If there is a remaining carry, add the 9th bit
        if (carry == 1) {
            sumResult.add(0, 1);
        }

        return sumResult;
    }


}


