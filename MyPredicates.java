package lab7;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

public class MyPredicates {
	// Remove every object, obj, from coll for which p.test(obj)
	// is true. (This does the same thing as coll.removeIf(p).)
	public static <T> void remove(Collection<T> coll, Predicate<T> p) {
		// TODO
		Iterator<T> inter = coll.iterator(); // Duyệt qua các phần tử
		while (inter.hasNext()) { // còn phần tử
			T obj = inter.next(); // Lấy phần tử
			if (p.test(obj))
				inter.remove();
		}

	}

	// Remove every object, obj, from coll for which
	// pr.test(obj) is false. (That is, retain the
	// objects for which the predicate is true.)
	public static <T> void retain(Collection<T> coll, Predicate<T> p) {
		// TODO
		Iterator<T> inter = coll.iterator();
		while (inter.hasNext()) {
			T obj = inter.next();
			if (!p.test(obj))
				inter.remove();
		}

	}

	// Return a Set that contains all unique objects, obj,
	// from the collection, coll, such that p.test(obj)
	// is true.
	public static <T> Set<T> collect(Collection<T> coll, Predicate<T> p) {
		// TODO
		Set<T> re = new TreeSet<>();
		Iterator<T> iter = coll.iterator();
		T obj = iter.next();
		while (iter.hasNext())
			if (p.test(obj))
				re.add(obj);
		return re;
	}

	// Return the index of the first item in list
	// for which the predicate is true, if any.
	// If there is no such item, return -1.
	public static <T> int find(Collection<T> coll, Predicate<T> p) {
		// TODO
		int i = 0;
		for (T item : coll) {
			if (p.test(item))
				return i;
			i++;
		}

		return -1;
	}

	public static void main(String[] args) {
		Collection<Integer> numbers = new HashSet<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		Even evenPredicate = new Even();

		// In kết quả ra màn hình
		remove(numbers, evenPredicate);
		System.out.println("remove: ");
		for (Integer number : numbers) {
			System.out.println(number);
		}
		Collection<Integer> numbers1 = new HashSet<>();
		numbers1.add(1);
		numbers1.add(2);
		numbers1.add(3);
		numbers1.add(4);
		numbers1.add(5);
		retain(numbers1, evenPredicate);
		System.out.println("retain: ");
		for (Integer number1 : numbers1) {
			System.out.println(number1);
		}
		Collection<Integer> numbers2 = new LinkedHashSet<>();
		numbers2.add(1);
		numbers2.add(9);
		numbers2.add(3);
		numbers2.add(4);
		numbers2.add(5);
		find(numbers2, evenPredicate);
		System.out.println("find: ");
		System.out.println(find(numbers2, evenPredicate));

	}

}
