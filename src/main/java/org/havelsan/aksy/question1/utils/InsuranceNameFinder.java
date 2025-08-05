package org.havelsan.aksy.question1.utils;

import org.apache.commons.lang3.StringUtils;
import org.havelsan.aksy.question1.models.Car;
import org.havelsan.aksy.question1.models.Insurance;
import org.havelsan.aksy.question1.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * QUESTION 1
 * Utility class to find insurance names from a list of people.
 */
public class InsuranceNameFinder {
    /**
     * Retrieves a list of insurance names from a list of people.
     * If the insurance name is null or blank, it adds "Unknown" to the list.
     *
     * @param people List of Person objects
     * @return Optional containing a list of insurance names
     */
    public static Optional<List<String>> getInsuranceNameList(List<Person> people) {
        return Optional.of(people)
                .flatMap(list -> {
                    List<String> insuranceNameList = new ArrayList<>();
                    list.forEach(person -> Optional.ofNullable(person)
                            .map(Person::getCar)
                            .map(Car::getInsurance)
                            .map(Insurance::getName)
                            .filter(StringUtils::isNotBlank)
                            .ifPresentOrElse(
                                insuranceNameList::add,
                                () -> insuranceNameList.add("Unknown")
                            ));
                    return Optional.of(insuranceNameList);
                });
    }
}
