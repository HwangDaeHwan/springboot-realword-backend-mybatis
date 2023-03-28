package com.realworld.backend.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.backend.api.exception.NoAuthorizationException;
import com.realworld.backend.api.exception.ResourceNotFoundException;
import com.realworld.backend.application.CommentQueryService;
import com.realworld.backend.application.data.CommentData;
import com.realworld.backend.core.article.Article;
import com.realworld.backend.core.article.ArticleRepository;
import com.realworld.backend.core.comment.Comment;
import com.realworld.backend.core.comment.CommentRepository;
import com.realworld.backend.core.service.AuthorizationService;
import com.realworld.backend.core.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(path = "/articles/{slug}/comments")
@AllArgsConstructor
public class CommentsApi {
	private ArticleRepository articleRepository;
	private CommentRepository commentRepository;
	private CommentQueryService commentQueryService;

	@PostMapping
	public ResponseEntity<?> createComment(@PathVariable("slug") String slug, @AuthenticationPrincipal User user,
			@Valid @RequestBody NewCommentParam newCommentParam) {
		Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
		Comment comment = new Comment(newCommentParam.getBody(), user.getId(), article.getId());
		commentRepository.save(comment);
		return ResponseEntity.status(201)
				.body(commentResponse(commentQueryService.findById(comment.getId(), user).get()));
	}

	@GetMapping
	public ResponseEntity getComments(@PathVariable("slug") String slug, @AuthenticationPrincipal User user) {
		Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
		List<CommentData> comments = commentQueryService.findByArticleId(article.getId(), user);
		return ResponseEntity.ok(new HashMap<String, Object>() {
			{
				put("comments", comments);
			}
		});
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteComment(@PathVariable("slug") String slug, @PathVariable("id") String commentId,
			@AuthenticationPrincipal User user) {
		Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
		return commentRepository.findById(article.getId(), commentId).map(comment -> {
			if (!AuthorizationService.canWriteComment(user, article, comment)) {
				throw new NoAuthorizationException();
			}
			commentRepository.remove(comment);
			return ResponseEntity.noContent().build();
		}).orElseThrow(ResourceNotFoundException::new);
	}

	private Map<String, Object> commentResponse(CommentData commentData) {
		return new HashMap<String, Object>() {
			{
				put("comment", commentData);
			}
		};
	}
}

@Getter
@NoArgsConstructor
@JsonRootName("comment")
class NewCommentParam {
	@NotBlank(message = "can't be empty")
	private String body;
}
