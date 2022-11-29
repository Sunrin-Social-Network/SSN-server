package io.twotle.ssn.repository;

import io.twotle.ssn.entity.Comment;
import io.twotle.ssn.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
