package com.example.apitest.repository.post;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apitest.domain.hashtag.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Set<Hashtag> findByHashtagNameIn(Set<String> hashtags);
}
