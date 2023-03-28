package com.realworld.backend.application.article;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.realworld.backend.core.article.Article;
import com.realworld.backend.core.article.ArticleRepository;
import com.realworld.backend.core.user.User;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class ArticleCommandService {

	private ArticleRepository articleRepository;

	public Article createArticle(@Valid NewArticleParam newArticleParam, User creator) {
		Article article = new Article(newArticleParam.getTitle(), newArticleParam.getDescription(),
				newArticleParam.getBody(), newArticleParam.getTagList(), creator.getId());
		articleRepository.save(article);
		return article;
	}

	public Article updateArticle(Article article, @Valid UpdateArticleParam updateArticleParam) {
		article.update(updateArticleParam.getTitle(), updateArticleParam.getDescription(),
				updateArticleParam.getBody());
		articleRepository.save(article);
		return article;
	}
}