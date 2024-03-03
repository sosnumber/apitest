package com.example.apitest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apitest.domain.post.PostDto;
import com.example.apitest.domain.post.domain.Post;
import com.example.apitest.repository.post.PopularPostRepository;
import com.example.apitest.repository.post.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

	private final PostMemoryService postMemoryService;
	private final PostRepository postRepository;
	private final PopularPostRepository popularPostRepository;

	@Transactional
	public PostDto postDetail(long postId) {
		if (isPopularPost(postId)) {
			postMemoryService.addViewCount(postId);
			return popularPostDetail(postId);
		} else {
			updateViewCountInDb(postId);
			return regularPostDetail(postId);
		}
	}
	private PostDto regularPostDetail(long postId) {
		PostDto postDetail = postRepository.postDetail(postId);
		postDetail.inputHashtags(postRepository.postHashtagsBy(postDetail));
		postDetail.inputComments(postRepository.commentsBy(postDetail));
		return postDetail;
	}

	private PostDto popularPostDetail(long postId) {
		PostDto postDetail = postRepository.postDetailWithoutCountQuery(postId);
		postDetail.inputLikeCount(postMemoryService.likeCount(postId));
		postDetail.inputCommentCount(postMemoryService.commentCount(postId));
		postDetail.inputHashtags(postRepository.postHashtagsBy(postDetail));
		postDetail.inputComments(postRepository.commentsBy(postDetail));
		return postDetail;
	}

	private boolean isPopularPost(long postId) {
		return popularPostRepository.findByPostId(postId) != null;
	}

	private void updateViewCountInDb(long postId) {
		Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
		post.addViewCount();
	}
}
