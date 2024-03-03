package com.example.apitest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.apitest.domain.post.PostDto;
import com.example.apitest.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {

	private final TestService testService;

	@GetMapping("/posts/{postId}")
	public PostDto postDetail(@PathVariable long postId) {
		return testService.postDetail(postId);
	}
}
