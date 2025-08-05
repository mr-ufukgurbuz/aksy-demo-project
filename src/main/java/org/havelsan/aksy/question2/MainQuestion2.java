package org.havelsan.aksy.question2;

import org.havelsan.aksy.question2.utils.NotBlankStringFinder;

public class MainQuestion2 {
    public static void main(String[] args) {
        System.out.println("<<< ANSWER 2 >>>");
        System.out.println("---------------------------------------------------");
        question2Demo();
    }

    public static void question2Demo() {
        // Print the first non-blank string from the list
        System.out.println(NotBlankStringFinder.findNotBlankString());
    }
}