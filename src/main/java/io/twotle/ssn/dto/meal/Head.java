package io.twotle.ssn.dto.meal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Head{
    public int list_total_count;
    @JsonProperty("RESULT")
    public RESULT rESULT;
}
