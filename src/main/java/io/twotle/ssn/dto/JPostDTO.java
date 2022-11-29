package io.twotle.ssn.dto;

import io.twotle.ssn.dto.UserDTO;
import io.twotle.ssn.entity.Post;
import io.twotle.ssn.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JPostDTO {
    private Long id;
    private String text;
    private String imageUrl;
    private String tags;
    private UserDTO user;
    private List<JCommentDTO> comment;

    public JPostDTO(Post post) {
        this.id = post.getId();
        this.text = post.getText();
        this.imageUrl = post.getImageUrl();
        this.tags = post.getTags();
        this.user = new UserDTO(post.getUser());
        List<JCommentDTO> l = new ArrayList<>();
        for(int i = 0; i < post.getComments().size() ; i ++) {
            l.add(new JCommentDTO(post.getComments().get(i)));
        }
        System.out.println(l);
        if(l.size() > 0) this.comment = l;
    }
}
