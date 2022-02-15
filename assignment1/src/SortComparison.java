/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Wesley Shaw
 *  @version HT 2022
 */

class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     */
    static double [] insertionSort (double[] a){
        double temp;
        for(int i = 1; i < a.length; i++)
        {
            for(int j = i; j > 0; j--)
            {
                if(a[j] < a[j-1])
                {
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
        return a;
    }//end insertionsort

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     */
    static double [] selectionSort (double[] a){
        int n = a.length;
        for(int i = 0; i < n-1; i++)
        {
            int min = i;
            for(int j = i+1; j < n; j++)
            {
                if(a[j] < a[min])
                {
                    min = j;
                }
            }
            double temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
        return a;
    }//end selectionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     */
    static double [] quickSort (double[] a){
        recursiveQuicksort(a, 0, a.length-1);
        return a;
    }//end quicksort

    /**
     * Recursive method for Quick Sort to be called by quicksort().
     * @param a: An unsorted array of doubles.
     * @param lo: The lowest index of the array to be sorted.
     * @param hi: The highest index of the array to be sorted
     */
    private static void recursiveQuicksort (double[] a, int lo, int hi)
    {
        if(hi <= lo)
        {
            return;
        }
        int pivotPos = partition(a, lo, hi);
        recursiveQuicksort(a, lo, pivotPos-1);
        recursiveQuicksort(a, pivotPos+1, hi);
    }

    /**
     * Method used to partition an array by finding a pivot value with all elements
     * on its left being smaller than it, and all elements on its right being greater.
     * @param a: an unsorted array of doubles
     * @param lo: the lowest index of the array to be partitioned
     * @param hi: the highest index of the array to be partitioned
     * @return an index in the array to be used as a pivot value
     */
    private static int partition(double[] a, int lo, int hi)
    {
        int i = lo;
        int j = hi+1;
        double pivot = a[lo];
        while(true)
        {
            while(a[++i] < pivot)
            {
                if(i == hi)
                {
                    break;
                }
            }
            while(pivot < a[--j])
            {
                if(j == lo)
                {
                    break;
                }
            }
            if(i >= j)
            {
                break;
            }
            double temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        a[lo] = a[j];
        a[j] = pivot;
        return j;
    }

    /**
     * Merges an array of doubles with an auxiliary array to allow the implementation of Merge Sort.
     * This method is private and can only be called by mergeSortIterative and mergeSortRecursive.
     * @param a: the initial unsorted array of doubles to be merged
     * @param aux: the auxiliary array of doubles where the sorted array will be stored
     * @param lo: the index in the full sorted array where the first element of aux will be stored
     * @param mid: the index in the full sorted array where the middle element of aux will be stored
     * @param hi: the index in the fully sorted array where the last element of aux will be stored
     */
    private static void merge(double[] a, double[] aux, int lo, int mid, int hi)
    {
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k++)
        {
            if(i > mid)
            {
                a[k] = aux[j++];
            }
            else if(j > hi)
            {
                a[k] = aux[i++];
            }
            else if(aux[j] < aux[i])
            {
                a[k] = aux[j++];
            }
            else
            {
                a[k] = aux[i++];
            }
        }
    }

    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortIterative (double[] a) {
        int N = a.length;
        double[] aux = new double[N];
        for(int sz = 1; sz < N; sz = sz+sz)
        {
            for(int lo = 0; lo < N-sz; lo += sz+sz)
            {
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
        return a;
    }//end mergesortIterative

    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double[] a) {
        double[] aux = new double[a.length];
        mergeSortRecursive(a, aux, 0, a.length-1);
        return a;
    }//end mergeSortRecursive

    /**
     * The recursive method for Merge Sort to be called by mergeSortRecursive().
     * @param a: an unsorted array of doubles
     * @param aux: an auxiliary array for copying the original array before sorting
     * @param lo: the lowest index in the array to be sorted
     * @param hi: the highest index in the array to be sorted
     */
    private static void mergeSortRecursive (double[] a, double [] aux, int lo, int hi)
    {
        if(hi <= lo)
        {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSortRecursive(a, aux, lo, mid);
        mergeSortRecursive(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /*
    ** Main method that I used to test and debug the sorting algorithms before I wrote the JUnit tests
    public static void main(String[] args) {
        double[] a1 = new double[] {
                3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        double[] b1 = SortComparison.insertionSort(a1);

        double[] a2 = new double[] {
                3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        double[] b2 = SortComparison.selectionSort(a2);

        double[] a3 = new double[] {
                3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        double[] b3 = SortComparison.mergeSortIterative(a3);

        double[] a4 = new double[] {
                3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        double[] b4 = SortComparison.mergeSortRecursive(a4);

        double[] a5 = new double[] {
                3.2, 1.2, 9.8, 5.3, 0.2, 10.3, 2.4, 4.5, 6.7, 2.3, 1.2, 9.8, 9.7, 3.1, 4.5, 6.5, 5.0};
        double[] b5 = SortComparison.quickSort(a5);

        for (double v : b1) {
            System.out.print(v + " ");
        }
        System.out.println(" InsertionSort");

        for (double v : b2) {
            System.out.print(v + " ");
        }
        System.out.println(" SelectionSort");

        for (double v : b3) {
            System.out.print(v + " ");
        }
        System.out.println(" MergeSortIterative");

        for (double v : b4) {
            System.out.print(v + " ");
        }
        System.out.println(" MergeSortRecursive");

        for (double v : b5) {
            System.out.print(v + " ");
        }
        System.out.println(" Quicksort");
    }//end main
     **/
}//end class
