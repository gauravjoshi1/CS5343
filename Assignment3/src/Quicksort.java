import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Quicksort {
    int pivot;
    int[] arr;

    public int partitionAscending(int[] arr, int left, int right) {
        pivot = arr[left];

        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;

            if (left <= right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }

        return left;
    }

    public int partitionDescending(int[] arr, int left, int right) {
        pivot = arr[left];

        while (left <= right) {
            while (arr[left] > pivot) left++;
            while (arr[right] < pivot) right--;

            if (left <= right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }

        return left;
    }

    public void quicksortAscending(int[] arr, int left, int right) {
        this.arr = arr;
        int partitionArr = partitionAscending(arr, left, right);

        if (left < partitionArr - 1) {
            quicksortAscending(arr, left, partitionArr-1);
        }

        if (partitionArr < right) {
            quicksortAscending(arr, partitionArr, right);
        }
    }

    public void quicksortDescending(int[] arr, int left, int right) {
        this.arr = arr;
        int partitionArr = partitionDescending(arr, left, right);

        if (left < partitionArr - 1) {
            quicksortDescending(arr, left, partitionArr-1);
        }

        if (partitionArr < right) {
            quicksortDescending(arr, partitionArr, right);
        }
    }

    public static void main(String[] args) {

        // generate an array of 15 unique numbers
        int[] arr = ThreadLocalRandom.current().ints(0, 100).distinct().limit(15).toArray();

        // input array
        System.out.println("original array == " + Arrays.toString(arr));

        Quicksort qs = new Quicksort();

        // quick sort ascending
        qs.quicksortAscending(arr, 0, arr.length - 1);
        System.out.println("sorted in ascending order == "  + Arrays.toString(arr));

         // quick sort descending
        qs.quicksortDescending(arr, 0, arr.length - 1);
        System.out.println("sorted in descending order == " + Arrays.toString(arr));
    }
}
