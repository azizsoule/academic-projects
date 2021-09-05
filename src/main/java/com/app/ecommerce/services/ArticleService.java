package com.app.ecommerce.services;

import com.app.ecommerce.dtos.ArticleDTO;
import com.app.ecommerce.models.Article;
import com.app.ecommerce.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService extends BaseService<ArticleDTO, Long> {

    @Autowired
    private ArticleRepository repository;

    @Override
    public ArticleDTO findById(Long idUser) {
        Article article = repository.getById(idUser);
        return modelMapper().map(article, ArticleDTO.class);
    }

    @Override
    public List<ArticleDTO> findAll() {
        List<Article> articles = repository.findAll();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        articles.forEach(article -> {
            ArticleDTO articleDTO = modelMapper().map(article, ArticleDTO.class);
            articleDTOS.add(articleDTO);
        });
        return articleDTOS;
    }

    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        Article article = modelMapper().map(articleDTO, Article.class);
        article = repository.save(article);
        articleDTO = modelMapper().map(article, ArticleDTO.class);
        return articleDTO;
    }

    @Override
    public void deleteById(Long idUser) {
        repository.deleteById(idUser);
    }

    @Override
    public void delete(ArticleDTO articleDTO) {
        Article article = modelMapper().map(articleDTO, Article.class);
        repository.delete(article);
    }

}
