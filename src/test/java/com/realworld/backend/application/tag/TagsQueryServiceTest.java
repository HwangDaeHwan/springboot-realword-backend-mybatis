package com.realworld.backend.application.tag;

import com.realworld.backend.application.TagsQueryService;
import com.realworld.backend.core.article.Article;
import com.realworld.backend.core.article.ArticleRepository;
import com.realworld.backend.infrastructure.DbTestBase;
import com.realworld.backend.infrastructure.repository.MyBatisArticleRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import({TagsQueryService.class, MyBatisArticleRepository.class})
public class TagsQueryServiceTest extends DbTestBase {
  @Autowired private TagsQueryService tagsQueryService;

  @Autowired private ArticleRepository articleRepository;

  @Test
  public void should_get_all_tags() {
    articleRepository.save(new Article("test", "test", "test", Arrays.asList("java"), "123"));
    Assertions.assertTrue(tagsQueryService.allTags().contains("java"));
  }
}
