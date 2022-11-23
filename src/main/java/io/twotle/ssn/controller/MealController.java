package io.twotle.ssn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.twotle.ssn.component.CustomException;
import io.twotle.ssn.dto.MealDTO;
import io.twotle.ssn.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/meal")
@Api(tags = {"2. Meal"})
public class MealController {
    private final MealService mealService;

    @ApiOperation(value = "GetTodayFood", notes = "Get today food")
    @GetMapping("/today")
    public ResponseEntity<MealDTO> getTodayMeal() throws CustomException {
        MealDTO meal = mealService.getTodayMealData();
        return ResponseEntity.status(HttpStatus.OK).body(meal);
    }
}
