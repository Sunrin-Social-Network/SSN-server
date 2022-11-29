package io.twotle.ssn.dto;

import io.twotle.ssn.entity.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data

public class PostsDTO {
    private List<JPostDTO> data;

    public PostsDTO(List<JPostDTO> p) {
        this.data = p;
    }

}
