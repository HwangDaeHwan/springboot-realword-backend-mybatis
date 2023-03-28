package com.realworld.backend.core.comment;

import java.util.UUID;

import org.joda.time.DateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {
	private String id;
	private String body;
	private String userId;
	private String articleId;
	private DateTime createdAt;

	public Comment(String body, String userId, String articleId) {
		this.id = UUID.randomUUID().toString();
		this.body = body;
		this.userId = userId;
		this.articleId = articleId;
		this.createdAt = new DateTime();
	}
}
