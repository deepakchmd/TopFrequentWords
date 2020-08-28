package com.dpchmd.assignment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class TopTenFrequentWords {

	public static void main(String[] args) {

		Document doc;
		String str = "";
		try {

			System.out.println("\n\nThis program wil scan through a given webpage via URL, and display the top 10 frequent words and the top 10 frequent word pairs (two words in the same order) along with their frequency\n");
			System.out.println("Please enter the URL that needs to scanned: ");
			Scanner sc = new Scanner(System.in);
			String url = sc.nextLine();

			// need http protocol
			doc = Jsoup.connect(url).get();
			str = doc.body().text(); // "An example link"
			// System.out.println(">> " + str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * Formatting string for capturing words
		 */
		String[] wordsArr = str.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");

		/**
		 * For words
		 */
		List<Node> topFreqWordlist = topKFrequent(wordsArr, 10);
		Collections.sort(topFreqWordlist, new NodeComparator());
		System.out.println("\n/*** Top 10 words: ***/ \n");
		int j = 1;
		for(Node it:topFreqWordlist) {
			System.out.println(j+") "+it.word+" - "+it.count+" times");
			j++;
		}
		
		/**
		 * For word pairs
		 */
		final List<String> wordPairs = new ArrayList<>();
		for (int i = 0; i < wordsArr.length - 1; ++i) {
			wordPairs.add(wordsArr[i] + " " + wordsArr[i + 1]);
		}

		List<Node> topFreqWordPairslist = topKFrequent(wordPairs.toArray(new String[0]), 10);
		Collections.sort(topFreqWordPairslist, new NodeComparator());
		System.out.println("\n/*** Top 10 word pairs: ***/ \n");
		j = 1;
		for(Node it:topFreqWordPairslist) {
			System.out.println(j+") "+it.word+" - "+it.count+" times");
			j++;
		}

	}
	
	public static List<Node> topKFrequent(String[] words, int k) {
		int n = words.length;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < n; ++i) {
			map.put(words[i], map.getOrDefault(words[i], 0) + 1);
		}

		PriorityQueue<Node> maxHeap = new PriorityQueue<Node>(new NodeComparator());

		for (Entry<String, Integer> entry : map.entrySet()) {
			maxHeap.add(new Node(entry.getKey(),entry.getValue()));
		}
		
		List<Node> nodes = new ArrayList<Node>();
		while (nodes.size() < k) {
			nodes.add(maxHeap.poll());
		}
		return nodes;
	}
}

