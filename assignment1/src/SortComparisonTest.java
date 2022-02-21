import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
   All measurements are in nanoseconds

                           Insert      Selection   Quick       Merge Rec   Merge It
   1000 random             4009333     3040833     1080000     227600      616900
   1000 few unique         987533      1544466     113833      164733      208500
   1000 nearly ordered     1765200     832800      129533      116133      294733
   1000 reverse order      665100      418100      1910033     66533       123333
   1000 sorted             619100      324300      1771600     48866       91666
   10000 random            42356433    23854333    1256000     937900      1477966

   biggest difference 1000 3390233     2716533     1796200     178734      525234
   scalability input size  38347100    20813500    176000      710300      861066

   a)   The order of input has an effect on all of these algorithms. As you can see all of them are at least twice
        as fast when the input is nearly ordered. Even Selection Sort, which is theoretically meant to be unaffected
        by the order, is nearly 10 times faster when the array is sorted. I believe that this is due to the system
        that I am running my algorithms on. Similarly, reverse order is meant to be the worst case for Insertion Sort,
        but the average running time on my system is much faster than 1000 random numbers.

   b)   Insertion Sort has the biggest difference between the best and worse performance for 1000 numbers. This is
        because it is heavily dependent on the order of the numbers. The more sorted the array, the faster the algorithm
        should work, which is apparent from the table above. When the array is sorted, Insertion Sort should actually
        theoretically be one of the fastest algorithm, and when it is not sorted it should be among the slowest. This
        is not what my results showed, as I actually found it to be the second-slowest algorithm for the sorted array
        when I ran them on my system.

   c)   Insertion Sort has the worst scalability according to my results, while Quick Sort has the best. This is
        because Insertion Sort is quite inefficient at sorting random numbers, and the more random numbers it has to
        sort, the worse the performance will be. On the other hand, Quick Sort is one of the most efficient sorting
        algorithms along with Merge Sort, and according to my results it is able to scale very well showing only a
        difference of 0.176 milliseconds despite 10 times increase in input size.

   d)   My recursive Merge Sort results were consistently faster than my iterative Merge Sort results. This is
        probably due to the way the system I ran the experiments on handles memory.

   e)   Recursive Merge Sort is fastest for all the input files except for 1000 few unique numbers. Quick Sort is
        slightly faster for that one input file. On my system, Merge Sort seems to be the best all-round sorting
        algorithm, but that may be system-dependent and three experiments too small a sample size to draw any real
        conclusions.
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