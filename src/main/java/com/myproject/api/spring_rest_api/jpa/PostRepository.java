package com.myproject.api.spring_rest_api.jpa;

import com.myproject.api.spring_rest_api.user.Post;
import com.myproject.api.spring_rest_api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
