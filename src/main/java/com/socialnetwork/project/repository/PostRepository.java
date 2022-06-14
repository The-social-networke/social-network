package com.socialnetwork.project.repository;

import com.socialnetwork.project.entity.Post;
import com.socialnetwork.project.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM posts WHERE user_id = :userId", nativeQuery = true)
    List<Post> getUserPosts(@Param("userId") Long userId);
}
