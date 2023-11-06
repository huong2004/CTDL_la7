package lab7;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MyWordCount {
	// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "data/fit.txt";

	private List<String> words = new ArrayList<>();

	public MyWordCount() {
		try {
			this.words.addAll(Utils.loadWords(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public List<WordCount> getWordCounts() {
		// TODO
		List<WordCount> re = new ArrayList<>();
		for (String w : words) {
			WordCount wc = new WordCount(w, count(w));
			if (!re.contains(wc))
				re.add(wc);
		}
		return re;
	}

	public int count(String str) {
		int count = 0;
		for (String w : words) {
			if (w.equals(str))
				count++;
		}

		return count;

	}

	// Returns the words that their appearance are 1, do not consider duplidated
	// words
	public Set<String> getUniqueWords() {
		Set<String> re = new HashSet<>(); // Set khong sap xep dung HashSet <>()
		for (WordCount w : getWordCounts()) {
			if (w.getCount() == 1)
				re.add(w.getWord());
		}

		return re;
	}

	// Returns the words in the text file, duplicated words appear once in the
	// result
	public Set<String> getDistinctWords() {
		// TODO
		Set<String> re = new HashSet<>();
		for (String w : words) {
			if (!re.contains(w))
				re.add(w);
		}
		// Có thể dùng TreeSet <>()
		// Không cần dùng contain
		return re;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according ascending order of tokens
	// Example: An - 3, Bug - 10, ...
	public Set<WordCount> printWordCounts() {
		// TODO
		Set<WordCount> re = new TreeSet<>(new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {
				// TODO Auto-generated method stub
				int count = o1.getCount() - o2.getCount();
				if (count == 0)
					return o1.getWord().compareTo(o2.getWord());
				return count;
			}

		});
		re.addAll(this.getWordCounts());
		return re;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according descending order of occurences
	// Example: Bug - 10, An - 3, Nam - 2.
	public Set<WordCount> exportWordCountsByOccurence() {
		// TODO
		Set<WordCount> re = new TreeSet<>(new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {
				// TODO Auto-generated method stub
				int count = o2.getCount() - o1.getCount();
				if (count == 0)
					return o2.getWord().compareTo(o1.getWord());
				return count;
			}
		});
		re.addAll(getWordCounts());
		return re;
	}

	// delete words begining with the given pattern (i.e., delete words begin with
	// 'A' letter
	public Set<String> filterWords(String pattern) {
		// TODO
		Set<String> re = new HashSet<>();
		for (String word : words) {
			if (!(word.charAt(0) == pattern.charAt(0)))
				re.add(word);
		}
		return re;
	}

	public static void main(String[] args) {
		MyWordCount obj = new MyWordCount();
		System.out.println("getWordCounts: " + obj.getWordCounts());
		System.out.println("getUniqueWords: " + obj.getUniqueWords());
		System.out.println("getDistinctWords: " + obj.getDistinctWords());
		System.out.println("getWordCounts: " + obj.printWordCounts());
		System.out.println("exportWordCountsByOccurence: " + obj.exportWordCountsByOccurence());
		System.out.println("filterWords D: " + obj.filterWords("D"));
	}
}
