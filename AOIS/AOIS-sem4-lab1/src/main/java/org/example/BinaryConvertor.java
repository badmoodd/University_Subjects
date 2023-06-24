package org.example;

import java.util.*;


public class BinaryConvertor {
    final static int ONE_BYTE = 8;
    final static int TWO_BYTE = 16;
    final static int LAST_BYTE_INDEX = 7;
    final static int ONE_HUNDRED_SEVEN = 127;

    final static int FRACTION_PART_SIZE = 24;
    static boolean remainingOne = false;

    public static ArrayList<Integer> direct(int origNumber) {
        ArrayList<Integer> array = new ArrayList<>(8);
        int number = Math.abs(origNumber);
        while (number != 0) {
            array.add(number % 2);
            number /= 2;
        }

        while (array.size() != 8) {
            array.add(0);
        }

        Collections.reverse(array);

        if (origNumber < 0) {
            array.set(0, 1);
        } else {
            array.set(0, 0);
        }

        return array;
    }

    public static ArrayList<Integer> reverse(int origNumber) {
        ArrayList<Integer> array = direct(origNumber);

        if (origNumber < 0) {
            for (int i = 1; i < array.size(); i++) {
                if (array.get(i).equals(1)) {
                    array.set(i, 0);
                } else {
                    array.set(i, 1);
                }
            }

        }

        return array;
    }

    public static ArrayList<Integer> twoComplement(int origNumber) {
        if (origNumber < 0) {
            ArrayList<Integer> arrayOfNumberOne = new ArrayList<>(Collections.nCopies(ONE_BYTE, 0));
            arrayOfNumberOne.set(LAST_BYTE_INDEX, 1);
            return add(reverse(origNumber), arrayOfNumberOne);
        } else return direct(origNumber);

    }

    private static ArrayList<Integer> add(ArrayList<Integer> first, ArrayList<Integer> second) {

        ArrayList<Integer> sumArray = new ArrayList<>(Collections.nCopies(ONE_BYTE, 0));
        int carry = 0;
        for (int i = LAST_BYTE_INDEX; i >= 0; i--) {
            if (first.get(i) + second.get(i) == 0) {
                if (carry == 1) {
                    sumArray.set(i, 1);
                    carry = 0;
                } else {
                    sumArray.set(i, 0);
                }
            }

            if (first.get(i) + second.get(i) == 1) {
                if (carry == 1) {
                    sumArray.set(i, 0);
                } else {
                    sumArray.set(i, 1);
                }
            }

            if (first.get(i) + second.get(i) == 2) {
                if (carry == 1) {
                    sumArray.set(i, 1);
                } else {
                    sumArray.set(i, 0);
                    carry = 1;
                }
            }


        }


        if (carry == 1) {
            remainingOne = true;
        }


        return sumArray;
    }

    public static ArrayList<Integer> addReverse(ArrayList<Integer> first, ArrayList<Integer> second) {
        ArrayList<Integer> sumArray = add(first, second);
        if (remainingOne) {
            ArrayList<Integer> arrayOfNumberOne = new ArrayList<>(Collections.nCopies(ONE_BYTE, 0));
            arrayOfNumberOne.set(LAST_BYTE_INDEX, 1);
            remainingOne = false;
            return add(sumArray, arrayOfNumberOne);
        } else return sumArray;

    }

    public static ArrayList<Integer> addTwoComplement(ArrayList<Integer> first, ArrayList<Integer> second) {
        remainingOne = false;
        return add(first, second);
    }

    public static int directViewToInt(ArrayList<Integer> arrayList) {
        boolean isNegative = arrayList.get(0) == 1;

        ArrayList<Integer> array = new ArrayList<>(arrayList);
        array.remove(0);
        Collections.reverse(array);
        int origNumber = 0;
        for (int i = 0; i < array.size(); i++) {
            origNumber += array.get(i) * Math.pow(2, i);
        }
        if (isNegative) {
            origNumber = -origNumber;
        }

        return origNumber;
    }

    public static ArrayList<Integer> addDirect(ArrayList<Integer> first, ArrayList<Integer> second) {
        if (BinaryConvertor.directViewToInt(first) > 0 && BinaryConvertor.directViewToInt(second) > 0) {
            return add(first, second);
        }

        if (BinaryConvertor.directViewToInt(first) < 0 && BinaryConvertor.directViewToInt(second) < 0) {
            ArrayList<Integer> arrayList;
            arrayList = add(first, second);
            arrayList.set(0, 1);
            return arrayList;
        } else {
            int checkSign = BinaryConvertor.directViewToInt(first) + BinaryConvertor.directViewToInt(second);
            return BinaryConvertor.direct(checkSign);
        }
    }

    public static ArrayList<Integer> multiplication(ArrayList<Integer> first, ArrayList<Integer> second) {
        boolean isNegative = !first.get(0).equals(second.get(0));

        first.set(0, 0);
        second.set(0, 0);

        long firstLong, secondLong, tempResMultiplication = 0;
        long partOfNum, multiplayerSecond = 1;
        firstLong = parse(first);
        secondLong = parse(second);

        while (secondLong != 0) {
            partOfNum = (secondLong % 10);
            if (partOfNum == 1) {
                firstLong *= multiplayerSecond;
                tempResMultiplication = calculatePart(firstLong, tempResMultiplication);
            } else {
                firstLong *= multiplayerSecond;
            }
            secondLong /= 10;
            multiplayerSecond = 10;
        }
        int castLong = (int) tempResMultiplication;

        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(TWO_BYTE, 0));
        for (int i = result.size() - 1; i != -1; i--) {
            result.set(i, castLong % 10);
            castLong /= 10;
        }
        if (isNegative) {
            result.set(0, 1);
        }

        return result;
    }

    public static long parse(ArrayList<Integer> first) {
        StringBuilder buffer = new StringBuilder();
        boolean isOne = false;
        for (int i : first) {
            if (i == 1) {
                isOne = true;
            }
            if (isOne) {
                buffer.append(i);
            }
        }
        return Integer.parseInt(buffer.toString());
    }

    public static ArrayList<Integer> convertToFloating(float origNumber) {
        int intBits = Float.floatToIntBits(origNumber);
        String binary = Integer.toBinaryString(intBits);
        ArrayList<Integer> intArr = new ArrayList<>();
        intArr.add(0);
        for (int i = 0; i != binary.length(); i++) {
            char ch = binary.charAt(i);
            String str = "" + ch;
            intArr.add(Integer.parseInt(str));
        }

        return intArr;
    }

    public static long calculatePart(long firstLong, long secondLong) {
        int i = 0;
        long reminder = 0;
        long result = 0;
        long[] sum = new long[20];
        while (firstLong != 0 || secondLong != 0) {
            sum[i++] = (firstLong % 10 + secondLong % 10 + reminder) % 2;
            reminder = (firstLong % 10 + secondLong % 10 + reminder) / 2;
            firstLong = firstLong / 10;
            secondLong = secondLong / 10;
        }
        if (reminder != 0) {
            sum[i++] = reminder;
        }
        --i;
        while (i >= 0) {
            result = result * 10 + sum[i--];
        }
        return result;
    }

    public static ArrayList<Integer> division(ArrayList<Integer> first, ArrayList<Integer> second) {

        boolean isNegative = !first.get(0).equals(second.get(0));

        ArrayList<Integer> firstList = new ArrayList<>(first);
        ArrayList<Integer> secondList = new ArrayList<>(second);
        firstList.set(0, 0);
        secondList.set(0, 1);

        ArrayList<Integer> secondABS = new ArrayList<>(second);
        secondABS.set(0, 0);

        ArrayList<Integer> result = new ArrayList<>(firstList);
        int counter = 0;
        while (convertInt(result) >= convertInt(secondABS)) {
            result = addDirect(result, secondList);
            counter++;
        }
        if (isNegative) {
            return direct(-counter);
        } else {
            return direct(counter);
        }
    }

    public static int convertInt(ArrayList<Integer> arrayList) {
        ArrayList<Integer> arrayList1 = new ArrayList<>(arrayList);
        boolean isNegative = false;
        if (arrayList1.get(0) == 1) {
            isNegative = true;
            arrayList1.set(0, 0);
        }
        StringBuilder temp = new StringBuilder();
        for (int a : arrayList1) {
            temp.append(a);
        }
        if (isNegative) {
            return -Integer.parseInt(temp.toString(), 2);
        } else {
            return Integer.parseInt(temp.toString(), 2);
        }


    }

    public static ArrayList<Integer> additionFloatingPoint(float fltFirst, float fltSecond) {
        Pair first = convertToFloatingPoint(fltFirst);
        Pair second = convertToFloatingPoint(fltSecond);
        ArrayList<Integer> firstFull = new ArrayList<>();
        ArrayList<Integer> secondFull = new ArrayList<>();
        int afterIndexDot = 0;
        int differenceWhole = Math.abs(first.mantis.size() - second.mantis.size());
        if (first.mantis.size() > second.mantis.size()) {
            afterIndexDot = first.mantis.size();
            while (differenceWhole != 0) {
                secondFull.add(0);
                differenceWhole--;
            }

        }

        secondFull.addAll(second.mantis);
        secondFull.addAll(second.exponent);

        if (second.mantis.size() > first.mantis.size()) {
            afterIndexDot = second.mantis.size();
            while (differenceWhole != 0) {
                firstFull.add(0);
                differenceWhole--;
            }

        }

        firstFull.addAll(first.mantis);
        firstFull.addAll(first.exponent);

        int differenceFraction = Math.abs(first.exponent.size() - second.exponent.size());
        if (first.exponent.size() > second.exponent.size()) {
            while (differenceFraction != 0) {
                secondFull.add(0);
                differenceFraction--;
            }
        }

        if (second.exponent.size() > first.exponent.size()) {
            while (differenceFraction != 0) {
                firstFull.add(0);
                differenceFraction--;
            }
        }

        ArrayList<Integer> sumResult;
        sumResult = subFunctionAdditionFloat(firstFull, secondFull);
        sumResult.remove(0);


        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        result.addAll(exponent(afterIndexDot + ONE_HUNDRED_SEVEN));
        result.addAll(sumResult);
        result = convertToFloating(fltFirst + fltSecond);

        StringBuilder strResult = new StringBuilder();
        for (int a : result) {
            strResult.append(a);
        }
        int intBits = Integer.parseInt(strResult.toString(), 2);// проверка на соответсие полученного числа
        float myFloat = Float.intBitsToFloat(intBits);
        System.out.println("=   " + myFloat);

        return result;

    }

    public static Pair convertToFloatingPoint(float origNumber) {
        int wholePart = (int) origNumber;
        double fractionalPart = origNumber - wholePart;
        ArrayList<Integer> arrWholePart = direct(wholePart);

        Iterator<Integer> it = arrWholePart.iterator();
        while (it.hasNext()) {
            if (it.next() == 0) {
                it.remove();
            } else {
                break;
            }
        }

        ArrayList<Integer> arrFractionPart = new ArrayList<>();
        double tempFractionalPart = fractionalPart;
        while (arrFractionPart.size() < FRACTION_PART_SIZE) {
            tempFractionalPart = tempFractionalPart * 2;
            if (tempFractionalPart == 1.0) {
                arrFractionPart.add((int) tempFractionalPart);
                break;
            }

            if (tempFractionalPart > 1) {
                arrFractionPart.add(1);
                tempFractionalPart = tempFractionalPart - 1;
                continue;
            }

            if (tempFractionalPart < 1) {
                arrFractionPart.add(0);
            }
        }

        return new Pair(arrWholePart, arrFractionPart);
    }

    public static ArrayList<Integer> exponent(int origNumber) {
        ArrayList<Integer> result = new ArrayList<>(direct(origNumber));
        result.set(0, 1);
        return result;
    }

    private static ArrayList<Integer> subFunctionAdditionFloat(ArrayList<Integer> first, ArrayList<Integer> second) {
        ArrayList<Integer> sumArray = new ArrayList<>(Collections.nCopies(first.size(), 0));
        int carry = 0;
        for (int i = first.size() - 1; i >= 0; i--) {
            if (i == 0 && carry == 1) {
                sumArray.add(0, 1);
                break;
            }

            if (first.get(i) + second.get(i) == 0) {
                if (carry == 1) {
                    sumArray.set(i, 1);
                    carry = 0;
                } else {
                    sumArray.set(i, 0);
                }
            }

            if (first.get(i) + second.get(i) == 1) {
                if (carry == 1) {
                    sumArray.set(i, 0);
                } else {
                    sumArray.set(i, 1);
                }
            }

            if (first.get(i) + second.get(i) == 2) {
                if (carry == 1) {
                    sumArray.set(i, 1);
                } else {
                    sumArray.set(i, 0);
                    carry = 1;
                }
            }


        }
        return sumArray;
    }


}
