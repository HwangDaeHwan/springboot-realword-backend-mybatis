package com.realworld.backend.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realworld.backend.core.favorite.ArticleFavorite;

@Mapper
public interface ArticleFavoriteMapper {
	ArticleFavorite find(@Param("articleId") String articleId, @Param("userId") String userId);

	void insert(@Param("articleFavorite") ArticleFavorite articleFavorite);

	void delete(@Param("favorite") ArticleFavorite favorite);
}