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
     *
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
     *
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
     *
     */
    static double [] quickSort (double[] a){

        return a;
    }//end quicksort

    /**
     * Merges an array of doubles with an auxiliary array to allow the implementation of Merge Sort.
     * This method is private and can only be called by mergeSortIterative and mergeSortRecursive.
     *
     * @param a: the initial unsorted array of doubles to be merged
     * @param aux: the auxiliary array of doubles where the sorted array will be stored
     * @param lo: the index in the full sorted array where the first element of aux will be stored
     * @param mid: the index in the full sorted array where the middle element of aux will be stored
     * @param hi: the index in the fully sorted array where the last element of aux will be stored
     */

    private static void merge(double[] a, double[] aux, int lo, int mid, int hi)
    {
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k++)
        {
            if      (i > mid)       {aux[k] = a[j++];}
            else if (j > hi)        {aux[k] = a[i++];}
            else if (a[j] < a[i])   {aux[k] = a[j++];}
            else                    {aux[k] = a[i++];}
        }
    }

    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
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
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double[] a) {

        return a;
        //todo: implement the sort

    }//end mergeSortRecursive

    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }//end main
}//end class