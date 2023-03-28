package com.realworld.backend.application.article;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.realworld.backend.application.ArticleQueryService;
import com.realworld.backend.core.article.Article;

class DuplicatedArticleValidator implements ConstraintValidator<DuplicatedArticleConstraint, String> {

	@Autowired
	private ArticleQueryService articleQueryService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !articleQueryService.findBySlug(Article.toSlug(value), null).isPresent();
	}
}
