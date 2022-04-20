package com.algorithm.demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BaseMethod {

    public <T extends Comparable<T>> int find(T[] array, T value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(value) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param array is an array where the element should be found
     * @param key is an element which should be found
     * @param <T> is any comparable type
     * @return index of the element
     */
    public <T extends Comparable<T>> int find1(T[] array, T key) {
        return search(array, key, 0, array.length);
    }

    /**
     * This method implements the Generic Binary Search
     *
     * @param array The array to make the binary search
     * @param key The number you are looking for
     * @param left The lower bound
     * @param right The upper bound
     * @return the location of the key
     */
    private <T extends Comparable<T>> int search(T array[], T key, int left, int right) {
        if (right < left) {
            return -1; // this means that the key not found
        }
        // find median
        int median = (left + right) >>> 1;
        int comp = key.compareTo(array[median]);

        if (comp == 0) {
            return median;
        } else if (comp < 0) {
            return search(array, key, left, median - 1);
        } else {
            return search(array, key, median + 1, right);
        }
    }

    @Test
    void LinearSearch()
    {
        // just generate data
        Random r = new Random();
        int size = 200;
        int maxElement = 100;
        Integer[] integers
                = Stream.generate(() -> r.nextInt(maxElement)).limit(size).toArray(Integer[]::new);

        // the element that should be found
        Integer shouldBeFound = integers[r.nextInt(size - 1)];

        int atIndex = find(integers, shouldBeFound);

        System.out.println(
                String.format(
                        "Should be found: %d. Found %d at index %d. An array length %d",
                        shouldBeFound, integers[atIndex], atIndex, size));
    }


    @Test
    void binary_search()
    {
        // Just generate data
        Random r = ThreadLocalRandom.current();

        int size = 100;
        int maxElement = 100000;

        Integer[] integers
                = IntStream.generate(() -> r.nextInt(maxElement))
                .limit(size)
                .sorted()
                .boxed()
                .toArray(Integer[]::new);

        // The element that should be found
        int shouldBeFound = integers[r.nextInt(size - 1)];

        int atIndex = find1(integers, shouldBeFound);

        System.out.println(
                String.format(
                        "Should be found: %d. Found %d at index %d. An array length %d",
                        shouldBeFound, integers[atIndex], atIndex, size));

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(
                String.format(
                        "Found by system method at an index: %d. Is equal: %b", toCheck, toCheck == atIndex));
    }


    /**
     * @param array is a sorted array
     * @param key is a value what shoulb be found in the array
     * @return an index if the array contains the key unless -1
     */
    public int find2(int array[], int key) {
        // Find indexes of two corners
        int start = 0, end = (array.length - 1);

        // Since array is sorted, an element present
        // in array must be in range defined by corner
        while (start <= end && key >= array[start] && key <= array[end]) {
            // Probing the position with keeping
            // uniform distribution in mind.
            int pos = start + (((end - start) / (array[end] - array[start])) * (key - array[start]));

            // Condition of target found
            if (array[pos] == key) {
                return pos;
            }

            // If key is larger, key is in upper part
            if (array[pos] < key) {
                start = pos + 1;
            } // If key is smaller, x is in lower part
            else {
                end = pos - 1;
            }
        }
        return -1;

    }

    @Test
    void interpolation_search()
    {
        Random r = new Random();
        int size = 100;
        int maxElement = 100000;
        int[] integers = IntStream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().toArray();

        // the element that should be found
        Integer shouldBeFound = integers[r.nextInt(size - 1)];

        int atIndex = find2(integers, shouldBeFound);

        System.out.println(
                String.format(
                        "Should be found: %d. Found %d at index %d. An array length %d",
                        shouldBeFound, integers[atIndex], atIndex, size));

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(
                String.format(
                        "Found by system method at an index: %d. Is equal: %b", toCheck, toCheck == atIndex));
    }
}