package com.realworld.backend.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realworld.backend.core.comment.Comment;

@Mapper
public interface CommentMapper {
	void insert(@Param("comment") Comment comment);

	Comment findById(@Param("articleId") String articleId, @Param("id") String id);

	void delete(@Param("id") String id);
}
