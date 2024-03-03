package com.example.apitest.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PostMemoryService {

	private final Map<Long, Long> viewCountStore = new ConcurrentHashMap<>(); // postId, viewCount
	private final Map<Long, Long> likeCountStore = new HashMap<>(); // postId, likeCount
	private final Map<Long, Long> commentCountStore = new HashMap<>(); // postId, commentCount

	public void addViewCount(Long postId) {
		viewCountStore.compute(postId, (key, value) -> (value == null) ? 1 : value + 1);
	}

	public void initViewCount(Long postId) {
		viewCountStore.replace(postId, 0L);
	}

	public Long likeCount(Long postId) {
		return likeCountStore.getOrDefault(postId, 0L);
	}

	public Long commentCount(Long postId) {
		return commentCountStore.getOrDefault(postId, 0L);
	}

	public Set<Long> getEntry() {
		return viewCountStore.keySet();
	}

	public void saveLikeCount(Long popularPostKey, Long likeCount) {
		likeCountStore.put(popularPostKey, likeCount);
	}

	public void saveCommentCount(Long popularPostKey, Long commentCount) {
		commentCountStore.put(popularPostKey, commentCount);
	}
}
