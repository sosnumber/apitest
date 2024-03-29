package com.example.apitest.domain.post.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.apitest.domain.member.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * title과 contents가 fulltext index로 설정되어있습니다.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Table(indexes = {
        @Index(columnList = "createdAt"),
})
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 글 제목

    @Column(nullable = false)
    @Lob
    private String contents; // 글내용

    private long viewCount; // 조회수
    private LocalDateTime createdAt; // 작성날짜
    private LocalDateTime updatedAt; // 수정날짜

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus; // 글 해결 여부

    @ToString.Exclude
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHashtag> postHashtags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Post(Long id, String title, String contents, long viewCount, LocalDateTime createdAt,
            LocalDateTime updatedAt, PostStatus postStatus, List<PostHashtag> postHashtags,
            Member member) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.postStatus = postStatus;
        this.postHashtags = postHashtags;
        this.member = member;
    }

    public void addPostHashtag(PostHashtag postHashtag) {
        this.postHashtags.add(postHashtag);
    }

    public void addMember(Member member) {
        this.member = member;
    }

    public void updateTitleAndContents(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.updatedAt = LocalDateTime.now();
    }

    public void addViewCount() {
        this.viewCount += 1;
    }

    public void updateViewCountFromCache(long viewCount) {
        this.viewCount += viewCount;
    }
}
