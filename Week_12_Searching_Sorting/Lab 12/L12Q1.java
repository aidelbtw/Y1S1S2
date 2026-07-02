import java.util.Arrays;

public class L12Q1 {
    public static void main(String[] args) {
        L12Q1 obj = new L12Q1();
        int[] array = {45, 7, 2, 8, 19, 3};
        System.out.println("Original: " + Arrays.toString(array));
        obj.selectionSortSmallest(array);
        System.out.println("Modified: " + Arrays.toString(array));

        int[] array2 = {45, 7, 2, 8, 19, 3};
        System.out.println("Original: " + Arrays.toString(array2));
        obj.selectionSortLargest(array2);
        System.out.println("Modified: " + Arrays.toString(array2));

        int[] array3 = {10, 34, 2, 56, 7, 67, 88, 42};
        System.out.println("Original: " + Arrays.toString(array3));
        obj.insertionSort(array3);
        System.out.println("Modified: " + Arrays.toString(array3));

        int[] array4 = {25, 76, 26, 38, 119, 23, 11, 11, 8, 39, 47, 57, 14, 54, 22};
        System.out.println("Original: " + Arrays.toString(array4));
        obj.mergeSort(array4);
        System.out.println("Modified: " + Arrays.toString(array4));

        int [] array5 = {45, 7, 2, 8, 19, 3};
        System.out.println("Original: " + Arrays.toString(array5));
        obj.bubbleSort(array5);
        System.out.println("Modified: " + Arrays.toString(array5));

        int[] searchArr = {45, 7, 2, 8, 19, 3};
        int target = 8;
        System.out.println("\nSearching for " + target + " in " + Arrays.toString(searchArr));

        // Linear search (works on unsorted array)
        int linearResult = obj.linearSearch(searchArr, target);
        if (linearResult != -1)
            System.out.println("linearSearch: found at index " + linearResult);
        else
            System.out.println("linearSearch: not found");

        obj.bubbleSort(searchArr);
        System.out.println("Sorted for binary search: " + Arrays.toString(searchArr));
        int binaryResult = obj.binarySearch(searchArr, target);
        if (binaryResult != -1)
            System.out.println("binarySearch: found at index " + binaryResult);
        else
            System.out.println("binarySearch: not found");
    }

    public void bubbleSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - 1 - i; j++){
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void selectionSortSmallest(int[] arr){
        int n = arr.length;
        for (int i = 0 ; i < n ; i++){
            int minIndex = i;
            for (int j = i + 1 ; j < n ; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp; 
        }
    }

    public void selectionSortLargest(int[] arr){
        int n = arr.length;
        for (int i = 0 ; i < n ; i++){
            int maxIndex = i;
            for (int j = i + 1 ; j < n ; j++){
                if (arr[j] > arr[maxIndex]){
                    maxIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
    }

    public void insertionSort(int[] arr){   //Will use i for the elemt youre moving, k will be used to look at spots that are before i
        int n = arr.length;                 //it will go through all at k and finds if any suitable spots, and it moves all the spots
        for (int i = 0; i < n ; i++){       //by 1 forward to make space in case thats the right spot
            int currentElement = arr[i];
            int k;
            for (k = i - 1; k >= 0 && arr[k] > currentElement ; k--){
                arr[k + 1] = arr [k];
            }
            arr[k + 1] = currentElement; 
        }
    }

    public void mergeSort(int[] arr){
        if (arr.length > 1){
            int[] firstHalf = new int[arr.length / 2];
            System.arraycopy(arr, 0, firstHalf, 0, arr.length/2);
            mergeSort(firstHalf);

            int secondHalfLength = arr.length - arr.length/2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(arr, arr.length/2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            merge(firstHalf, secondHalf, arr);
        }
    }

    public void merge(int[] arr1, int[] arr2, int[] temp){
        int current1 = 0 ;
        int current2 = 0 ;
        int current3 = 0 ;

        while (current1 < arr1.length && current2 < arr2.length){
            if (arr1[current1] < arr2[current2]){
                temp[current3++] = arr1[current1++];
            } else {
                temp[current3++] = arr2[current2++];
            }
        }
        while (current1 < arr1.length){
            temp[current3++] = arr1[current1++];
        }
        while (current2 < arr2.length){
            temp[current3++] = arr2[current2++];
        }
    }

    // Linear search: scans every element one by one from left to right
    // Works on ANY array, sorted or unsorted
    public int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;   // found, return the index
            }
        }
        return -1;          // not found
    }

    // Binary search: repeatedly cuts the search range in half
    // ONLY works on a SORTED array
    public int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;         // found, return the index
            } else if (arr[mid] < target) {
                low = mid + 1;      // target is in the right half
            } else {
                high = mid - 1;     // target is in the left half
            }
        }
        return -1;                  // not found
    }
}
}
