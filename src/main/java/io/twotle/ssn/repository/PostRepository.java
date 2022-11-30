package io.twotle.ssn.repository;

import io.twotle.ssn.entity.Post;
import io.twotle.ssn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTagsContaining(String tags);
}
