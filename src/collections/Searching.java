package collections;

/**
 * The class Searching includes two searching algorithms, Binary search and
 * Linear search. Both of the algorithms are able to handle Objects of generic
 * type <E>.
 * 
 * @author Filip Nilsson
 *
 */

public class Searching {

	/**
	 * Method searches to see if an ArrayList of generic type <E> contains a
	 * specific element. If so, the index of this element is returned. If not,
	 * -1 is returned meaning that the element was not found in the ArrayList.
	 * 
	 * NOTE: The ArrayList must be sorted before given as a parameter to this
	 * method.
	 * 
	 * @param list
	 *            , ArrayList to be searched
	 * @param element
	 *            , element to be searched for in an ArrayList
	 * @return , if found the index is returned. If not -1 is returned meaning
	 *         that the element was not found in the ArrayList.
	 */
	public static <E> int binarySearch(ArrayList<E> list, E element) {

		Comparable<E> comp = (Comparable<E>) element;
		int min = 0, max = list.size() - 1, pos;

		while (min <= max) {
			pos = (min + max) / 2;
			if (comp.compareTo(list.get(pos)) == 0) {
				return pos;
			} else if (comp.compareTo(list.get(pos)) < 0) {
				max = pos - 1;
			} else {
				min = pos + 1;
			}

		}
		return -1;

	}

	/**
	 * Method searches to see if an List of generic type <E> contains a specific
	 * element. If so, the index of this element is returned. If not, -1 is
	 * returned meaning that the element was not found in the List.
	 * 
	 * NOTE: The List does NOT have to be sorted before given as a parameter to
	 * this method.
	 * 
	 * @param list
	 *            , List to be searched
	 * @param element
	 *            , element to be searched for
	 * @return , if found the index is returned. If not -1 is returned meaning
	 *         that the element was not found in the List.
	 */
	public static <E> int linearSearch(List<E> list, Object element) {

		for (int i = 0; i < list.size(); i++) {
			if (element.equals(list.get(i)))
				return i;

		}
		return -1;

	}

	public static void main(String[] args) {
		Searching prog = new Searching();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(4);
		list.add(6);
		list.add(9);
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i));
		// }
		Integer element = 6;
		System.out.println(prog.binarySearch(list, element));

	}
}
