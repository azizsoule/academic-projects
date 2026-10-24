package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.models.CartItem;
import com.app.ecommerce.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Boolean existsByCustomerAndArticle(Customer customer, Article article);

    CartItem findByCustomerAndArticle(Customer customer, Article article);

    List<CartItem> findAllByCustomer(Customer customer);

}