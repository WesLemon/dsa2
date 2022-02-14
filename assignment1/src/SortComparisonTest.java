import org.junit.Test;

class SortComparisonTest {

    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        double[] a = new double[10];
        SortComparison.quickSort(a);
        SortComparison.selectionSort(a);
        SortComparison.insertionSort(a);
        SortComparison.mergeSortRecursive(a);
        SortComparison.mergeSortIterative(a);
    }

    // ----------------------------------------------------------
    /*
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *

    public static void main(String[] args)
    {
        //TODO: implement this method
    }
    */
}