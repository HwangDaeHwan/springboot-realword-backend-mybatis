package com.realworld.backend.api;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realworld.backend.application.ArticleQueryService;
import com.realworld.backend.application.Page;
import com.realworld.backend.application.article.ArticleCommandService;
import com.realworld.backend.application.article.NewArticleParam;
import com.realworld.backend.core.article.Article;
import com.realworld.backend.core.user.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/articles")
@AllArgsConstructor
public class ArticlesApi {
	private ArticleCommandService articleCommandService;
	private ArticleQueryService articleQueryService;

	@PostMapping
	public ResponseEntity createArticle(@Valid @RequestBody NewArticleParam newArticleParam,
			@AuthenticationPrincipal User user) {
		Article article = articleCommandService.createArticle(newArticleParam, user);
		return ResponseEntity.ok(new HashMap<String, Object>() {
			{
				put("article", articleQueryService.findById(article.getId(), user).get());
			}
		});
	}

	@GetMapping(path = "feed")
	public ResponseEntity getFeed(@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "20") int limit, @AuthenticationPrincipal User user) {
		return ResponseEntity.ok(articleQueryService.findUserFeed(user, new Page(offset, limit)));
	}

	@GetMapping
	public ResponseEntity getArticles(@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "20") int limit,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "favorited", required = false) String favoritedBy,
			@RequestParam(value = "author", required = false) String author, @AuthenticationPrincipal User user) {
		return ResponseEntity
				.ok(articleQueryService.findRecentArticles(tag, author, favoritedBy, new Page(offset, limit), user));
	}
}
