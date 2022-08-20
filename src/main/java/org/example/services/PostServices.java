package org.example.services;

import org.example.model.Message;
import org.example.model.Post;

public interface PostServices {
    Message getPost();
    Message appendPost(Post post);
}
