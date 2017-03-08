package collections;

import java.util.Comparator;
import java.util.Random;

/**
 * The Class Sorting contains two sorting algorithms, QuickSort and MergeSort.
 * The QuickSort algorithm can only receive and sort arrays of type double. The
 * MergeSort algorithm can receive and sort any type of ArrayList. Note: the
 * MergeSort sorts the elements in its natural order.
 * 
 * @author Filip Nilsson
 *
 */
public class Sorting {

	/**
	 * The QuickSort algorithm. Receives a Array of type double and sorts it in
	 * ascending order.
	 * 
	 * @param arr
	 *            , double Array to be sorted in ascending order.
	 */
	public static void sort(double[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private static void sort(double[] arr, int left, int right) {
		int pivotIndex;
		if (left < right) {
			pivotIndex = partition(arr, left, right, (left + right) / 2);
			sort(arr, left, pivotIndex - 1);
			sort(arr, pivotIndex + 1, right);
		}
	}

	private static int partition(double[] arr, int left, int right, int pivotIndex) {
		double pivotValue = arr[pivotIndex];
		int storeIndex = left;
		swap(arr, pivotIndex, right);
		for (int i = left; i <= right - 1; i++) {
			if (arr[i] < pivotValue) {
				swap(arr, i, storeIndex);
				storeIndex++;
			}
		}
		swap(arr, storeIndex, right);
		return storeIndex;
	}

	private static void swap(double[] arr, int index1, int index2) {
		double temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;

	}

	/**
	 * The MergeSort algorithm. Recieves an ArrayList of generic type <E> and
	 * sorts it in ascending order.
	 * 
	 * @param list
	 *            , ArrayList of type <E> to be sorted in ascending order
	 */
	public static <E> void sort(ArrayList<E> list) {
		ArrayList<E> temp = new ArrayList<E>(list.size());
		for (int i = 0; i < list.size(); i++) {
			temp.add(i, list.get(i));
		}
		mergesortAsc(list, 0, list.size(), temp);
		temp = null;
	}

	private static <E> void mergesortAsc(ArrayList<E> list, int start, int n, ArrayList<E> temp) {
		int n1, n2;
		if (n > 1) {
			n1 = n / 2;
			n2 = n - n1;
			mergesortAsc(list, start, n1, temp);
			mergesortAsc(list, start + n1, n2, temp);
			merge(list, start, n1, n2, temp);
		}
	}

	private static <E> void merge(ArrayList<E> list, int first, int n1, int n2, ArrayList<E> temp) {
		int counter = 0, cursor1 = 0, cursor2 = n1, last = n1 + n2;
		E element;
		while ((cursor1 < n1) && (cursor2 < last)) {
			if (((Comparable<E>) list.get(first + cursor1)).compareTo(list.get(first + cursor2)) > 0) {
				element = list.get(first + cursor1);
				temp.set(counter, element);
				cursor1++;
			} else {
				element = list.get(first + cursor2);
				temp.set(counter, element);
				cursor2++;
			}
			counter++;
		}

		while (cursor1 < n1) {
			element = list.get(first + cursor1);
			temp.set(counter, element);
			cursor1++;
			counter++;
		}

		while (cursor2 < last) {
			element = list.get(first + cursor2);
			temp.set(counter, element);
			cursor2++;
			counter++;
		}
		for (int i = 0; i < n1 + n2; i++) {
			element = temp.get(i);
			list.set((first + i), element);
		}
		// element=null;
	}

	public static void main(String[] args) {
		double[] array = { 7.0, 6.0, 5.0, 46.0, 23.5, 14.0, 25.0 };
		sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}

		// ArrayList<Long> list = new ArrayList<Long>();
		// Random rand = new Random();
		// for (int i = 0; i < 10; i++) {
		// Long nbr = rand.nextLong();
		// list.add(nbr);
		// }
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i));
		// }
		// System.out.println("------------------------------------------------------");
		// sort(list);
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i));
		// }
	}

}
