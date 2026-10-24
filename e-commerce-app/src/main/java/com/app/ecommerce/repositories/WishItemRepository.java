package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.models.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishItemRepository extends JpaRepository<WishItem, Long> {

    Boolean existsByCustomerAndArticle(Customer customer, Article article);

    WishItem findByCustomerAndArticle(Customer customer, Article article);

    List<WishItem> findAllByCustomer(Customer customer);

}