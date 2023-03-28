package com.realworld.backend.infrastructure.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.realworld.backend.core.comment.Comment;
import com.realworld.backend.core.comment.CommentRepository;
import com.realworld.backend.infrastructure.mybatis.mapper.CommentMapper;

@Component
public class MyBatisCommentRepository implements CommentRepository {
	private CommentMapper commentMapper;

	@Autowired
	public MyBatisCommentRepository(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	@Override
	public void save(Comment comment) {
		commentMapper.insert(comment);
	}

	@Override
	public Optional<Comment> findById(String articleId, String id) {
		return Optional.ofNullable(commentMapper.findById(articleId, id));
	}

	@Override
	public void remove(Comment comment) {
		commentMapper.delete(comment.getId());
	}
}
