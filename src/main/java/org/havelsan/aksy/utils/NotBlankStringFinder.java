package org.havelsan.aksy.utils;

import java.util.Optional;

/**
 * QUESTION 2
 * Utility class to find a non-empty string from a series of Optional<String> values.
 */
public class NotBlankStringFinder {
    private static Optional<String> getOptEmpty() {
        return Optional.empty();
    }

    private static Optional<String> getOpt1() {
        return Optional.of("Optional1");
    }

    private static Optional<String> getOpt2() {
        return Optional.of("Optional2");
    }


    /**
     * Finds a non-empty string from a series of Optional<String> values.
     * It checks the first Optional, then the second, and finally throws an exception if none are present.
     *
     * @return A non-empty string from the available Optionals.
     * @throws IllegalArgumentException if no non-empty string is found.
     */
    public static String findNotBlankString() {
        return getOptEmpty()
                .orElseGet(() -> getOpt1()
                    .orElseGet(() -> getOpt2()
                        .orElseThrow(() -> new IllegalArgumentException("No non-empty string found"))));
    }
}
