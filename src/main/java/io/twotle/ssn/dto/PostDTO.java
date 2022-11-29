package io.twotle.ssn.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
@JsonAutoDetect
public class PostDTO {
    @NotEmpty(message = "not empty")
    private String text;

    private String tags;

    private MultipartFile file;
}
