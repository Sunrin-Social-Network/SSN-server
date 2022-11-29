package io.twotle.ssn.dto;

import io.twotle.ssn.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JCommentDTO {
    private Long id;
    private String comment;
    private UserDTO user;

    public JCommentDTO(Comment c) {
        this.id = c.getId();
        this.comment = c.getComment();
        this.user = new UserDTO(c.getUser());
    }
}
