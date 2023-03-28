package com.realworld.backend.api;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realworld.backend.api.exception.ResourceNotFoundException;
import com.realworld.backend.application.ArticleQueryService;
import com.realworld.backend.application.data.ArticleData;
import com.realworld.backend.core.article.Article;
import com.realworld.backend.core.article.ArticleRepository;
import com.realworld.backend.core.favorite.ArticleFavorite;
import com.realworld.backend.core.favorite.ArticleFavoriteRepository;
import com.realworld.backend.core.user.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "articles/{slug}/favorite")
@AllArgsConstructor
public class ArticleFavoriteApi {
	private ArticleFavoriteRepository articleFavoriteRepository;
	private ArticleRepository articleRepository;
	private ArticleQueryService articleQueryService;

	@PostMapping
	public ResponseEntity favoriteArticle(@PathVariable("slug") String slug, @AuthenticationPrincipal User user) {
		Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
		ArticleFavorite articleFavorite = new ArticleFavorite(article.getId(), user.getId());
		articleFavoriteRepository.save(articleFavorite);
		return responseArticleData(articleQueryService.findBySlug(slug, user).get());
	}

	@DeleteMapping
	public ResponseEntity unfavoriteArticle(@PathVariable("slug") String slug, @AuthenticationPrincipal User user) {
		Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
		articleFavoriteRepository.find(article.getId(), user.getId()).ifPresent(favorite -> {
			articleFavoriteRepository.remove(favorite);
		});
		return responseArticleData(articleQueryService.findBySlug(slug, user).get());
	}

	private ResponseEntity<HashMap<String, Object>> responseArticleData(final ArticleData articleData) {
		return ResponseEntity.ok(new HashMap<String, Object>() {
			{
				put("article", articleData);
			}
		});
	}
}