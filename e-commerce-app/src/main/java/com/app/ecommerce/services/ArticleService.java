package com.app.ecommerce.services;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.models.Comment;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.repositories.ArticleRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleService extends BaseService<Article, Long> {

    @Autowired
    private ArticleRepository repository;

    @Override
    public Article findById(Long idUser) {
        return repository.getById(idUser);
    }

    @Override
    public List<Article> findAll() {
        return repository.findAll();
    }

    @Override
    public Article save(Article article) {
        return repository.save(article);
    }

    @Override
    public void deleteById(Long idUser) {
        repository.deleteById(idUser);
    }

    @Override
    public void delete(Article article) {
        repository.delete(article);
    }
    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final Article article = this.modelMapper().map(data, Article.class);
        if (Envelope.Operation.DELETE == operation) {
            this.deleteById(article.getIdArticle());
        } else {
            this.save(article);
        }
    }

}
