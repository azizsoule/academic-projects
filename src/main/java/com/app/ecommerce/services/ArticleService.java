package com.app.ecommerce.services;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.repositories.ArticleRepository;
import com.app.ecommerce.utils.FilterSytem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository repository;
    public Article findById(Long id) {
        return repository.getById(id);
    }

    public List<Article> findAll() {
        return repository.findAll();
    }

    public Article save(Article article) {
        return repository.save(article);
    }
    public Article update(Article article) {
        return save(article);
    }

    public void deleteById(Long idUser) {
        repository.deleteById(idUser);
    }

    public void delete(Article article) {
        repository.delete(article);
    }

}
