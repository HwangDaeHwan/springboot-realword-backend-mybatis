package com.realworld.backend.infrastructure.mybatis.readservice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realworld.backend.application.CursorPageParameter;
import com.realworld.backend.application.Page;
import com.realworld.backend.application.data.ArticleData;

@Mapper
public interface ArticleReadService {
	ArticleData findById(@Param("id") String id);

	ArticleData findBySlug(@Param("slug") String slug);

	List<String> queryArticles(@Param("tag") String tag, @Param("author") String author,
			@Param("favoritedBy") String favoritedBy, @Param("page") Page page);

	int countArticle(@Param("tag") String tag, @Param("author") String author,
			@Param("favoritedBy") String favoritedBy);

	List<ArticleData> findArticles(@Param("articleIds") List<String> articleIds);

	List<ArticleData> findArticlesOfAuthors(@Param("authors") List<String> authors, @Param("page") Page page);

	List<ArticleData> findArticlesOfAuthorsWithCursor(@Param("authors") List<String> authors,
			@Param("page") CursorPageParameter page);

	int countFeedSize(@Param("authors") List<String> authors);

	List<String> findArticlesWithCursor(@Param("tag") String tag, @Param("author") String author,
			@Param("favoritedBy") String favoritedBy, @Param("page") CursorPageParameter page);
}
