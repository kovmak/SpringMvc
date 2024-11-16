package com.krnelx.SpringMvc.repository;

import com.krnelx.SpringMvc.model.Post;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

    private final List<Post> posts = new ArrayList<>();
    private Long currentId = 1L;
    private final Set<Long> deletedIds = new HashSet<>();

    public List<Post> findAll(int page, int size) {
        int start = page * size;
        int end = Math.min(start + size, posts.size());
        return posts.subList(start, end);
    }

    public Optional<Post> findById(Long id) {
        return posts.stream().filter(post -> post.getId().equals(id)).findFirst();
    }

    public Post save(Post post) {
        if (post.getId() == null) {
            if (!deletedIds.isEmpty()) {
                Long reusedId = deletedIds.iterator().next();
                deletedIds.remove(reusedId);
                post.setId(reusedId);
            } else {
                post.setId(currentId++);
            }
            posts.add(post);
        } else {
            posts.removeIf(p -> p.getId().equals(post.getId()));
            posts.add(post);
        }
        return post;
    }

    public void delete(Long id) {
        posts.removeIf(post -> post.getId().equals(id));
        deletedIds.add(id);
    }
}