package org.havelsan.aksy.question3;

import org.havelsan.aksy.question3.models.Person;

import java.util.*;
import java.util.stream.Collectors;

public class MainQuestion3 {
    private static final List<Person> team = Arrays.asList(
            new Person("Ali", Optional.of("ali.com"), "Bursa", 22),
            new Person("Ahmet", Optional.of("ahmet.com"), "İstanbul", 55),
            new Person("Ayşe", Optional.empty(), "Ankara", 21),
            new Person("Burak", Optional.of("burak.com"), "İstanbul", 40),
            new Person("Can", Optional.empty(), "İzmir", 32),
            new Person("Ece", Optional.of("ece.com"), "İstanbul", 29),
            new Person("Nehir", Optional.of("nehir.com"), "Ankara", 22)
    );

    public static void main(String[] args) {
        System.out.println("<<< ANSWER 3 >>>");
        System.out.println("---------------------------------------------------");
        question3Demo();
    }

    public static void question3Demo() {
        // Her şehirde kaç kişi yaşadığını hesaplayan bir Map<String, Long>
        Map<String, Long> cityPopulation = team.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));

        System.out.println("Her Şehirde Yaşayan Kişi Sayısı: " + cityPopulation);
        // -----------------------------------------------------------------------------

        // Her şehirdeki kişilerin yaş ortalamasını hesaplayan bir Map<String, Double>
        Map<String, Double> averageAgePerCity = team.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.averagingInt(Person::getAge)));

        System.out.println("Her Şehirde Yaş Ortalaması: " + averageAgePerCity);
        // -----------------------------------------------------------------------------

        // 0-25 -> GENÇ, 26-49 -> ORTA ve 50+ -> YAŞLI olacak şekilde bir kural seti ile
        // Map<String, Map<String, List<String>>> şeklinde Şehir -> (Yaş Grubu -> Kişi İsimleri) yapısı
        Map<String, Map<String, List<String>>> cityAgeGroupNames = team.stream()
                .collect(Collectors.groupingBy(Person::getCity,
                        Collectors.groupingBy(person -> {
                            int age = person.getAge();
                            if (age <= 25) return "GENÇ";
                            else if (age <= 49) return "ORTA";
                            else return "YAŞLI";
                        }, Collectors.mapping(Person::getName, Collectors.toList()))));

        System.out.println("Şehir ve Yaş Grubuna Göre Kişiler: " + cityAgeGroupNames);
        // -----------------------------------------------------------------------------

        // Map<String, String> oluştur. Kişi ismi -> Email.
        // Sadece Optional olan email'ler dahil edilecek,
        // Aynı isme sahip iki kişi varsa, yaşı büyük olanın email'i kullanılacak
        Map<String, String> nameToEmailMap = team.stream()
                .filter(person -> person.getEmail().isPresent()) // Sadece emaili olan kişileri filtrele
                .collect(Collectors.toMap(
                        Person::getName, // Anahtar olarak ismi kullan
                        person -> person.getEmail().get(), // Değer olarak emaili kullan
                        (existingName, newEmail) ->
                                team.stream()
                                        .filter(p -> p.getName().equals(existingName)) // Aynı isme sahip kişileri filtrele
                                        .max(Comparator.comparingInt(Person::getAge))
                                        .flatMap(Person::getEmail) // Eğer sonuç yoksa boş Optional döndür
                                        .orElse("") // Boş Optional ise boş string döndür
                ));

        System.out.println("Kişi İsimleri ve Email'leri: " + nameToEmailMap);
    }
}