package com.realworld.backend.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realworld.backend.core.article.Article;
import com.realworld.backend.core.article.Tag;

@Mapper
public interface ArticleMapper {
	void insert(@Param("article") Article article);

	Article findById(@Param("id") String id);

	Tag findTag(@Param("tagName") String tagName);

	void insertTag(@Param("tag") Tag tag);

	void insertArticleTagRelation(@Param("articleId") String articleId, @Param("tagId") String tagId);

	Article findBySlug(@Param("slug") String slug);

	void update(@Param("article") Article article);

	void delete(@Param("id") String id);
}
