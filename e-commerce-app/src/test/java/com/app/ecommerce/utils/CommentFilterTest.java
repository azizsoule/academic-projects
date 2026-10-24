package com.app.ecommerce.utils;

import com.app.ecommerce.models.Comment;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.models.Dictionary;
import com.app.ecommerce.repositories.CommentRepository;
import com.app.ecommerce.repositories.CustomerRepository;
import com.app.ecommerce.repositories.DictionaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentFilterTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DictionaryRepository dictionaryRepository;

    @BeforeEach
    void inti(){
        populateDatabase();
       }
    @Test
    void shouldBlockOnPresist() {
        Comment comment = new Comment();
        comment.setContent("Good comment but deoufbabierecestdedans");
        comment.setCustomer(customerRepository.getById(1L));
        Comment saved = commentRepository.saveAndFlush(comment);
        assertTrue(commentRepository.getById(saved.getIdComment()).isBlocked());
    }
    @Test
    void shouldNotBlockOnPresist() {
        Comment comment = new Comment();
        comment.setContent("Good comment but swift");
        comment.setCustomer(customerRepository.getById(1L));
        Comment saved = commentRepository.saveAndFlush(comment);
        assertFalse(commentRepository.getById(saved.getIdComment()).isBlocked());

    }
    @Test
    void showPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        System.out.println(passwordEncoder.encode("sapassword"));

    }
//    @Test
//    void shouldUpdateComment(){
//        int blockCount = commentRepository.findByBlockedIsTrue().size();
//        Dictionary word = dictionaryRepository.getById(2L);
//        word.setLabel("bracodji");
//        dictionaryRepository.save(word);
//        assertTrue(commentRepository.findByBlockedIsTrue().size() != blockCount);
//
//    }

    void populateDatabase(){
        Customer customer = new Customer();
        customer.setFirstName("papittou");
        customerRepository.save(customer);
        dictionaryRepository.saveAll(Arrays.asList(new Dictionary(null,"cool"),new Dictionary(null, "babiere")));
    }


}