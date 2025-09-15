package org.havelsan.aksy.questiondemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person2 {
    private String name;
    private String city;
    private List<String> roles;
}
