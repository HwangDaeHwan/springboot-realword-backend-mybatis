package com.realworld.backend;

import java.util.ArrayList;
import java.util.Arrays;

import org.joda.time.DateTime;

import com.realworld.backend.application.data.ArticleData;
import com.realworld.backend.application.data.ProfileData;
import com.realworld.backend.core.article.Article;
import com.realworld.backend.core.user.User;

public class TestHelper {
  public static ArticleData articleDataFixture(String seed, User user) {
    DateTime now = new DateTime();
    return new ArticleData(
        seed + "id",
        "title-" + seed,
        "title " + seed,
        "desc " + seed,
        "body " + seed,
        false,
        0,
        now,
        now,
        new ArrayList<>(),
        new ProfileData(user.getId(), user.getUsername(), user.getBio(), user.getImage(), false));
  }

  public static ArticleData getArticleDataFromArticleAndUser(Article article, User user) {
    return new ArticleData(
        article.getId(),
        article.getSlug(),
        article.getTitle(),
        article.getDescription(),
        article.getBody(),
        false,
        0,
        article.getCreatedAt(),
        article.getUpdatedAt(),
        Arrays.asList("joda"),
        new ProfileData(user.getId(), user.getUsername(), user.getBio(), user.getImage(), false));
  }
}
