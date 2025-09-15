package org.havelsan.aksy.questiondemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person {
    private String name;
    @Builder.Default
    private Optional<String> email = Optional.empty();
    private String city;
    private int age;
}
