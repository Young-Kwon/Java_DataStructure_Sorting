/*
COMP-10205 - Starting code for Assignment # 2
*/
/**
 * interface used so we can pass method references to Perfromance method
 */
/**
 * All methods are static to the class - functional style
 * @author Young Sang Kwon, 000847777
 *
/**
 * Comment Section:
 *
 * a. For an array size of 20 elements, the sorting algorithms in order from fastest to slowest based on execution time are:
 *    gSort, cSort, fSort, bSort, dSort, eSort, aSort
 *
 * b. For an array size of 50000 elements, the sorting algorithms in order from fastest to slowest based on execution time are:
 *    gSort, dSort, fSort, aSort, cSort, bSort, eSort
 *
 * c. For 50,000 items, bSort is the most efficient based on basic instruction time, with a score of 0.2.
 *    But, even though it scores well in this, it's not the fastest in real use.
 *    The basic instruction time tells us how the algorithm works in a simple way.
 *    But real performance can be affected by other things.
 *    So, a good score here doesn't always mean it's the quickest overall
 *
 * d. Sort Algorithm Identification:
 * =========================================================================
 *  Sort Algorithm  | Sort Algorithm Name  | Big O (time)  | Big O (space)
 *  ------------------------------------------------------------------------
 *  aSort           | Quick Sort           | O(n∙log n)    | O(lon n)
 *  bSort           | Selection Sort       | O(n^2)        | O(1)
 *  cSort           | Insertion Sort       | O(n^2)        | O(1)
 *  dSort           | Merge Sort           | O(n∙log n)    | O(n)
 *  eSort           | Bubble Sort          | O(n^2)        | O(1)
 *  fSort           | Shell Sort           | O(n(log n)^2) | O(1)
 *  gSort           | Radix Sort           | O(d∙n)        | O(r) or O(n+r)
 * * ------------------------------------------------------------------------
  */

import java.util.*;

public class Assignment2 {

    static long qSortCompares = 0; // Left in for comparison purposes only
    /**
     * The swap method swaps the contents of two elements in an int array.
     *
     * @param array where elements are to be swapped.
     * @param a The subscript of the first element.
     * @param b The subscript of the second element.
     */
    private static void swap(int[] array, int a, int b) {
        int temp;
        temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    /**
     *------------- eSort -------------------------------------------------
     * Buble Sort
     */
    public static long eSort(int[] array) {
        int lastPos; // Position of last element to compare
        int index; // Index of an element to compare
        long comparisons = 0; // count comparisons
        // The outer loop positions lastPos at the last element to compare during each pass through the array.
        // Initially lastPos is the index of the last element in the array.
        // During each iteration, it is decreased by one.
        for (lastPos = array.length - 1; lastPos >= 0; lastPos--) {
            // The inner loop steps through the array, comparing each element with its neighbor.
            // All of the elements from index 0 thrugh lastPos are involved in the comparison.
            // If two elements are out of order, they are swapped.
            for (index = 0; index <= lastPos - 1; index++) {
                // Compare an element with its neighbor.
                comparisons++;
                if (array[index] > array[index + 1]) {
                    // Swap the two elements.
                    swap(array, index, index + 1);
                }
            }
        }
        return comparisons;
    }
    /**
     *------------- cSort -------------------------------------------------
     * Insert Sort
     */
    public static long cSort(int[] array) {
        int unsortedValue; // The first unsorted value
        int scan; // Used to scan the array
        long comparisons = 0; // count comparisons
        // The outer loop steps the index variable through
        // each subscript in the array, starting at 1. The portion of
        // the array containing element 0 by itself is already sorted.
        for (int index = 1; index < array.length; index++) {
            // The first element outside the sorted portion is
            // array[index]. Store the value of this element
            // in unsortedValue.
            unsortedValue = array[index];
            // Start scan at the subscript of the first element
            // outside the sorted part.
            scan = index;
            // Move the first element in the still unsorted part
            // into its proper position within the sorted part.
            if(!(scan > 0 && array[scan - 1] > unsortedValue)) {
                comparisons++;
            }
            while (scan > 0 && array[scan - 1] > unsortedValue) {
                array[scan] = array[scan - 1];
                scan--;
                comparisons++;
            }
            // Insert the unsorted value in its proper position
            // within the sorted subset.
            array[scan] = unsortedValue;
        }
        return comparisons;
    }
    /**
     *------------------- b Sort ---------------------------------------------
     * Selection Sort
     */
    public static long bSort(int[] array) {
        int startScan; // Starting position of the scan
        int index; // To hold a subscript value
        int minIndex; // Element with smallest value in the scan
        int minValue; // The smallest value found in the scan
        long comparisons = 0; // count comparisons
        // The outer loop iterates once for each element in the
        // array. The startScan variable marks the position where
        // the scan should begin.
        for (startScan = 0; startScan < (array.length - 1); startScan++) {
        // Assume the first element in the scannable area
        // is the smallest value.
            minIndex = startScan;
            minValue = array[startScan];
            // Scan the array, starting at the 2nd element in
            // the scannable area. We are looking for the smallest
            // value in the scannable area.
            for (index = startScan + 1; index < array.length; index++) {
                comparisons++;
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
            }
            // Swap the element with the smallest value
            // with the first element in the scannable area.
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }
        return comparisons;
    }
    /**
     * -------------------------- f Sort --------------------------------
     * Shell Sort
     */
    public static long fSort(int array[])
    {
        int n = array.length;
        long comparisons = 0; // count comparisons
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = array[i];
                int j = i;
                if(!(j >= gap && array[j - gap] > key)) {
                    comparisons++;
                }
                while (j >= gap && array[j - gap] > key) {
                    comparisons++;
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = key;
            }
        }
        return comparisons;
    }
    /**
     * ---------------------------- g Sort ---------------------------------------
     * Radix Sort
     */
    public static long gSort(int array[]) {
        int count = 0;
        int min = array[0];
        int max = array[0];
        long comparisons = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min)
                min = array[i];
            else if (array[i] > max)
                max = array[i];
            comparisons++;
        }
        int b[] = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            b[array[i] - min]++;
            comparisons++;
        }
        for (int i = 0; i < b.length; i++)
            for (int j = 0; j < b[i]; j++) {
                array[count++] = i + min;
                comparisons++;
            }
        return comparisons;
    }
    /**
     * The non-recursive Quicksort - manages first call
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */
    public static long aSort(int array[]) { // Quick Sort
        qSortCompares = 0;
        return doASort(array, 0, array.length - 1, qSortCompares);
    }
    /**
     * The doQuickSort method uses the QuickSort algorithm to sort an int array.
     *
     * @param array The array to sort.
     * @param start The starting subscript of the list to sort
     * @param end The ending subscript of the list to sort
     */
    private static long doASort(int array[], int start, int end, long qSortCompares) {
        //int pivotPoint;
        long[] pivotValueAndCompares = {0, qSortCompares};
        if (start < end) {
            // Get the pivot point.
            //pivotPoint = part1(array, start, end, numberOfCompares);
            pivotValueAndCompares = part1(array, start, end, qSortCompares);
            int pivotPoint = (int)pivotValueAndCompares[0];
            // Sort the first sub list.
            pivotValueAndCompares[1] = doASort(array, start, pivotPoint - 1, pivotValueAndCompares[1]);
            // Sort the second sub list.
            pivotValueAndCompares[1] = doASort(array, pivotPoint + 1, end, pivotValueAndCompares[1]);
        }
        return pivotValueAndCompares[1];
    }
    /**
     * The partition method selects a pivot value in an array and arranges the
     * array into two sub lists. All the values less than the pivot will be
     * stored in the left sub list and all the values greater than or equal to
     * the pivot will be stored in the right sub list.
     *
     * @param array The array to partition.
     * @param start The starting subscript of the area to partition.
     * @param end The ending subscript of the area to partition.
     * @return The subscript of the pivot value.
     */
    private static long[] part1(int array[], int start, int end, long qSortCompares) {
        int pivotValue; // To hold the pivot value
        int endOfLeftList; // Last element in the left sub list.
        int mid; // To hold the mid-point subscript
        // see http://www.cs.cmu.edu/~fp/courses/15122-s11/lectures/08-qsort.pdf
        // for discussion of middle point - This improves the almost sorted cases
        // of using quicksort
        // Find the subscript of the middle element.
        // This will be our pivot value.
        mid = (start + end) / 2;
        // mid = start;
        // Swap the middle element with the first element.
        // This moves the pivot value to the start of
        // the list.
        swap(array, start, mid);
        // Save the pivot value for comparisons.
        pivotValue = array[start];
        // For now, the end of the left sub list is
        // the first element.
        endOfLeftList = start;
        // Scan the entire list and move any values that
        // are less than the pivot value to the left
        // sub list.
        for (int scan = start + 1; scan <= end; scan++) {
            qSortCompares++;
            if (array[scan] < pivotValue) {
                endOfLeftList++;
                swap(array, endOfLeftList, scan);
            }
        }
        // Move the pivot value to end of the
        // left sub list.
        swap(array, start, endOfLeftList);
        // Return the subscript of the pivot value.
        long[] pivotValueAndCompares = {endOfLeftList, qSortCompares};

        return pivotValueAndCompares;
    }
    public static long dSort(int inputArray[]) {
        int length = inputArray.length;
        // Create array only once for merging
        int[] workingArray = new int[inputArray.length];
        long count = 0;
        count = doDSort(inputArray, workingArray, 0, length - 1, count);
        return count;
    }
    private static long doDSort(int[] inputArray, int[] workingArray, int lowerIndex, int higherIndex, long count) {
        long comparisons = 0; // count comparisons
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            comparisons += doDSort(inputArray, workingArray, lowerIndex, middle, count);
            // Below step sorts the right side of the array
            comparisons += doDSort(inputArray, workingArray, middle + 1, higherIndex, count);
            // Now merge both sides
            comparisons += part2(inputArray, workingArray, lowerIndex, middle, higherIndex);
        }
        return comparisons;
    }
    private static long part2(int[] inputArray, int[] workingArray, int lowerIndex, int middle, int higherIndex) {
        long count = 0;
        long comparisons = 0; // count comparisons
        for (int i = lowerIndex; i <= higherIndex; i++) {
            workingArray[i] = inputArray[i];
        }
        int i1 = lowerIndex;
        int i2 = middle + 1;
        int newIndex = lowerIndex;
        while (i1 <= middle && i2 <= higherIndex) {
            if (workingArray[i1] <= workingArray[i2]) {
                inputArray[newIndex] = workingArray[i1];
                i1++;
                comparisons++;
            } else {
                inputArray[newIndex] = workingArray[i2];
                i2++;
                comparisons++;
            }
            newIndex++;
        }
        while (i1 <= middle) {
            inputArray[newIndex] = workingArray[i1];
            newIndex++;
            i1++;
        }
        return comparisons;
    }
    /**
     * The main method will run through all of the Sorts for the prescribed sizes
     and produce output for parts A and B
     *
     * Part C should be answered at the VERY TOP of the code in a comment
     * *
     */
    public static void main(String[] args) {
        part1Assignment();
        part2Assignment();
    }
    /**
     * A demonstration of recursive counting in a Binary Search
     * @param array - array to search
     * @param low - the low index - set to 0 to search whiole array
     * @param high - set to length of array to search whole array
     * @param value - item to search for
     * @param count - Used in recursion to accumulate the number of comparisons
    made (set to 0 on initial call)
     * @return
     */
    private static int[] binarySearchR(int[] array, int low, int high, int value, int count)
    {
        int middle; // Mid point of search
        // Test for the base case where the value is not found.
        if (low > high)
            return new int[] {-1,count};
        // Calculate the middle position.
        middle = (low + high) / 2;
        // Search for the value.
        if (array[middle] == value)
        // Found match return the index
            return new int[] {middle, count};
        else if (value > array[middle])
        // Recursive method call here (Upper half of remaining data)
            return binarySearchR(array, middle + 1, high, value, count+1);
        else
        // Recursive method call here (Lower half of remaining data)
            return binarySearchR(array, low, middle - 1, value, count+1);
    }

    private static void part1Assignment() {
        System.out.println("Part 1: Results Correct results for Part A");
        int[] sizes = {20, 100, 10000, 50000};
        Random random = new Random();
        for (int size : sizes) {
            System.out.println("\nComparison for array size of " + size + " (Averaged over 5 runs)");
            System.out.println("==========================================================");
            System.out.println(String.format("%-10s %-15s %-10s %-10s", "Sort",      "Execution Time", "Compares", "Basic Step"));
            System.out.println(String.format("%-10s %-15s %-10s %-10s", "Algorithm", "(ns)",           "",         "(ns)"));
            System.out.println("----------------------------------------------------------");

            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = random.nextInt(size*2);
            }
            String algorithmName = "";
            // Loop over different sort algorithms
            String[] algorithms = {"aSort", "bSort", "cSort", "dSort", "eSort", "fSort", "gSort"};
            for (String algorithm : algorithms) {

                long compares = 0;
                long totalTime = 0;
                long start = 0, end = 0;
                algorithmName = algorithm;

                for (int run = 1; run <= 5; run++) {
                    int[] copy = Arrays.copyOf(arr, arr.length); // Copy array to use the same random values

                    switch (algorithm) {
                        case "aSort":
                            start = System.nanoTime();
                            compares += aSort(copy);
                            end = System.nanoTime();
                            break;
                        case "bSort":
                            start = System.nanoTime();
                            compares += bSort(copy);
                            end = System.nanoTime();
                            break;
                        case "cSort":
                            start = System.nanoTime();
                            compares += cSort(copy);
                            end = System.nanoTime();
                            break;
                        case "dSort":
                            start = System.nanoTime();
                            compares += dSort(copy);
                            end = System.nanoTime();
                            break;
                        case "eSort":
                            start = System.nanoTime();
                            compares += eSort(copy);
                            end = System.nanoTime();
                            break;
                        case "fSort":
                            start = System.nanoTime();
                            compares += fSort(copy);
                            end = System.nanoTime();
                            break;
                        case "gSort":
                            start = System.nanoTime();
                            compares += gSort(copy);
                            end = System.nanoTime();
                            break;
                        default:
                            start = System.nanoTime();
                            compares = eSort(copy);
                            end = System.nanoTime();
                            break;
                    }
                    totalTime += end - start;
                }
                System.out.printf("%-10s %-15d %-10d %-10.1f\n", algorithmName, (totalTime/5), (compares/5), (double) (totalTime/5)/(compares/5));
                System.out.println("----------------------------------------------------------");
            }
        }
    }

    private static void part2Assignment() {
        System.out.println("\nPart 2: Unsorted vs sorting searching");
        int size = 100000;
        Random random = new Random();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 2) + 1;
        }
        int[] arrCopy = Arrays.copyOf(arr, arr.length);

        // Linear Search
        long startLinear = System.nanoTime();
        int countLinear = linearSearch(arr, -1);
        long endLinear = System.nanoTime();
        long timeLinear = endLinear - startLinear;

        // Merge Sort / Binary Search
        long startSort = System.nanoTime();
        long compareSort = eSort(arrCopy);
        long endSort = System.nanoTime();
        long timeSort = endSort - startSort;

        long startBinary = System.nanoTime();
        int[] binaryResult = binarySearchR(arrCopy, 0, arrCopy.length - 1, -1, 0);
        long endBinary = System.nanoTime();
        long timeBinary = endBinary - startBinary;

        long totalTimeSortAndBinary = timeSort + timeBinary;

        System.out.println("Linear Search           : " + timeLinear +             "ns, Search count: " + countLinear);
        System.out.println("Buble Sort/Binary Search: " + totalTimeSortAndBinary + "ns, Search Count: " + binaryResult[1]);
        System.out.println("                          Buble Sort(" + timeSort + "ns) + Binary Search(" + timeBinary + ")");

        // justify sorting the data first
        if(timeLinear-totalTimeSortAndBinary>0) {
            System.out.println("sorting the data and a binary search would be " + (timeLinear-totalTimeSortAndBinary)+ "ns more time-efficient compared to doing linear search");
        }
        else if(timeLinear-totalTimeSortAndBinary<0) {
            System.out.println("Comparing only the search time, search after sorting is faster than linear search.");
            System.out.println("However, because sorting takes a lot of time, the overall time for search after sorting is slower than for linear search.");
            System.out.println("Therefore, when searching only once, searching after sorting is slower than linear search without sorting.");
            System.out.println("If you do a lot of searching after sorting, it is advantageous to search after sorting because it has a relatively short search time.");
        }
        else { // timeLinear == totalTimeSortAndBinary
            System.out.println("sorting the data and a binary search would be same time-efficient compared to doing linear search");
        }
    }

    private static int linearSearch(int[] arr, int value) {
        int count = 0;
        for (int i : arr) {
            count++;
            if (i == value) return count;
        }
        return count;
    }
}
