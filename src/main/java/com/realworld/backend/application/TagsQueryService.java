package com.realworld.backend.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realworld.backend.infrastructure.mybatis.readservice.TagReadService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TagsQueryService {
	private TagReadService tagReadService;

	public List<String> allTags() {
		return tagReadService.all();
	}
}
