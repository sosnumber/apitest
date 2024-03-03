package com.example.apitest.repository.like;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apitest.domain.like.Like;
import com.example.apitest.domain.member.Member;
import com.example.apitest.domain.post.domain.Post;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByMemberAndPost(Member member, Post post);
}
