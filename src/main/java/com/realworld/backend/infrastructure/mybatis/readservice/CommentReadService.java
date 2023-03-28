package com.realworld.backend.infrastructure.mybatis.readservice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;

import com.realworld.backend.application.CursorPageParameter;
import com.realworld.backend.application.data.CommentData;

@Mapper
public interface CommentReadService {
	CommentData findById(@Param("id") String id);

	List<CommentData> findByArticleId(@Param("articleId") String articleId);

	List<CommentData> findByArticleIdWithCursor(@Param("articleId") String articleId,
			@Param("page") CursorPageParameter<DateTime> page);
}
