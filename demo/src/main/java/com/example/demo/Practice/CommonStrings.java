package com.example.demo.Practice;

import java.util.Arrays;
import java.util.List;

public class CommonStrings {
	
	public static String longestCommonPrefix(List<String> strings) {
	    if (strings == null || strings.isEmpty()) {
	        return "";
	    }

	    String prefix = strings.get(0);

	    for (int i = 1; i < strings.size(); i++) {
	        String current = strings.get(i);

	        int j = 0;
	        while (j < prefix.length() && j < current.length() && prefix.charAt(j) == current.charAt(j)) {
	            j++;
	        }

	        if (j == 0) {
	            return "";
	        }

	        prefix = prefix.substring(0, j);
	    }

	    return prefix;
	}

	
	public static void main(String[] args) {
		
	
		List<String> strings = Arrays.asList("flower", "flow", "flight");
		String longestCommonPrefix = longestCommonPrefix(strings);
		System.out.println("Longest common prefix: " + longestCommonPrefix);

		
	}
}
