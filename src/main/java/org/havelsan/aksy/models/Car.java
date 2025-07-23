package org.havelsan.aksy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Car {
    private Insurance insurance;
}
