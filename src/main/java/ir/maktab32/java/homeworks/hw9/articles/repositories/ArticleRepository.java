package ir.maktab32.java.homeworks.hw9.articles.repositories;

import ir.maktab32.java.homeworks.hw9.articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw9.articles.entities.Article;

public class ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    protected Class getEntityClass() {
        return Article.class;
    }

    private static ArticleRepository articleRepository;
    public static ArticleRepository getInstance(){
        if (articleRepository == null)
            articleRepository = new ArticleRepository();
        return articleRepository;
    }

    private ArticleRepository(){}
}
