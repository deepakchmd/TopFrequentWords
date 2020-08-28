package com.dpchmd.assignment;

import java.util.Comparator;


public class Node {
	String word;
	int count;
	
	public Node(String word, int count) {
		this.word = word;
		this.count = count;
	}
}

class NodeComparator implements Comparator<Node> {

	// Overriding compare()method of Comparator

	public int compare(Node p1, Node p2) {

		if (p1.count < p2.count)
			return 1;
		else if (p1.count > p2.count)
			return -1;
		else if (p1.count == p2.count) {
			int val = p1.word.compareTo(p2.word);
			// if < 0 the string calling the method is lexographically first
			if (val < 0) {
				return -1;
			} else if (val > 0) {
				return 1;
			} else {
				return 1;
			}
		}
		return 0;
	}
}
