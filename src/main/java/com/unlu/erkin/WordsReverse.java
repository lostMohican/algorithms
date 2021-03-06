package com.unlu.erkin;

import java.util.Stack;

/**
 * Created by ERKIN on 28/01/15.
 */
public class WordsReverse {

    public static void main(String[] args) {
        System.out.println(reverse("Hey you! out there in the dark, will you help me?"));

        System.out.println(reverseWord("whatup yo?"));
    }

    private static String reverse(String sentence) {
        char[] chars = sentence.toCharArray();

        StringBuilder builder = new StringBuilder();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                builder.append(chars[i]);
            }
            else {
                //flush the builder
                stack.push(builder.toString());
                builder = new StringBuilder();
            }
        }

        stack.push(builder.toString());

        builder = new StringBuilder();
        while (!stack.empty()) {
            String word = stack.pop();
            builder.append(word).append(" ");
        }

        return builder.toString();
    }

    public static String reverseWord(String word) {
        char[] wordArray = word.toCharArray();

        StringBuilder builder = new StringBuilder();

        for (int i = wordArray.length - 1; i >= 0; i--) {
            builder.append(wordArray[i]);
        }

        return builder.toString();
    }
}
