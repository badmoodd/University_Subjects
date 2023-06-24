package org.example;

import org.example.Processor.Memory.Exceptions.MemoryException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MaskSearch {
    private static final int BITS_COUNT = 1 << 4;

    public static boolean searchByMask(String inputMask, List<Integer> binaryRepresentation) throws MemoryException {

        if (inputMask.length() > BITS_COUNT) {
            throw new MemoryException("Wrong mask size " + inputMask.length());
        }

        if (!isValidMask(inputMask)) {
            throw new MemoryException("Invalid chars in mask. Must contains only '0', '1' or '*'");
        }

        if (inputMask.length() < BITS_COUNT) {
            inputMask = inputMask + appendStars(inputMask.length());
        }

        boolean searchFind = true;
        for (int j = 0; j < binaryRepresentation.size(); j++) {
            int maskBit = Character.digit(inputMask.charAt(j), 10);
            int binaryBit = binaryRepresentation.get(j);

            if (!((maskBit == binaryBit) || maskBit == -1)) {
                searchFind = false;
                break;
            }
        }
        return searchFind;
    }

    private static boolean isValidMask(String inputMask) {
        return inputMask.matches("[01*]+");
    }

    private static @NotNull StringBuilder appendStars(int maskSize) {
        StringBuilder starsStr = new StringBuilder();
        int starsCountToAdd = BITS_COUNT - maskSize;

        int currentStarsSize = 0;
        while (currentStarsSize != starsCountToAdd) {
            starsStr.append("*");
            ++currentStarsSize;
        }
        return starsStr;
    }


}
