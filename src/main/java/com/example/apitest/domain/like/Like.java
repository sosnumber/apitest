package com.example.apitest.domain.like;


import com.example.apitest.domain.member.Member;
import com.example.apitest.domain.post.domain.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Like {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "comment_id")
//    private Comment comment;

    @Builder
    private Like(Long id, Member member, Post post) {
        this.id = id;
        this.member = member;
        this.post = post;
//        this.comment = comment;
    }

    public static Like create(Member member, Post post) {
        return Like.builder()
                .member(member)
                .post(post)
                .build();
    }
}
