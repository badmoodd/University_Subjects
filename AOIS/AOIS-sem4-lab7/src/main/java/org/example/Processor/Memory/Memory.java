package org.example.Processor.Memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Memory {
    private final List<BinaryNumberRepresentation> words;

    private final int size;

    private boolean sorted = false;

    public Memory(int... initialList) {
        this.words = Arrays.stream(initialList)
                .mapToObj(BinaryNumberRepresentation::new)
                .collect(Collectors.toCollection(ArrayList::new));
        this.size = initialList.length;
    }


    public int getSize() {
        return size;
    }

    public void mergeSort() {
        mergeSort(0, words.size() - 1);
        this.sorted = true;
    }

    private void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
        List<BinaryNumberRepresentation> temp = new ArrayList<>(right - left + 1);

        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (words.get(i).compareTo(words.get(j)) <= 0) {
                temp.add(words.get(i));
                i++;
            } else {
                temp.add(words.get(j));
                j++;
            }
        }

        while (i <= mid) {
            temp.add(words.get(i));
            i++;
        }

        while (j <= right) {
            temp.add(words.get(j));
            j++;
        }

        for (int k = 0; k < temp.size(); k++) {
            words.set(left + k, temp.get(k));
        }
    }

    public boolean isSorted() {
        return sorted;
    }

    public void reverse() {
        if (!isSorted()) {
            mergeSort(0, words.size() - 1);
        }
        int left = 0;
        int right = words.size() - 1;

        while (left < right) {
            BinaryNumberRepresentation temp = words.get(left);
            words.set(left, words.get(right));
            words.set(right, temp);

            left++;
            right--;
        }

        this.sorted = false;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < words.size(); i++) {
            output.append(String.format("[%#x] ", i)).append(words.get(i).toString()).append('\n');
        }
        return "Memory{\n" + output + '}';
    }

    public List<BinaryNumberRepresentation> getWords() {
        return words;
    }
}
