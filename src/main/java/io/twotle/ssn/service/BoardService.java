package io.twotle.ssn.service;

import io.twotle.ssn.dto.JPostDTO;
import io.twotle.ssn.entity.Comment;
import io.twotle.ssn.entity.Post;
import io.twotle.ssn.entity.User;
import io.twotle.ssn.repository.CommentRepository;
import io.twotle.ssn.repository.PostRepository;
import io.twotle.ssn.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(
            User user,
            String text,
            String imageUrl,
            String tags
    ) {

       Post post = Post.builder()
               .text(text)
               .imageUrl(imageUrl)
               .user(user)
               .tags(tags)
               .build();
       Post saved = this.postRepository.save(post);

       return saved;
    }

    @Transactional
    public Comment createComment(
            User user,
            String text,
            Long id
    ) {
        Post p =this.postRepository.getById(id);
        Comment c = new Comment().builder()
                .comment(text)
                .user(user)
                .post(p).build();
        Comment saved = this.commentRepository.save(c);
        p.addComments(saved);
        return saved;
    }

    @Transactional
    public List<JPostDTO> getPosts() {
        return this.postRepository.findAll()
                .stream().map(JPostDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public JPostDTO getPost(Long id) {
        return new JPostDTO(this.postRepository.findById(id).get());

    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).get();
    }
}
