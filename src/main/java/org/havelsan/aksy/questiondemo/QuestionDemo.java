package org.havelsan.aksy.questiondemo;

import org.havelsan.aksy.questiondemo.models.Person2;
import org.havelsan.aksy.question3.models.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuestionDemo {
    private static final List<Person> team = Arrays.asList(
            new Person("Ahmet", Optional.of("ali.com"), "Bursa", 22),
            new Person("Ahmet", Optional.of("ahmet.com"), "İstanbul", 55),
            new Person("Ayşe", Optional.empty(), "Ankara", 21),
            new Person("Burak", Optional.of("burak.com"), "İstanbul", 40),
            new Person("Can", Optional.empty(), "İzmir", 32),
            new Person("Ece", Optional.of("ece.com"), "İstanbul", 29),
            new Person("Nehir", Optional.of("nehir.com"), "Ankara", 22)
    );

    private static final List<Person2> team2 = List.of (
            new Person2("Ali", "Bursa", List.of("ENGINEER")),
            new Person2("Ahmet", "İstanbul", List.of("ADMIN", "USER")),
            new Person2("Ayşe", "Ankara", List.of("ENGINEER")),
            new Person2("Burak", "İstanbul", List.of("ADMIN", "ENGINEER")),
            new Person2("Can", "İzmir", List.of("TEACHER")),
            new Person2("Ece", "İstanbul", List.of("ADMIN")),
            new Person2("Nehir", "Ankara", List.of("USER"))
    );

    public static void main(String[] args) {
        System.out.println("<<< ANSWER >>>");
        System.out.println("---------------------------------------------------");

        Map<String, Long> cityPopulation = team.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        System.out.println("Her Sehirde Yasayan Kisi Sayisi: " + cityPopulation);

        // Yasi 30'den Buyuk Olanlari true yapacak bir map
        Map<Boolean, List<Person>> agePopulation = team.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 30));
        agePopulation.forEach((key, value) -> {
            String ageGroup = key ? "30'dan Buyuk" : "30'dan Kucuk";
            System.out.println(ageGroup + " Kisi Sayisi: " + value.size());
        });

        // Her sehirdeki kisilerin yaslarini ortalama olarak hesaplayacak bir map
        Map<String, Double> ageAveragingPopulation = team.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.averagingDouble(Person::getAge)));
        System.out.println("Her Sehirdeki Kisi Yaslarinin Ortalamasi: " + ageAveragingPopulation);


        Function<Person, String> cityAgeFunc = person -> {
            int age = person.getAge();
            if (age <= 25) return "GENC";
            else if (age <= 49) return "ORTA";
            else return "YASLI";
        };

        Map<String, Map<String, List<String>>> cityAgeGroupNames = team.stream()
                .collect(Collectors.groupingBy(Person::getCity,
                        Collectors.groupingBy(cityAgeFunc, Collectors.mapping(Person::getName, Collectors.toList()))));

        System.out.println("Her Sehirdeki Yas Grubuna Gore Kisiler: " + cityAgeGroupNames);

        Map<String, Person> personalEmails = team.stream()
                .filter(person -> person.getEmail().isPresent())    // filter optional email
                .collect(Collectors.toMap(Person::getName,
                        Function.identity(), (p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2));

        System.out.println(personalEmails);
    }

}
