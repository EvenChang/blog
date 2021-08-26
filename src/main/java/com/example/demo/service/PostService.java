package com.example.demo.service;

import com.example.demo.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findForId(Long id);

    Post save(Post post);

    /**
     * Finds a {@link Page) of all {@link Post} ordered by date
     */
//    Page<Post> findAllOrderedByDatePageable(int page);

    void delete(Post post);

    List<Post> getPosts();
}
