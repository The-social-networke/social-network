package com.socialnetwork.project.repository;

import com.socialnetwork.project.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT * FROM profiles WHERE user_id = :userId", nativeQuery = true)
    Optional<Profile> getByUserId(@Param("userId") Long userId);
}
