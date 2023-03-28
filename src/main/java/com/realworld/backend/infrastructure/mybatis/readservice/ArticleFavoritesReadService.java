package com.realworld.backend.infrastructure.mybatis.readservice;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realworld.backend.application.data.ArticleFavoriteCount;
import com.realworld.backend.core.user.User;

@Mapper
public interface ArticleFavoritesReadService {
	boolean isUserFavorite(@Param("userId") String userId, @Param("articleId") String articleId);

	int articleFavoriteCount(@Param("articleId") String articleId);

	List<ArticleFavoriteCount> articlesFavoriteCount(@Param("ids") List<String> ids);

	Set<String> userFavorites(@Param("ids") List<String> ids, @Param("currentUser") User currentUser);
}
