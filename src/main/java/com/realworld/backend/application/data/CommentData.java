package com.realworld.backend.application.data;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.backend.application.DateTimeCursor;
import com.realworld.backend.application.Node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentData implements Node {
	private String id;
	private String body;
	@JsonIgnore
	private String articleId;
	private DateTime createdAt;
	private DateTime updatedAt;

	@JsonProperty("author")
	private ProfileData profileData;

	@Override
	public DateTimeCursor getCursor() {
		return new DateTimeCursor(createdAt);
	}
}
