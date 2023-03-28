package com.realworld.backend.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realworld.backend.api.exception.NoAuthorizationException;
import com.realworld.backend.api.exception.ResourceNotFoundException;
import com.realworld.backend.application.ArticleQueryService;
import com.realworld.backend.application.article.ArticleCommandService;
import com.realworld.backend.application.article.UpdateArticleParam;
import com.realworld.backend.application.data.ArticleData;
import com.realworld.backend.core.article.Article;
import com.realworld.backend.core.article.ArticleRepository;
import com.realworld.backend.core.service.AuthorizationService;
import com.realworld.backend.core.user.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/articles/{slug}")
@AllArgsConstructor
public class ArticleApi {
	private ArticleQueryService articleQueryService;
	private ArticleRepository articleRepository;
	private ArticleCommandService articleCommandService;

	@GetMapping
	public ResponseEntity<?> article(@PathVariable("slug") String slug, @AuthenticationPrincipal User user) {
		return articleQueryService.findBySlug(slug, user)
				.map(articleData -> ResponseEntity.ok(articleResponse(articleData)))
				.orElseThrow(ResourceNotFoundException::new);
	}

	@PutMapping
	public ResponseEntity<?> updateArticle(@PathVariable("slug") String slug, @AuthenticationPrincipal User user,
			@Valid @RequestBody UpdateArticleParam updateArticleParam) {
		return articleRepository.findBySlug(slug).map(article -> {
			if (!AuthorizationService.canWriteArticle(user, article)) {
				throw new NoAuthorizationException();
			}
			Article updatedArticle = articleCommandService.updateArticle(article, updateArticleParam);
			return ResponseEntity
					.ok(articleResponse(articleQueryService.findBySlug(updatedArticle.getSlug(), user).get()));
		}).orElseThrow(ResourceNotFoundException::new);
	}

	@DeleteMapping
	public ResponseEntity deleteArticle(@PathVariable("slug") String slug, @AuthenticationPrincipal User user) {
		return articleRepository.findBySlug(slug).map(article -> {
			if (!AuthorizationService.canWriteArticle(user, article)) {
				throw new NoAuthorizationException();
			}
			articleRepository.remove(article);
			return ResponseEntity.noContent().build();
		}).orElseThrow(ResourceNotFoundException::new);
	}

	private Map<String, Object> articleResponse(ArticleData articleData) {
		return new HashMap<String, Object>() {
			{
				put("article", articleData);
			}
		};
	}
}