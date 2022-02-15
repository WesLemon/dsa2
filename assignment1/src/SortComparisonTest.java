import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
                           Insert      Selection   Quick       Merge Rec   Merge It
   1000 random             4009333     3040833     1080000     227600      616900
   1000 few unique         987533      1544466     113833      164733      208500
   1000 nearly ordered     1765200     832800      129533      116133      294733
   1000 reverse order      665100      418100      1910033     66533       123333
   1000 sorted             619100      324300      1771600     48866       91666
   10000 random            42356433    23854333    1256000     937900      1477966

   a)   The order of input has an effect on all of these algorithms. As you can see all of them are at least twice
        as fast when the input is nearly ordered. This is because

   b)

   c)

   d)

   e)
 */

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

    private  static final double[] ARRAY_TO_SORT = {
            3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0
    };

    @org.junit.Test
    public void testSortingAlgorithms()
    {
        assertArrayEquals(SortComparison.insertionSort(ARRAY_TO_SORT), SORTED_RESULT, 0.0);
        assertArrayEquals(SortComparison.selectionSort(ARRAY_TO_SORT), SORTED_RESULT, 0.0);
        assertArrayEquals(SortComparison.mergeSortIterative(ARRAY_TO_SORT), SORTED_RESULT, 0.0);
        assertArrayEquals(SortComparison.mergeSortRecursive(ARRAY_TO_SORT), SORTED_RESULT, 0.0);
        assertArrayEquals(SortComparison.quickSort(ARRAY_TO_SORT), SORTED_RESULT, 0.0);
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
        double[] random1000 = getArrayFromFile("numbers1000.txt", 1000);
        double[] fewUnique  = getArrayFromFile("numbers1000Duplicates.txt", 1000);
        double[] nearlyOrdered = getArrayFromFile("numbersNearlyOrdered1000.txt", 1000);
        double[] reverse = getArrayFromFile("numbersReverse1000.txt", 1000);
        double[] sorted = getArrayFromFile("numbersSorted1000.txt", 1000);
        double[] random10000 = getArrayFromFile("numbers10000.txt", 10000);

        System.out.println("1000 Random Doubles: ");
        sortArray(random1000);
        System.out.println();

        System.out.println("1000 Doubles - Few Unique: ");
        sortArray(fewUnique);
        System.out.println();

        System.out.println("1000 Doubles - Nearly Ordered: ");
        sortArray(nearlyOrdered);
        System.out.println();

        System.out.println("1000 Doubles - Reverse Order: ");
        sortArray(reverse);
        System.out.println();

        System.out.println("1000 Sorted Doubles: ");
        sortArray(sorted);
        System.out.println();

        System.out.println("10000 Random Doubles: ");
        sortArray(random10000);
    }

    private static void sortArray(double[] a)
    {
        double[] temp = a.clone();

        long start = System.nanoTime();
        SortComparison.quickSort(temp);
        long end = System.nanoTime();
        System.out.println("Quick Sort - " + (end-start) + " nanoseconds");

        start = System.nanoTime();
        SortComparison.insertionSort(temp);
        end = System.nanoTime();
        System.out.println("Insertion Sort - " + (end-start) + " nanoseconds");

        start = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        end = System.nanoTime();
        System.out.println("Merge Sort Iterative - " + (end-start) + " nanoseconds");

        start = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        end = System.nanoTime();
        System.out.println("Merge Sort Recursive - " + (end-start) + " nanoseconds");

        start = System.nanoTime();
        SortComparison.selectionSort(temp);
        end = System.nanoTime();
        System.out.println("Selection Sort - " + (end-start) + " nanoseconds");
    }

    private static double[] getArrayFromFile(String filename, int size)
    {
        Scanner inFile = null;

        try {
            inFile = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        double[] array = new double[size];

        if (inFile != null)
        {
            for(int i = 0; inFile.hasNextDouble(); i++)
            {
                array[i] = inFile.nextDouble();
            }
        }

        return array;
    }
}