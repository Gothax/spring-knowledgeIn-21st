package com.ceos21.knowledgein.post.repository;

import com.ceos21.knowledgein.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);
    List<Post> findByTitleContaining(String title);
    List<Post> findByContentContaining(String content);
}
