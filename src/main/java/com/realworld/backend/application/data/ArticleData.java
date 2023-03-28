package com.realworld.backend.application.data;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.backend.application.DateTimeCursor;
import com.realworld.backend.application.Node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleData implements Node {
	private String id;
	private String slug;
	private String title;
	private String description;
	private String body;
	private boolean favorited;
	private int favoritesCount;
	private DateTime createdAt;
	private DateTime updatedAt;
	private List<String> tagList;

	@JsonProperty("author")
	private ProfileData profileData;

	@Override
	public DateTimeCursor getCursor() {
		return new DateTimeCursor(updatedAt);
	}
}
