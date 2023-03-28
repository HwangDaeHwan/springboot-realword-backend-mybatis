package com.realworld.backend.infrastructure.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.realworld.backend.core.favorite.ArticleFavorite;
import com.realworld.backend.core.favorite.ArticleFavoriteRepository;
import com.realworld.backend.infrastructure.mybatis.mapper.ArticleFavoriteMapper;

@Repository
public class MyBatisArticleFavoriteRepository implements ArticleFavoriteRepository {
	private ArticleFavoriteMapper mapper;

	@Autowired
	public MyBatisArticleFavoriteRepository(ArticleFavoriteMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void save(ArticleFavorite articleFavorite) {
		if (mapper.find(articleFavorite.getArticleId(), articleFavorite.getUserId()) == null) {
			mapper.insert(articleFavorite);
		}
	}

	@Override
	public Optional<ArticleFavorite> find(String articleId, String userId) {
		return Optional.ofNullable(mapper.find(articleId, userId));
	}

	@Override
	public void remove(ArticleFavorite favorite) {
		mapper.delete(favorite);
	}
}