package com.realworld.backend.api;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realworld.backend.application.TagsQueryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "tags")
@AllArgsConstructor
public class TagsApi {
	private TagsQueryService tagsQueryService;

	@GetMapping
	public ResponseEntity getTags() {
		return ResponseEntity.ok(new HashMap<String, Object>() {
			{
				put("tags", tagsQueryService.allTags());
			}
		});
	}
}
