import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Wesley Shaw
 *  @version HT 2022
 */

@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @org.junit.Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @org.junit.Test
    public void testEmpty()
    {
        double[] a = new double[10];
        assertArrayEquals(SortComparison.insertionSort(a), a, 0.0);
        assertArrayEquals(SortComparison.selectionSort(a), a, 0.0);
        assertArrayEquals(SortComparison.mergeSortIterative(a), a, 0.0);
        assertArrayEquals(SortComparison.quickSort(a), a, 0.0);
        assertArrayEquals(SortComparison.mergeSortRecursive(a), a, 0.0);
    }

    private static final double[] SORTED_RESULT = {
            0.2, 1.2, 1.2, 2.3, 2.4, 3.1, 3.2, 4.5, 4.5, 5.0, 5.3, 6.5, 6.7, 9.7, 9.8, 9.8, 10.3
    };

    @org.junit.Test
    public void testInsertionSort()
    {
        double[] array = {3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        assertArrayEquals(SortComparison.insertionSort(array), SORTED_RESULT, 0.0);
    }

    @org.junit.Test
    public void testSelectionSort()
    {
        double[] array = {3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        assertArrayEquals(SortComparison.selectionSort(array), SORTED_RESULT, 0.0);
    }

    @org.junit.Test
    public void testMergeSortIterative()
    {
        double[] array = {3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        assertArrayEquals(SortComparison.mergeSortIterative(array), SORTED_RESULT, 0.0);
    }

    @org.junit.Test
    public void testMergeSortRecursive()
    {
        double[] array = {3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        assertArrayEquals(SortComparison.mergeSortRecursive(array), SORTED_RESULT, 0.0);
    }

    @org.junit.Test
    public void testQuickSort()
    {
        double[] array = {3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        assertArrayEquals(SortComparison.quickSort(array), SORTED_RESULT, 0.0);
    }

    @org.junit.Test
    public void additionalCoverageTest()
    {
        double[] array = {5.0, 6.5};
        assertArrayEquals(SortComparison.quickSort(array), array, 0.0);
    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args) {
        Scanner inFile1 = null;

        try {
            inFile1 = new Scanner(new File("numbers1000.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        double[] random = new double[1000];

        if (inFile1 != null) {
            for(int i = 0; inFile1.hasNextDouble(); i++)
            {
                random[i] = inFile1.nextDouble();
            }
        }

        double[] randomQuick = SortComparison.quickSort(random);
        double[] randomInsertion = SortComparison.insertionSort(random);
        double[] randomMerge1 = SortComparison.mergeSortIterative(random);
        double[] randomMerge2 = SortComparison.mergeSortRecursive(random);
    }
}