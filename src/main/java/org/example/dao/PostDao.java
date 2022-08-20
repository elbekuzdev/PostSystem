package org.example.dao;

import org.example.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostDao extends BasicDao<Post>{
    List<Post> findByUserId(Integer userId);
}
