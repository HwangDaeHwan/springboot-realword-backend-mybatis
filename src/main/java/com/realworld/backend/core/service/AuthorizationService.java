package com.realworld.backend.core.service;

import com.realworld.backend.core.article.Article;
import com.realworld.backend.core.comment.Comment;
import com.realworld.backend.core.user.User;

public class AuthorizationService {
	public static boolean canWriteArticle(User user, Article article) {
		return user.getId().equals(article.getUserId());
	}

	public static boolean canWriteComment(User user, Article article, Comment comment) {
		return user.getId().equals(article.getUserId()) || user.getId().equals(comment.getUserId());
	}
}