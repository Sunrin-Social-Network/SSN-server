package io.twotle.ssn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealDTO {
    private String date;
    private String meal;

    public MealDTO(String date, String meal) {
        this.date = date;
        this.meal =meal;
    }
}
