package org.havelsan.aksy.question1;

import org.havelsan.aksy.question1.models.Car;
import org.havelsan.aksy.question1.models.Insurance;
import org.havelsan.aksy.question1.models.Person;
import org.havelsan.aksy.question1.utils.InsuranceNameFinder;

import java.util.Arrays;
import java.util.List;

public class MainQuestion1 {
    public static void main(String[] args) {
        System.out.println("<<< ANSWER 1 >>>");
        System.out.println("---------------------------------------------------");
        question1Demo();
    }

    public static void question1Demo() {
        // Create Insurance objects
        Insurance allianzInsurance = Insurance.builder().name("Allianz A.S. Insurance").build();
        Insurance turkiyesigortaInsurance = Insurance.builder().name("Turkiye Sigorta A.S. Insurance").build();
        Insurance nullInsurance = Insurance.builder().name(null).build();   // 4) insurance name is null case

        // Create Car objects with the Insurance
        Car volkswagen = Car.builder().insurance(allianzInsurance).build();
        Car nissan = Car.builder().insurance(turkiyesigortaInsurance).build();
        Car mazda = Car.builder().insurance(nullInsurance).build();
        Car fiat = Car.builder().insurance(null).build();       // 3) insurance is null case

        // Create Person objects with the Cars
        Person ibrahim = Person.builder().car(volkswagen).build();
        Person ufuk = Person.builder().car(nissan).build();
        Person kemal = Person.builder().car(mazda).build();
        Person mehmet = Person.builder().car(fiat).build();
        Person ahmet = Person.builder().car(null).build();      // 2) car is null case

        List<Person> people = Arrays.asList(ibrahim, ufuk, kemal, mehmet, ahmet, null);   // 1) person is null case

        // Retrieve and print the list of insurance names
        InsuranceNameFinder.getInsuranceNameList(people)
            .ifPresent(insuranceNameList -> insuranceNameList.
                forEach(System.out::println));
    }
}